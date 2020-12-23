package com.junit5.service.task;

import com.junit5.service.apiobject.UserObject;
import io.restassured.response.Response;

import java.util.ArrayList;

public class UserTask {
    //删除列表下除指定用户的用户
    public static void clearUserTask(String accessToken){
        Response response = UserObject.listUser("1",accessToken);
        ArrayList<String> userList = response.path("userlist.userid");
        for (String userId:userList){
            if ("WuChao".equals(userId)){
                continue;
            }
            UserObject.deleteUser(userId,accessToken);
        }
    }
}
