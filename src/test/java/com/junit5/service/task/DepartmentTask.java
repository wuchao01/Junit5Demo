package com.junit5.service.task;

import com.junit5.service.apiobject.DepartmentObject;
import io.restassured.response.Response;

import java.util.ArrayList;

public class DepartmentTask {
    public static void clearDepartmentTask(String accessToken){
        Response listResponse = DepartmentObject.departmentList("1",accessToken);
        ArrayList<Integer> departmentList = listResponse.path("department.id");
        for (int departmentId:departmentList){
            if (1 == departmentId){
                continue;
            }
            DepartmentObject.deleteDepartment(String.valueOf(departmentId),accessToken);
        }
    }
}
