package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootListAdminResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<ListAdminResponse> data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ListAdminResponse> getData() {
        return data;
    }
}
