package com.junit5.service.demo;

import com.junit5.service.apiobject.DepartmentObject;
import com.junit5.service.apiobject.TokenHelper;
import com.junit5.service.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertEquals;

public class DepartmentParallel {

    static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建部门")
    @RepeatedTest(value = 5,name = "创建完成度：{currentRepetition}/{totalRepetitions}")
    public void createDepartmentRepeated(){
        String backEndStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();
        String createName = "createName" + backEndStr;
        String createEnName = "createEnName" + backEndStr;
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        assertEquals("0",createResponse.path("errcode").toString());
    }

//    @BeforeEach
//    @AfterEach
//    public void clearDepartment(){
//        DepartmentTask.clearDepartmentTask(accessToken);
//    }

    @DisplayName("删除部门")
    @RepeatedTest(value = 5,name = "删除完成度：{currentRepetition}/{totalRepetitions}")
    public void deleteDepartmentRepeated(){
        String backEndStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();
        String createName = "createName" + backEndStr;
        String createEnName = "createEnName" + backEndStr;
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        String departmentId = createResponse.path("id").toString();
        Response deleteResponse = DepartmentObject.deleteDepartment(departmentId,accessToken);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }
}
