package haiying.service.breastmilk.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.breastmilk.domain.BreastMilk;
import haiying.service.physical.domain.HeightAndWeight;

import java.util.List;

public interface BreastMilkService {

    BreastMilk saveBreastMilk(BreastMilk breastMilk);

    List<JSONObject> findBreastMilk(JSONObject jsonObject);
}
