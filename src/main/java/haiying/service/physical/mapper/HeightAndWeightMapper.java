package haiying.service.physical.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.physical.domain.HeightAndWeight;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HeightAndWeightMapper {

    @Select("select hwId hwId,guid guid,height height,weight weight,heightIncrease heightIncrease,weightIncrease weightIncrease,heightEvaluation heightEvaluation,weightEvaluation weightEvaluation,heightCorrectEvaluation heightCorrectEvaluation,weightCorrectEvaluation weightCorrectEvaluation,suggestion suggestion,monthAge monthAge,monthAgeInt monthAgeInt,correctMonthAge correctMonthAge,age age,doctor doctor,DATE_FORMAT(createTime,'%Y-%m-%d') createTime from height_and_weight where guid=#{guid} ORDER BY createtime DESC limit 1")
    HeightAndWeight findHeightAndWeightOne(String guid);

    @Select("select hwId hwId,guid guid,height height,weight weight,heightIncrease heightIncrease,weightIncrease weightIncrease,heightEvaluation heightEvaluation,weightEvaluation weightEvaluation,heightCorrectEvaluation heightCorrectEvaluation,weightCorrectEvaluation weightCorrectEvaluation,suggestion suggestion,monthAge monthAge,monthAgeInt monthAgeInt,correctMonthAge correctMonthAge,age age,doctor doctor,DATE_FORMAT(createTime,'%Y-%m-%d') createTime from height_and_weight where guid=#{guid}")
    List<HeightAndWeight> findHeightAndWeightList(String guid);

    @Select("select guid guid,cardId cardId,name name,sex sex,idnumber idnumber,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,unionId unionId,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,status status,openId openId,onlyChild onlyChild,pregnantWeek pregnantWeek,IFNULL(avatar,'')  avatar,birthHeight birthHeight,birthWeight birthWeight,pregnancySecond pregnancySecond,yieldSecond yieldSecond,address address from record where guid=#{guid}  limit 1")
    Record findOne(String guid);

    @Select("select hwId hwId,guid guid,height height,weight weight,heightIncrease heightIncrease,weightIncrease weightIncrease,heightEvaluation heightEvaluation,weightEvaluation weightEvaluation,heightCorrectEvaluation heightCorrectEvaluation,weightCorrectEvaluation weightCorrectEvaluation,suggestion suggestion,monthAge monthAge,monthAgeInt monthAgeInt,correctMonthAge correctMonthAge,age age,doctor doctor,DATE_FORMAT(createTime,'%Y-%m-%d') createTime from height_and_weight where guid=#{guid} and monthAgeInt=#{monthAgeInt} order by monthAgeInt desc ")
    HeightAndWeight findLastHeightAndWeight(@Param("guid") String guid,@Param("monthAgeInt") Integer monthAgeInt);

    @Delete("delete from height_and_weight where guid=#{guid} and monthAgeInt=#{monthAgeInt}")
    long remove(@Param("guid") String guid,@Param("monthAgeInt") Integer monthAgeInt);

    @Insert("insert into height_and_weight (hwId,guid,height,weight,heightIncrease,weightIncrease,heightEvaluation,weightEvaluation,heightCorrectEvaluation,weightCorrectEvaluation,suggestion,monthAge,monthAgeInt,correctMonthAge,age,doctor,createTime) values(#{hwId},#{guid},#{height},#{weight},#{heightIncrease},#{weightIncrease},#{heightEvaluation},#{weightEvaluation},#{heightCorrectEvaluation},#{weightCorrectEvaluation},#{suggestion},#{monthAge},#{monthAgeInt},#{correctMonthAge},#{age},#{doctor},#{createTime})")
    long save(HeightAndWeight heightAndWeight);

    @Select("select hwId hwId,guid guid,height height,weight weight,heightIncrease heightIncrease,weightIncrease weightIncrease,heightEvaluation heightEvaluation,weightEvaluation weightEvaluation,heightCorrectEvaluation heightCorrectEvaluation,weightCorrectEvaluation weightCorrectEvaluation,suggestion suggestion,monthAge monthAge,monthAgeInt monthAgeInt,correctMonthAge correctMonthAge,age age,doctor doctor,DATE_FORMAT(createTime,'%Y-%m-%d') createTime from height_and_weight where guid=#{guid} ORDER BY createtime DESC  limit #{pageNum},#{pageSize} ")
    List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject);

    //@Select("SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,height,weight  FROM height_and_weight WHERE guid = #{guid}")
    @Select("SELECT DATE_FORMAT(a.click_date,'%Y-%m-%d ') AS createTime,IFNULL(b.height,0) AS height,IFNULL(b.weight,0) AS weight\n" +
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
            "    SELECT DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,height,weight  FROM height_and_weight WHERE guid = #{guid} \n" +
            ") b ON a.click_date = b.createTime ORDER BY createTime DESC;")
    List<JSONObject> getHeightAndWeightDateCurve(String guid);
}
