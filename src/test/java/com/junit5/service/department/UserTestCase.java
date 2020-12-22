package com.junit5.service.department;

import com.junit5.service.apiobject.TokenHelper;
import com.junit5.service.apiobject.UserObject;
import com.junit5.service.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTestCase {

    static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建成员")
    @Order(1)
    @Test
    public void createUser(){
        String userId = "id" + FakerUtils.getTimeStamp();
        String userName = "name" + FakerUtils.getTimeStamp();
        String userAlias = "alias" + FakerUtils.getTimeStamp();
        String userEmail = "email" + FakerUtils.getEmail();
        Response createResponse = UserObject.createUser(userId,userName,userAlias,userEmail,accessToken);
        assertEquals("0",createResponse.path("errcode"));
    }

    @DisplayName("修改成员")
    @Order(2)
    @Test
    public void updateUser(){
        String userId = "id" + FakerUtils.getTimeStamp();
        String userName = "name" + FakerUtils.getTimeStamp();
        String userAlias = "alias" + FakerUtils.getTimeStamp();
        Response updateResponse = UserObject.updateUser(userId,userName,userAlias,accessToken);
        assertEquals("0",updateResponse.path("errcode"));
    }

    @DisplayName("删除成员")
    @Order(3)
    @Test
    public void deleteUser(){
        String userId = "id" + FakerUtils.getTimeStamp();
        Response deleteResponse = UserObject.deleteUser(userId,accessToken);
        assertEquals("0",deleteResponse.path("errcode"));
    }
}
