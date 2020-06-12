package haiying.service.breastmilk.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import haiying.service.breastmilk.domain.BreastMilk;
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
@Controller("/haoyun1/breastMilk")
public class BreastMilkController {

    private final BreastMilkService breastMilkService;

    public BreastMilkController(BreastMilkService breastMilkService) {
        this.breastMilkService = breastMilkService;
    }

    /**
     * @api {Post} /breastMilk/saveBreastMilk saveBreastMilk
     * @apiGroup 母乳测量
     * @apiDescription 保存母乳测量信息
     * @apiParam {json}openId openId
     * @apiParam {json}breastType 母乳类型
     * @apiParam {json}fat 脂肪
     * @apiParam {json}lactose 乳糖
     * @apiParam {json}protein 蛋白质
     * @apiParam {json}energy 能量
     * @apiParam {json}water 水分
     * @apiParam {json}mineral 矿物质
     * @apiParam {json}density 密度
     * @apiParam {json}createTime 时间
     * @apiParam {json}result 结果
     * @apiParam {json}proposal 指导建议
     * @apiParam {json}age 年龄
     * @apiParamExample {json} 传参示例
     *{
     * 	"openId": "ojP-tvwqOu-kwE4qxKqhFJSC3KOw",
     * 	"breastType": "过渡乳",
     * 	"fat": "3.80",
     * 	"lactose": "7.86",
     * 	"protein": "1.52",
     * 	"energy": "63.66",
     * 	"water": "91.52",
     * 	"mineral": "0.29",
     * 	"density": "1.041",
     * 	"createTime": "2020-04-24 13:20:09",
     * 	"result": "正常",
     * 	"proposal": "非常好",
     * 	"age": "3个月零4周"
     * }
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"id":"1267755868787073024","openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","breastType":"过渡乳","fat":"3.80","lactose":"7.86","protein":"1.52","energy":"63.66","water":"91.52","mineral":"0.29","density":"1.041","createTime":"2020-04-24 13:20:09","result":"正常","proposal":"非常好","age":"3个月零4周"},"success":true}
     */
    @Post("/saveBreastMilk")
    public Result<BreastMilk> saveBreastMilk(@Body @Valid BreastMilk breastMilk)  {
        return Result.ok(this.breastMilkService.saveBreastMilk(breastMilk));
    }

    /**
     * @api {Post} /breastMilk/findBreastMilk findBreastMilk
     * @apiGroup 母乳测量
     * @apiDescription 获取母乳测量记录信息
     * @apiParam {json} guid 档案号
     * @apiParam {json} pageSize 每页固定数
     * @apiParam {json} pageNum 开始
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","pageSize": 5,"pageNum": 1}
     * @apiSuccess {json}openId openId
     * @apiSuccess {json}breastType 母乳类型
     * @apiSuccess {json}fat 脂肪
     * @apiSuccess {json}lactose 乳糖
     * @apiSuccess {json}protein 蛋白质
     * @apiSuccess {json}energy 能量
     * @apiSuccess {json}water 水分
     * @apiSuccess {json}mineral 矿物质
     * @apiSuccess {json}density 密度
     * @apiSuccess {json}createTime 时间
     * @apiSuccess {json}result 结果
     * @apiSuccess {json}proposal 指导建议
     * @apiSuccess {json}age 年龄
     * @apiSuccessExample  {json} 返回值示例
     * [{"proposal":"非常好","density":"1.041","openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","lactose":"7.86","water":"91.52","result":"正常","createTime":"2020-04-24 13:20:09","breastType":"过渡乳","protein":"1.52","fat":"3.80","mineral":"0.29","age":"3个月零4周","energy":"63.66"},{"proposal":"非常好","density":"1.041","openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","lactose":"7.86","water":"91.52","result":"正常","createTime":"2020-04-24 13:20:09","breastType":"过渡乳","protein":"1.52","fat":"3.80","mineral":"0.29","age":"3个月零4周","energy":"63.66"}]
     */
    @Post("/findBreastMilk")
    public List<JSONObject> findBreastMilk(JSONObject jsonObject) {
        return  this.breastMilkService.findBreastMilk(jsonObject);
    }
}
