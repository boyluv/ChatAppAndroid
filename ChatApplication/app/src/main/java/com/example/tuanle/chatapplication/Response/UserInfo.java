package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    public String getUser_id() {
        return user_id;
    }
}
