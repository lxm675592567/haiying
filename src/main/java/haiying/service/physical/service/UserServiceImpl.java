package haiying.service.physical.service;

import haiying.service.physical.domain.User;
import haiying.service.physical.mapper.UserMapper;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.util.Optional;
@Singleton // <1>
@Validated
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(@NotNull Long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }
}
