package haiying.service.auth.mapper;

import haiying.service.auth.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User findById(long id);

    @Select("select * from user where openId=#{openId}")
    User findByOpenId(String openId);

    @Update("update user set userId=#{userId},unionId=#{unionId},nickname=#{nickname},gender=#{gender},country=#{country},province=#{province},city=#{city},avatarUrl=#{avatarUrl},createTime=#{createTime} where id=#{openId}")
    void updateUserInfo(User user);

    @Insert("insert into user (userId,unionId,nickname,gender,country,province,city,avatarUrl,createTime) values(#{userId},#{unionId},#{nickname},#{gender},#{country},#{province},#{city},#{avatarUrl},#{createTime})")
    @Options(useGeneratedKeys = true)
    void saveUserInfo(User user);
}
