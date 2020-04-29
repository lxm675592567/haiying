package haiying.service.physical.constant;

import lombok.Getter;

public class CoreConstant {
    private CoreConstant() {
    }

    /**
     * 性别枚举
     */
    @Getter
    public enum Gender {

        MALE("1", "男", 1),
        FEMALE("0", "女", 2),
        UNKNOWN("-1", "未知", 3);

        private String value;
        private String sex;
        private Integer valueInt;

        Gender(String value, String sex, Integer valueInt) {
            this.value = value;
            this.sex = sex;
            this.valueInt = valueInt;
        }

        public static Gender getInstanceByValue(String value) {
            for (Gender gender : Gender.values()) {
                if (gender.value.equals(value)) {
                    return gender;
                }
            }
            // throw new RuntimeException("传入性别值有误");
            return UNKNOWN;
        }

        public static Gender getInstanceBySex(String sex) {
            for (Gender gender : Gender.values()) {
                if (gender.sex.equals(sex)) {
                    return gender;
                }
            }
            // throw new RuntimeException("传入性别值有误");
            return UNKNOWN;
        }

        public static Gender getInstanceByValueInt(Integer valueInt) {
            for (Gender gender : Gender.values()) {
                if (gender.valueInt.equals(valueInt)) {
                    return gender;
                }
            }
            // throw new RuntimeException("传入性别值有误");
            return UNKNOWN;
        }

        public static boolean isMale(String sex) {
            return MALE.sex.equals(sex);
        }

        public String getValue() {
            return value;
        }

        public Gender setValue(String value) {
            this.value = value;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public Gender setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Integer getValueInt() {
            return valueInt;
        }

        public Gender setValueInt(Integer valueInt) {
            this.valueInt = valueInt;
            return this;
        }
    }
}
