package haiying.service.physical.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.physical.domain.HeightAndWeight;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;
@Singleton
public class HeightAndWeightMapperImpl implements HeightAndWeightMapper{

    private final SqlSessionFactory sqlSessionFactory;

    public HeightAndWeightMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public HeightAndWeight findHeightAndWeightOne(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).findHeightAndWeightOne(guid);
        }
    }

    @Override
    public List<HeightAndWeight> findHeightAndWeightList(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).findHeightAndWeightList(guid);
        }
    }

    @Override
    public Record findOne(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).findOne(guid);
        }
    }

    @Override
    public HeightAndWeight findLastHeightAndWeight(String guid, Integer monthAgeInt) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).findLastHeightAndWeight(guid,monthAgeInt);
        }
    }

    @Override
    public long remove(String guid, Integer monthAgeInt) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            long remove = getHeightAndWeightMapper(sqlSession).remove(guid, monthAgeInt);
            sqlSession.commit();
            return  remove;
        }
    }

    @Override
    public long save(HeightAndWeight heightAndWeight) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            long save = getHeightAndWeightMapper(sqlSession).save(heightAndWeight);
            sqlSession.commit();
            return save;
        }
    }

    @Override
    public List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).findHeightAndWeightHistoryPagination(jsonObject);
        }
    }

    @Override
    public List<JSONObject> getHeightAndWeightDateCurve(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getHeightAndWeightMapper(sqlSession).getHeightAndWeightDateCurve(guid);
        }
    }

    private HeightAndWeightMapper getHeightAndWeightMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(HeightAndWeightMapper.class);
    }
}
