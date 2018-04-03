package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootCheckConnectResponse {
    @SerializedName("data")
    @Expose
    private List<CheckConnectResponse> results ;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("haveConnect")
    @Expose
    private boolean haveConnect;

    public List<CheckConnectResponse> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public boolean isHaveConnect() {
        return haveConnect;
    }
}
