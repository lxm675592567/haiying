package haiying.service.archives.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.auth.domain.User;

import java.util.List;

public interface RecordService {

    JSONObject findOne(String guid);

    Record saveUpdateRecord(Record record);

    JSONObject getGrowUpArchives(JSONObject jsonObject);

    List<JSONObject> getBabyArchives(String openId);

    JSONObject findMessageOne(JSONObject jsonObject);

    JSONObject findMessageNew(JSONObject jsonObject);
}
