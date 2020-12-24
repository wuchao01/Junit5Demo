package com.junit5.service.department;

import com.junit5.service.apiobject.DepartmentObject;
import com.junit5.service.apiobject.TokenHelper;
import com.junit5.service.task.DepartmentTask;
import com.junit5.service.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("接口测试")
@Feature("冒烟测试")
public class DepartmentTestCase {
    static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建部门")
    @Story("创建部门用例")
    //注解Severity标识缺陷等级用户报告展示
    //blocker　 阻塞缺陷（功能未实现，无法下一步）
    //critical　　严重缺陷（功能点缺失）
    //normal　　 一般缺陷（边界情况，格式错误）
    //minor　 次要缺陷（界面错误与ui需求不符）
    //trivial　　 轻微缺陷（必须项无提示，或者提示不规范）
    //执行报告命令allure serve allure-results
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "企业微信创建部门",type = "create",url = "https://ceshiren.com/t/topic/8967")
    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void createDepartment(String createName,String createEnName,String returnCode){
        //numLinesToSkip = 1代表略过第一条记录
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        assertEquals(returnCode,createResponse.path("errcode").toString());
    }

    @BeforeEach
    @AfterEach
    public void clearDepartment(){
        DepartmentTask.clearDepartmentTask(accessToken);
    }

    @DisplayName("修改部门")
    @Story("修改部门用例")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "企业微信修改部门",type = "update",url = "https://ceshiren.com/t/topic/8967")
    @Order(2)
    @Test
    public void updateDepartment(){
        String updateName = "updateName" + FakerUtils.getTimeStamp();
        String updateEnName = "updateEnName" + FakerUtils.getTimeStamp();
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }

    @DisplayName("删除部门")
    @Story("删除部门用例")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "企业微信删除部门",type = "delete",url = "https://ceshiren.com/t/topic/8967")
    @Order(4)
    @Test
    public void deleteDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response deleteResponse = DepartmentObject.deleteDepartment(departmentId,accessToken);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }

    @DisplayName("获取部门列表")
    @Story("获取部门列表用例")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "企业微信部门列表",type = "list",url = "https://ceshiren.com/t/topic/8967")
    @Order(3)
    @Test
    public void departmentList(){
        String createName= "name"+FakerUtils.getTimeStamp();
        String createEnName="en_name"+FakerUtils.getTimeStamp();
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString(): null;
        Response listResponse = DepartmentObject.departmentList(departmentId,accessToken);
        assertEquals("0",listResponse.path("errcode").toString());
    }
}
