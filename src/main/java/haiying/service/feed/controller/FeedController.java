package haiying.service.feed.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.service.FeedService;
import haiying.util.Result;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@Validated
@Controller("/haoyunyi/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    /**
     * @api {Post} /feed/saveFeed saveFeed
     * @apiGroup 喂养记录
     * @apiDescription 保存宝宝实时喂养记录信息
     * @apiParam {json} feed 喂养记录实体类
     * @apiParam {json}guid(必传) 档案号
     * @apiParam {json}type(必传) 类型  1母乳亲喂  2瓶装母乳  3配方奶  4辅食  5换尿布  6睡眠
     * @apiParam {json}typeName(必传) 类型名称 母乳亲喂  瓶装母乳  配方奶  辅食  换尿布  睡眠
     * @apiParam {json}createTime(必传) 开始时间
     * @apiParam {json}endTime 结束时间
     * @apiParam {json}duration 哺乳时长
     * @apiParam {json}nurseContent 喂奶量
     * @apiParam {json}foodName 辅食名称
     * @apiParam {json}foodDescribe 辅食描述
     * @apiParam {json}foodPhoto 辅食照片
     * @apiParam {json}selectType 换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便
     * @apiParam {json}selectTypeName 换尿布选择类型
     * @apiParam {json}urineShape 嘘嘘性状
     * @apiParam {json}shitShape 便便性状
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"1","typeName":"母乳亲喂","createTime":"2020-04-23 16:05","endTime":"2020-04-23 16:07","lactation":"左侧哺乳","duration":"2"}
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"2","typeName":"瓶装母乳","createTime":"2020-04-23 16:05","nurseContent":"120"}
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"3","typeName":"配方奶","createTime":"2020-04-23 16:05","nurseContent":"120"}
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"4","typeName":"辅食","createTime":"2020-04-23 16:05","foodName":"米粉","foodDescribe":"少量","foodPhoto":""}
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"5","typeName":"换尿布","diaperTime":"16:29","selectTypeName":"嘘嘘+便便","selectType":"3","urineShape":"正常","shitShape":"绿色"}
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"6","typeName":"睡眠","createTime":"2020-04-23 16:05","endTime":"2020-04-23 16:35"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"id":"1260848390719692800","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15","duration":0,"nurseContent":0,"selectTypeName":"嘘嘘+便便","urineShape":"正常","shitShape":"绿色"},"success":true}
     */
    @Post("/saveFeed")
    public Result<Feed> saveFeed(@Body @Valid Feed feed) throws ParseException {
        return Result.ok(this.feedService.saveFeed(feed));
    }

    /**
     * @api {Post} /feed/findFeed findFeed
     * @apiGroup 喂养记录
     * @apiDescription 首页-喂养记录-统计 获取宝宝实时喂养记录信息
     * @apiParam {json} guid 档案号
     * @apiParam {json} createTime查询的时间
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","createTime":"2020-04-23"}
     * @apiSuccess {census} typeName 类型名称
     * @apiSuccess {census} type 1母乳亲喂  2瓶装母乳  3配方奶  4辅食  5换尿布  6睡眠
     * @apiSuccess {census} total 次数
     * @apiSuccess {census} record 统计记录结果
     * @apiSuccess {census} selectTypeName 换尿布选择类型
     * @apiSuccess {census} selectType 换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便
     * @apiSuccess {census} selectCount 换尿布次数
     * @apiSuccess {total} urineShape 换尿布次数
     * @apiSuccess {total} createTime 开始时间(单次时间)
     * @apiSuccess {total} endTime 结束时间
     * @apiSuccess {total} urineshape 嘘嘘性状
     * @apiSuccess {total} shitShape 便便性状
     * @apiSuccess {total} nurseContent 喂奶量
     * @apiSuccess {total} lactation 哺乳行为
     * @apiSuccess {total} spacing  睡眠时间
     * @apiSuccess {total} time     小时分钟
     * @apiSuccess {total} state   最近记录时间
     * @apiSuccessExample  {json} 返回值示例
     * {"total":[{"urineShape":"正常","selectTypeName":"嘘嘘+便便","shapeType":"3","createTime":"2020-04-23 16:15","typeName":"换尿布","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","selectType":"3","endTime":"2020-04-23 16:17:05","shitShape":"绿色","time":"16:15","state":"嘘嘘+便便:正常/绿色","type":"5"},{"urineShape":"正常","selectTypeName":"嘘嘘","createTime":"2020-04-23 16:15:06","typeName":"换尿布","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","selectType":"1","endTime":"2020-04-23 16:27:05","shitShape":"绿色","time":"16:15","state":"嘘嘘:正常","type":"5","nurseContent":0},{"urineShape":"正常","selectTypeName":"便便","createTime":"2020-04-23 16:15:06","typeName":"换尿布","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","selectType":"2","endTime":"2020-04-23 16:37:05","shitShape":"绿色","time":"16:15","state":"便便:绿色","type":"5","nurseContent":0},{"urineShape":"正常","selectTypeName":"嘘嘘+便便","shapeType":"3","createTime":"2020-04-23 16:07:05","typeName":"换尿布","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","selectType":"3","endTime":"2020-04-23 16:47:05","shitShape":"绿色","time":"16:07","state":"嘘嘘+便便:正常/绿色","type":"5"},{"duration":2,"createTime":"2020-04-23 16:06:08","lactation":"左侧哺乳","typeName":"母乳亲喂","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","endTime":"2020-04-23 16:07:05","time":"16:06","state":"母乳亲喂:2分钟","type":"1"},{"createTime":"2020-04-23 16:06:05","typeName":"睡眠","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","endTime":"2020-04-23 16:35:05","time":"16:06","state":"睡眠:0小时29分钟","type":"6"},{"foodName":"米粉","createTime":"2020-04-23 16:05:15","foodDescribe":"少量","typeName":"辅食","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","time":"16:05","state":"辅食:米粉","type":"4"},{"duration":3,"createTime":"2020-04-23 16:05:08","lactation":"左侧哺乳","typeName":"母乳亲喂","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","endTime":"2020-04-23 16:07:05","time":"16:05","state":"母乳亲喂:3分钟","type":"1"},{"createTime":"2020-04-23 16:05:06","typeName":"配方奶","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","time":"16:05","state":"配方奶:120ml","type":"3","nurseContent":120},{"createTime":"2020-04-23 16:05:05","typeName":"瓶装母乳","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","time":"16:05","state":"瓶装母乳:100ml","type":"2","nurseContent":100}],"census":[{"total":2,"record":"5分钟","typeName":"母乳亲喂","type":"1"},{"total":1,"record":"100ml","typeName":"瓶装母乳","type":"2"},{"total":1,"record":"120ml","typeName":"配方奶","type":"3"},{"total":1,"typeName":"辅食","type":"4"},{"total":4,"record":[{"selectTypeName":"嘘嘘","selectCount":1,"selectType":"1"},{"selectTypeName":"便便","selectCount":1,"selectType":"2"},{"selectTypeName":"嘘嘘+便便","selectCount":2,"selectType":"3"}],"typeName":"换尿布","type":"5"},{"total":1,"spacing":"1小时13分钟","typeName":"睡眠","type":"6"}]}
     */
    @Post("/findFeed")
    public String findFeed(JSONObject jsonObject) {
        JSONObject feed = this.feedService.findFeed(jsonObject);
        return  JSONObject.toJSONString(feed, SerializerFeature.WriteMapNullValue);
    }


    /**
     * @api {Post} /feed/getDateCurve getDateCurve
     * @apiGroup 喂养记录
     * @apiDescription 首页-喂养记录-统计(图标) 获取宝宝喂养记录曲线图
     * @apiParam {json} guid 档案号
     * @apiParam {json} createTime查询的时间
     * @apiParam {json} feedtype 喂奶:1 辅食:2 换尿布:3 睡眠:4
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","createTime":"2020-04","feedtype":"1"}
     * @apiSuccess {json} nurseTotal 喂奶量
     * @apiSuccess {json} count 次数
     * @apiSuccess {json} createTime 时间
     * @apiSuccess {json} spacing 睡眠时长
     * @apiSuccessExample  {json} 返回值示例
     * [{"nurseTotal":220,"createTime":"2020-04-23 ","count":4},{"nurseTotal":0,"createTime":"2020-04-24 ","count":1}]
     */
    @Post("/getDateCurve")
    public List<JSONObject>  getDateCurve(JSONObject jsonObject) {
        return  this.feedService.getDateCurve(jsonObject);
    }

    /**
     * @api {Post} /feed/findFeedPagination findFeedPagination
     * @apiGroup 首页
     * @apiDescription 首页-动态 获取宝宝喂养记录动态历史(暂时未用)
     * @apiParam {json} guid 档案号
     * @apiParam {json} pageSize:每页固定数
     * @apiParam {json} pageNum:开始
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","pageSize":5,"pageNum":1}
     * @apiSuccessExample  {json} 返回值示例
     * [{"id":"1254591150861869056","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"辅食","type":"4","createTime":"2020-04-24 16:05:15","nurseContent":0,"foodName":"米粉","foodDescribe":"少量"},{"id":"1254649324197404672","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1255298935547256832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591084684140544","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:07:05","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591268839251968","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:06:08","endTime":"2020-04-23 16:07:05","duration":"2","nurseContent":0}]
     */
    @Post("/findFeedPagination")
    public List<JSONObject>  findFeedPagination(JSONObject jsonObject) {
        return  this.feedService.findFeedPagination(jsonObject);
    }


}
