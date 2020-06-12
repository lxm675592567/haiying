package haiying.service.auth.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.auth.domain.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserService {

    User findById(@NotNull Long id);

    User findByOpenId(@NotNull String openId);

    User updateUserInfo(@NotNull User user);

    void saveUserInfo(@NotNull User user);

    JSONObject findOne(String openId);

    JSONObject selectGet();

    JSONObject findAreaList();

    void updateUser(JSONObject json);

    User saveUser(User user);
}
