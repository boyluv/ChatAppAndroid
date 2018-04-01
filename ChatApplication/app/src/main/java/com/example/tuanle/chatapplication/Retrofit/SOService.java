package com.example.tuanle.chatapplication.Retrofit;

import com.example.tuanle.chatapplication.Request.MessageRequest;
import com.example.tuanle.chatapplication.Request.SignupRequest;
import com.example.tuanle.chatapplication.Response.ConvoResponse;
import com.example.tuanle.chatapplication.Response.DetailConvoResponse;
import com.example.tuanle.chatapplication.Response.ListConvoResponse;
import com.example.tuanle.chatapplication.Response.LogInResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tuanle on 3/4/18.
 */

public interface SOService {
    //Log in and return user id
    @GET("/user/login")
    Call<LogInResponse> logIn(@Query("name") String userName, @Query("pass") String password);

    //Get list all conservation
    //TODO
    @GET("/listconvo")
    Call<ListConvoResponse> getListConvo(@Query("id") String userId);

    //Get a detail conservation
    @GET("/convo/{id}")
    Call<DetailConvoResponse> getDetailConvo(@Path("id") int id);

    //Sign up User
    //TODO--Fix id number by remove it
    @POST("/user/add")
    Call<SignupRequest> signUp(@Query("name") String name,
                               @Query("pass") String pass,@Query("isUser") boolean isUser);

    //Add new message
    //TODO--Fix id number by remove it
    @POST("/user/replies")
    Call<MessageRequest> addMessage(@Query("rep_message") String rep_message,
                                @Query("ref_convo_id") int ref_convo_id, @Query("rep_by") int rep_by);
}
