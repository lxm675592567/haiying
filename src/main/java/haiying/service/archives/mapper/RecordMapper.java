package haiying.service.archives.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RecordMapper {

    @Select("select * from record where guid=#{guid} LIMIT 1")
    Record findOne(String guid);

    @Insert("insert into record (guid,cardId,name,sex,idnumber,birthday,unionId,createTime,status,openId,onlyChild,pregnantWeek,avatar,birthHeight,birthWeight,pregnancySecond,yieldSecond) values(#{guid},#{cardId},#{name},#{sex},#{idnumber},#{birthday},#{unionId},#{createTime},#{status},#{openId},#{onlyChild},#{pregnantWeek},#{avatar},#{birthHeight},#{birthWeight},#{pregnancySecond},#{yieldSecond})")
    void saveRecord(Record record);

    @Update("update record set name=#{name},sex=#{sex},idnumber=#{idnumber},birthday=#{birthday},status=#{status},onlyChild=#{onlyChild},pregnantWeek=#{pregnantWeek},avatar=#{avatar} where guid=#{guid}")
    void updateRecord(Record record);

    @Select("select * from cardNum where date=#{date}")
    JSONObject findOneCardNum(String date);

    @Update("update cardNum set cardnum=#{cardnum} where date=#{date}")
    void updateOneCardNum(@Param("cardnum")String newCard,@Param("date")String date);

    @Insert("insert into cardNum(cardNum,date) values (#{cardNum},#{date})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertOneCardNum(CardNum cardNum);

    @Select("select * from record where openId=#{openId} LIMIT 1")
    List<Record> getBabyArchives(String openId);

    //@Select("select * from record where openId=#{openId} and guid=#{guid} LIMIT 1")
    @Select({"<script>",
            "SELECT * FROM record",
            "WHERE 1=1",
            "<when test='guid!=null'>",
            "AND guid = #{guid}",
            "</when>",
            "<when test='openId!=null'>",
            "AND openId = #{openId}",
            "</when>",
            "LIMIT 1",
            "</script>"})
    Record findSingleOne(String openId,String guid);
}

