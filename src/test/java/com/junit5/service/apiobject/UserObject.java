package com.junit5.service.apiobject;

import com.junit5.service.utils.FakerUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserObject {

    public static Response createUser(Map<String,String> createBody,String accessToken){
        Response createUserResponse = given()
                .proxy(8888)
                .contentType(ContentType.JSON)
                .body(createBody)
                .queryParam("access_token",accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then()
                .log()
                .all()
                .extract()
                .response();
        return createUserResponse;
    }

    public static Response updateUser(String userId, String updateName, String accessToken){
        Response createUserResponse = given()
                .body("{\n" +
                        "    \"userid\": \""+ userId +"\",\n" +
                        "    \"name\": \""+ updateName +"\"\n" +
                        "}")
                .queryParam("access_token",accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then()
                .log()
                .all()
                .extract()
                .response();
        return createUserResponse;
    }

    public static Response deleteUser(String userId, String accessToken){
        Response createUserResponse = given()
                .param("access_token",accessToken)
                .param("userid",userId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then()
                .log()
                .all()
                .extract()
                .response();
        return createUserResponse;
    }

    public static Response listUser(String departmentId, String accessToken){
        Response listUserResponse = given()
                .param("access_token",accessToken)
                .param("department_id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then()
                .log()
                .all()
                .extract()
                .response();
        return listUserResponse;
    }
}
