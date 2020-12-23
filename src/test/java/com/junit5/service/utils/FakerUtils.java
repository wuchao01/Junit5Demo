package com.junit5.service.utils;

import java.util.Random;

public class FakerUtils {

    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getEmail(){
        Random random = new Random();
        String email = random.nextInt(1000) + "@qq.com";
        return email;
    }

    /**
     * @author: kuohai
     * @methodsName: getNum
     * @description:
     * @param: [start, end]
     * @return: int
     */
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 电话号码生成器
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,188,185,181".split(",");
    /**
     * @author: kuohai
     * @methodsName: getTel
     * @description:
     * @return: java.lang.String
     */
    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }
}
