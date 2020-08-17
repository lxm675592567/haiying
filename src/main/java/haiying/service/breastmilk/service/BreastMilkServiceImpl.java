package haiying.service.breastmilk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.archives.mapper.RecordMapper;
import haiying.service.breastmilk.domain.BreastMilk;
import haiying.service.breastmilk.domain.HistoricalDataDTO;
import haiying.service.breastmilk.mapper.BreastMilkMapper;
import haiying.service.physical.constant.CoreConstant;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;
import haiying.service.physical.service.HeightAndWeightService;
import haiying.service.physical.utils.HeightAndWeightUtils;
import haiying.util.GuidUtil;
import haiying.util.HttpclientUtil;
import haiying.util.StringUtil;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
@Validated
public class BreastMilkServiceImpl implements BreastMilkService{

    private final BreastMilkMapper breastMilkMapper;

    private final RecordMapper recordMapper;

    public BreastMilkServiceImpl(BreastMilkMapper breastMilkMapper,RecordMapper recordMapper) {
        this.breastMilkMapper = breastMilkMapper;
        this.recordMapper = recordMapper;
    }


    @Override
    public BreastMilk saveBreastMilk(BreastMilk breastMilk) {
        breastMilk.setId(GuidUtil.generateGuid());
        String openId = breastMilk.getOpenId();
        String guid = breastMilk.getGuid();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        breastMilk.setCreateTime(ft.format(date));
        breastMilkMapper.saveBreastMilk(breastMilk);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        JSONObject oneNew = recordMapper.findOneNew(guid);
        String ptGuid = oneNew.getString("ptGuid");

        HistoricalDataDTO historicalDataDTO = new HistoricalDataDTO();
        historicalDataDTO.setBgdname(df.format(new Date())+"母乳数据");
        historicalDataDTO.setData(JSONObject.parseObject(JSON.toJSONString(breastMilk)));
        historicalDataDTO.setDataGuid(UUID.randomUUID().toString().replace("-", ""));
        historicalDataDTO.setHzguid(ptGuid);
        historicalDataDTO.setDevicType("ky.stl.chirld.mr");
        String postUrl = HttpclientUtil.get("httpclient.ptInfo.post");
        HttpclientUtil.httpPost(postUrl, JSON.parseObject(JSON.toJSONString(historicalDataDTO)));

        System.out.println(historicalDataDTO);


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
