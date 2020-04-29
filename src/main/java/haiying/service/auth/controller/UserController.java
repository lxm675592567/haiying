package haiying.service.auth.controller;

import haiying.service.auth.base.AppSetting;
import haiying.service.auth.domain.User;
import haiying.service.auth.service.UserService;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;

@Validated // <1>
@Controller("/user")
public class UserController {

    @Inject
    private final UserService UserService;

    public UserController(UserService UserService) { // <3>
        this.UserService = UserService;
    }

//    @Get("/show/{id}") // <4>
//    public User show(Long id) {
//        return UserService
//                .findById(id)
//                .orElse(null); // <5>
//    }

    @Get("/auth")
    public User auth(String code) {
        SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
                .code2Session(code);
        assertNotNull(sessionKey);
        //通过openid findone 获取用户信息
        String openId = sessionKey.getOpenId();
        User user = UserService.findByOpenId(openId);
        if(Objects.isNull(user)){
            //如果没有,注册,并返回一个注册标识给前端
            UserService.saveUserInfo(
                    user = new User()
                            .setOpenId(openId)
                            .setLoginType(0)
            );
            return user;
        }
        //如果有则返回全部
        user.setLoginType(1);
        return user;
    }

    @Post("/updateUserInfo")
    public void updateUserInfo(User userInfo) {
        UserService.updateUserInfo(userInfo);
    }

}
