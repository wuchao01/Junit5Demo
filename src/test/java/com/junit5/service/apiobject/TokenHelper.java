package com.junit5.service.apiobject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class TokenHelper {
    /**
     * 获取accessToken
     */
    public static String getAccessToken(){
        useRelaxedHTTPSValidation();
        String accessToken = given()
                .when()
                .params("corpid","wwa6e174bdb8889eff","corpsecret","yWX6DThWTj3ilKx9fQnECNbl2BDYGB2RerYf0ccEXWQ")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().path("access_token");
        return accessToken;
    }
}
