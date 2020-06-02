package haiying.service.physical.controller;

import com.alibaba.fastjson.JSONObject;
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
@Controller("/physical/heightAndWeight")
public class HeightAndWeightController {

    private final HeightAndWeightService heightAndWeightService;

    public HeightAndWeightController(HeightAndWeightService heightAndWeightService) {
        this.heightAndWeightService = heightAndWeightService;
    }

    /**
     * @api {Post} /physical/heightAndWeight/findHeightAndWeightGraphData/{guid} findHeightAndWeightGraphData
     * @apiGroup 体格发育
     * @apiDescription 百分位统计表-根据guid获取体格发育数据曲线图(暂时不用)
     * @apiParam {String} guid 档案号
     * @apiParamExample {String} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8"}
     */
    @Get("findHeightAndWeightGraphData/{guid}")
    public JSONObject findHeightAndWeightGraphData(String guid) {
        return this.heightAndWeightService.findHeightAndWeightGraphData(guid);
    }

    /**
     * @api {Post} /physical/heightAndWeight/saveOrEditHeightAndWeight saveOrEditHeightAndWeight
     * @apiGroup 体格发育
     * @apiDescription 保存-根据传递的身高体重数据保存体格发育数据
     * @apiParam {Json} guid(必填) guid
     * @apiParam {json}height(必填) 身高
     * @apiParam {json}weight(必填) 体重
     * @apiParamExample {Json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","height":100,"weight":43}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"hwId":"1260856430453874688","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","height":100.0,"weight":43.0,"heightEvaluation":"上异常","weightEvaluation":"上异常","monthAgeInt":12,"age":"1岁0月27天","createTime":"2020-05-14 16:56:13","monthIntHeightEntry":[12,100.0],"monthIntWeightEntry":[12,43.0],"correctMonthHeightEntry":[null,100.0],"monthHeightEntry":[null,100.0],"monthWeightEntry":[null,43.0],"correctMonthWeightEntry":[null,43.0]},"success":true}
     */
    @Post("/saveOrEditHeightAndWeight")
    public Result<HeightAndWeight> saveOrEditHeightAndWeight(@Body @Valid HeightAndWeight heightAndWeight) {
        return Result.ok( this.heightAndWeightService.saveOrEditHeightAndWeight(heightAndWeight));
    }


    /**
     * @api {Post} /physical/heightAndWeight/findHeightAndWeightHistoryPagination findHeightAndWeightHistoryPagination
     * @apiGroup 体格发育
     * @apiDescription 历史记录-根据传递的身高体重数据保存体格发育历史数据(暂未用到)
     * @apiParam {Json} HeightAndWeight 身高体重实体类
     * @apiParamExample {Json} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81","height":100,"weight":43}
     */
    @Post("findHeightAndWeightHistoryPagination")
    public List<HeightAndWeight> findHeightAndWeightHistoryPagination(JSONObject jsonObject) {
        return this.heightAndWeightService.findHeightAndWeightHistoryPagination(jsonObject);
    }


    /**
     * @api {Post} /physical/heightAndWeight/getHeightAndWeightDateCurve getHeightAndWeightDateCurve
     * @apiGroup 体格发育
     * @apiDescription 获取曲线图数据
     * @apiParam {String} guid 档案号
     * @apiParamExample {String} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81"}
     * @apiSuccessExample  {json} 返回值示例
     * [
     *     {
     *         "createTime": "2020-04-29 ",
     *         "weight": 43.0,
     *         "height": 100.0
     *     },
     *    {
     *         "createTime": "2020-04-30 ",
     *         "weight": 44.0,
     *         "height": 100.0
     *     }
     * ]
     */
    @Post("getHeightAndWeightDateCurve")
    public List<JSONObject> getHeightAndWeightDateCurve(String guid) {
        return this.heightAndWeightService.getHeightAndWeightDateCurve(guid);
    }
}
