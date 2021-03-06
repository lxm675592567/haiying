package haiying.util;

import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Slf4j
public class CommUtil {

    /**
     * 描述：获得guid
     *
     * @return
     */
    public static String getGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
