package haiying.service.archives.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RecordMapper {

    @Select("select guid guid,cardId cardId,name name,sex sex,idnumber idnumber,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,unionId unionId,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,status status,openId openId,onlyChild onlyChild,pregnantWeek pregnantWeek,IFNULL(avatar,'')  avatar,birthHeight birthHeight,birthWeight birthWeight,pregnancySecond pregnancySecond,yieldSecond yieldSecond,address address from record where guid=#{guid} LIMIT 1")
    Record findOne(String guid);

    @Select("select guid guid,cardId cardId,name name,sex sex,idnumber idnumber,DATE_FORMAT(birthday,'%Y-%m-%d') birthday,unionId unionId,DATE_FORMAT(createTime,'%Y-%m-%d') createTime,status status,openId openId,onlyChild onlyChild,pregnantWeek pregnantWeek,IFNULL(avatar,'')  avatar,birthHeight birthHeight,birthWeight birthWeight,pregnancySecond pregnancySecond,yieldSecond yieldSecond,address address  from record where guid=#{guid} LIMIT 1")
    JSONObject findOneNew(String guid);

    @Insert("insert into record (guid,cardId,name,sex,idnumber,birthday,unionId,createTime,status,openId,onlyChild,pregnantWeek,avatar,birthHeight,birthWeight,pregnancySecond,yieldSecond,address) values(#{guid},#{cardId},#{name},#{sex},#{idnumber},#{birthday},#{unionId},#{createTime},#{status},#{openId},#{onlyChild},#{pregnantWeek},#{avatar},#{birthHeight},#{birthWeight},#{pregnancySecond},#{yieldSecond},#{address})")
    void saveRecord(Record record);

    @Update("update record set name=#{name},sex=#{sex},idnumber=#{idnumber},birthday=#{birthday},status=#{status},onlyChild=#{onlyChild},pregnantWeek=#{pregnantWeek},avatar=#{avatar},birthHeight=#{birthHeight},birthWeight=#{birthWeight},pregnancySecond=#{pregnancySecond},yieldSecond=#{yieldSecond},address=#{address} where guid=#{guid}")
    void updateRecord(Record record);

    @Select("select id id,date date,cardNum cardNum  from cardNum where date=#{date}")
    JSONObject findOneCardNum(String date);

    @Update("update cardNum set cardnum=#{cardnum} where date=#{date}")
    void updateOneCardNum(@Param("cardnum")String newCard,@Param("date")String date);

    @Insert("insert into cardNum(cardNum,date) values (#{cardNum},#{date})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertOneCardNum(CardNum cardNum);

    @Select("select * from record where openId=#{openId}")
    List<Record> getBabyArchives(String openId);

    @Select({"<script>",
            "SELECT guid,cardId,name,sex,idnumber,DATE_FORMAT(birthday,'%Y-%m-%d ') birthday,unionId,DATE_FORMAT(createTime,'%Y-%m-%d ') createTime,status,openId,onlyChild,pregnantWeek,avatar,birthHeight,birthWeight,pregnancySecond,yieldSecond FROM record",
            "WHERE 1=1",
            "<when test='guid!=null and guid.trim() != &quot;&quot;'>",
            "AND guid = #{guid}",
            "</when>",
            "<when test='openId!=null and openId.trim() != &quot;&quot;'>",
            "AND openId = #{openId}",
            "</when>",
            "LIMIT 1",
            "</script>"})
    JSONObject findSingleOne(String openId,String guid);
}

