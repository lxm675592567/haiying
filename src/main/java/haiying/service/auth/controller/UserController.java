package haiying.service.auth.controller;

import haiying.service.auth.base.AppSetting;
import haiying.service.auth.domain.User;
import haiying.service.auth.service.UserService;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import haiying.util.CommUtil;
import haiying.util.Result;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
@Validated // <1>
@Controller("/user")
public class UserController {

    private final UserService UserService;

    public UserController(UserService UserService) { // <3>
        this.UserService = UserService;
    }

    /**
     * @api {Get} /user/auth auth
     * @apiGroup 用户权限
     * @apiDescription 通过code获取openid等基本信息
     * @apiParam {String} code 微信code
     * @apiParamExample {json} 传参示例
     * {
     *  "code": "EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8"
     * }
     * @apiSuccess {json} loginType 登入状态(0注册1登入)
     * @apiSuccess {json} gender 性别(0未知1男2女)
     * @apiSuccess {json} city 城市
     * @apiSuccess {json}country 国家
     * @apiSuccess {json}province 省份
     * @apiSuccess {json}avatarUrl 用户图片头像url
     * @apiSuccess {json}createTime 创建时间
     * @apiSuccess {json}birthday  出生日期
     * @apiSuccess {json}phone  手机号
     * @apiSuccess {json}height  身高
     * @apiSuccess {json}menstruation  末次月经
     * @apiSuccessExample  {json} 返回值示例
     * {"userId":0,"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":0,"city":0,"loginType":0}
     */
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
                            .setUserId(CommUtil.getGuid())
                            .setOpenId(openId)
                            .setLoginType(1)
            );
            user.setLoginType(0);
            return user;
        }
//        //如果有则返回全部
//        user.setLoginType(1);
//        User users = UserService.updateUserInfo(user);
        return user;
    }

    /**
     * @api {Put} /user/updateUserInfo updateUserInfo
     * @apiGroup 用户权限
     * @apiParam {Json} User 用户信息
     * @apiParamExample {json} 传参示例
     * {"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":1,"city":"济南","nickName":"王维","avatarUrl":"dasdsa","country":"中国","province":"山东省"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":1,"country":"中国","province":"山东省","city":"济南","avatarUrl":"dasdsa","loginType":0},"success":true}
     */
    @Put("/updateUserInfo")
    public Result<User> updateUserInfo(User userInfo) {
        return Result.ok(UserService.updateUserInfo(userInfo));
    }


    @Get("/findOne/{guid}")
    public User findOne( String guid) {
        return this.UserService.findOne(guid);
    }

    @Get("/aaa")
    public User aaa(String code) {
        User user = UserService.findByOpenId(code);
        if(Objects.isNull(user)){
            UserService.saveUserInfo(
                    user = new User()
                            .setUserId(CommUtil.getGuid())
                            .setOpenId(code)
                            .setLoginType(0)
            );
            return user;
        }
        //如果有则返回全部
        user.setLoginType(1);
        User users = UserService.updateUserInfo(user);
        return users;
    }




}
