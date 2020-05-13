package haiying.service.feed.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;

import java.util.List;

public interface FeedService {

    Feed saveFeed(Feed feed);

    JSONObject findFeed(JSONObject jsonObject);

    List<JSONObject> getDateCurve(JSONObject jsonObject);

    List<Feed> findFeedPagination(JSONObject jsonObject);
}
