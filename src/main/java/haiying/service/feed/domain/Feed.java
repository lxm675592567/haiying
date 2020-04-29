package haiying.service.feed.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import haiying.util.DateUtil;

import java.util.Date;

public class Feed {

    public Feed() {
    }

    public Feed(String id, String guid, String typeName, String type, String lactation, Date createTime, Date endTime, String duration, int nurseContent, String foodName, String foodDescribe, String foodPhoto, String selectType, String selectTypeName, String urineShape, String shitShape) {
        this.id = id;
        this.guid = guid;
        this.typeName = typeName;
        this.type = type;
        this.lactation = lactation;
        this.createTime = createTime;
        this.endTime = endTime;
        this.duration = duration;
        this.nurseContent = nurseContent;
        this.foodName = foodName;
        this.foodDescribe = foodDescribe;
        this.foodPhoto = foodPhoto;
        this.selectType = selectType;
        this.selectTypeName = selectTypeName;
        this.urineShape = urineShape;
        this.shitShape = shitShape;
    }

    private String id;    //主键id

    private String guid;

    private String typeName; //类型名称

    private String type;  //类型  1母乳亲喂  2瓶装母乳  3配方奶  4辅食  5换尿布  6睡眠

    private String lactation;    //哺乳行为

    @JsonFormat(pattern = DateUtil.DATE_TIME_FMT, timezone = DateUtil.TIME_ZONE)
    private Date createTime;  //开始时间

    @JsonFormat(pattern = DateUtil.DATE_TIME_FMT, timezone = DateUtil.TIME_ZONE)
    private Date endTime;    //结束时间

    private String duration; //哺乳时长

    private int nurseContent;  //喂奶量

    private String foodName; //辅食名称

    private String foodDescribe;  //描述

    private String foodPhoto; //辅食照片

    private String selectType;  //换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便

    private String selectTypeName; //换尿布选择类型

    private String urineShape;  //嘘嘘形状

    private String shitShape;  //便便形状

    public String getId() {
        return id;
    }

    public Feed setId(String id) {
        this.id = id;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public Feed setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public Feed setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public String getType() {
        return type;
    }

    public Feed setType(String type) {
        this.type = type;
        return this;
    }

    public String getLactation() {
        return lactation;
    }

    public Feed setLactation(String lactation) {
        this.lactation = lactation;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Feed setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Feed setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public Feed setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public int getNurseContent() {
        return nurseContent;
    }

    public Feed setNurseContent(int nurseContent) {
        this.nurseContent = nurseContent;
        return this;
    }

    public String getFoodName() {
        return foodName;
    }

    public Feed setFoodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }

    public Feed setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
        return this;
    }

    public String getFoodPhoto() {
        return foodPhoto;
    }

    public Feed setFoodPhoto(String foodPhoto) {
        this.foodPhoto = foodPhoto;
        return this;
    }

    public String getSelectType() {
        return selectType;
    }

    public Feed setSelectType(String selectType) {
        this.selectType = selectType;
        return this;
    }

    public String getSelectTypeName() {
        return selectTypeName;
    }

    public Feed setSelectTypeName(String selectTypeName) {
        this.selectTypeName = selectTypeName;
        return this;
    }

    public String getUrineShape() {
        return urineShape;
    }

    public Feed setUrineShape(String urineShape) {
        this.urineShape = urineShape;
        return this;
    }

    public String getShitShape() {
        return shitShape;
    }

    public Feed setShitShape(String shitShape) {
        this.shitShape = shitShape;
        return this;
    }


}
