package haiying.service.archives.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import haiying.util.DateUtil;

import java.util.Date;

/**
 * 宝宝档案
 */

public class Record {

    public Record() {
    }

    public Record(String guid, String cardId, String name, String sex, String idnumber, Date birthday, String unionId, Date createTime, DateUtil.Age ageDetail, String status, String openId, String onlyChild, String pregnantWeek, String avatar) {
        this.guid = guid;
        this.cardId = cardId;
        this.name = name;
        this.sex = sex;
        this.idnumber = idnumber;
        this.birthday = birthday;
        this.unionId = unionId;
        this.createTime = createTime;
        this.ageDetail = ageDetail;
        this.status = status;
        this.openId = openId;
        this.onlyChild = onlyChild;
        this.pregnantWeek = pregnantWeek;
        this.avatar = avatar;
    }

    /**
     * 主键
     */
    private String guid;

    /**
     * 卡号
     */
    private String cardId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号/出生证明号
     */
    private String idnumber;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = DateUtil.DATE_FMT, timezone = DateUtil.TIME_ZONE)
    private Date birthday;

    /**
     * unionId
     */
    private String unionId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtil.DATE_FMT, timezone = DateUtil.TIME_ZONE)
    private Date createTime;

    /**
     * 年龄详细
     */
    private DateUtil.Age ageDetail;

    /**
     * 当前状态 已出生,备孕中
     */
    private String status;

    /**
     * openId
     */
    private String openId;

    /**
     * 独生子女
     */
    private String onlyChild;

    /**
     * 出生孕周
     */
    private String pregnantWeek;

    /**
     * 头像url
     */
    private String avatar;
    /**
     * 月龄
     */
    /*@Transient
    private Double monthAge;*/

   /* *//**
     * 场景
     *//*
    private String scenesId;

    *//**
     * 下次检查日期
     *//*
    @JsonFormat(pattern = DateUtil.DATE_FMT, timezone = DateUtil.TIME_ZONE)
    private Date nextCheckDate;

    *//**
     * 微信id
     *//*
    private String Weixinid;

    *//**
     * 角色
     *//*
    private Integer node;*/
    /**
     * 年龄详细str
     */
    /*@Transient
    private String ageDetailStr;*/
    public Record putAgeDetail() {
        this.ageDetail = DateUtil.getAge(this.birthday);
        /*this.monthAge = this.ageDetail.getMonthAge();
        this.ageDetailStr = this.ageDetail.getAgeDetail();*/
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public DateUtil.Age getAgeDetail() {
        return ageDetail;
    }

    public void setAgeDetail(DateUtil.Age ageDetail) {
        this.ageDetail = ageDetail;
    }

    public String getStatus() {
        return status;
    }

    public Record setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public Record setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getOnlyChild() {
        return onlyChild;
    }

    public Record setOnlyChild(String onlyChild) {
        this.onlyChild = onlyChild;
        return this;
    }

    public String getPregnantWeek() {
        return pregnantWeek;
    }

    public Record setPregnantWeek(String pregnantWeek) {
        this.pregnantWeek = pregnantWeek;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public Record setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
