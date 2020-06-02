package haiying.service.archives.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
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

import javax.inject.Singleton;
import java.util.*;

@Singleton
@Validated
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;

    private final FeedMapper feedMapper;

    private final UserMapper userMapper;

    private final HeightAndWeightMapper heightAndWeightMapper;

    public RecordServiceImpl(RecordMapper recordMapper, FeedMapper feedMapper, HeightAndWeightMapper heightAndWeightMapper,UserMapper userMapper) {
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
        Record one = recordMapper.findOne(guid);
        if(!Objects.isNull(one)){
            recordMapper.updateRecord(record);
        }else {
            String date = DateUtil.GetNowDateString("yyyy-MM-dd");
            JSONObject oneCardNum = recordMapper.findOneCardNum(date);
            String returnData = "";
            if(!Objects.isNull(oneCardNum)){
                long card = Long.valueOf(oneCardNum.getString("cardNum"));
                card = card + 1;
                String newCard = Long.toString(card);
                recordMapper.updateOneCardNum(newCard, date);
                returnData = newCard;
            }else {
                String dateString = DateUtil.GetNowDateString("yyyyMMdd");
                String cardString = dateString + "000001";
                CardNum cardNum = new CardNum();
                cardNum.setCardNum(cardString).setDate(date);
                recordMapper.insertOneCardNum(cardNum);
                returnData = cardString;
            }
            record.setCardId(returnData);
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
        if(Objects.isNull(heightAndWeightOne)){
            return object;
        }
        Double height = heightAndWeightOne.getHeight();
        Double weight = heightAndWeightOne.getWeight();
        Double bmi = getBMI(weight, height * 0.01);
        heightAndWeight.put("bmi",bmi);
        heightAndWeight.put("weight",weight);
        heightAndWeight.put("height",height);
        List<JSONObject> archivesRecord = feedMapper.getArchivesRecord(guid);
        List<JSONObject> archivesList = new ArrayList<>();
        for (JSONObject archives : archivesRecord) {
            if(!Objects.isNull(archives)){
            int spacing = Integer.parseInt(archives.getString("spacing"));
            int hours = (int) Math.floor(spacing / 60);
            int minute = spacing % 60;
            archives.put("spacing",hours + "小时" + minute + "分钟");
            archivesList.add(archives);
            }
        }
        object.put("record",record);
        object.put("feed",archivesList);
        object.put("heightAndWeight",heightAndWeight);
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
            object.put("name",name);
            object.put("age",age);
            object.put("record",record);
            jsonObjects.add(object);
        }
        return jsonObjects;
    }

    @Override
    public JSONObject findMessageOne(JSONObject jsonObject) {
        String openId = jsonObject.getString("openId");
        String guid = jsonObject.getString("guid");
        JSONObject object = new JSONObject();
        if(guid.equals("")){
            guid=null;
        }
        JSONObject one = recordMapper.findSingleOne(openId, guid);
        if (Objects.isNull(one) || StringUtil.stringIsNull(openId)){
            return object;
        }
        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        if(!Objects.isNull(heightAndWeightOne)){
            one.put("height",heightAndWeightOne.getHeight());
            one.put("weight",heightAndWeightOne.getWeight());
        }

        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("guid",one.getString("guid"));
        object.put("record",one);
        if (StringUtils.isBlank(guid)){
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
            jsonObject.put("createTime",data);
            List<JSONObject> pagination = feedMapper.findFeedPagination(jsonObject);
            ArrayList<JSONObject> daily = new ArrayList<>();
            for (JSONObject feed : pagination) {
                String type = feed.getString("type");
                Date date = feed.getDate("createTime");
                String times = String.format("%tR", date);
                JSONObject dailyRecord = feedMapper.getDailyRecord(guid, type);
                String typeName = dailyRecord.getString("typeName");

                String state ="";
                feed.put("time",times);
                feed.put("title",typeName);
                if (type.equals("1")){
                    state = typeName+":"+feed.getString("duration")+ "分钟";
                }else if(Arrays.asList ("2", "3") .contains(type)){
                    state = typeName+":"+feed.getString("nurseContent")+ "ml";
                }else if(type.equals("4")){
                    state = typeName+":"+feed.getString("foodName");
                }else if(type.equals("5")){
                    String selectType = feed.getString("selectType");
                    String character ="";
                    if(selectType.equals("1")){
                        character = feed.getString("urineShape");
                    }else if(selectType.equals("2")){
                        character = feed.getString("shitShape");
                    }else if(selectType.equals("3")){
                        character = feed.getString("urineShape")+"/"+feed.getString("shitShape");
                    }
                    state = feed.getString("selectTypeName")+":"+character;
                }else if(type.equals("6")){
                    int spacing = Integer.parseInt(dailyRecord.getString("spacing"));
                    int hours = (int) Math.floor(spacing / 60);
                    int minute = spacing % 60;
                    state = typeName+":"+hours + "小时" + minute + "分钟";
                }
                feed.put("desc",state);
                daily.add(feed);
            }
            ergodic.put("time",time);
            ergodic.put("contents",daily);
            jsonObjects.add(ergodic);
        }
        object.put("dynamics",jsonObjects);
        return object;
    }

    @Override
    public JSONObject findMessageNew(JSONObject jsonObject) {
        String openId = jsonObject.getString("openId");
        String guid = jsonObject.getString("guid");
        JSONObject object = new JSONObject();
        if (StringUtil.stringIsNull(openId) ){
            return object;
        }
        JSONObject one = recordMapper.findSingleOne(openId, guid); //查询是否有孩子信息

        //平台查询
//        User user = userMapper.findByOpenId(openId); //先查询母亲信息
//        String url = HttpclientUtil.get("httpclient.listByMotherInfo.get")+"wxOpenId="+openId+"&phoneNumber="+user.getPhone(); //孩子查询接口地址
//        String httpGet = HttpclientUtil.httpGet(url);
//        JSONObject result = JSONObject.parseObject(httpGet);
//        JSONArray jsonArray = result.getJSONArray("resultData");//获取孩子接口信息
//        if(jsonArray.isEmpty()||jsonArray.size()<1){//查询平台没有孩子信息
//            //本地有1个 平台无 则保存到平台
//            if (!Objects.isNull(one)) {
//                JSONObject jsonParam = new JSONObject();
//                jsonParam.put("remoteId", one.getString("guid"));
//                jsonParam.put("birthday", one.getString("birthday"));
//                jsonParam.put("name", one.getString("name"));
//                jsonParam.put("sex", one.getString("sex"));
//                jsonParam.put("tenantId", user.getTenantId());
//                jsonParam.put("motherId", user.getUserId());
//                //没有则保存
//                String postUrl = HttpclientUtil.get("httpclient.record.post");
//                String httpPost = HttpclientUtil.httpPost(postUrl, jsonParam);
//                JSONObject ptResult = JSONObject.parseObject(httpPost);
//                JSONObject resultData = ptResult.getJSONObject("resultData");
//                Record record = JSONObject.toJavaObject(one, Record.class);
//                record.setCardId(resultData.getString("cardId"));
//                record.setPtGuid(resultData.getString("guid"));
//                recordMapper.updateRecord(record);
//            }else {
//                return object;
//            }
//        }else{
//            //本地无  平台有  直接查询保存
//            if(Objects.isNull(one)){
//                //查询出平台,然后保存
//                Record record = new Record();
//                //guid(主键生成)
//                //record.
//
//
//                //recordMapper.saveRecord();
//            }else {
//                //本地有  平台有  对比
//            }
//        }

        if (Objects.isNull(one) ){
            return object;
        }
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("guid",one.getString("guid"));
        object.put("record",one);
        if (StringUtils.isBlank(guid)){
            guid = one.getString("guid");
        }

        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        if(!Objects.isNull(heightAndWeightOne)){
            one.put("height",heightAndWeightOne.getHeight());
            one.put("weight",heightAndWeightOne.getWeight());
        }
        List<JSONObject> feedTime = feedMapper.getFeedTimeNew(guid,pageNum,pageSize);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (JSONObject json : feedTime) {
            JSONObject ergodic = new JSONObject();
            String day = json.getString("days");
            String time = json.getString("createTime");
            List<JSONObject> pagination = feedMapper.findFeedPaginationNew(jsonObject);
            ArrayList<JSONObject> daily = new ArrayList<>();
            for (JSONObject feed : pagination) {
                if(feed.getString("days").equals(day)){
                String type = feed.getString("type");
                Date date = feed.getDate("createTime");
                String times = String.format("%tR", date);
                JSONObject dailyRecord = feedMapper.getDailyRecord(guid, type);
                String typeName = dailyRecord.getString("typeName");

                String state ="";
                feed.put("time",times);
                feed.put("title",typeName);
                if (type.equals("1")){
                    state = typeName+":"+feed.getString("duration")+ "分钟";
                }else if(Arrays.asList ("2", "3") .contains(type)){
                    state = typeName+":"+feed.getString("nurseContent")+ "ml";
                }else if(type.equals("4")){
                    state = typeName+":"+feed.getString("foodName");
                }else if(type.equals("5")){
                    String selectType = feed.getString("selectType");
                    String character ="";
                    if(selectType.equals("1")){
                        character = feed.getString("urineShape");
                    }else if(selectType.equals("2")){
                        character = feed.getString("shitShape");
                    }else if(selectType.equals("3")){
                        character = feed.getString("urineShape")+"/"+feed.getString("shitShape");
                    }
                    state = feed.getString("selectTypeName")+":"+character;
                }else if(type.equals("6")){
                    int spacing = Integer.parseInt(dailyRecord.getString("spacing"));
                    int hours = (int) Math.floor(spacing / 60);
                    int minute = spacing % 60;
                    state = typeName+":"+hours + "小时" + minute + "分钟";
                }
                feed.put("desc",state);
                daily.add(feed);
            }
            }
            ergodic.put("time",time);
            ergodic.put("contents",daily);
            jsonObjects.add(ergodic);

        }
        object.put("dynamics",jsonObjects);
        return object;
    }

    public Double getBMI(Double weight, Double height)
    {
        return  Math.round((weight / (height * height)) * 10) / 10.0;
    }
}
