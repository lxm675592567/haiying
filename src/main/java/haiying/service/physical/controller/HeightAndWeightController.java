package haiying.service.physical.controller;

import com.alibaba.fastjson.JSONObject;
import haiying.service.physical.domain.HeightAndWeight;
import haiying.service.physical.service.HeightAndWeightService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller("/physical/heightAndWeight")
public class HeightAndWeightController {

    private final HeightAndWeightService heightAndWeightService;

    public HeightAndWeightController(HeightAndWeightService heightAndWeightService) {
        this.heightAndWeightService = heightAndWeightService;
    }

    @Get("findHeightAndWeightGraphData/{guid}")
    public JSONObject findHeightAndWeightGraphData(String guid) {
        return this.heightAndWeightService.findHeightAndWeightGraphData(guid);
    }
    /**
     *
     * @param heightAndWeight
     * @return 提交结果保存
     */
    @Post( "/getHeightAdnWeightTestResult")
    public HeightAndWeight getHeightAdnWeightTestResult(HeightAndWeight heightAndWeight) {
        return this.heightAndWeightService.getHeightAdnWeightTestResult(heightAndWeight);
    }

    @Post("/saveOrEditHeightAndWeight")
    public void saveOrEditHeightAndWeight(@Body @Valid HeightAndWeight heightAndWeight) {
        this.heightAndWeightService.saveOrEditHeightAndWeight(heightAndWeight);
    }

    @Post("findHeightAndWeightHistoryPagination")
    public List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject) {
        return this.heightAndWeightService.findHeightAndWeightHistoryPagination(jsonObject);
    }

}
