package haiying.service.feed.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.mapper.FeedMapper;
import haiying.util.DateUtil;
import haiying.util.GuidUtil;
import io.micronaut.validation.Validated;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Singleton
@Validated
public class FeedServiceImpl implements FeedService {

    private final FeedMapper feedMapper;

    public FeedServiceImpl(FeedMapper feedMapper) {
        this.feedMapper = feedMapper;
    }


    @Override
    public void saveFeed(Feed feed) {
        //先根据guid 和 createTime 查询 有没有相同时间的,相同的则替换掉
        String guid = feed.getGuid();
        Date createTime = feed.getCreateTime();
        Feed oneTime = feedMapper.findOneTime(guid, createTime);
        if (!Objects.isNull(oneTime)) {
            feedMapper.updateTime(feed);
            return;
        }
        feed.setId(GuidUtil.generateGuid());
        feedMapper.saveFeed(feed);
    }

    @Override
    public JSONObject findFeed(JSONObject jsonObject) {
        //guid  时间,如果没有时间,传递当天时间
        String guid = jsonObject.getString("guid");
        String createTime = jsonObject.getString("createTime");
        //查询出当天所有数据 List
        List<Feed> feedTodayList = feedMapper.getFeedTodayList(guid, createTime);
        JSONObject object = new JSONObject();
        JSONObject total = new JSONObject();
        JSONObject wyjson = new JSONObject();
        JSONArray nbArray = new JSONArray();
        JSONArray smArray = new JSONArray();
        JSONArray mrArray = new JSONArray();
        JSONArray pzmrArray = new JSONArray();
        JSONArray pfnArray = new JSONArray();
        JSONArray fsArray = new JSONArray();
        int mrTotal = 0, mrsecond = 0, pzmrsuckle = 0, pzmrsecond = 0,pfnsuckle = 0, pfnsecond = 0,fssecond = 0,nbsecond = 0,smsecond = 0;
        JSONObject census = new JSONObject();
        for (Feed feed : feedTodayList) {
            //先判断type类型
            JSONObject jsonList = new JSONObject();

            String type = feed.getType();
            String typeName = feed.getTypeName();
            String format = DateFormatUtils.format(feed.getCreateTime(), DateUtil.DATE_TIME_FMT);
            String endTime = "";
            if (feed.getEndTime() != null) {
                endTime = DateFormatUtils.format(feed.getEndTime(), DateUtil.DATE_TIME_FMT);
            }
            jsonList.put("typeName", typeName);
            jsonList.put("createTime", format);
            if (type.equals("1")) {
                int duration = Integer.parseInt(feed.getDuration());
                jsonList.put("lactation", feed.getLactation());
                jsonList.put("endTime", endTime);
                jsonList.put("duration", feed.getDuration());
                mrArray.add(jsonList);
                mrsecond +=1;         //母乳次数
                mrTotal = mrTotal + duration;    //母乳分钟
                census.put("mrsecond",mrsecond);
                census.put("mrTotal",mrTotal);
            } else if (type.equals("2")) {
                int nurseContent = feed.getNurseContent();
                jsonList.put("nurseContent", feed.getNurseContent());
                pzmrArray.add(jsonList);
                pzmrsecond += 1;       //瓶装母乳次数
                pzmrsuckle = pzmrsuckle + nurseContent; //含奶量
                census.put("pzmrsuckle",pzmrsuckle);
                census.put("pzmrsecond",pzmrsecond);
            } else if (type.equals("3")) {
                int nurseContent = feed.getNurseContent();
                jsonList.put("nurseContent", feed.getNurseContent());
                pfnArray.add(jsonList);
                pfnsecond += 1;     //配方奶次数
                pfnsuckle = pfnsuckle + nurseContent;   //含奶量
                census.put("pfnsuckle",pfnsuckle);
                census.put("pfnsecond",pfnsecond);
            } else if (type.equals("4")) {
                jsonList.put("foodName", feed.getFoodName());
                jsonList.put("foodDescribe", feed.getFoodDescribe());
                jsonList.put("foodPhoto", feed.getFoodPhoto());
                fsArray.add(jsonList); //辅食次数
                fssecond +=1;
                census.put("fssecond",fssecond);
            } else if (type.equals("5")) {
                jsonList.put("selectTypeName", feed.getSelectTypeName());
                jsonList.put("urineShape", feed.getUrineShape());
                jsonList.put("shitShape", feed.getShitShape());
                nbArray.add(jsonList);
                nbsecond +=1;    //换尿布次数
                census.put("nbsecond",nbsecond);
            } else if (type.equals("6")) {
                jsonList.put("endTime", endTime);
                smArray.add(jsonList);
                smsecond+=1;  //睡眠次数
                census.put("smsecond",smsecond);
            }
        }
        total.put("nbjson", nbArray);
        total.put("smjson", smArray);
        wyjson.put("mrjson", mrArray);
        wyjson.put("pzmrjson", pzmrArray);
        wyjson.put("pfnjson", pfnArray);
        wyjson.put("fsjson", fsArray);
        total.put("wyjson", wyjson);

        //object.put("total",total);
        object.put("total",feedTodayList);
        object.put("census",census);
        return object;
    }

    @Override
    public List<JSONObject>  getDateCurve(JSONObject jsonObject) {
        String guid = jsonObject.getString("guid");
        String createTime = jsonObject.getString("createTime");
        String feedtype = jsonObject.getString("feedtype");
        String selectType = jsonObject.getString("selectType");
        List<JSONObject>  dateCurve = new ArrayList<JSONObject>();
        if(feedtype.equals("1")){  //喂奶
             dateCurve = feedMapper.getWnDateCurve(guid, createTime);
        }else if(feedtype.equals("2")){ //辅食
             dateCurve = feedMapper.getFsDateCurve(guid, createTime);
        }else if(feedtype.equals("3")){ //尿布
            dateCurve = feedMapper.getNbDateCurve(guid, createTime,selectType);
        }else { //睡眠
            List<JSONObject> smDateCurve = feedMapper.getSmDateCurve(guid, createTime);
            JSONObject sm = new JSONObject();
            //List<JSONObject>  Curve = new List<JSONObject>;
            for (JSONObject object : smDateCurve) {
                int spacing = Integer.parseInt(object.getString("spacing"));
                double hours = new BigDecimal(spacing / 60).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                sm.put("spacing",hours);
                sm.put("createTime",object.getString("createTime"));
                dateCurve.add(sm);
            }
        }
        return dateCurve;
    }

    @Override
    public List<Feed> findFeedPagination(JSONObject jsonObject) {
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        return feedMapper.findFeedPagination(jsonObject);
    }


}
