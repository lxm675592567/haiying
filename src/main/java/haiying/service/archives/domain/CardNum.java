package haiying.service.archives.domain;

public class CardNum {

    public CardNum() {
    }

    public CardNum(String id, String cardNum, String date) {
        this.id = id;
        this.cardNum = cardNum;
        this.date = date;
    }

    /**
     * 主键
     */
    private String id;

    /**
     * 卡号
     */
    private String cardNum;

    /**
     * 时间
     */
    private String date;

    public String getId() {
        return id;
    }

    public CardNum setId(String id) {
        this.id = id;
        return this;
    }

    public String getCardNum() {
        return cardNum;
    }

    public CardNum setCardNum(String cardNum) {
        this.cardNum = cardNum;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CardNum setDate(String date) {
        this.date = date;
        return this;
    }
}
