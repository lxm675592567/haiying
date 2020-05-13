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
    public Feed saveFeed(Feed feed) {
        //先根据guid 和 createTime 查询 有没有相同时间的,相同的则替换掉
        String guid = feed.getGuid();
        Date createTime = feed.getCreateTime();
        Feed oneTime = feedMapper.findOneTime(guid, createTime);
        if (!Objects.isNull(oneTime)) {
            feedMapper.updateTime(feed);
            return feed;
        }
        feed.setId(GuidUtil.generateGuid());
        feedMapper.saveFeed(feed);
        return feed;
    }

    @Override
    public JSONObject findFeed(JSONObject jsonObject) {
        //guid  时间,如果没有时间,传递当天时间
        String guid = jsonObject.getString("guid");
        String createTime = jsonObject.getString("createTime");
        //查询出当天所有数据 List
        List<Feed> feedTodayList = feedMapper.getFeedTodayList(guid, createTime);
        JSONObject object = new JSONObject();
        int mrTotal = 0, mrsecond = 0, pzmrsuckle = 0, pzmrsecond = 0,pfnsuckle = 0, pfnsecond = 0,fssecond = 0,nbsecond = 0,smsecond = 0;
        JSONObject census = new JSONObject();

        //查询类型
        List<JSONObject> selectTotal = feedMapper.getSelectTotal(guid, createTime);
        for (JSONObject json : selectTotal) {
            String type = json.getString("type");
            String totalCount = json.getString("total");
            JSONObject milkCount = feedMapper.getMilkCount(guid, createTime, type);
            if (type.equals("1")){
                census.put("mrsecond",totalCount); //母乳次数
                census.put("mrTotal",milkCount.getString("mrTotal"));//母乳分钟
            }else if(type.equals("2")){
                census.put("pzmrsecond",totalCount); //瓶装母乳次数
                census.put("pzmrsuckle",milkCount.getString("suckle"));//瓶装母乳含奶量
            }else if(type.equals("3")){
                census.put("pfnsecond",totalCount); //配方奶次数
                census.put("pfnsuckle",milkCount.getString("suckle"));//配方奶含奶量
            }else if(type.equals("4")){
                census.put("fssecond",totalCount); //辅食次数
            }else if(type.equals("5")){
                census.put("nbsecond",totalCount); //换尿布次数
                List<JSONObject> selectCount = feedMapper.getSelectCount(guid, createTime);
                for (JSONObject hnbjson : selectCount) {
                    String selectType = hnbjson.getString("selectType");
                    String count = hnbjson.getString("selectCount");
                    if (selectType.equals("1")){
                        census.put("xxsecond",count);
                    }else if(selectType.equals("2")){
                        census.put("bbsecond",count);
                    }else{
                        census.put("xbsecond",count);
                    }
                }
            }else if(type.equals("6")){
                census.put("smsecond",totalCount); //睡眠次数
                List<JSONObject> smDateCurve = feedMapper.getSmDateCurve(guid, createTime);
                //List<JSONObject>  Curve = new List<JSONObject>;
                for (JSONObject objects : smDateCurve) {
                    int spacing = Integer.parseInt(objects.getString("spacing"));
                    int hours = (int) Math.floor(spacing / 60);
                    int minute = spacing % 60;
                    census.put("spacing",hours + "小时" + minute + "分钟");
                }
            }

        }

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
