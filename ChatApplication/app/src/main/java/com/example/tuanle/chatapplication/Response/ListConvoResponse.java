package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListConvoResponse {
    @SerializedName("data")
    @Expose
    private List<ConvoResponse> results ;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("isSignin")
    @Expose
    private boolean isSignin;

    public List<ConvoResponse> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSignin() {
        return isSignin;
    }

    public void setResults(List<ConvoResponse> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSignin(boolean signin) {
        isSignin = signin;
    }
}
