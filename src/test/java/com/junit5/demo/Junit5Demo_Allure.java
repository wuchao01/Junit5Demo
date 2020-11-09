package com.junit5.demo;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import util.Calculator;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Epic 计算器项目")
@Feature("feature 冒烟测试用例")
public class Junit5Demo_Allure {
    @Test
    @Order(1)
    @Description("Description")
    @Story("story 加法测试")
    @DisplayName("displayName 加法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://www.baidu.com")
    @Link(name = "爱测社区",type = "myLink",url = "https://ceshiren.com/t/topic/7718")
    public void addTest() throws FileNotFoundException {
        int resultPass = Calculator.add(4,2);
        int resultFail = Calculator.add(4,2);
        int result = Calculator.add(4,2);
        System.out.println("add:" + result);
        Allure.addAttachment("pick","image/png",this.getClass().getResourceAsStream("/pick.png"),".png");
        assertAll(("计算结果："),
        ()->assertEquals(6,resultPass),
        ()->assertEquals(6,resultFail),
        ()->assertEquals(7,result));
    }

    @Order(3)
    @Test
    public void subtractTest() throws InterruptedException {
        int result = Calculator.subtract(4,2);
        System.out.println("subtract" + result);
        assertEquals(2,result);
    }

    @Order(2)
    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println("multiply" + result);
        assertEquals(8,result);
    }

    @Order(4)
    @Test
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println("divide" + result);
        assertEquals(2,result);
    }

    @BeforeEach
    public void clear(){
        Calculator.clear();
    }

    @Order(5)
    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(4);
        System.out.println("count" + result);
        assertEquals(4,result);
    }
}
