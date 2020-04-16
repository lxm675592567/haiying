package haiying.service.physical.service;

import haiying.service.physical.domain.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(@NotNull Long id);

}
