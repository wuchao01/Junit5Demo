package com.junit5.demo;

import org.junit.jupiter.api.RepeatedTest;
import util.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5Demo_4_2_MixParallel {
    @RepeatedTest(10)
    public void addTest() {
        int result = Calculator.add(2,4);
        System.out.println(result);
        assertEquals(6,result);
    }

    @RepeatedTest(10)
    public void subtractTest() throws InterruptedException {
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }
}
