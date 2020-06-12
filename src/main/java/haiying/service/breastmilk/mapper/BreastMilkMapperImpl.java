package haiying.service.breastmilk.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.breastmilk.domain.BreastMilk;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BreastMilkMapperImpl implements BreastMilkMapper{

    private final SqlSessionFactory sqlSessionFactory;

    public BreastMilkMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private BreastMilkMapper getFeedMapperr(SqlSession sqlSession) {
        return sqlSession.getMapper(BreastMilkMapper.class);
    }

    @Override
    public void saveBreastMilk(BreastMilk breastMilk) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getFeedMapperr(sqlSession).saveBreastMilk(breastMilk);
            sqlSession.commit();
        }
    }

    @Override
    public List<JSONObject> findBreastMilk(JSONObject jsonObject) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).findBreastMilk(jsonObject);
        }
    }


}
