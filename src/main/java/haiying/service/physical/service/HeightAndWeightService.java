package haiying.service.physical.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.physical.domain.HeightAndWeight;

import java.util.List;

public interface HeightAndWeightService {

    JSONObject findHeightAndWeightGraphData(String guid);

    List<HeightAndWeight> findHeightAndWeightList(JSONObject jsonObject);

    //HeightAndWeight getHeightAdnWeightTestResult(HeightAndWeight heightAndWeight);

    HeightAndWeight saveOrEditHeightAndWeight(HeightAndWeight heightAndWeight);

    List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject);

    List<JSONObject> getHeightAndWeightDateCurve(String guid);
}
