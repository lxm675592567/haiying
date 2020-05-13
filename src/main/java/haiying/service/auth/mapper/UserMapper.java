package haiying.service.auth.mapper;

import haiying.service.auth.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("select * from user where userid=#{userid} LIMIT 1")
    User findById(long id);

    @Select("select * from user where openId=#{openId}  LIMIT 1")
    User findByOpenId(String openId);

    @Update("update user set unionId=#{unionId},nickName=#{nickName},gender=#{gender},country=#{country},province=#{province},city=#{city},avatarUrl=#{avatarUrl},createTime=#{createTime},loginType=#{loginType} where openId=#{openId}")
    void updateUserInfo(User user);

    @Insert("insert into user (userId,unionId,nickName,gender,country,province,city,avatarUrl,createTime,openId,loginType) values(#{userId},#{unionId},#{nickName},#{gender},#{country},#{province},#{city},#{avatarUrl},#{createTime},#{openId},#{loginType})")
    @Options(useGeneratedKeys = true)
    void saveUserInfo(User user);

    @Select("select * from user where guid=#{guid} LIMIT 1")
    User findOne(String guid);
}
