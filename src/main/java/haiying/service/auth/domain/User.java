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

    public User(int userId, String openId, String unionId, String nickname, int gender, String country, String province, int city, String avatarUrl, Date createTime, int loginType) {
        this.userId = userId;
        this.openId = openId;
        this.unionId = unionId;
        this.nickname = nickname;
        this.gender = gender;
        this.country = country;
        this.province = province;
        this.city = city;
        this.avatarUrl = avatarUrl;
        this.createTime = createTime;
        this.loginType = loginType;
    }

    private int userId;    //主键id

    private String openId;

    private String unionId;

    private String nickname;  //姓名

    private int gender;    //性别 0未知 1男 2女

    private String country;  //国家

    private String province; //省份

    private int city;  //城市

    private String avatarUrl;  //用户图片头像url

    @JsonFormat(pattern = DateUtil.DATE_TIME_FMT, timezone = DateUtil.TIME_ZONE)
    private Date createTime;    //创建时间

    private int loginType;

    public int getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
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

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
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

    public int getCity() {
        return city;
    }

    public User setCity(int city) {
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
}
