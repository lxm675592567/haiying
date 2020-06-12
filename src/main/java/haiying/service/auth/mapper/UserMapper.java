package haiying.service.auth.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.auth.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("select userId userId,openId openId,unionId unionId,nickName nickName,gender gender,country country,province province,city city,avatarUrl avatarUrl,avatar avatar,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,loginType loginType,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,phone phone,height height,menstruation menstruation,tenantId tenantId,tenantName tenantName,occupation occupation,session_key sessionKey from user where userid=#{userid} LIMIT 1")
    User findById(long id);

    @Select("select userId userId,openId openId,unionId unionId,nickName nickName,gender gender,country country,province province,city city,avatarUrl avatarUrl,avatar avatar,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,loginType loginType,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,phone phone,height height,menstruation menstruation,tenantId tenantId,tenantName tenantName,occupation occupation,session_key sessionKey from user where openId=#{openId}  LIMIT 1")
    User findByOpenId(String openId);

    //@Update("update user set unionId=#{unionId},nickName=#{nickName},gender=#{gender},country=#{country},province=#{province},city=#{city},avatarUrl=#{avatarUrl},createTime=#{createTime},
    // loginType=#{loginType},phone=#{phone},menstruation=#{menstruation},birthday=#{birthday},height=#{height},tenantId=#{tenantId},tenantName=#{tenantName},occupation=#{occupation} where openId=#{openId}")
    @Update({"<script> " +
            "update user set loginType=1" +
            "<if test='unionId!=null and unionId.trim() != &quot;&quot;'>,unionId=#{unionId} </if> " +
            "<if test='nickName!=null and nickName.trim() != &quot;&quot;'>,nickName=#{nickName} </if> " +
            "<if test='gender!=null '>,gender=#{gender} </if> " +
            "<if test='country!=null and country.trim() != &quot;&quot;'>,country=#{country} </if> " +
            "<if test='province!=null and province.trim() != &quot;&quot;'>,province=#{province} </if> " +
            "<if test='city!=null and city.trim() != &quot;&quot;'>,city=#{city} </if> " +
            "<if test='avatarUrl!=null and avatarUrl.trim() != &quot;&quot;'>,avatarUrl=#{avatarUrl} </if> " +
            "<if test='createTime!=null and createTime.trim() != &quot;&quot;'>,createTime=#{createTime} </if> " +
            "<if test='phone!=null and phone.trim() != &quot;&quot;'>,phone=#{phone} </if> " +
            "<if test='menstruation!=null and menstruation.trim() != &quot;&quot;'>,menstruation=#{menstruation} </if> " +
            "<if test='birthday!=null '>,birthday=#{birthday} </if> " +
            "<if test='height!=null and height.trim() != &quot;&quot;'>,height=#{height} </if> " +
            "<if test='tenantId!=null and tenantId.trim() != &quot;&quot;'>,tenantId=#{tenantId} </if> " +
            "<if test='tenantName!=null and tenantName.trim() != &quot;&quot;'>,tenantName=#{tenantName} </if> " +
            "<if test='occupation!=null and occupation.trim() != &quot;&quot;'>,occupation=#{occupation} </if> " +
            "<if test='avatar!=null and avatar.trim() != &quot;&quot;'>,avatar=#{avatar} </if> " +
            "where openId=#{openId} " +
            "</script>"})
    void updateUserInfo(User user);

    @Insert("insert into user (userId,unionId,nickName,gender,country,province,city,avatarUrl,createTime,openId,loginType,session_key,phone) values(#{userId},#{unionId},#{nickName},#{gender},#{country},#{province},#{city},#{avatarUrl},#{createTime},#{openId},#{loginType},#{sessionKey},#{phone})")
    @Options(useGeneratedKeys = true)
    void saveUserInfo(User user);

    @Select("select userId userId,openId openId,unionId unionId,nickName nickName,gender gender,country country,province province,city city,avatarUrl avatarUrl,IFNULL(avatar,'') avatar,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,loginType loginType,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,phone phone,height height,menstruation menstruation,tenantId tenantId,tenantName tenantName,occupation occupation,session_key sessionKey from user where openId=#{openId} LIMIT 1")
    JSONObject findOne(String openId);
}
