package haiying.service.archives.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import haiying.service.archives.domain.Record;
import haiying.service.auth.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecordService {

    JSONObject findOne(String guid);

    Record saveUpdateRecord(Record record);

    JSONObject getGrowUpArchives(JSONObject jsonObject);

    List<JSONObject> getBabyArchives(String openId);

    JSONObject findMessageOne(JSONObject jsonObject);

    JSONObject findMessageNew(JSONObject jsonObject);

    String editMovieInfo(MultipartFile myfiles, String uploadDir);

    String editMovieInfos(MultipartFile[] myfiles, String uploadDir);

    String uploadFile(String file) throws Base64DecodingException;
}
