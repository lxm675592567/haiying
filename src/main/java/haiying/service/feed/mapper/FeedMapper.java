package haiying.service.feed.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface FeedMapper {

    @Insert("insert into feed (id,guid,typeName,type,lactation,createTime,endTime,duration,nurseContent,foodName,foodDescribe,foodPhoto,selectType,selectTypeName,urineShape,shitShape) " +
            "values(#{id},#{guid},#{typeName},#{type},#{lactation},#{createTime},#{endTime},#{duration},#{nurseContent},#{foodName},#{foodDescribe},#{foodPhoto},#{selectType},#{selectTypeName},#{urineShape},#{shitShape})")
    void saveFeed(Feed feed);

    @Select("select * from feed where guid=#{guid} and createTime like  CONCAT('%',#{createTime},'%') limit 1")
    Feed findOneTime(String guid, Date createTime);

    @Update("update feed set typeName=#{typeName},type=#{type},lactation=#{lactation},createTime=#{createTime},endTime=#{endTime},duration=#{duration},nurseContent=#{nurseContent},foodName=#{foodName},foodDescribe=#{foodDescribe},foodPhoto=#{foodPhoto},selectType=#{selectType},selectTypeName=#{selectTypeName},urineShape=#{urineShape},shitShape=#{shitShape} where guid=#{guid} and createTime=#{createTime}")
    void updateTime(Feed feed);

    @Select("select * from feed where guid=#{guid} and createTime like CONCAT('%',#{createTime},'%') ORDER BY createTime DESC")
    List<Feed> getFeedTodayList(String guid, String createTime);

    @Select(" SELECT a.typeName,a.lactation,a.createTime,a.endTime,a.duration,a.selectTypeName,a.urineShape,a.shitShape FROM feed a WHERE a.createTime = (SELECT createtime FROM feed WHERE TYPE = 1 LIMIT 1) AND a.guid = #{guid} AND a.type = 1 \n" +
            " UNION \n" +
            " SELECT b.typeName,b.lactation,b.createTime,b.endTime,b.duration,b.selectTypeName,b.urineShape,b.shitShape FROM feed b WHERE b.createTime = (SELECT createtime FROM feed WHERE TYPE = 5 LIMIT 1) AND b.guid = #{guid} AND b.type = 5 \n" +
            " UNION \n" +
            " SELECT c.typeName,c.lactation,c.createTime,c.endTime,c.duration,c.selectTypeName,c.urineShape,c.shitShape FROM feed c WHERE c.createTime = (SELECT createtime FROM feed WHERE TYPE = 6 LIMIT 1) AND c.guid = #{guid} AND c.type = 6 \n")
    List<Feed> getFeedTodayMaxList(String guid);

    @Select("SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,COUNT(*) count ,IFNULL(SUM(IFNULL(nurseContent ,0)), 0 ) nurseTotal FROM feed WHERE guid = #{guid} AND TYPE IN (1,2,3,4) AND createTime LIKE CONCAT('%',#{createTime},'%') GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ');")
    List<JSONObject> getWnDateCurve(String guid, String createTime);

    @Select("SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,COUNT(*) count FROM feed WHERE guid = #{guid} AND TYPE = 4 AND createTime LIKE CONCAT('%',#{createTime},'%') GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ');")
    List<JSONObject> getFsDateCurve(String guid, String createTime);

    @Select("SELECT  DATE_FORMAT(createTime,'%Y-%m-%d ') createTime, COUNT(*) COUNT  FROM feed WHERE guid = #{guid} AND  TYPE = 5 AND createTime LIKE CONCAT('%',#{createTime},'%') AND selectType IN (#{selectType},3) GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ')")
    List<JSONObject> getNbDateCurve(String guid, String createTime,String selectType);

    @Select("SELECT t.createTime createTime, SUM(t.spacing)  spacing FROM (SELECT  DATE_FORMAT(createTime,'%Y-%m-%d') createTime ,TIMESTAMPDIFF(MINUTE,createTime,endTime) spacing FROM feed WHERE guid = #{guid} AND  TYPE = 1 AND createTime LIKE CONCAT('%',#{createTime},'%')) t GROUP BY createTime ")
    List<JSONObject> getSmDateCurve(String guid, String createTime);

    @Select("select * from feed where guid=#{guid} limit #{pageNum},#{pageSize} and createTime CONCAT('%',#{createTime},'%') ")
    List<Feed> findFeedPagination(JSONObject jsonObject);
}

