package com.junit5.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class Junit5Demo2 {

    @DisplayName("重复执行")
    @RepeatedTest(value = 3,name = "{displayName},执行第{currentRepetition}次}")
    void repetition(){
        System.out.println("repetition");
    }

    @Nested
    @DisplayName("第二个执行")
    class B{
        @Test
        void putInB(){
            System.out.println("test B");
        }
    }

    /**
     * Junit5分组后是从最后往前开始执行
     */
    @Nested
    @DisplayName("第一个执行")
    class A{
        @Test
        void putInA(){
            System.out.println("test A");
        }
    }
}
