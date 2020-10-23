package com.tust.school.res.utils;

import java.util.Random;

public class RandomUtils {

    /**
     * 获取随机的流水号(10位)
     *
     * @return
     */
    public static String getRandomReqNo() {
        int length = 12;
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
