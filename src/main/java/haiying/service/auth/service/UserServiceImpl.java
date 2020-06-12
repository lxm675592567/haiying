package haiying.service.auth.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.auth.base.WxEndpoint;
import haiying.service.auth.domain.User;
import haiying.service.auth.mapper.UserMapper;
import haiying.util.CommUtil;
import haiying.util.DateUtil;
import haiying.util.HttpclientUtil;
import haiying.util.StringUtil;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
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
        String openId = user.getOpenId();
        String tenantId = user.getTenantId();
        String phone = user.getPhone();
//        if (StringUtil.stringIsNotNull(tenantId)){
//            //根据openId查询,然后获取
//            User byUser = userMapper.findByOpenId(openId);
//            String url = HttpclientUtil.get("httpclient.page.get")+"tenantId="+tenantId+"&phoneNumber="+phone;
//            String httpGet = HttpclientUtil.httpGet(url);
//            JSONObject result = JSONObject.parseObject(httpGet);
//
////            System.out.println("jsonArray="+jsonArray);
//            if(Objects.isNull(result)){
//                //保存到平台
//                DateUtil.Age age = DateUtil.getAge(byUser.getBirthday());
//                JSONObject jsonParam = new JSONObject();
//                jsonParam.put("wxOpenId",openId);jsonParam.put("tenantId",tenantId);jsonParam.put("id",byUser.getUserId());jsonParam.put("name",byUser.getNickName());jsonParam.put("occupation",byUser.getOccupation());jsonParam.put("phoneNumber",phone);jsonParam.put("wxUnionId",byUser.getUnionId());jsonParam.put("age",age.getYear());
//                System.out.println("jsonParam="+jsonParam);
//                String postUrl = HttpclientUtil.get("httpclient.motherInfo.post");
//                HttpclientUtil.httpPost(postUrl, jsonParam);
//            }else {
//                JSONArray jsonArray = result.getJSONObject("resultData").getJSONArray("list");
//                JSONObject motherInfo = (JSONObject) jsonArray.get(0);
//                user.setOccupation(motherInfo.getString("occupation"))
//                        .setUnionId(motherInfo.getString("wxUnionId"))
//                        .setUserId(motherInfo.getString("id"))
//                        .setNickName(user.getNickName())
//                        .setBirthday(user.getBirthday())
//                        .setTenantId(tenantId)
//                        .setTenantName(user.getTenantName())
//                        .setPhone(phone);
//            }
//        }
        userMapper.updateUserInfo(user);
        return user;
    }

    @Override
    public void saveUserInfo(@NotNull User user) {
        user.setUserId(CommUtil.getGuid());
        userMapper.saveUserInfo(user);
    }

    @Override
    public JSONObject findOne(String openId) {
        return userMapper.findOne(openId);
    }

    @Override
    public JSONObject selectGet() {
        String url = HttpclientUtil.get("httpclient.select.get");
        String httpGet = HttpclientUtil.httpGet(url);
        JSONObject result = JSONObject.parseObject(httpGet);
        return result;
    }

    @Override
    public JSONObject findAreaList() {
        String url = HttpclientUtil.get("httpclient.findAreaList.get");
        String httpGet = HttpclientUtil.httpGet(url);
        JSONObject result = JSONObject.parseObject(httpGet);
        return result;
    }

    @Override
    public void updateUser(JSONObject json) {
        String openId = json.getString("openId");
        String tenantId = json.getString("tenantId");
        String tenantName = json.getString("tenantName");
        String phone = json.getString("phone");
        Date birthday = json.getDate("birthday");
        //根据openId查询,然后获取
        User byUser = userMapper.findByOpenId(openId);
        if (StringUtil.stringIsNotNull(tenantId)){
            String url = HttpclientUtil.get("httpclient.page.get")+"tenantId="+tenantId+"&phoneNumber="+phone;
            String httpGet = HttpclientUtil.httpGet(url);
            JSONObject result = JSONObject.parseObject(httpGet);
            JSONArray jsonArray = result.getJSONObject("resultData").getJSONArray("list");
            System.out.println("result="+jsonArray);
            if(jsonArray.isEmpty()||jsonArray.size()<1){
                //保存到平台
                DateUtil.Age age = DateUtil.getAge(byUser.getBirthday());
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("wxOpenId",byUser.getOpenId());
                jsonParam.put("tenantId",tenantId);
                jsonParam.put("id",byUser.getUserId());
                jsonParam.put("name",byUser.getNickName());
                jsonParam.put("occupation",byUser.getOccupation());
                jsonParam.put("phoneNumber",phone);
                jsonParam.put("wxUnionId",byUser.getUnionId());
                jsonParam.put("age",age.getYear());
                System.out.println("jsonParam++="+jsonParam);
                String postUrl = HttpclientUtil.get("httpclient.motherInfo.post");
                HttpclientUtil.httpPost(postUrl, jsonParam);
            }else {
                JSONObject motherInfo = (JSONObject) jsonArray.get(0);
                byUser.setOccupation(motherInfo.getString("occupation"))
                        .setUnionId(motherInfo.getString("wxUnionId"))
                        .setNickName(json.getString("nickName"))
                        .setBirthday(birthday)
                        .setTenantId(tenantId)
                        .setTenantName(tenantName)
                        .setPhone(phone);
                userMapper.updateUserInfo(byUser);
            }
        }
    }

    @Override
    public User saveUser(User user) {
        user.setUserId(CommUtil.getGuid());
        String tenantId = user.getTenantId();
        String openId = user.getOpenId();
        String phone = user.getPhone();
        if (StringUtil.stringIsNotNull(tenantId)){
            //根据openId查询,然后获取
            //User byUser = userMapper.findByOpenId(openId);
            String url = HttpclientUtil.get("httpclient.page.get")+"tenantId="+tenantId+"&phoneNumber="+phone;
            String httpGet = HttpclientUtil.httpGet(url);
            JSONObject result = JSONObject.parseObject(httpGet);

//            System.out.println("jsonArray="+jsonArray);
            if(Objects.isNull(result)){
                //保存到平台
                DateUtil.Age age = DateUtil.getAge(user.getBirthday());
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("wxOpenId",openId);jsonParam.put("tenantId",tenantId);jsonParam.put("id",user.getUserId());jsonParam.put("name",user.getNickName());jsonParam.put("occupation",user.getOccupation());jsonParam.put("phoneNumber",phone);jsonParam.put("wxUnionId",user.getUnionId());jsonParam.put("age",age.getYear());
                System.out.println("jsonParam="+jsonParam);
                String postUrl = HttpclientUtil.get("httpclient.motherInfo.post");
                HttpclientUtil.httpPost(postUrl, jsonParam);
            }else {
                JSONArray jsonArray = result.getJSONObject("resultData").getJSONArray("list");
                JSONObject motherInfo = (JSONObject) jsonArray.get(0);
                user.setOccupation(motherInfo.getString("occupation"))
                        .setUnionId(motherInfo.getString("wxUnionId"))
                        .setUserId(motherInfo.getString("id"))
                        .setNickName(user.getNickName())
                        .setBirthday(user.getBirthday())
                        .setTenantName(user.getTenantName());
            }
        }

        userMapper.saveUserInfo(user);
        return user;
    }
}
