package haiying.service.auth.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.auth.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserMapperImpl implements UserMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public UserMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getUserMapper(sqlSession).findById(id);
        }
    }

    @Override
    public User findByOpenId(String openId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getUserMapper(sqlSession).findByOpenId(openId);
        }
    }

    @Override
    public void updateUserInfo(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getUserMapper(sqlSession).updateUserInfo(user);
            sqlSession.commit();
        }
    }

    @Override
    public void saveUserInfo(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getUserMapper(sqlSession).saveUserInfo(user);
            sqlSession.commit();
        }
    }

    @Override
    public JSONObject findOne(String openId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getUserMapper(sqlSession).findOne(openId);
        }
    }

    private UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }
}
