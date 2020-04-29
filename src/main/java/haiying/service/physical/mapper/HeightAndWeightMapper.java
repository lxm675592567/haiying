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
    @Select("select * from height_and_weight where guid=#{guid}")
    List<HeightAndWeight> findHeightAndWeightList(String guid);

    @Select("select * from record where guid=#{guid}")
    Record findOne(String guid);

    @Select("select * from height_and_weight where guid=#{guid} and monthAgeInt=#{monthAgeInt} order by monthAgeInt desc ")
    HeightAndWeight findLastHeightAndWeight(@Param("guid") String guid,@Param("monthAgeInt") Integer monthAgeInt);

    @Delete("delete from height_and_weight where guid=#{guid} and monthAgeInt=#{monthAgeInt}")
    long remove(@Param("guid") String guid,@Param("monthAgeInt") Integer monthAgeInt);

    @Insert("insert into height_and_weight (hwId,guid,height,weight,heightIncrease,weightIncrease,heightEvaluation,weightEvaluation,heightCorrectEvaluation,weightCorrectEvaluation,suggestion,monthAge,monthAgeInt,correctMonthAge,age,doctor,createTime) values(#{hwId},#{guid},#{height},#{weight},#{heightIncrease},#{weightIncrease},#{heightEvaluation},#{weightEvaluation},#{heightCorrectEvaluation},#{weightCorrectEvaluation},#{suggestion},#{monthAge},#{monthAgeInt},#{correctMonthAge},#{age},#{doctor},#{createTime})")
    long save(HeightAndWeight heightAndWeight);

    @Select("select * from height_and_weight where guid=#{guid} limit #{pageNum},#{pageSize} ")
    List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject);
}
