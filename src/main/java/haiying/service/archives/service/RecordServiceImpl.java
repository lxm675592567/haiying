package haiying.service.archives.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import haiying.service.archives.mapper.RecordMapper;
import haiying.service.archives.util.DateUtil;
import haiying.service.auth.domain.User;
import haiying.service.auth.mapper.UserMapper;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.mapper.FeedMapper;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;
import haiying.util.*;
import io.micronaut.validation.Validated;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
@Validated
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;

    private final FeedMapper feedMapper;

    private final UserMapper userMapper;

    private final HeightAndWeightMapper heightAndWeightMapper;

    public RecordServiceImpl(RecordMapper recordMapper, FeedMapper feedMapper, HeightAndWeightMapper heightAndWeightMapper, UserMapper userMapper) {
        this.recordMapper = recordMapper;
        this.feedMapper = feedMapper;
        this.userMapper = userMapper;
        this.heightAndWeightMapper = heightAndWeightMapper;
    }

    @Override
    public JSONObject findOne(String guid) {
        return recordMapper.findOneNew(guid);
    }

    @Override
    public Record saveUpdateRecord(Record record) {
        String guid = record.getGuid();
        String name = record.getName();
        String sex = record.getSex();
        Date birthday = record.getBirthday();
        String openId = record.getOpenId();
        if (StringUtil.stringIsNull(name)&&StringUtil.stringIsNull(guid)&&StringUtil.stringIsNull(sex)&&StringUtil.stringIsNull(birthday)&&StringUtil.stringIsNull(openId)){
            return null;
        }
        Record one = recordMapper.findOne(guid);
        if (!Objects.isNull(one)) {
            recordMapper.updateRecord(record);
        } else {
            String date = DateUtil.GetNowDateString("yyyy-MM-dd");
            JSONObject oneCardNum = recordMapper.findOneCardNum(date);
            String returnData = "";
            if (!Objects.isNull(oneCardNum)) {
                long card = Long.valueOf(oneCardNum.getString("cardNum"));
                card = card + 1;
                String newCard = Long.toString(card);
                recordMapper.updateOneCardNum(newCard, date);
                returnData = newCard;
            } else {
                String dateString = DateUtil.GetNowDateString("yyyyMMdd");
                String cardString = dateString + "000001";
                CardNum cardNum = new CardNum();
                cardNum.setCardNum(cardString).setDate(date);
                recordMapper.insertOneCardNum(cardNum);
                returnData = cardString;
            }

            //保存到平台
            User user = userMapper.findByOpenId(record.getOpenId()); //先查询母亲信息
            JSONObject jsonParam = new JSONObject();
            //jsonParam.put("guid", record.getPtGuid());
            jsonParam.put("remoteId", record.getGuid());
            jsonParam.put("motherId", user.getUserId());
            jsonParam.put("resource", "母乳小程序");
            jsonParam.put("birthday", record.getBirthday());
            jsonParam.put("name", record.getName());
            jsonParam.put("sex", record.getSex());
            String postUrl = HttpclientUtil.get("httpclient.record.post");
            String httpPost = HttpclientUtil.httpPost(postUrl, jsonParam);
            if (!Objects.isNull(JSONObject.parseObject(httpPost).getJSONObject("resultData").getString("guid"))){
                String ptGuid = JSONObject.parseObject(httpPost).getJSONObject("resultData").getString("guid");
                record.setCardId(returnData);
                record.setPtGuid(ptGuid);
            }
            recordMapper.saveRecord(record);
        }
        return record;
    }

    @Override
    public JSONObject getGrowUpArchives(JSONObject jsonObject) {
        JSONObject object = new JSONObject();
        JSONObject heightAndWeight = new JSONObject();
        String guid = jsonObject.getString("guid");
        Record record = recordMapper.findOne(guid);
        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        if (Objects.isNull(heightAndWeightOne)) {
            return object;
        }
        Double height = heightAndWeightOne.getHeight();
        Double weight = heightAndWeightOne.getWeight();
        Double bmi = getBMI(weight, height * 0.01);
        heightAndWeight.put("bmi", bmi);
        heightAndWeight.put("weight", weight);
        heightAndWeight.put("height", height);
        List<JSONObject> archivesRecord = feedMapper.getArchivesRecord(guid);
        List<JSONObject> archivesList = new ArrayList<>();
        for (JSONObject archives : archivesRecord) {
            if (!Objects.isNull(archives)) {
                int spacing = Integer.parseInt(archives.getString("spacing"));
                int hours = (int) Math.floor(spacing / 60);
                int minute = spacing % 60;
                archives.put("spacing", hours + "小时" + minute + "分钟");
                archivesList.add(archives);
            }
        }
        object.put("record", record);
        object.put("feed", archivesList);
        object.put("heightAndWeight", heightAndWeight);
        return object;
    }

    @Override
    public List<JSONObject> getBabyArchives(String openId) {
        List<Record> babyArchives = recordMapper.getBabyArchives(openId);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Record babyArchive : babyArchives) {
            JSONObject object = new JSONObject();
            Record record = babyArchive.putAgeDetail();
            String age = record.getAgeDetail().getAgeDetail();
            String name = record.getName();
            object.put("name", name);
            object.put("age", age);
            object.put("record", record);
            jsonObjects.add(object);
        }
        return jsonObjects;
    }

    @Override
    public JSONObject findMessageOne(JSONObject jsonObject) {
        String openId = jsonObject.getString("openId");
        String guid = jsonObject.getString("guid");
        JSONObject object = new JSONObject();
        if (guid.equals("")) {
            guid = null;
        }
        JSONObject one = recordMapper.findSingleOne(openId, guid);
        if (Objects.isNull(one) || StringUtil.stringIsNull(openId)) {
            return object;
        }
        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        if (!Objects.isNull(heightAndWeightOne)) {
            one.put("height", heightAndWeightOne.getHeight());
            one.put("weight", heightAndWeightOne.getWeight());
        }

        Long pageSize = (Long) jsonObject.get("pageSize"), pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum - 1) * pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum", pageNum);
        jsonObject.put("guid", one.getString("guid"));
        object.put("record", one);
        if (StringUtils.isBlank(guid)) {
            guid = one.getString("guid");
        }
        List<JSONObject> feedTime = feedMapper.getFeedTime(guid);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (JSONObject json : feedTime) {
            JSONObject ergodic = new JSONObject();
            String time = json.getString("createTime");
            //time 年月日修改
            String years = time.replaceAll("(?:年|月)", "-");
            String data = years.replace("日", "");
            jsonObject.put("createTime", data);
            List<JSONObject> pagination = feedMapper.findFeedPagination(jsonObject);
            ArrayList<JSONObject> daily = new ArrayList<>();
            for (JSONObject feed : pagination) {
                String type = feed.getString("type");
                String id = feed.getString("id");
                Date date = feed.getDate("createTime");
                String times = String.format("%tR", date);
                JSONObject dailyRecord = feedMapper.getDailyRecord(guid, type,id);
                String typeName = dailyRecord.getString("typeName");

                String state = "";
                feed.put("time", times);
                feed.put("title", typeName);
                if (type.equals("1")) {
                    state = typeName + ":" + feed.getString("duration") + "分钟";
                } else if (Arrays.asList("2", "3").contains(type)) {
                    state = typeName + ":" + feed.getString("nurseContent") + "ml";
                } else if (type.equals("4")) {
                    state = typeName + ":" + feed.getString("foodName");
                } else if (type.equals("5")) {
                    String selectType = feed.getString("selectType");
                    String character = "";
                    if (selectType.equals("1")) {
                        character = feed.getString("urineShape");
                    } else if (selectType.equals("2")) {
                        character = feed.getString("shitShape");
                    } else if (selectType.equals("3")) {
                        character = feed.getString("urineShape") + "/" + feed.getString("shitShape");
                    }
                    state = feed.getString("selectTypeName") + ":" + character;
                } else if (type.equals("6")) {
                    int spacing = Integer.parseInt(dailyRecord.getString("spacing"));
                    int hours = (int) Math.floor(spacing / 60);
                    int minute = spacing % 60;
                    state = typeName + ":" + hours + "小时" + minute + "分钟";
                }
                feed.put("desc", state);
                daily.add(feed);
            }
            ergodic.put("time", time);
            ergodic.put("contents", daily);
            jsonObjects.add(ergodic);
        }
        object.put("dynamics", jsonObjects);
        return object;
    }

    @Override
    public JSONObject findMessageNew(JSONObject jsonObject) {
        String openId = jsonObject.getString("openId");
        String guid = jsonObject.getString("guid");
        JSONObject object = new JSONObject();
        if (StringUtil.stringIsNull(openId)) {
            return object;
        }
        JSONObject one = recordMapper.findSingleOne(openId, guid); //查询是否有孩子信息
        List<JSONObject> single = recordMapper.findSingle(openId);
        //平台查询
        User user = userMapper.findByOpenId(openId); //先查询母亲信息
        String url = HttpclientUtil.get("httpclient.listByMotherInfo.get") + "wxOpenId=" + openId + "&phoneNumber=" + user.getPhone(); //孩子查询接口地址
        String httpGet = HttpclientUtil.httpGet(url);
        JSONObject result = JSONObject.parseObject(httpGet);
        JSONArray jsonArray = result.getJSONArray("resultData");//获取孩子接口信息
        Record re = new Record();
        if (!jsonArray.isEmpty() || jsonArray.size() > 1) {
            //本地无 平台有 则保存到本地
            if (Objects.isNull(single)) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    re.setGuid(CommUtil.getGuid());
                    re.setPtGuid(job.getString("guid"));
                    re.setCardId(job.getString("cardId"));
                    re.setBirthday(job.getDate("birthday"));
                    re.setName(job.getString("name"));
                    re.setSex(job.getString("sex"));
                    re.setTenantId(job.getString("tenantId"));
                    re.setOpenId(job.getJSONObject("motherInfo").getString("wxOpenId"));
                    recordMapper.saveRecord(re);
                    //把remoteId guid 传过去 让平台加上remoteId
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("guid", re.getPtGuid());
                    jsonParam.put("remoteId", re.getGuid());
                    jsonParam.put("motherId", user.getUserId());
                    jsonParam.put("resource", "母乳小程序");
                    jsonParam.put("birthday", re.getBirthday());
                    jsonParam.put("name", re.getName());
                    jsonParam.put("sex", re.getSex());
                    //jsonParam.put("tenantId", user.getTenantId());
                    //jsonParam.put("weixinid", re.getOpenId());
                    String postUrl = HttpclientUtil.get("httpclient.record.post");
                    HttpclientUtil.httpPost(postUrl, jsonParam);
                }
            } else {
                if (single.size() < jsonArray.size()) {//本地single必须小于jsonArray平台数才符合规则
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);
                        String ptGuid = job.getString("guid");
                        int type = 0;
                        for (JSONObject obj : single) {
                            String pt = obj.getString("ptGuid"); //本地 远程id
                            if (pt.equals(ptGuid)) {
                                type = 1;
                            }
                        }
                        if (type == 0){
                            re.setGuid(CommUtil.getGuid());
                            re.setPtGuid(job.getString("guid"));
                            re.setCardId(job.getString("cardId"));
                            re.setBirthday(job.getDate("birthday"));
                            re.setName(job.getString("name"));
                            re.setSex(job.getString("sex"));
                            re.setTenantId(job.getString("tenantId"));
                            re.setOpenId(job.getJSONObject("motherInfo").getString("wxOpenId"));
//                                Record Rs = recordMapper.findOne(ptGuid);
//                                if (!Objects.isNull(Rs)){
//                                    continue;
//                                }
                            recordMapper.saveRecord(re);
                            //把remoteId guid 传过去 让平台加上remoteId
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("guid", re.getPtGuid());
                            jsonParam.put("remoteId", re.getGuid());
                            jsonParam.put("motherId", user.getUserId());
                            jsonParam.put("resource", "母乳小程序");
                            jsonParam.put("birthday", re.getBirthday());
                            jsonParam.put("name", re.getName());
                            jsonParam.put("sex", re.getSex());
                            String postUrl = HttpclientUtil.get("httpclient.record.post");
                            HttpclientUtil.httpPost(postUrl, jsonParam);
                        }
                    }
                }
            }
        }


        if (Objects.isNull(one)) {
            return object;
        }
        Long pageSize = (Long) jsonObject.get("pageSize"), pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum - 1) * pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum", pageNum);
        jsonObject.put("guid", one.getString("guid"));
        object.put("record", one);
        if (StringUtils.isBlank(guid)) {
            guid = one.getString("guid");
        }

        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        if (!Objects.isNull(heightAndWeightOne)) {
            one.put("height", heightAndWeightOne.getHeight());
            one.put("weight", heightAndWeightOne.getWeight());
        }
        List<JSONObject> feedTime = feedMapper.getFeedTimeNew(guid, pageNum, pageSize);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (JSONObject json : feedTime) {
            JSONObject ergodic = new JSONObject();
            String day = json.getString("days");
            String time = json.getString("createTime");
            List<JSONObject> pagination = feedMapper.findFeedPaginationNew(jsonObject);
            ArrayList<JSONObject> daily = new ArrayList<>();
            for (JSONObject feed : pagination) {
                if (feed.getString("days").equals(day)) {
                    String type = feed.getString("type");
                    String id = feed.getString("id");
                    Date date = feed.getDate("createTime");
                    String times = String.format("%tR", date);
                    JSONObject dailyRecord = feedMapper.getDailyRecord(guid, type,id);
                    String typeName = dailyRecord.getString("typeName");

                    String state = "";
                    feed.put("time", times);
                    feed.put("title", typeName);
                    if (type.equals("1")) {
                        state = typeName + ":" + feed.getString("duration") + "分钟";
                    } else if (Arrays.asList("2", "3").contains(type)) {
                        state = typeName + ":" + feed.getString("nurseContent") + "ml";
                    } else if (type.equals("4")) {
                        state = typeName + ":" + feed.getString("foodName");
                    } else if (type.equals("5")) {
                        String selectType = feed.getString("selectType");
                        String character = "";
                        if (selectType.equals("1")) {
                            character = feed.getString("urineShape");
                        } else if (selectType.equals("2")) {
                            character = feed.getString("shitShape");
                        } else if (selectType.equals("3")) {
                            character = feed.getString("urineShape") + "/" + feed.getString("shitShape");
                        }
                        state = feed.getString("selectTypeName") + ":" + character;
                    } else if (type.equals("6")) {
                        int spacing = 0;
                        if (StringUtil.stringIsNull(dailyRecord.getString("spacing"))) {
                            spacing = 0;
                        } else {
                            spacing = Integer.parseInt(dailyRecord.getString("spacing"));
                        }
                        int hours = (int) Math.floor(spacing / 60);
                        int minute = spacing % 60;
                        state = typeName + ":" + hours + "小时" + minute + "分钟";
                    }
                    feed.put("desc", state);
                    daily.add(feed);
                }
            }
            ergodic.put("time", time);
            ergodic.put("contents", daily);
            jsonObjects.add(ergodic);

        }
        object.put("dynamics", jsonObjects);
        return object;
    }

    @Override
    public String editMovieInfo(MultipartFile myfiles, String uploadDir) {
        try {
            // 图片路径
            String imgUrl = null;
            //上传
            String filename = upload(myfiles, uploadDir, myfiles.getOriginalFilename());
            if (!StringUtil.stringIsNull(filename)) {
                imgUrl = new File(uploadDir).getName() + "/" + filename;
            }
            return imgUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String editMovieInfos(MultipartFile[] myfiles, String uploadDir) {
        try {
            // 图片路径
            String imgUrl = null;
            //上传
            String filename = upload(myfiles[0], uploadDir,myfiles[0].getOriginalFilename());
            if (!StringUtil.stringIsNull(filename)) {
                imgUrl = new File(uploadDir).getName() + "/" + filename;
            }
            return imgUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String uploadFile(String file) throws Base64DecodingException {
        String ImgUrl = saveImg(file);
        return ImgUrl;
    }

    public Double getBMI(Double weight, Double height) {
        return Math.round((weight / (height * height)) * 10) / 10.0;
    }

    public String upload(MultipartFile file, String path, String fileName) throws Exception {
        // 生成新的文件名
        String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        // 保存文件
        file.transferTo(dest);
        return dest.getName();
    }


    // 图片路劲层级分隔符
    private static String separator = "/";


    public String saveImg(String baseImg) throws Base64DecodingException {
        //定义一个正则表达式的筛选规则，为了获取图片的类型
        String rgex = "data:image/(.*?);base64";
        String type = getSubUtilSimple(baseImg, rgex);
        //去除base64图片的前缀
        baseImg = baseImg.replaceFirst("data:(.+?);base64,", "");
        byte[] b;
        byte[] bs;
        OutputStream os = null;
        String fileName = "";
        String nowDate = "";
        // 格式化并获取当前日期（用来命名）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = format.format(new Date());
        //把图片转换成二进制
        b = com.sun.org.apache.xml.internal.security.utils.Base64.decode(baseImg.replaceAll(" ", "+"));
        //生成路径
        String pathUrl = HttpclientUtil.get("file.ImgUrl")  + separator + "img" + separator + nowDate + separator;
        String path = HttpclientUtil.get("file.ImgUrl.post")  + separator + "img" + separator + nowDate + separator;
        //String path = "D:\\项目\\haiying\\file"  + separator + "img" + separator + nowDate + separator;
        //随机生成图片的名字，同时根据类型结尾
        fileName = UUID.randomUUID().toString() + "." + type;
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        File imageFile = new File(path + "/" + fileName);
        BASE64Decoder d = new BASE64Decoder();
        // 保存
        try {
            bs = d.decodeBuffer(Base64.encode(b));
            os = new FileOutputStream(imageFile);
            os.write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.getLocalizedMessage();
                }
            }
        }

       // return "img" + separator + nowDate + separator + fileName;
        pathUrl.replace(" ","");
        return pathUrl + fileName;
    }


    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "jpg";
    }

}
