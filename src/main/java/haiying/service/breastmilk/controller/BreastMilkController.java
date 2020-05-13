package haiying.service.breastmilk.controller;

import com.alibaba.fastjson.JSONObject;
import haiying.service.breastmilk.service.BreastMilkService;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.service.HeightAndWeightService;
import haiying.util.Result;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller("/breastMilk")
public class BreastMilkController {

    private final BreastMilkService breastMilkService;

    public BreastMilkController(BreastMilkService breastMilkService) {
        this.breastMilkService = breastMilkService;
    }


}
