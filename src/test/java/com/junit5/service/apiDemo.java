package com.junit5.service;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class apiDemo {
    public static String accessToken;
    @Test
    public void getDemo(){
        accessToken = given()
                .when()
                .params("corpid","wwa6e174bdb8889eff","corpsecret","8dm-npda-T3Fd1G17Ii6xOdXEjnyYjq3kM5YoE4fzgc")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().path("access_token");
    }

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

}
