package haiying.service.physical.mapper;

import haiying.service.physical.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;

@Singleton
public class UserMapperImpl implements UserMapper {

    private final SqlSessionFactory sqlSessionFactory; // <2>

    public UserMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory; // <2>
    }

    @Override
    public User findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) { // <3>
            return getUserMapper(sqlSession).findById(id); // <5>
        }
    }
    private UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class); // <4>
    }
}
