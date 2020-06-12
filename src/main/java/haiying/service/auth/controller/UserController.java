package haiying.service.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import haiying.service.auth.base.AppSetting;
import haiying.service.auth.base.WxEndpoint;
import haiying.service.auth.domain.User;
import haiying.service.auth.service.UserService;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import haiying.util.CommUtil;
import haiying.util.Result;
import haiying.util.WXCore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;
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
     * {"userId":0,"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":0,"city":0,"loginType":0,"sessionKey":"tiihtNczf5v6AKRyjwEUhQ=="}
     */
    @Get("/auth")
    public User auth(String code) {
        SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
                .code2Session(code);
        assertNotNull(sessionKey);
        //通过openid findone 获取用户信息
        String openId = sessionKey.getOpenId();
        String sessionkey = sessionKey.getSessionKey();
        User user = UserService.findByOpenId(openId);
        if(Objects.isNull(user)){
            //如果没有,注册,并返回一个注册标识给前端
            //UserService.saveUserInfo(
                    user = new User()
                            .setOpenId(openId)
                            .setSessionKey(sessionkey)
                            .setLoginType(0);
           // );
           // user.setLoginType(0);
            return user;
        }
//        //如果有则返回全部
//        user.setLoginType(1);
//        User users = UserService.updateUserInfo(user);
        return user;
    }
    /**
     * @api {Put} /user/updateUserInfo updateUserInfo
     * @apiGroup 档案信息
     * @apiDescription 我的-设置妈妈信息
     * @apiParam {Json} openId(必传) openId
     * @apiParam {Json} nickName(必传) 微信用户姓名
     * @apiParam {Json} phone(必传) 手机号
     * @apiParam {Json} birthday(必传) 生日
     * @apiParam {Json} tenantId(必传) 租户id
     * @apiParam {Json} birthday(必传) 生日
     * @apiParam {Json} tenantName(必传) 租户名称
     * @apiParam {Json} occupation 职业
     * @apiParam {Json} menstruation 末次月经
     * @apiParam {Json} height 身高(cm)
     * @apiParamExample {json} 传参示例
     * {"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","nickName":"王维","phone":"13210698888","menstruation":"2019年12月21日","birthday":"1990年12月21日","height":"170","tenantId":"121","tenantName":"1223"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","nickName":"王维","gender":0,"loginType":0,"birthday":"1990年12月21日","phone":"13210698888","height":"170","menstruation":"2019年12月21日"},"success":true}
     */
    /**
     * @api {Put} /user/updateUserInfo updateUserInfo
     * @apiGroup 用户权限
     * @apiParam {Json} User 微信用户信息
     * @apiParamExample {json} 传参示例
     * {"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":1,"city":"济南","nickName":"王维","avatarUrl":"dasdsa","country":"中国","province":"山东省"}
     * @apiSuccessExample  {json} 返回值示例
     * {"status":0,"msg":"ok","result":{"openId":"ojP-tvwqOu-kwE4qxKqhFJSC3KOw","gender":1,"country":"中国","province":"山东省","city":"济南","avatarUrl":"dasdsa","loginType":0},"success":true}
     */
    @Put("/updateUserInfo")
    public Result<User> updateUserInfo(User user) {
        return Result.ok(UserService.updateUserInfo(user));
    }


    @Post("/saveUser")
    public Result<User> saveUser( User user) {
        return Result.ok(UserService.saveUser(user));
    }

    /**
     * @api {Get} /user/findOne/{openId} findOne
     * @apiGroup 档案信息
     * @apiDescription 首页通过guid获取妈妈档案信息
     * @apiParam {String} openId 档案号
     * @apiSuccessExample  {json} 返回值示例
     * {"address":"","ageDetail":null,"avatar":"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg","birthHeight":"20","birthWeight":"10","birthday":null,"cardId":"20200521000001","createTime":null,"guid":"TQDV26PDGS15VAV58JA68OU5VX86GG86","idnumber":"","name":"杜甫","onlyChild":"","openId":"","pregnancySecond":"1","pregnantWeek":"39周+1天","sex":"1","status":"","unionId":"","yieldSecond":"1"}
     */
    @Get("/findOne/{openId}")
    public String findOne( String openId) {
        JSONObject one = this.UserService.findOne(openId);
        return  JSONObject.toJSONString(one, SerializerFeature.WriteNullStringAsEmpty);
    }

//    @Get("/aaa")
//    public User aaa(String code) {
//        User user = UserService.findByOpenId(code);
//        if(Objects.isNull(user)){
//            UserService.saveUserInfo(
//                    user = new User()
//                            .setUserId(CommUtil.getGuid())
//                            .setOpenId(code)
//                            .setLoginType(0)
//            );
//            return user;
//        }
//        //如果有则返回全部
//        user.setLoginType(1);
//        User users = UserService.updateUserInfo(user);
//        return users;
//    }

    /**
     * @api {Get} /user/hospital/select select
     * @apiGroup 平台
     * @apiDescription 医院查询接口(取tenantName,tenantId)
     * @apiSuccessExample  {json} 返回值示例
     * {"success":true,"resultData":[{"id":"1183574326192406530","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2019-10-14 02:44:44","updateUser":"1123598821738675201","updateTime":"2019-10-14 02:44:44","status":1,"isDeleted":0,"tenantId":"884605","tenantName":"北大人民医院","domain":null,"backgroundUrl":null,"linkman":"Tom","contactNumber":"123456789","address":"北大人民医院","accountNumber":null,"expireTime":null},{"id":"1187616183406903298","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2019-10-25 06:25:38","updateUser":"1123598821738675201","updateTime":"2019-10-25 06:25:38","status":1,"isDeleted":0,"tenantId":"123807","tenantName":"儿童营养","domain":null,"backgroundUrl":null,"linkman":"儿童","contactNumber":"","address":"","accountNumber":null,"expireTime":null},{"id":"1188654792096415746","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2019-10-28 03:12:41","updateUser":"1123598821738675201","updateTime":"2019-10-28 03:12:41","status":1,"isDeleted":0,"tenantId":"081681","tenantName":"泰安高铁医院","domain":null,"backgroundUrl":null,"linkman":"admin","contactNumber":"22211442","address":"","accountNumber":null,"expireTime":null},{"id":"1188694440776572929","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2019-10-28 05:50:14","updateUser":"1123598821738675201","updateTime":"2019-10-28 05:50:14","status":1,"isDeleted":0,"tenantId":"948459","tenantName":"孕期营养","domain":null,"backgroundUrl":null,"linkman":"admin","contactNumber":"123456","address":"","accountNumber":null,"expireTime":null}]}
     */
    @Get("/hospital/select")
    public JSONObject select() {
         return this.UserService.selectGet();
    }


    /**
     * @api {Get} /user/findAreaList findAreaList
     * @apiGroup 平台
     * @apiDescription 地区查询接口
     * @apiSuccessExample  {json} 返回值示例
     * {
     * 	"resultData": [{
     * 		"areaCode": "11",
     * 		"areaName": "北京",
     * 		"level": 1,
     * 		"fullName": "北京",
     * 		"originalCode": "110000",
     * 		"childLevel": 2,
     * 		"nodeId": "11",
     * 		"isLeaf": false,
     * 		"parentId": "0"
     *        }],
     * 	"success": true,
     * 	"message": "操作成功"
     * }
     */
    @Get("/findAreaList")
    public JSONObject findAreaList() {
        return this.UserService.findAreaList();
    }

    @Post("/updateUser")
    public void updateUser(JSONObject json) {
         this.UserService.updateUser(json);
    }


    /**
     * @api {post} /user/getPhoneNumber getPhoneNumber
     * @apiGroup 用户权限
     * @apiDescription 用户权限 手机号解密
     * @apiParam {json} encryptedData 加密数据
     * @apiParam {json} sessionKey sessionKey
     * @apiParam {json} iv 初始向量
     * @apiParamExample {json} 传参示例
     * {"encryptedData":"CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC","sessionKey":"tiihtNczf5v6AKRyjwEUhQ==","iv":"r7BXXKkLb8qrSNn05n0qiA=="}
     */
    @Post("/getPhoneNumber")
    public String getPhoneNumber(JSONObject jsonObject) {
        String appId = WxEndpoint.get("url.token.appId");
        String encryptedData = jsonObject.getString("encryptedData");
        String sessionKey = jsonObject.getString("sessionKey");
        String iv = jsonObject.getString("iv");
        String decrypt = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
        return decrypt;
    }

}
