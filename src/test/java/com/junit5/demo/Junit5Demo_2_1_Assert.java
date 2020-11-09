package com.junit5.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5Demo_2_1_Assert {
    @BeforeEach
    public void clearTest(){
        Calculator.clear();
    }

    @Test
    public void addTest(){
        int resultPass = Calculator.add(4,2);
        int resultFail = Calculator.add(4,2);
        int result = Calculator.add(4,2);
        System.out.println("add:" + result);
        assertEquals(6,resultPass);
    }

    @Test
    public void subtractTest() throws InterruptedException {
        int result = Calculator.subtract(4,2);
        System.out.println("subtract" + result);
        assertEquals(2,result);
    }

    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println("multiply" + result);
        assertEquals(8,result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println("divide" + result);
        assertEquals(2,result);
    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(4);
        System.out.println("count" + result);
        assertEquals(4,result);
    }
}
