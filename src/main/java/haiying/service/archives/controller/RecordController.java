package haiying.service.archives.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import haiying.service.archives.domain.Record;
import haiying.service.archives.service.RecordService;
import haiying.service.auth.domain.User;
import haiying.util.Result;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import java.net.URI;
import java.util.List;



@Validated
@Controller("/haoyun1/archives/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController( RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * @api {Get} /archives/record/findOne/{guid} findOne
     * @apiGroup 首页
     * @apiDescription 首页通过guid获取宝宝档案信息
     * @apiParam {String} guid 档案号
     * @apiSuccessExample  {json} 返回值示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","openId":"1"}
     */
    @Get("/findOne/{guid}")
    public String findOne( String guid) {
        JSONObject one = this.recordService.findOne(guid);
        return  JSONObject.toJSONString(one, SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * @api {Post} /archives/record/saveUpdateRecord saveUpdateRecord
     * @apiGroup 档案信息
     * @apiDescription 我的-修改宝宝信息
     * @apiParam {json} guid(必传) guid
     * @apiParam {json} birthday(必传) 生日
     * @apiParam {json} openId(必传) openId
     * @apiParam {json} name(必传) 姓名
     * @apiParam {json} sex(必传)  性别(1男2女)
     * @apiParam {json} birthHeight 出生身高(cm)
     * @apiParam {json} birthWeight 出生体重(kg)
     * @apiParam {json} pregnancySecond 第几孕
     * @apiParam {json} yieldSecond 第几产
     * @apiParam {json} pregnantWeek 出生孕周
     * @apiParam {json} avatar 头像
     * @apiParamExample {json} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG84","name":"李小白","sex":"1","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1","avatar":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg","pregnantWeek":"39周+1天"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG84","name":"李小白","sex":"1","pregnantWeek":"39周+1天","avatar":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1"},"success":true}
     */
    @Post("/saveUpdateRecord")
    public Result<Record> saveUpdateRecord(@Body Record record) {
        return Result.ok(recordService.saveUpdateRecord(record));
    }

    /**
     * @api {Post} /archives/record/getGrowUpArchives getGrowUpArchives
     * @apiGroup 成长档案
     * @apiDescription 成长档案-获取宝宝基本
     * @apiParam {json} guid guid
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8"}
     * @apiSuccessExample  {json} 返回值示例
     * {"feed":[{"spacing":"8小时22分钟","typeName":"瓶装母乳"},{"spacing":"4小时20分钟","typeName":"换尿布"},{"spacing":"1小时21分钟","typeName":"睡眠"}],"record":{"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw"},"heightAndWeight":{"weight":43.0,"bmi":43.0,"height":100.0}}
     */
    @Post("/getGrowUpArchives")
    public JSONObject getGrowUpArchives(JSONObject jsonObject) {
        return recordService.getGrowUpArchives(jsonObject);
    }


    /**
     * @api {Get} /archives/record/getBabyArchives getBabyArchives
     * @apiGroup 档案信息
     * @apiDescription 我的-已登入 获取所有宝宝基本信息
     * @apiParam {String} openId 微信openId
     * @apiParamExample {String} 传参示例
     * {"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG81"}
     * @apiSuccess {json} record 宝宝基础信息
     * @apiSuccessExample  {json} 返回值示例
     * [{"record":{"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","cardId":"20190515000003","name":"刘静","sex":"女","birthday":"2019-04-17","a3geDetail":{"year":1,"month":0,"day":27,"monthAge":12.9,"monthAgeInt":12,"ageDetail":"1岁0月27天"},"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw"},"name":"刘静","age":"1岁0月27天"},{"record":{"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG83","cardId":"20200506000003","name":"李大白","sex":"1","birthday":"2019-04-10","ageDetail":{"year":1,"month":1,"day":4,"monthAge":13.1,"monthAgeInt":13,"ageDetail":"1岁1月4天"},"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","avatar":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg","birthHeight":"20","birthWeight":"10","pregnancySecond":"1","yieldSecond":"1"},"name":"李大白","age":"1岁1月4天"}]
     */
    @Get("/getBabyArchives")
    public List<JSONObject> getBabyArchives(String openId) {
        return recordService.getBabyArchives(openId);
    }

    /**
     * @api {Post} /archives/record/findMessageOne findMessageOne
     * @apiGroup 首页
     * @apiDescription 首页获取宝宝信息
     * @apiParam {String} openId
     * @apiParam {String} guid (没有不传)
     * @apiParam {String} pageSize 每页数
     * @apiParam {String} pageNum  当前页
     * @apiParamExample {json} 传参示例
     * {"openId":"ob1uW5IVlh-hcEgQVoFtfCS32owY","pageSize":15,"pageNum":1,"guid":""}
     * @apiSuccess {json} record 宝宝基础信息(同findone接口)
     * @apiSuccess {json} History 宝宝历史喂养记录(同findFeedPagination接口)
     * @apiSuccessExample  {json} 返回值示例
     * {"dynamics":[{"contents":[{"typeName":"辅食","dates":"2020-05-18","type":"4","title":"辅食","nurseContent":0,"duration":0,"foodName":"水果,蔬菜","createTime":"2020-05-18 14:25","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262267200106422272","time":"14:25","desc":"辅食:水果,蔬菜"},{"foodDescribe":"222","typeName":"辅食","dates":"2020-05-18","type":"4","title":"辅食","nurseContent":0,"duration":0,"foodName":"水果","createTime":"2020-05-18 14:00","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262260987125850112","time":"14:00","desc":"辅食:水果"},{"foodDescribe":"333","typeName":"辅食","dates":"2020-05-18","type":"4","title":"辅食","nurseContent":0,"duration":0,"foodName":"米粉","createTime":"2020-05-18 14:00","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262261423513821184","time":"14:00","desc":"辅食:米粉"},{"duration":0,"createTime":"2020-05-18 13:59","typeName":"配方奶","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","dates":"2020-05-18","id":"1262260689217019904","time":"13:59","type":"3","title":"配方奶","nurseContent":30,"desc":"配方奶:30ml"},{"duration":0,"createTime":"2020-05-18 13:57","typeName":"瓶装母乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","dates":"2020-05-18","id":"1262260304700006400","time":"13:57","type":"2","title":"瓶装母乳","nurseContent":30,"desc":"瓶装母乳:30ml"},{"duration":0,"createTime":"2020-05-18 13:53","typeName":"瓶装母乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","dates":"2020-05-18","id":"1262259929653731328","time":"13:53","type":"2","title":"瓶装母乳","nurseContent":30,"desc":"瓶装母乳:30ml"},{"typeName":"母乳亲喂","dates":"2020-05-18","type":"1","title":"母乳亲喂","nurseContent":0,"duration":0,"createTime":"2020-05-18 13:52","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262259108132184064","endTime":"2020-05-18 13:52","time":"13:52","desc":"母乳亲喂:0分钟"},{"typeName":"母乳亲喂","dates":"2020-05-18","type":"1","title":"母乳亲喂","nurseContent":0,"duration":0,"createTime":"2020-05-18 13:51","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262258734893654016","endTime":"2020-05-18 13:51","time":"13:51","desc":"母乳亲喂:0分钟"},{"typeName":"母乳亲喂","dates":"2020-05-18","type":"1","title":"母乳亲喂","nurseContent":0,"duration":0,"createTime":"2020-05-18 13:33","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-05-18","id":"1262256645941518336","endTime":"2020-05-18 13:33","time":"13:33","desc":"母乳亲喂:0分钟"}],"time":"2020年05月18日"},{"contents":[{"typeName":"母乳亲喂","dates":"2020-04-24","type":"1","title":"母乳亲喂","nurseContent":0,"duration":2,"createTime":"2020-04-24 17:05","lactation":"左侧哺乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","id":"1262229370823008256","endTime":"2020-04-25 18:07","time":"17:05","desc":"母乳亲喂:2分钟"},{"typeName":"母乳亲喂","dates":"2020-04-24","type":"1","title":"母乳亲喂","nurseContent":0,"duration":2,"createTime":"2020-04-24 17:05","lactation":"左侧哺乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","id":"1262255838319894528","endTime":"2020-04-25 18:07","time":"17:05","desc":"母乳亲喂:2分钟"},{"typeName":"母乳亲喂","dates":"2020-04-24","type":"1","title":"母乳亲喂","nurseContent":0,"duration":2,"createTime":"2020-04-24 17:05","lactation":"左侧哺乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","id":"1262255891994402816","endTime":"2020-04-25 18:07","time":"17:05","desc":"母乳亲喂:2分钟"},{"typeName":"换尿布","dates":"2020-04-24","type":"5","title":"换尿布","nurseContent":0,"duration":0,"urineShape":"正常","selectTypeName":"嘘嘘+便便","createTime":"2020-04-24 16:18","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","selectType":"3","id":"1262296583928045568","shitShape":"绿色","time":"16:18","desc":"嘘嘘+便便:正常/绿色"},{"typeName":"换尿布","dates":"2020-04-24","type":"5","title":"换尿布","nurseContent":0,"duration":0,"urineShape":"正常","selectTypeName":"嘘嘘+便便","createTime":"2020-04-24 16:15","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","selectType":"3","id":"1262204426756055040","shitShape":"绿色","time":"16:15","desc":"嘘嘘+便便:正常/绿色"},{"duration":0,"createTime":"2020-04-24 16:05","typeName":"配方奶","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","days":"2020-04-24","dates":"2020-04-24","id":"1262204631140294656","time":"16:05","type":"3","title":"配方奶","nurseContent":120,"desc":"配方奶:120ml"}],"time":"2020年04月24日"}],"record":{"birthday":"2019-04-17 ","openId":"ob1uW5IVlh-hcEgQVoFtfCS32owY","cardId":"20190515000003","sex":"女","name":"刘静","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","weight":43.0,"avatar":"https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1979468705,1007738189&fm=26&gp=0.jpg","height":100.0}}
     */
    @Post("/findMessageOne")
    public JSONObject  findMessageOne(JSONObject jsonObject) {
        return  this.recordService.findMessageNew(jsonObject);
    }
    @Post("/uploadFile")
    public String uploadFile(JSONObject json) throws Exception {
        String file = json.getString("file");
        String ImgUrl = recordService.uploadFile(file);
        return ImgUrl;
    }


}
