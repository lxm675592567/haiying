package haiying.service.archives.controller;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.Record;
import haiying.service.archives.service.RecordService;
import haiying.util.Result;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import java.net.URI;
import java.util.List;



@Validated
@Controller("/archives/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController( RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * @api {Get} /archives/record/findOne/{openId} findOne
     * @apiGroup 首页
     * @apiDescription 首页通过guid获取宝宝档案信息 (暂时不用)
     * @apiParam {String} guid 档案号
     * @apiSuccessExample  {json} 返回值示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","openId":"1"}
     */
    @Get("/findOne/{guid}")
    public Record findOne( String guid) {
        return this.recordService.findOne(guid) ;
    }


    /**
     * @api {Put} /archives/record/updateRecord updateRecord
     * @apiGroup 档案信息
     * @apiDescription 我的-修改宝宝信息
     * @apiParam {json} record 宝宝信息实体类
     * @apiParamExample {json} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG83","name":"李白","sex":"1","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1","url":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG83","name":"李太白","sex":"1","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1"},"success":true}
     */
    @Put("/updateRecord")
    public Result<Record> updateRecord(@Body Record record) {
        return Result.ok(recordService.updateRecord(record));
    }


    /**
     * @api {Post} /archives/record/saveRecord saveRecord
     * @apiGroup 档案信息
     * @apiDescription 我的-保存宝宝信息
     * @apiParam {json} record 宝宝信息实体类
     * @apiParam{String} guid 前端生成32位随机数
     * @apiParamExample {json} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG83","name":"李白","sex":"1","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1","url":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg"}
     * @apiSuccess {json} cardId 卡号
     * @apiSuccess {json} name 姓名
     * @apiSuccess {json} sex 性别(0未知1男2女)
     * @apiSuccess {json} birthHeight 出生身长
     * @apiSuccess {json} birthWeight 出生体重
     * @apiSuccess {json} pregnancySecond 第几孕
     * @apiSuccess {json} yieldSecond 第几产
     * @apiSuccess {json} pregnantWeek 出生孕周
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG83","cardId":"20200506000008","name":"李白","sex":"1","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1"},"success":true}
     */
    @Post("/saveRecord")
    public Result<Record> saveRecord(@Body Record record) {
        return Result.ok(recordService.saveRecord(record));
    }

    /**
     * @api {Post} /archives/record/getGrowUpArchives getGrowUpArchives
     * @apiGroup 成长档案
     * @apiDescription 成长档案-获取宝宝基本
     * @apiParam {json} jsonObject
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","pageSize":5,"pageNum":1}
     * @apiSuccessExample  {json} 返回值示例
     * {"feed":[{"id":"1254591150861869056","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"辅食","type":"4","createTime":"2020-04-24 16:05:15","nurseContent":0,"foodName":"米粉","foodDescribe":"少量"},{"id":"1254649324197404672","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1255298935547256832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591084684140544","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:07:05","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591268839251968","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:06:08","endTime":"2020-04-23 16:07:05","duration":"2","nurseContent":0}],"heightAndWeight":{"weight":33.0,"bmi":27.3,"height":110.0}}
     */
    @Post("/getGrowUpArchives")
    public JSONObject getGrowUpArchives(JSONObject jsonObject) {
        return recordService.getGrowUpArchives(jsonObject);
    }


    /**
     * @api {Post} /archives/record/getBabyArchives getBabyArchives
     * @apiGroup 档案信息
     * @apiDescription 我的-已登入 获取所有宝宝基本信息
     * @apiParam {String} openId 微信openId
     * @apiParamExample {String} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81"}
     * @apiSuccess {json} record 宝宝基础信息
     * @apiSuccessExample  {json} 返回值示例
     * [{"record":{"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","ageDetail":{"year":1,"month":0,"day":19,"monthAgeInt":12,"ageDetail":"1岁0月19天","monthAge":12.6},"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw"},"name":"刘静","age":"1岁0月19天"}]
     */
    @Post("/getBabyArchives")
    public List<JSONObject> getBabyArchives(String openId) {
        return recordService.getBabyArchives(openId);
    }


    /**
     * @api {Post} /archives/record/findMessageOne findMessageOne
     * @apiGroup 首页
     * @apiDescription 首页通过openId获取宝宝信息
     * @apiParam {String} openId
     * @apiParam {String} guid (没有不传)
     * @apiParam {String} pageSize 每页数
     * @apiParam {String} pageNum  当前页
     * @apiParamExample {json} 传参示例
     * {"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","pageSize":5,"pageNum":1}
     * @apiSuccess {json} record 宝宝基础信息(同findone接口)
     * @apiSuccess {json} History 宝宝历史喂养记录(同findFeedPagination接口)
     * @apiSuccessExample  {json} 返回值示例
     *{"dynamics":[{"contents":[{"id":"1254591150861869056","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"辅食","type":"4","createTime":"2020-04-24 16:05:15","duration":0,"nurseContent":0,"foodName":"米粉","foodDescribe":"少量"}],"time":"2020年04月24日"},{"contents":[{"id":"1254649324197404672","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:17:05","duration":0,"nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1255298935547256832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:27:05","duration":0,"nurseContent":0,"selectType":"1","selectTypeName":"xuxu","urineShape":"正常","shitShape":"绿色"},{"id":"1257965654136872960","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:37:05","duration":0,"nurseContent":0,"selectType":"2","selectTypeName":"便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591084684140544","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:07:05","endTime":"2020-04-23 16:47:05","duration":0,"nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591268839251968","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:06:08","endTime":"2020-04-23 16:07:05","duration":2,"nurseContent":0}],"time":"2020年04月23日"}],"record":{"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw"}}
     */
    @Post("/findMessageOne")
    public JSONObject  findMessageOne(JSONObject jsonObject) {
        return  this.recordService.findMessageOne(jsonObject);
    }
}
