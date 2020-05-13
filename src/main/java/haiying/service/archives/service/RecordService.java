package haiying.service.archives.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;

import java.util.List;

public interface RecordService {

    Record findOne(String guid);

    Record saveRecord(Record record);

    Record updateRecord(Record record);

    JSONObject getGrowUpArchives(JSONObject jsonObject);

    List<JSONObject> getBabyArchives(String openId);

    JSONObject findMessageOne(JSONObject jsonObject);
}
