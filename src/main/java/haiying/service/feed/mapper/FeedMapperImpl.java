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
    public List<JSONObject> getFeedTodayList(String guid, String createTime) {
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
    public List<JSONObject>  getWnDateCurve(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getWnDateCurve(guid);
        }
    }

    @Override
    public List<JSONObject> getFsDateCurve(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFsDateCurve(guid);
        }
    }

    @Override
    public List<JSONObject> getNbDateCurve(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getNbDateCurve(guid);
        }
    }

    @Override
    public JSONObject getSmDateCurve(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getSmDateCurve(guid,createTime);
        }
    }

    @Override
    public List<JSONObject> getSmDateCurveNew(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getSmDateCurveNew(guid);
        }
    }

    @Override
    public List<JSONObject> findFeedPagination(JSONObject jsonObject) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).findFeedPagination(jsonObject);
        }
    }

    @Override
    public List<JSONObject>  getSelectTotal(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getSelectTotal(guid,createTime);
        }
    }

    @Override
    public List<JSONObject> getSelectCount(String guid, String createTime) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getSelectCount(guid,createTime);
        }
    }

    @Override
    public JSONObject getMilkCount(String guid, String createTime, String type) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getMilkCount(guid,createTime,type);
        }
    }

    @Override
    public List<JSONObject> getFeedTime(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFeedTime(guid);
        }
    }

    @Override
    public List<JSONObject> getFeedTimeNew(String guid, Long pageNum, Long pageSize) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getFeedTimeNew(guid,pageNum,pageSize);
        }
    }

    @Override
    public List<JSONObject> findFeedPaginationNew(JSONObject jsonObject) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).findFeedPaginationNew(jsonObject);
        }
    }


    @Override
    public List<JSONObject> getArchivesRecord(String guid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getArchivesRecord(guid);
        }
    }


    @Override
    public JSONObject getDailyRecord(String guid, String type, String id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getFeedMapperr(sqlSession).getDailyRecord(guid,type,id);
        }
    }
}
