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

    @Select("select guid ,typeName ,type,lactation,DATE_FORMAT(createTime,'%Y-%m-%d %H:%i') createTime,DATE_FORMAT(endTime,'%Y-%m-%d %H:%i') endTime,duration,nurseContent,foodName,foodPhoto,selectType,selectTypeName,urineShape,shitShape,foodDescribe,shapeType from feed where guid=#{guid} and createTime like CONCAT('%',#{createTime},'%') ORDER BY createTime DESC")
    List<JSONObject> getFeedTodayList(String guid, String createTime);

    @Select(" SELECT a.typeName,a.lactation,a.createTime,a.endTime,a.duration,a.selectTypeName,a.urineShape,a.shitShape FROM feed a WHERE a.createTime = (SELECT createtime FROM feed WHERE TYPE = 1 LIMIT 1) AND a.guid = #{guid} AND a.type = 1 \n" +
            " UNION \n" +
            " SELECT b.typeName,b.lactation,b.createTime,b.endTime,b.duration,b.selectTypeName,b.urineShape,b.shitShape FROM feed b WHERE b.createTime = (SELECT createtime FROM feed WHERE TYPE = 5 LIMIT 1) AND b.guid = #{guid} AND b.type = 5 \n" +
            " UNION \n" +
            " SELECT c.typeName,c.lactation,c.createTime,c.endTime,c.duration,c.selectTypeName,c.urineShape,c.shitShape FROM feed c WHERE c.createTime = (SELECT createtime FROM feed WHERE TYPE = 6 LIMIT 1) AND c.guid = #{guid} AND c.type = 6 \n")
    List<Feed> getFeedTodayMaxList(String guid);

    //@Select("SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,COUNT(*) count ,IFNULL(SUM(IFNULL(nurseContent ,0)), 0 ) nurseTotal FROM feed WHERE guid = #{guid} AND TYPE IN (1,2,3,4) AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime) GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ');")
    @Select("SELECT  DATE_FORMAT(a.click_date,'%Y-%m-%d ') AS createTime,IFNULL(b.count,0) AS COUNT,IFNULL(b.nurseTotal,0) AS nurseTotal\n" +
            "FROM (\n" +
            "    SELECT CURDATE() AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS click_date\n" +
            ") a LEFT JOIN (\n" +
            "  SELECT DATE(createTime) AS DATETIME, COUNT(*) AS COUNT,IFNULL(SUM(IFNULL(nurseContent ,0)), 0 ) AS nurseTotal\n" +
            "  FROM feed WHERE guid = #{guid} AND TYPE IN (1,2,3,4)\n" +
            "  GROUP BY DATE(createTime)\n" +
            ") b ON a.click_date = b.datetime ORDER BY createTime DESC;")
    List<JSONObject> getWnDateCurve(String guid);

    //@Select("SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,COUNT(*) count FROM feed WHERE guid = #{guid} AND TYPE = 4 AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime) GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ');")
    @Select("SELECT DATE_FORMAT(a.click_date,'%Y-%m-%d ')  AS createTime,IFNULL(b.count,0) AS COUNT\n" +
            "FROM (\n" +
            "    SELECT CURDATE() AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS click_date\n" +
            ") a LEFT JOIN (\n" +
            "  SELECT DATE(createTime) AS DATETIME, COUNT(*) AS COUNT\n" +
            "  FROM feed WHERE guid = #{guid} AND TYPE = 4\n" +
            "  GROUP BY DATE(createTime)\n" +
            ") b ON a.click_date = b.datetime ORDER BY createTime DESC;")
    List<JSONObject> getFsDateCurve(String guid);

//    @Select("SELECT  DATE_FORMAT(createTime,'%Y-%m-%d ') createTime, COUNT(*) COUNT  FROM feed WHERE guid = #{guid} AND  TYPE = 5 AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime) AND selectType IN (#{selectType},3) GROUP BY DATE_FORMAT(createTime,'%Y-%m-%d ')")
@Select("SELECT DATE_FORMAT(a.click_date,'%Y-%m-%d ')  AS createTime,IFNULL(b.count,0) AS COUNT\n" +
        "FROM (\n" +
        "    SELECT CURDATE() AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS click_date\n" +
        "    UNION ALL\n" +
        "    SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS click_date\n" +
        ") a LEFT JOIN (\n" +
        "  SELECT DATE(createTime) AS DATETIME, COUNT(*) AS COUNT\n" +
        "  FROM feed WHERE guid = #{guid} AND TYPE = 5\n" +
        "  GROUP BY DATE(createTime)\n" +
        ") b ON a.click_date = b.datetime ORDER BY createTime DESC;")
    List<JSONObject> getNbDateCurve(String guid);

    @Select("SELECT t.createTime createTime, SUM(t.spacing)  spacing FROM (SELECT  DATE_FORMAT(createTime,'%Y-%m-%d') createTime ,TIMESTAMPDIFF(MINUTE,createTime,endTime) spacing FROM feed WHERE guid = #{guid} AND  TYPE = 6 AND createTime LIKE CONCAT('%',#{createTime},'%')) t GROUP BY createTime ")
    JSONObject getSmDateCurve(String guid, String createTime);


    //@Select("SELECT t.createTime createTime, SUM(t.spacing)  spacing FROM (SELECT  DATE_FORMAT(createTime,'%Y-%m-%d') createTime ,TIMESTAMPDIFF(MINUTE,createTime,endTime) spacing FROM feed WHERE guid = #{guid} AND  TYPE = 6 AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime)) t GROUP BY createTime ")
    @Select("SELECT  DATE_FORMAT(a.click_date,'%Y-%m-%d ') AS createTime,IFNULL(b.spacing,0) AS spacing\n" +
            "FROM (\n" +
            "    SELECT CURDATE() AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS click_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS click_date\n" +
            ") a LEFT JOIN (\n" +
            "  SELECT DATE(c.createTime) AS DATETIME, COUNT(*) AS COUNT,SUM(c.spacing)  spacing\n" +
            "  FROM (SELECT  DATE_FORMAT(createTime,'%Y-%m-%d') AS createTime ,TIMESTAMPDIFF(MINUTE,createTime,endTime) AS spacing,TYPE AS TYPE FROM feed WHERE \n" +
            "  guid = #{guid} AND TYPE = 6 ) c WHERE c.type = 6\n" +
            "  GROUP BY DATE(createTime) \n" +
            ") b ON a.click_date = b.datetime ORDER BY createTime DESC;")
    List<JSONObject> getSmDateCurveNew(String guid);

    @Select("select id,guid,typeName,type,lactation,DATE_FORMAT(createTime,'%Y-%m-%d %H:%i') createTime,DATE_FORMAT(createTime,'%Y-%m-%d') dates,DATE_FORMAT(endTime,'%Y-%m-%d %H:%i') endTime,duration,nurseContent,foodName,foodPhoto,selectType,selectTypeName,urineShape,shitShape,foodDescribe,shapeType from feed where guid=#{guid} AND createTime LIKE CONCAT('%',#{createTime},'%')  ORDER BY createtime DESC limit #{pageNum},#{pageSize} ")
    List<JSONObject> findFeedPagination(JSONObject jsonObject);

    @Select("SELECT type type,typeName typeName,COUNT(type) total FROM feed WHERE  guid = #{guid}  AND createTime LIKE CONCAT('%',#{createTime},'%')  GROUP BY type")
    List<JSONObject> getSelectTotal(String guid, String createTime);

    @Select("SELECT selectType selectType,selectTypeName selectTypeName, COUNT(selectType) selectCount FROM feed WHERE TYPE = 5 AND guid = #{guid}  AND createTime LIKE CONCAT('%',#{createTime},'%')  GROUP BY selectType")
    List<JSONObject> getSelectCount(String guid, String createTime);

    @Select("SELECT SUM(duration) mrTotal,SUM(nurseContent) suckle FROM feed WHERE type = #{type} AND guid = #{guid}  AND createTime LIKE CONCAT('%',#{createTime},'%')")
    JSONObject getMilkCount(String guid, String createTime,String type);

    @Select("SELECT t.createTime createTime FROM (SELECT  DATE_FORMAT(createTime,'%Y年%m月%d日') createTime,guid FROM feed where guid = #{guid} ORDER BY createTime DESC )t GROUP BY createTime ORDER BY createTime DESC")
    List<JSONObject> getFeedTime(String guid);

    @Select("SELECT t.createTime createTime ,t.days days FROM (SELECT  DATE_FORMAT(createTime,'%Y年%m月%d日') createTime,DATE_FORMAT(createTime,'%Y-%m-%d') days,guid FROM feed where guid = #{guid} ORDER BY createTime DESC limit #{pageNum},#{pageSize})t GROUP BY createTime ORDER BY createTime DESC")
    List<JSONObject> getFeedTimeNew(String guid,Long pageNum,Long pageSize);

    @Select("select id,guid,typeName,type,lactation,DATE_FORMAT(createTime,'%Y-%m-%d %H:%i') createTime,DATE_FORMAT(createTime,'%Y-%m-%d') days,DATE_FORMAT(createTime,'%Y-%m-%d') dates,DATE_FORMAT(endTime,'%Y-%m-%d %H:%i') endTime,duration,nurseContent,foodName,foodPhoto,selectType,selectTypeName,urineShape,shitShape,foodDescribe,shapeType from feed where guid=#{guid}  ORDER BY createtime DESC limit #{pageNum},#{pageSize} ")
    List<JSONObject> findFeedPaginationNew(JSONObject jsonObject);

//    @Select("(SELECT typeName,TIMESTAMPDIFF(MINUTE,createTime,NOW()) spacing FROM feed WHERE  TYPE IN (1,2,3,4) AND guid = #{guid}  ORDER BY createTime LIMIT 1)\n" +
//            "UNION\n" +
//            "(SELECT typeName,TIMESTAMPDIFF(MINUTE,createTime,NOW()) spacing FROM feed WHERE  TYPE =5 AND guid = #{guid}  ORDER BY createTime LIMIT 1)\n" +
//            "UNION\n" +
//            "(SELECT typeName,TIMESTAMPDIFF(MINUTE,createTime,NOW()) spacing FROM feed WHERE  TYPE =6 AND guid = #{guid}  ORDER BY createTime LIMIT 1)")
@Select("(SELECT  typeName,SUM(duration) spacing  FROM feed WHERE  TYPE IN (1,2,3,4) AND guid = #{guid} AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime) )\n" +
        "UNION\n" +
        "(SELECT typeName,TIMESTAMPDIFF(MINUTE,createTime,NOW()) spacing FROM feed WHERE  TYPE =5 AND guid = #{guid}  ORDER BY createTime LIMIT 1)\n" +
        "UNION\n" +
        "(SELECT t.typeName,SUM(t.spacing) spacing FROM (SELECT typeName,TIMESTAMPDIFF(MINUTE,createTime,endTime) spacing FROM feed WHERE  TYPE =6 AND guid = #{guid} AND DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(createTime))t )")
    List<JSONObject> getArchivesRecord(String guid);

    @Select("SELECT type,typeName,TIMESTAMPDIFF(MINUTE,createTime,endTime) spacing FROM feed WHERE  type = #{type} AND guid=#{guid} LIMIT 1")
    JSONObject getDailyRecord(String guid,String type);
}

