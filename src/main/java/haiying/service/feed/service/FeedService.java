package haiying.service.feed.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.feed.domain.Feed;

import java.text.ParseException;
import java.util.List;

public interface FeedService {

    Feed saveFeed(Feed feed) throws ParseException;

    JSONObject findFeed(JSONObject jsonObject);

    List<JSONObject> getDateCurve(JSONObject jsonObject);

    List<JSONObject> findFeedPagination(JSONObject jsonObject);
}
