package haiying.service.archives.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RecordMapperImpl implements RecordMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public RecordMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Record findOne(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).findOne(guid);
        }
    }

    @Override
    public JSONObject findOneNew(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).findOneNew(guid);
        }
    }

    @Override
    public void saveRecord(Record record) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getRecordMapperr(sqlSession).saveRecord(record);
            sqlSession.commit();
        }
    }

    @Override
    public void updateRecord(Record record) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                getRecordMapperr(sqlSession).updateRecord(record);
                sqlSession.commit();
            }
    }

    @Override
    public JSONObject findOneCardNum(String date) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).findOneCardNum(date);
        }
    }

    @Override
    public void updateOneCardNum(String newCard, String date) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
           getRecordMapperr(sqlSession).updateOneCardNum(newCard,date);
           sqlSession.commit();
        }
    }

    @Override
    public void insertOneCardNum(CardNum cardNum) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getRecordMapperr(sqlSession).insertOneCardNum(cardNum);
            sqlSession.commit();
        }
    }

    @Override
    public List<Record> getBabyArchives(String openId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).getBabyArchives(openId);
        }
    }

    @Override
    public JSONObject findSingleOne(String openId, String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).findSingleOne(openId,guid);
        }
    }

    @Override
    public List<JSONObject> findSingle(String openId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getRecordMapperr(sqlSession).findSingle(openId);
        }
    }

    private RecordMapper getRecordMapperr(SqlSession sqlSession) {
        return sqlSession.getMapper(RecordMapper.class);
    }
}
