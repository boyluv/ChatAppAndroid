package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailConvoResponse {
    @SerializedName("data")
    @Expose
    private List<Message> results ;

    @SerializedName("status")
    @Expose
    private String status;

    public List<Message> getResults() {
        return results;
    }

    public void setResults(List<Message> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
