package com.example.tuanle.chatapplication.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageRequest {
    @SerializedName("rep_id")
    @Expose
    private int rep_id;

    @SerializedName("rep_message")
    @Expose
    private String rep_message;

    @SerializedName("related_to_convo")
    @Expose
    private int related_to_convo;

    @SerializedName("rep_by")
    @Expose
    private int rep_by;
}
