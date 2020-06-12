package haiying.service.breastmilk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.breastmilk.domain.BreastMilk;
import haiying.service.breastmilk.mapper.BreastMilkMapper;
import haiying.service.physical.constant.CoreConstant;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;
import haiying.service.physical.service.HeightAndWeightService;
import haiying.service.physical.utils.HeightAndWeightUtils;
import haiying.util.GuidUtil;
import haiying.util.StringUtil;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Singleton
@Validated
public class BreastMilkServiceImpl implements BreastMilkService{

    private final BreastMilkMapper breastMilkMapper;

    public BreastMilkServiceImpl(BreastMilkMapper breastMilkMapper) {
        this.breastMilkMapper = breastMilkMapper;
    }


    @Override
    public BreastMilk saveBreastMilk(BreastMilk breastMilk) {
        breastMilk.setId(GuidUtil.generateGuid());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        breastMilk.setCreateTime(ft.format(date));
        breastMilkMapper.saveBreastMilk(breastMilk);
        return breastMilk;
    }

    @Override
    public List<JSONObject> findBreastMilk(JSONObject jsonObject) {
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        return breastMilkMapper.findBreastMilk(jsonObject);
    }
}
