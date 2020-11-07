public class Calculator {

    private static int result = 0;

    public static int add(int x,int y){
        result = x + y;
        return result;
    }

    public static int count(int x) throws InterruptedException {
        int i = 0;
        i += x;
        Thread.sleep(1000);
        return i;
    }

    public static int subtract(int x,int y){
        result = x - y;
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
