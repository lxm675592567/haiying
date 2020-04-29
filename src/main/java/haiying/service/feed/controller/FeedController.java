package haiying.service.feed.controller;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;
import haiying.service.feed.service.FeedService;
import haiying.util.GuidUtil;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.List;
/**
 * @api {Get} /user/get getUserInfo
 * @apiGroup User
 *
 * @apiParam {String} name 文章名
 * @apiParamExample {json} Request-Example
 * {
 *  "userName": "Eve"
 * }
 *
 * @apiSuccessExample  {json} Response-Example
 * {
 *   "userName": "Eve",
 *   "createTime": "1568901681"
 *   "updateTime": "1568901681"
 * }
 */
@Validated
@Controller("/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @Post("/saveFeed")
    public void saveFeed(@Body @Valid Feed feed) {
        this.feedService.saveFeed(feed);
    }
    //查询 根据不同
    @Post("/findFeed")
    public JSONObject findFeed(JSONObject jsonObject) {
         return  this.feedService.findFeed(jsonObject);
    }

    //查询 根据不同
    @Post("/getDateCurve")
    public List<JSONObject>  getDateCurve(JSONObject jsonObject) {
        return  this.feedService.getDateCurve(jsonObject);
    }
    //findFeedPagination

    @Post("/findFeedPagination")
    public List<Feed>  findFeedPagination(JSONObject jsonObject) {
        return  this.feedService.findFeedPagination(jsonObject);
    }
}
