package com.example.tuanle.chatapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConvoResponse {
    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("rep_message")
    @Expose
    private String rep_message;

    @SerializedName("related_to_convo")
    @Expose
    private String related_to_convo;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRep_message() {
        return rep_message;
    }

    public void setRep_message(String rep_message) {
        this.rep_message = rep_message;
    }

    public String getRelated_to_convo() {
        return related_to_convo;
    }

    public void setRelated_to_convo(String related_to_convo) {
        this.related_to_convo = related_to_convo;
    }
}
