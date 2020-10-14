package haiying.service.feed.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.mapper.FeedMapper;
import haiying.util.GuidUtil;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
@Validated
public class FeedServiceImpl implements FeedService {

    private final FeedMapper feedMapper;

    public FeedServiceImpl(FeedMapper feedMapper) {
        this.feedMapper = feedMapper;
    }


    @Override
    public Feed saveFeed(Feed feed) throws ParseException {
        //先根据guid 和 createTime 查询 有没有相同时间的,相同的则替换掉
        String guid = feed.getGuid();
        Date createTime = feed.getCreateTime();
        if (Objects.isNull(createTime)){
            String diaperTime = feed.getDiaperTime();
//            createTime = new Date();
//            feed.setCreateTime(createTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date now = new Date();
            SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd ");
            createTime = df.parse(ds.format(now) +diaperTime);
            feed.setCreateTime(createTime);
        }

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
        List<JSONObject> feedTodayList = feedMapper.getFeedTodayList(guid, createTime);
        List<JSONObject> daily = new ArrayList<>();
        for (JSONObject feed : feedTodayList) {
            String type = feed.getString("type");
            String id = feed.getString("id");
            Date date = feed.getDate("createTime");
            String time = String.format("%tR", date);
            JSONObject dailyRecord = feedMapper.getDailyRecord(guid, type,id);
            String typeName = dailyRecord.getString("typeName");

            String state ="";
            feed.put("time",time);

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
            feed.put("state",state);
            daily.add(feed);
        }
        JSONObject object = new JSONObject();

        List<JSONObject> feedCensus = feedMapper.getSelectTotal(guid, createTime);
        ArrayList<JSONObject> census = new ArrayList<>();
        for (JSONObject selectCensus : feedCensus) {
            String type = selectCensus.getString("type");
            JSONObject milkCount = feedMapper.getMilkCount(guid, createTime, type);
            if (type.equals("1")){
                selectCensus.put("record",milkCount.getString("mrTotal")+"分钟");
            }else if(Arrays.asList ("2", "3") .contains(type)){
                selectCensus.put("record",milkCount.getString("suckle")+"ml");
            }else if(type.equals("5")){
                List<JSONObject> selectCount = feedMapper.getSelectCount(guid, createTime);
                selectCensus.put("record",selectCount);
            }else if(type.equals("6")){
                JSONObject smDateCurve = feedMapper.getSmDateCurve(guid, createTime);
                int spacing = Integer.parseInt(smDateCurve.getString("spacing"));
                int hours = (int) Math.floor(spacing / 60);
                int minute = spacing % 60;
                selectCensus.put("spacing",hours + "小时" + minute + "分钟");
            }
            census.add(selectCensus);
        }
        object.put("total",daily);
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
             dateCurve = feedMapper.getWnDateCurve(guid);
        }else if(feedtype.equals("2")){ //辅食
             dateCurve = feedMapper.getFsDateCurve(guid);
        }else if(feedtype.equals("3")){ //尿布
            dateCurve = feedMapper.getNbDateCurve(guid);
        }else if(feedtype.equals("4")){ //睡眠
            dateCurve = feedMapper.getSmDateCurveNew(guid);
//            for (JSONObject object : dateCurve) {
//                JSONObject sm = new JSONObject();
//                int spacing = Integer.parseInt(object.getString("spacing"));
//                double hours = new BigDecimal(spacing / 60).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
//                sm.put("spacing",hours);
//                sm.put("createTime",object.getString("createTime"));
//                dateCurve.add(sm);
//            }
        }
        return dateCurve;
    }

    @Override
    public List<JSONObject> findFeedPagination(JSONObject jsonObject) {
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        return feedMapper.findFeedPagination(jsonObject);
    }


}
