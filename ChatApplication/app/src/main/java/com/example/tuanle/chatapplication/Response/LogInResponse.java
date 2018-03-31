package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tuanle on 3/4/18.
 */
public class LogInResponse {

    @SerializedName("results")
    @Expose
    private List<UserInfo> results ;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("isSignin")
    @Expose
    private boolean isSignin;

    public List<UserInfo> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSignin() {
        return isSignin;
    }

    public void setResults(List<UserInfo> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSignin(boolean signin) {
        isSignin = signin;
    }
}
