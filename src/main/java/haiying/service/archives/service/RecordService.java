package haiying.service.archives.service;

import haiying.service.archives.domain.Record;

public interface RecordService {

    Record findOne(String guid);

    void saveRecord(Record record);

    void updateRecord(Record record);
}
