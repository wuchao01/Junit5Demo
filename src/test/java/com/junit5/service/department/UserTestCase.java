package com.junit5.service.department;

import com.junit5.service.apiobject.TokenHelper;
import com.junit5.service.apiobject.UserObject;
import com.junit5.service.task.UserTask;
import com.junit5.service.utils.FakerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Issues;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTestCase {

    static String accessToken;
    Map<String,String> createBody;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建成员")
    @Order(1)
    @Test
    public void createUser(){
        Map<String,String> createBody = new HashMap<>();
        createBody.put("userid","id" + FakerUtils.getNum(1,100));
        createBody.put("name","name" + FakerUtils.getNum(1,100));
        createBody.put("alias","alias" + FakerUtils.getNum(1,100));
        createBody.put("email","email" + FakerUtils.getNum(1,100) + "@qq.com");
        createBody.put("department","1");
        createBody.entrySet().forEach(body->System.out.println("test body:"+body));
        Response createResponse = UserObject.createUser(createBody,accessToken);
        assertEquals("0",createResponse.path("errcode").toString());
    }

    @BeforeEach
    @AfterEach
    public void clearUserList(){
        UserTask.clearUserTask(accessToken);
    }

    @DisplayName("显示成员列表")
    @Order(2)
    @Test
    public void listUser(){
        String userId = "id" + FakerUtils.getTimeStamp();
        //departmentId暂时写死获取根部门下成员列表
        Response listResponse = UserObject.listUser("1",accessToken);
        assertEquals("0",listResponse.path("errcode").toString());
    }

    @DisplayName("修改成员")
    @Order(3)
    @Test
    public void updateUser(){
        createBody = new HashMap<>();
        createBody.put("userid","id" + FakerUtils.getNum(1,100));
        createBody.put("name","name" + FakerUtils.getNum(1,100));
        createBody.put("alias","alias" + FakerUtils.getNum(1,100));
        createBody.put("email","email" + FakerUtils.getNum(1,100) + "@qq.com");
        createBody.put("department","1");
        UserObject.createUser(createBody,accessToken);
        Response listResponse = UserObject.listUser("1",accessToken);
        String userId = listResponse.path("userlist[-1].userid") ;
        String updateName = "updateName" + FakerUtils.getNum(1,100);
        Response updateResponse = UserObject.updateUser(userId,updateName,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }

    @DisplayName("删除成员")
    @Order(4)
    @Test
    public void deleteUser(){
        createBody = new HashMap<>();
        createBody.put("userid","id" + FakerUtils.getNum(1,100));
        createBody.put("name","name" + FakerUtils.getNum(1,100));
        createBody.put("alias","alias" + FakerUtils.getNum(1,100));
        createBody.put("email","email" + FakerUtils.getNum(1,100) + "@qq.com");
        createBody.put("department","1");
        UserObject.createUser(createBody,accessToken);
        Response listResponse = UserObject.listUser("1",accessToken);
        String userId = listResponse.path("userlist[-1].userid") ;
        Response deleteResponse = UserObject.deleteUser(userId,accessToken);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }
}
