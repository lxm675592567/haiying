package haiying.service.breastmilk.mapper;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.breastmilk.domain.BreastMilk;
import haiying.service.physical.domain.HeightAndWeight;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BreastMilkMapper {


    @Insert("insert into breast_milk (id,openId,breastType,fat,lactose,protein,energy,water,mineral,density,createTime,guid) " +
            "values(#{id},#{openId},#{breastType},#{fat},#{lactose},#{protein},#{energy},#{water},#{mineral},#{density},#{createTime},#{guid})")
    void saveBreastMilk(BreastMilk breastMilk);

    @Select("SELECT id, openId,breastType,fat,lactose,protein,energy,water,mineral,density,createTime FROM breast_milk WHERE  openId = #{openId} ORDER BY createtime DESC limit #{pageNum},#{pageSize} ")
    List<JSONObject> findBreastMilk(JSONObject jsonObject);

}
