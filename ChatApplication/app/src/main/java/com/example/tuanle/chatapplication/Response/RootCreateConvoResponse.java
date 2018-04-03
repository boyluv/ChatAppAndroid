package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootCreateConvoResponse {
    @SerializedName("data")
    @Expose
    private List<CreateConvoResponse> results ;

    @SerializedName("status")
    @Expose
    private String status;

    public List<CreateConvoResponse> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
