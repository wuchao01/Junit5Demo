package com.junit5.service.apiobject;

import com.junit5.service.utils.FakerUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DepartmentObject {

    /**
     * 创建部门
     * @param accessToken 企业微信token
     */
    public static String createDepartment(String accessToken){
        String createName = "name" + FakerUtils.getTimeStamp();
        String createEnName = "en_name" + FakerUtils.getTimeStamp();
        Response createResponse = createDepartment(createName,createEnName,accessToken);
        String departmentId = createResponse.path("id") != null ? createResponse.path("id").toString():null;
        return departmentId;
    }

    /**
     * 创建部门
     * @param departmentName 部门名称
     * @param departmentEnName 部门英文名称
     * @param accessToken 企业微信token
     */
    public static Response createDepartment(String departmentName, String departmentEnName, String accessToken){
        Response creatResponse = given()
                .proxy(8888)
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"" + departmentName + "\",\n" +
                        "   \"name_en\": \"" + departmentEnName + "\",\n" +
                        "   \"parentid\": " + 1 + ",\n" +
                        "}")
                .queryParam("access_token", accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log()
                .all()
                .extract().response();
        return creatResponse;
    }

    /**
     * 修改部门
     * @param departmentName 部门名称
     * @param departmentEnName 部门英文名称
     * @param parentId 部门ID
     * @param accessToken 企业微信token
     */
    public static Response updateDepartment(String departmentName, String departmentEnName, String parentId, String accessToken) {
        Response updateResponse = given()
                .proxy(8888)
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"" + departmentName + "\",\n" +
                        "   \"name_en\": \"" + departmentEnName + "\",\n" +
                        "   \"parentid\": " + parentId + ",\n" +
                        "}")
                .queryParam("access_token", accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/Department/update")
                .then()
                .log()
                .all()
                .extract().response();
        return updateResponse;
    }

    /**
     * 删除部门
     * @param parentId 部门ID
     * @param accessToken 企业微信token
     */
    public static Response deleteDepartment(String parentId, String accessToken) {
        Response deleteResponse = given()
                .proxy(8888)
                .body("{\n" +
                        "   \"id\": " + parentId + ",\n" +
                        "}")
                .queryParam("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/Department/delete")
                .then()
                .log()
                .all()
                .extract().response();
        return deleteResponse;
    }

    /**
     * 查询部门列表
     * @param parentId 部门ID
     * @param accessToken 企业微信token
     */
    public static Response departmentList(String parentId,String accessToken){
        Response listResponse = given()
                .proxy(8888)
                .param("access_token", accessToken)
                .param("id",parentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/Department/list")
                .then()
                .log()
                .all()
                .extract().response();
        return listResponse;
    }
}