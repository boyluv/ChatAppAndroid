package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveRequestResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

}
