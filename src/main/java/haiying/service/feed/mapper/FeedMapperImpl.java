package haiying.service.feed.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

@Singleton
public class FeedMapperImpl implements FeedMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public FeedMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private FeedMapper getFeedMapperr(SqlSession sqlSession) {
        return sqlSession.getMapper(FeedMapper.class);
    }

    @Override
    public void saveFeed(Feed feed) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getFeedMapperr(sqlSession).saveFeed(feed);
            sqlSession.commit();
        }
    }

    @Override
    public Feed findOneTime(String guid, Date createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).findOneTime(guid, createTime);
        }
    }

    @Override
    public void updateTime(Feed feed) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getFeedMapperr(sqlSession).updateTime(feed);
            sqlSession.commit();
        }
    }

    @Override
    public List<Feed> getFeedTodayList(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFeedTodayList(guid,createTime);
        }
    }

    @Override
    public List<Feed> getFeedTodayMaxList(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFeedTodayMaxList(guid);
        }
    }

    @Override
    public List<JSONObject>  getWnDateCurve(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getWnDateCurve(guid,createTime);
        }
    }

    @Override
    public List<JSONObject> getFsDateCurve(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFsDateCurve(guid,createTime);
        }
    }

    @Override
    public List<JSONObject> getNbDateCurve(String guid, String createTime, String selectType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getNbDateCurve(guid,createTime,selectType);
        }
    }

    @Override
    public List<JSONObject> getSmDateCurve(String guid,String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getSmDateCurve(guid,createTime);
        }
    }

    @Override
    public List<Feed> findFeedPagination(JSONObject jsonObject) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).findFeedPagination(jsonObject);
        }
    }
}
