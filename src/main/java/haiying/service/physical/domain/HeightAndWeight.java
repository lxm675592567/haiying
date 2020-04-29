package haiying.service.physical.domain;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import haiying.util.DateUtil;


import java.io.Serializable;
import java.util.Date;

/**
 * 身高体重测量结果
 */

public class HeightAndWeight  {

    public HeightAndWeight() {
    }

    public HeightAndWeight(String hwId, String guid, Double height, Double weight, Double heightIncrease, Double weightIncrease, String heightEvaluation, String weightEvaluation, String heightCorrectEvaluation, String weightCorrectEvaluation, String suggestion, Double monthAge, Integer monthAgeInt, Double correctMonthAge, String age, String doctor, Date createTime) {
        this.hwId = hwId;
        this.guid = guid;
        this.height = height;
        this.weight = weight;
        this.heightIncrease = heightIncrease;
        this.weightIncrease = weightIncrease;
        this.heightEvaluation = heightEvaluation;
        this.weightEvaluation = weightEvaluation;
        this.heightCorrectEvaluation = heightCorrectEvaluation;
        this.weightCorrectEvaluation = weightCorrectEvaluation;
        this.suggestion = suggestion;
        this.monthAge = monthAge;
        this.monthAgeInt = monthAgeInt;
        this.correctMonthAge = correctMonthAge;
        this.age = age;
        this.doctor = doctor;
        this.createTime = createTime;
    }

    /**
     * 主键
     */

    private String hwId;

    /**
     * 患者guid
     */

    private String guid;

    /**
     * 体高
     */
    private Double height;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 身高增长
     */
    private Double heightIncrease;

    /**
     * 体重境长
     */
    private Double weightIncrease;

    /**
     * 身高评价
     */
    private String heightEvaluation;

    /**
     * 体重评价
     */
    private String weightEvaluation;

    /**
     * 身高修正评价
     */
    private String heightCorrectEvaluation;

    /**
     * 体重修正评价
     */
    private String weightCorrectEvaluation;

    /**
     * 医生建议
     */
    private String suggestion;

    /**
     * 月龄
     */
    private Double monthAge;

    /**
     * 月龄 整月
     */
    private Integer monthAgeInt;

    /**
     * 校正后月龄
     */
    private Double correctMonthAge;
    // private Double correctMonthAge;

    private String age;

    private String doctor;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtil.DATE_TIME_FMT, timezone = DateUtil.TIME_ZONE)
    private Date createTime;


    /**
     * 设置身高增长
     * @param lastHeight 上次身高
     * @return 当前对象
     */
    public HeightAndWeight putHeightIncrease(Double lastHeight) {
        if (this.height == null || lastHeight == null) {
            return this;
        }
        this.heightIncrease = this.height - lastHeight;
        return this;
    }

    /**
     * 设置体重增长
     * @param lastWeight 上次体重
     * @return 当前对象
     */
    public HeightAndWeight putWeightIncrease(Double lastWeight) {
        if (this.weight == null || lastWeight == null) {
            return this;
        }
        this.weightIncrease = this.weight - lastWeight;
        return this;
    }

    /**
     * 获取月份-身高
     *
     * @return 月份-身高
     */
    public JSONArray getMonthHeightEntry() {
        return new JSONArray().fluentAdd(this.monthAge).fluentAdd(this.height);
    }

    /**
     * 获取月份-体重
     *
     * @return 月份-体重
     */
    public JSONArray getMonthWeightEntry() {
        return new JSONArray().fluentAdd(this.monthAge).fluentAdd(this.weight);
    }

    /**
     * 获取月份-体重
     *
     * @return 月份-体重
     */
    public JSONArray getCorrectMonthHeightEntry() {
        return new JSONArray().fluentAdd(this.correctMonthAge).fluentAdd(this.height);
    }

    /**
     * 获取 修正月份-体重
     *
     * @return 修正月份-体重
     */
    public JSONArray getCorrectMonthWeightEntry() {
        return new JSONArray().fluentAdd(this.correctMonthAge).fluentAdd(this.weight);
    }

    private final static String MONTH_VALUE_ENTRY_FORMAT = "[%f,%f]";

    /**
     * 获取 月份（整月-身高
     *
     * @return 月份（整月-身高
     */
    public JSONArray getMonthIntHeightEntry() {
        return new JSONArray().fluentAdd(this.monthAgeInt).fluentAdd(this.height);
    }

    /**
     * 获取 月份（整月）-体重
     *
     * @return 月份（整月-体重
     */
    public JSONArray getMonthIntWeightEntry() {
        return new JSONArray().fluentAdd(this.monthAgeInt).fluentAdd(this.weight);
    }

    public String getHwId() {
        return hwId;
    }

    public HeightAndWeight setHwId(String hwId) {
        this.hwId = hwId;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public HeightAndWeight setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public HeightAndWeight setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public HeightAndWeight setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getHeightIncrease() {
        return heightIncrease;
    }

    public HeightAndWeight setHeightIncrease(Double heightIncrease) {
        this.heightIncrease = heightIncrease;
        return this;
    }

    public Double getWeightIncrease() {
        return weightIncrease;
    }

    public HeightAndWeight setWeightIncrease(Double weightIncrease) {
        this.weightIncrease = weightIncrease;
        return this;
    }

    public String getHeightEvaluation() {
        return heightEvaluation;
    }

    public HeightAndWeight setHeightEvaluation(String heightEvaluation) {
        this.heightEvaluation = heightEvaluation;
        return this;
    }

    public String getWeightEvaluation() {
        return weightEvaluation;
    }

    public HeightAndWeight setWeightEvaluation(String weightEvaluation) {
        this.weightEvaluation = weightEvaluation;
        return this;
    }

    public String getHeightCorrectEvaluation() {
        return heightCorrectEvaluation;
    }

    public HeightAndWeight setHeightCorrectEvaluation(String heightCorrectEvaluation) {
        this.heightCorrectEvaluation = heightCorrectEvaluation;
        return this;
    }

    public String getWeightCorrectEvaluation() {
        return weightCorrectEvaluation;
    }

    public HeightAndWeight setWeightCorrectEvaluation(String weightCorrectEvaluation) {
        this.weightCorrectEvaluation = weightCorrectEvaluation;
        return this;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public HeightAndWeight setSuggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public Double getMonthAge() {
        return monthAge;
    }

    public HeightAndWeight setMonthAge(Double monthAge) {
        this.monthAge = monthAge;
        return this;
    }

    public Integer getMonthAgeInt() {
        return monthAgeInt;
    }

    public HeightAndWeight setMonthAgeInt(Integer monthAgeInt) {
        this.monthAgeInt = monthAgeInt;
        return this;
    }

    public Double getCorrectMonthAge() {
        return correctMonthAge;
    }

    public HeightAndWeight setCorrectMonthAge(Double correctMonthAge) {
        this.correctMonthAge = correctMonthAge;
        return this;
    }

    public String getAge() {
        return age;
    }

    public HeightAndWeight setAge(String age) {
        this.age = age;
        return this;
    }

    public String getDoctor() {
        return doctor;
    }

    public HeightAndWeight setDoctor(String doctor) {
        this.doctor = doctor;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public HeightAndWeight setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public static String getMonthValueEntryFormat() {
        return MONTH_VALUE_ENTRY_FORMAT;
    }
}
