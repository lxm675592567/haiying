package haiying.service.breastmilk.domain;

public class BreastMilk {

    public BreastMilk() {
    }

    public BreastMilk(String id, String openId, String breastType, String fat, String lactose, String protein, String energy, String water, String mineral, String density, String createTime, String result, String proposal, String age) {
        this.id = id;
        this.openId = openId;
        this.breastType = breastType;
        this.fat = fat;
        this.lactose = lactose;
        this.protein = protein;
        this.energy = energy;
        this.water = water;
        this.mineral = mineral;
        this.density = density;
        this.createTime = createTime;
        this.result = result;
        this.proposal = proposal;
        this.age = age;
    }

    /**
     * 主键
     */
    private String id;

    /**
     * openId
     */
    private String openId;

    /**
     * 母乳类型
     */
    private String breastType;

    /**
     * 脂肪
     */
    private String fat;

    /**
     * 乳糖
     */
    private String lactose;

    /**
     * 蛋白质
     */
    private String protein;

    /**
     * 能量
     */
    private String energy;

    /**
     * 水分
     */
    private String water;

    /**
     * 矿物质
     */
    private String mineral;

    /**
     * 密度
     */
    private String density;

    /**
     * 时间
     */
    private String createTime;

    /**
     * 结果
     */
    private String result;

    /**
     * 指导建议
     */
    private String proposal;

    /**
     * 年龄
     */
    private String age;

    public String getId() {
        return id;
    }

    public BreastMilk setId(String id) {
        this.id = id;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public BreastMilk setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getBreastType() {
        return breastType;
    }

    public BreastMilk setBreastType(String breastType) {
        this.breastType = breastType;
        return this;
    }

    public String getFat() {
        return fat;
    }

    public BreastMilk setFat(String fat) {
        this.fat = fat;
        return this;
    }

    public String getLactose() {
        return lactose;
    }

    public BreastMilk setLactose(String lactose) {
        this.lactose = lactose;
        return this;
    }

    public String getProtein() {
        return protein;
    }

    public BreastMilk setProtein(String protein) {
        this.protein = protein;
        return this;
    }

    public String getEnergy() {
        return energy;
    }

    public BreastMilk setEnergy(String energy) {
        this.energy = energy;
        return this;
    }

    public String getWater() {
        return water;
    }

    public BreastMilk setWater(String water) {
        this.water = water;
        return this;
    }

    public String getMineral() {
        return mineral;
    }

    public BreastMilk setMineral(String mineral) {
        this.mineral = mineral;
        return this;
    }

    public String getDensity() {
        return density;
    }

    public BreastMilk setDensity(String density) {
        this.density = density;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public BreastMilk setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getResult() {
        return result;
    }

    public BreastMilk setResult(String result) {
        this.result = result;
        return this;
    }

    public String getProposal() {
        return proposal;
    }

    public BreastMilk setProposal(String proposal) {
        this.proposal = proposal;
        return this;
    }

    public String getAge() {
        return age;
    }

    public BreastMilk setAge(String age) {
        this.age = age;
        return this;
    }
}
