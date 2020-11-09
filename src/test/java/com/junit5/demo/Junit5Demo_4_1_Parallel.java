package com.junit5.demo;

import org.junit.jupiter.api.RepeatedTest;
import util.Calculator;


public class Junit5Demo_4_1_Parallel {
//    @Execution(ExecutionMode.CONCURRENT)
//    @DisplayName("多线程执行10次")
//    @RepeatedTest(value = 10, name="完成度：{currentRepetition}/{totalRepetitions}")
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println("count" + result);
    }

    @RepeatedTest(10)
    public void synCountTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        System.out.println("count" + result);
    }
}
