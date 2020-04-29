package haiying.service.archives.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;

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
    private RecordMapper getRecordMapperr(SqlSession sqlSession) {
        return sqlSession.getMapper(RecordMapper.class);
    }
}
