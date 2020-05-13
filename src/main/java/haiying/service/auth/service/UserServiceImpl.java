package haiying.service.auth.service;

import haiying.service.auth.domain.User;
import haiying.service.auth.mapper.UserMapper;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
@Validated
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(@NotNull Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByOpenId(@NotNull String openId) {
        return userMapper.findByOpenId(openId);
    }

    @Override
    public User updateUserInfo(@NotNull User user) {
        userMapper.updateUserInfo(user);
        return user;
    }

    @Override
    public void saveUserInfo(@NotNull User user) {
        userMapper.saveUserInfo(user);
    }

    @Override
    public User findOne(String guid) {
        return userMapper.findOne(guid);
    }
}
