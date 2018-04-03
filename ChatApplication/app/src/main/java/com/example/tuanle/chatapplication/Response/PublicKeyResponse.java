package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicKeyResponse {
    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("status")
    @Expose
    private String status;

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
