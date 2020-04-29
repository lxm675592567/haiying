package haiying.service.archives.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import org.apache.ibatis.annotations.*;

public interface RecordMapper {

    @Select("select * from record where guid=#{guid}")
    Record findOne(String guid);

    @Insert("insert into record (guid,cardId,name,sex,idnumber,birthday,unionId,createTime,status,openId,onlyChild,pregnantWeek,avatar) values(#{guid},#{cardId},#{name},#{sex},#{idnumber},#{birthday},#{unionId},#{createTime},#{status},#{openId},#{onlyChild},#{pregnantWeek},#{avatar})")
    void saveRecord(Record record);

    @Update("update genre set name=#{name},sex=#{sex},idnumber=#{idnumber},birthday=#{birthday},status=#{status},onlyChild=#{onlyChild},pregnantWeek=#{pregnantWeek},avatar=#{avatar} where guid=#{guid}")
    void updateRecord(Record record);

    @Select("select * from cardNum where date=#{date}")
    JSONObject findOneCardNum(String date);

    @Update("update cardNum set cardnum=#{cardnum} where date=#{date}")
    void updateOneCardNum(@Param("cardnum")String newCard,@Param("date")String date);

    @Insert("insert into cardNum(cardNum,date) values (#{cardNum},#{date})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertOneCardNum(CardNum cardNum);
}

