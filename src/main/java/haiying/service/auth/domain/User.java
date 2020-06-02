package haiying.service.auth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import haiying.util.DateUtil;

import java.util.Date;
/*
* 微信用户表
* */
public class User {
    public User() {
    }

    public User(String userId, String openId, String unionId, String nickName, int gender, String country, String province, String city, String avatarUrl, String avatar, Date createTime, int loginType, Date birthday, String phone, String height, String menstruation, String tenantId, String tenantName, String occupation) {
        this.userId = userId;
        this.openId = openId;
        this.unionId = unionId;
        this.nickName = nickName;
        this.gender = gender;
        this.country = country;
        this.province = province;
        this.city = city;
        this.avatarUrl = avatarUrl;
        this.avatar = avatar;
        this.createTime = createTime;
        this.loginType = loginType;
        this.birthday = birthday;
        this.phone = phone;
        this.height = height;
        this.menstruation = menstruation;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.occupation = occupation;
    }

    private String userId;    //主键id

    private String openId;

    private String unionId;

    private String nickName;  //姓名

    private int gender;    //性别 0未知 1男 2女

    private String country;  //国家

    private String province; //省份

    private String city;  //城市

    private String avatarUrl;  //用户图片头像url

    private String avatar;

    @JsonFormat(pattern = DateUtil.DATE_FMT, timezone = DateUtil.TIME_ZONE)
    private Date createTime;    //创建时间

    private int loginType;

    @JsonFormat(pattern = DateUtil.DATE_FMT, timezone = DateUtil.TIME_ZONE)
    private Date birthday;      //出生日期

    private String phone;       //手机号

    private String height;    //身高

    private String menstruation;  //末次月经

    private String tenantId;    //租户id

    private String tenantName;  //租户名称

    private String occupation;  //职业

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public User setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getUnionId() {
        return unionId;
    }

    public User setUnionId(String unionId) {
        this.unionId = unionId;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public int getGender() {
        return gender;
    }

    public User setGender(int gender) {
        this.gender = gender;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public User setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public User setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public int getLoginType() {
        return loginType;
    }

    public User setLoginType(int loginType) {
        this.loginType = loginType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public User setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getMenstruation() {
        return menstruation;
    }

    public User setMenstruation(String menstruation) {
        this.menstruation = menstruation;
        return this;
    }

    public String getTenantId() {
        return tenantId;
    }

    public User setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public User setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public User setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
