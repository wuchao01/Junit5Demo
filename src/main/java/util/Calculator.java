package util;

import static java.lang.Thread.sleep;

public class Calculator {

    private static int result = 0;

    public static int add(int x,int y){
        result = x + y;
        return result;
    }

    public static void clear(){
        result = 0;
        System.out.println("当前结果已清零！");
    }

    public static int count(int x) throws InterruptedException {
        int i = result;
        sleep(1000);
        result = i + x;
        return result;
    }

    public static synchronized int synCount(int x) throws InterruptedException {
        int i = result;
        sleep(1000);
        result = i + x;
        return result;
    }

    public static int subtract(int x,int y) throws InterruptedException {
        result = x - y;
        sleep(10);
        return result;
    }

    public static int multiply(int x,int y){
        result = x * y;
        return result;
    }

    public static int divide(int x,int y){
        result = x / y;
        return result;
    }
}
