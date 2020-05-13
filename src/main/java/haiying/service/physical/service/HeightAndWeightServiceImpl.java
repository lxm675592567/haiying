package haiying.service.physical.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.physical.constant.CoreConstant;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.mapper.HeightAndWeightMapper;

import haiying.service.physical.utils.HeightAndWeightUtils;
import haiying.util.DateUtil;
import haiying.util.GuidUtil;
import haiying.util.StringUtil;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Singleton
@Validated
public class HeightAndWeightServiceImpl implements HeightAndWeightService{

    private final HeightAndWeightMapper heightAndWeightMapper;

    public HeightAndWeightServiceImpl(HeightAndWeightMapper heightAndWeightMapper) {
        this.heightAndWeightMapper = heightAndWeightMapper;
    }


    @Override
    public JSONObject findHeightAndWeightGraphData(String guid) {
        //通过guid获取当前用户信息
        Record record = this.heightAndWeightMapper.findOne(guid).putAgeDetail();
        return this.findHeightAndWeightGraphData(record);
    }


    private JSONObject findHeightAndWeightGraphData(Record record) {
        String sex = record.getSex();//先获得性别
        JSONObject heightJsonObject = new JSONObject();
        JSONObject weightJsonObject = new JSONObject();

        List<Integer> monthList = this.getMonthList(record.getGuid(), heightJsonObject, weightJsonObject);

        int maxMonth = monthList.stream().filter(Objects::nonNull).max(Integer::compareTo).orElse(206) + 10;
        int minMonth = monthList.stream().filter(integer -> Objects.nonNull(integer) && integer > 10).min(Integer::compareTo).orElse(10) - 10;


        heightJsonObject.putAll(HeightAndWeightUtils.getHeightStandJson(sex, maxMonth, minMonth));
        weightJsonObject.putAll(HeightAndWeightUtils.getWeightStandJson(sex, maxMonth, minMonth));

        return new JSONObject().fluentPut("heightData", heightJsonObject)
                .fluentPut("weightData", weightJsonObject);
    }

    private List<Integer> getMonthList(String guid, JSONObject heightJsonObject, JSONObject weightJsonObject) {
        List<HeightAndWeight> heightAndWeightList = this.findHeightAndWeightList(new JSONObject().fluentPut("guid", guid)); //获得该用户身高体重记录
        JSONArray heightArray = new JSONArray(), weightArray = new JSONArray();
        List<Integer> monthList = new ArrayList<>();

        for (HeightAndWeight heightAndWeight : heightAndWeightList) {
            heightArray.add(heightAndWeight.getMonthIntHeightEntry());
            weightArray.add(heightAndWeight.getMonthIntWeightEntry());
            monthList.add(heightAndWeight.getMonthAgeInt());
        }
        heightJsonObject.put("heightArray", heightArray);
        weightJsonObject.put("weightArray", weightArray);

        return monthList;
    }

    @Override
    public List<HeightAndWeight> findHeightAndWeightList(JSONObject jsonObject) {
        String guid = jsonObject.getString("guid");
        return this.heightAndWeightMapper.findHeightAndWeightList(guid);
    }


    public HeightAndWeight getHeightAdnWeightTestResult(HeightAndWeight heightAndWeight) {
        checkHeightAndWeight(heightAndWeight);

        String guid = heightAndWeight.getGuid();
        double height = heightAndWeight.getHeight();
        double weight = heightAndWeight.getWeight();

        Record record = this.heightAndWeightMapper.findOne(guid).putAgeDetail();

        int monthAgeInt = record.getAgeDetail().getMonthAgeInt();
        heightAndWeight.setMonthAgeInt(monthAgeInt);

        HeightAndWeight lastHeightAndWeight
                = this.heightAndWeightMapper.findLastHeightAndWeight(guid, monthAgeInt);

        if (lastHeightAndWeight != null) {
            heightAndWeight.putHeightIncrease(lastHeightAndWeight.getHeight())
                    .putWeightIncrease(lastHeightAndWeight.getWeight());
        }

        String sexValue = CoreConstant.Gender.getInstanceBySex(record.getSex()).getValue();
        new HeightAndWeightUtils.HeightStand.Builder(sexValue,monthAgeInt).getInstance();
        HeightAndWeightUtils.HeightStand heightStand = new HeightAndWeightUtils.HeightStand.Builder(sexValue, monthAgeInt).getInstance();
        HeightAndWeightUtils.WeightStand weightStand = new HeightAndWeightUtils.WeightStand.Builder(sexValue, monthAgeInt).getInstance();

        if (heightStand != null) {
            heightAndWeight.setHeightEvaluation(heightStand.getJudgeStand(height));
        }

        if (weightStand != null) {
            heightAndWeight.setWeightEvaluation(weightStand.getJudgeStand(weight));
        }
        String ageDetail = record.getAgeDetail().getAgeDetail();
        heightAndWeight.setCreateTime(new Date()).setAge(ageDetail);
        return heightAndWeight;
    }

    @Override
    public HeightAndWeight saveOrEditHeightAndWeight(HeightAndWeight heightAndWeight) {
        //先判断guid和monthAgeInt是否有相同 有相同则删除相同
        HeightAndWeight heightAdnWeightTestResult = getHeightAdnWeightTestResult(heightAndWeight);
        HeightAndWeight lastHeightAndWeight = this.heightAndWeightMapper.findLastHeightAndWeight(heightAdnWeightTestResult.getGuid(), heightAdnWeightTestResult.getMonthAgeInt());
        if(!Objects.isNull(lastHeightAndWeight)){
            heightAndWeightMapper.remove(heightAdnWeightTestResult.getGuid(),heightAdnWeightTestResult.getMonthAgeInt());
        }
        heightAndWeight.setHwId(GuidUtil.generateGuid()).setCreateTime(new Date());

        heightAndWeightMapper.save(heightAdnWeightTestResult);
        return heightAdnWeightTestResult;
    }

    @Override
    public List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject) {
        Long pageSize = (Long) jsonObject.get("pageSize"),pageNum = (Long) jsonObject.get("pageNum");
        pageNum = (pageNum-1)*pageSize;
        jsonObject.remove("pageNum");
        jsonObject.put("pageNum",pageNum);
        return heightAndWeightMapper.findHeightAndWeightHistoryPagination(jsonObject);
    }

    @Override
    public List<JSONObject> getHeightAndWeightDateCurve(String guid) {
        return heightAndWeightMapper.getHeightAndWeightDateCurve(guid);
    }

    /**
     * 传入值验证
     *
     * @param heightAndWeight 传入值
     */
    private void checkHeightAndWeight(HeightAndWeight heightAndWeight) {
        if (StringUtil.stringIsNull(heightAndWeight.getGuid())) {
            throw new IllegalArgumentException("患者guid为空");
        }

        if (heightAndWeight.getHeight() == null) {
            throw new IllegalArgumentException("身高为空");
        }

        if (heightAndWeight.getWeight() == null) {
            throw new IllegalArgumentException("体重为空");
        }
    }

}
