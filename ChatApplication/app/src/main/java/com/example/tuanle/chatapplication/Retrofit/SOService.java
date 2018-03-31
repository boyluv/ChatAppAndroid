package com.example.tuanle.chatapplication.Retrofit;

import com.example.tuanle.chatapplication.Response.LogInResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tuanle on 3/4/18.
 */

public interface SOService {
    @GET("/user/login")
    Call<LogInResponse> getDetail(@Query("name") String userName, @Query("pass") String password);
}
