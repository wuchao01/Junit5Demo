package com.junit5.demo;

import org.junit.jupiter.api.*;

public class Junit5Demo_3_1_FixturesExtend extends Junit5Demo_3_1_Fixtures{
    @BeforeAll
    public static void beforeChildAll() {
        System.out.println("test beforeChildAll!");
    }

    @BeforeEach
    public void beforeChildEach() {
        System.out.println("test beforeChildEach!");
    }

    @DisplayName("测试A用例")
    @Test
    public void putInChildA(){
        System.out.println("test putInChildA");
    }

    @DisplayName("测试B用例")
    @Test
    public void putInChildB(){
        System.out.println("test putInChildB");
    }

    @AfterEach
    public void afterChildEach() {
        System.out.println("test afterChildEach!");
    }

    @AfterAll
    public static void afterChildAll() {
        System.out.println("test afterChildAll!");
    }
}
