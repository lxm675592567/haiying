package haiying.service.archives.service;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import haiying.service.archives.mapper.RecordMapper;
import haiying.service.archives.util.DateUtil;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.mapper.FeedMapper;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;
import io.micronaut.validation.Validated;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Singleton
@Validated
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;

    private final FeedMapper feedMapper;

    private final HeightAndWeightMapper heightAndWeightMapper;

    public RecordServiceImpl(RecordMapper recordMapper, FeedMapper feedMapper, HeightAndWeightMapper heightAndWeightMapper) {
        this.recordMapper = recordMapper;
        this.feedMapper = feedMapper;
        this.heightAndWeightMapper = heightAndWeightMapper;
    }

    @Override
    public Record findOne(String guid) {
        return recordMapper.findOne(guid);
    }

    @Override
    public Record saveRecord(Record record) {
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
         return record;
    }

    @Override
    public Record updateRecord(Record record) {
        recordMapper.updateRecord(record);
        return record;
    }

    @Override
    public JSONObject getGrowUpArchives(JSONObject jsonObject) {
        JSONObject object = new JSONObject();
        JSONObject heightAndWeight = new JSONObject();
        String guid = jsonObject.getString("guid");
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        Record record = recordMapper.findOne(guid);
        HeightAndWeight heightAndWeightOne = heightAndWeightMapper.findHeightAndWeightOne(guid);
        Double height = heightAndWeightOne.getHeight();
        Double weight = heightAndWeightOne.getWeight();
        Double bmi = getBMI(weight, height * 0.01);
        heightAndWeight.put("bmi",bmi);
        heightAndWeight.put("weight",weight);
        heightAndWeight.put("height",height);

        List<Feed> feedPagination = feedMapper.findFeedPagination(jsonObject);
        object.put("record",record);
        object.put("feed",feedPagination);
        object.put("heightAndWeight",heightAndWeight);
        return object;
    }

    @Override
    public List<JSONObject> getBabyArchives(String openId) {
        List<Record> babyArchives = recordMapper.getBabyArchives(openId);
        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject object = new JSONObject();
        for (Record babyArchive : babyArchives) {
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
        Record one = recordMapper.findSingleOne(openId,guid);
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("guid",one.getGuid());
        object.put("record",one);
        if (StringUtils.isBlank(guid)){
            guid = one.getGuid();
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
            List<Feed> pagination = feedMapper.findFeedPagination(jsonObject);
            ergodic.put("time",time);
            ergodic.put("contents",pagination);
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
