package com.junit5.service.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;

public class apiDemo {
    public static String accessToken;

    /**
     * 获取accessToken
     */
    @BeforeAll
    public static void getAccessToken(){
        useRelaxedHTTPSValidation();
        accessToken = given()
                .when()
                .params("corpid","wwa6e174bdb8889eff","corpsecret","yWX6DThWTj3ilKx9fQnECNbl2BDYGB2RerYf0ccEXWQ")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().path("access_token");
    }

    /**
     * 应用内发送消息
     */
    @Test
    public void postDemo(){
        given()
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"测试消息，霍格沃兹课程链接。点击详情跳转。\\n点击跳转<a href=\\\"https://ke.qq.com/course/348893\\\">霍格沃兹测试开发</a>，提前报名享受更多优惠活动。\"\n" +
                        "   },\n" +
                        "}")
                .queryParam("access_token",accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * 获取部门列表
     */
    @Test
    public void departmentList(){
        given()
                .proxy(8888)
                .contentType("application/json; charset=UTF-8")
                .queryParam("access_token",accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log()
                .all()
                .body("errcode",equalTo(0));
    }

    /**
     * 创建部门
     */
    @Test
    public void createDepartment(){
        given()
                .proxy(8888)
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"接口测试部门\",\n" +
                        "   \"name_en\": \"apitest\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 2,\n" +
                        "   \"id\": 3\n" +
                        "}")
                .queryParam("access_token",accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log()
                .all()
                .body("errcode",equalTo(0));
    }

}
