package haiying.util;

import cn.izern.sequence.Sequence;

public class GuidUtil {

    private GuidUtil() {
    }

    private static Sequence sequence = new Sequence();

    public static String generateGuid() {

        return String.valueOf(sequence.nextId());
    }

    public static long generateLongGuid() {

        return sequence.nextId();
    }

    public static String getRandom(int len) {
         int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
         return String.valueOf(rs);
    }

}
