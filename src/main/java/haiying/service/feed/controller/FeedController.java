package haiying.service.feed.controller;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.service.FeedService;
import haiying.util.Result;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
@Controller("/feed")
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
     * @apiParam {json}typeName 类型名称 母乳亲喂  瓶装母乳  配方奶  辅食  换尿布  睡眠
     * @apiParam {json}createTime 开始时间
     * @apiParam {json}endTime 结束时间
     * @apiParam {json}duration 哺乳时长
     * @apiParam {json}nurseContent 喂奶量
     * @apiParam {json}foodName 辅食名称
     * @apiParam {json}foodDescribe 辅食描述
     * @apiParam {json}foodPhoto 辅食照片
     * @apiParam {json}selectType 换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便
     * @apiParam {json}selectTypeName 换尿布选择类型
     * @apiParam {json}urineShape 嘘嘘形状
     * @apiParam {json}shitShape 便便形状
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","type":"5","typeName":"换尿布","createTime":"2020-04-23 16:15:06","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"}
     */
    @Post("/saveFeed")
    public Result<Feed> saveFeed(@Body @Valid Feed feed) {
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
     * @apiSuccess {json} mrsecond 母乳次数
     * @apiSuccess {json} mrTotal 母乳分钟
     * @apiSuccess {json} pzmrsecond 瓶装母乳次数
     * @apiSuccess {json} pzmrsuckle 瓶装母乳含奶量
     * @apiSuccess {json} pfnsecond 配方奶次数
     * @apiSuccess {json} pfnsuckle 配方奶含奶量
     * @apiSuccess {json} fssecond 辅食次数
     * @apiSuccess {json} nbsecond 换尿布次数
     * @apiSuccess {json} smsecond 睡眠次数
     * @apiSuccess {json} xxsecond 嘘嘘次数
     * @apiSuccess {json} bbsecond 便便次数
     * @apiSuccess {json} xbsecond 嘘嘘+便便次数
     * @apiSuccess {json} spacing  睡眠时间
     * @apiSuccessExample  {json} 返回值示例
     * {"total":[{"id":"1254649324197404672","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:17:05","duration":0,"nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1255298935547256832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:27:05","duration":0,"nurseContent":0,"selectType":"1","selectTypeName":"xuxu","urineShape":"正常","shitShape":"绿色"},{"id":"1257965654136872960","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","endTime":"2020-04-23 16:37:05","duration":0,"nurseContent":0,"selectType":"2","selectTypeName":"便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591084684140544","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:07:05","endTime":"2020-04-23 16:47:05","duration":0,"nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591268839251968","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:06:08","endTime":"2020-04-23 16:07:05","duration":2,"nurseContent":0},{"id":"1254590934670663680","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"睡眠","type":"6","createTime":"2020-04-23 16:06:05","endTime":"2020-04-23 16:35:05","duration":0,"nurseContent":0},{"id":"1254642108090310656","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:05:08","endTime":"2020-04-23 16:07:05","duration":3,"nurseContent":0},{"id":"1254591212543303680","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"配方奶","type":"3","createTime":"2020-04-23 16:05:06","duration":0,"nurseContent":120},{"id":"1253607860331896832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"瓶装母乳","type":"2","createTime":"2020-04-23 16:05:05","duration":0,"nurseContent":100}],"census":{"xxsecond":"1","pzmrsecond":"1","pzmrsuckle":"100","spacing":"1小时13分钟","xbsecond":"2","bbsecond":"1","nbsecond":"4","smsecond":"1","mrsecond":"2","pfnsuckle":"120","mrTotal":"5","pfnsecond":"1"}}
     */
    @Post("/findFeed")
    public JSONObject findFeed(JSONObject jsonObject) {
         return  this.feedService.findFeed(jsonObject);
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
     * @apiDescription 首页-动态 获取宝宝喂养记录动态历史
     * @apiParam {json} guid 档案号   pageSize:每页固定数  pageNum:开始
     * @apiParamExample {json} 传参示例
     * {"guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","pageSize":5,"pageNum":1}
     * @apiSuccessExample  {json} 返回值示例
     * [{"id":"1254591150861869056","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"辅食","type":"4","createTime":"2020-04-24 16:05:15","nurseContent":0,"foodName":"米粉","foodDescribe":"少量"},{"id":"1254649324197404672","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1255298935547256832","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:15:06","nurseContent":0,"selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591084684140544","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"换尿布","type":"5","createTime":"2020-04-23 16:07:05","nurseContent":0,"selectType":"3","selectTypeName":"xuxu+便便","urineShape":"正常","shitShape":"绿色"},{"id":"1254591268839251968","guid":"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8","typeName":"母乳亲喂","type":"1","lactation":"左侧哺乳","createTime":"2020-04-23 16:06:08","endTime":"2020-04-23 16:07:05","duration":"2","nurseContent":0}]
     */
    @Post("/findFeedPagination")
    public List<Feed>  findFeedPagination(JSONObject jsonObject) {
        return  this.feedService.findFeedPagination(jsonObject);
    }
}
