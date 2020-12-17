package com.junit5.service;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTest {

    private static String cookie;

    @BeforeAll
    public static void loginTest(){
        cookie = given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParams("username","cwu","password","n2s_LZ39")
                .when()
                .post("https://eywa.today36524.com/api/passport/security_login.html")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract().header("Set-Cookie");
        System.out.println(cookie);
    }

    @Test
    public void getPromotionList(){
        given()
                .cookie(cookie)
                .contentType("application/x-www-form-urlencoded")
                .formParam("parameter","{\"body\":{\"request\":{\"pageRequest\":{\"start\":0,\"limit\":10,\"results\":0,\"pageIndex\":1}}}}")
                .when()
                .post("https://eywa.today36524.com/api/com.today.api.promotion.service.PromotionService/1.0.0/listPromotions.html")
                .then()
                .body("success.promotionslist.tpId[0]",equalTo(19935))
                .statusCode(200);

    }
}
