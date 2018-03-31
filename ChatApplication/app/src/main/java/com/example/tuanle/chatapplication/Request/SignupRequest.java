package com.example.tuanle.chatapplication.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan Le on 7/26/2017.
 */

public class SignupRequest {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("isUser")
    @Expose
    private boolean isUser;

    public SignupRequest(int id, String name, String password, boolean isUser) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isUser = isUser;
    }
}
