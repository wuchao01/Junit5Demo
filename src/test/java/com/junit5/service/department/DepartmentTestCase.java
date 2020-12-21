package com.junit5.service.department;

import com.junit5.service.apiobject.DepartmentObject;
import com.junit5.service.apiobject.TokenHelper;
import com.junit5.service.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentTestCase {
    static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建部门")
    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void createDepartment(String createName,String createEnName,String returnCode){
        //numLinesToSkip = 1代表略过第一条记录
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        assertEquals(returnCode,createResponse.path("errcode").toString());
    }

//    @BeforeEach
//    @AfterEach
    public void clearDepartment(){
        Response listResponse = DepartmentObject.departmentList("1",accessToken);
        ArrayList<Integer> departmentList = listResponse.path("department.id");
        for (int departmentId:departmentList){
            if (1 == departmentId){
                continue;
            }
            DepartmentObject.deleteDepartment(String.valueOf(departmentId),accessToken);
        }
    }

    @DisplayName("修改部门")
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
    @Order(4)
    @Test
    public void deleteDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response deleteResponse = DepartmentObject.deleteDepartment(departmentId,accessToken);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }

    @DisplayName("获取部门列表")
    @Order(3)
    @Test
    public void departmentList(){
        String createName= "name"+FakerUtils.getTimeStamp();
        String createEnName="en_name"+FakerUtils.getTimeStamp();
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString(): null;
        Response listResponse = DepartmentObject.departmentList(departmentId,accessToken);
        assertEquals("1",listResponse.path("errcode").toString());
    }
}
