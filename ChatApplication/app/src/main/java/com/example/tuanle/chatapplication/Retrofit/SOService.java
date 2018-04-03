package com.example.tuanle.chatapplication.Retrofit;

import com.example.tuanle.chatapplication.Request.MessageRequest;
import com.example.tuanle.chatapplication.Request.SignupRequest;
import com.example.tuanle.chatapplication.Response.ConvoResponse;
import com.example.tuanle.chatapplication.Response.CreateCategoryResponse;
import com.example.tuanle.chatapplication.Response.CreateConvoResponse;
import com.example.tuanle.chatapplication.Response.DetailConvoResponse;
import com.example.tuanle.chatapplication.Response.KeyResponse;
import com.example.tuanle.chatapplication.Response.ListConvoResponse;
import com.example.tuanle.chatapplication.Response.LogInResponse;
import com.example.tuanle.chatapplication.Response.RemoveRequestResponse;
import com.example.tuanle.chatapplication.Response.RequestCommingResponse;
import com.example.tuanle.chatapplication.Response.RequestDetail;
import com.example.tuanle.chatapplication.Response.RootCreateConvoResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
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
                               @Query("pass") String pass,@Query("pb_key") String pb_key,@Query("ref_cat_id") int ref_cat_id);

    //Add new message
    //TODO--Fix id number by remove it
    @POST("/user/replies")
    Call<MessageRequest> addMessage(@Query("rep_message") String rep_message,
                                @Query("ref_convo_id") int ref_convo_id, @Query("rep_by") int rep_by);

    //Log in and return user id
    @GET("/key")
    Call<KeyResponse> getKey();

    //Get all of your request
    @GET("/request/{id}")
    Call<RequestCommingResponse> requestComming(@Path("id") int id);

    //Add new Request
    @POST("/request/add")
    Call<RequestDetail> sendRequest(@Query("req_sender") int req_sender,
                                    @Query("req_receiver") int req_receiver, @Query("message") String message);

    //Get all of your request
    @DELETE("/request/remove/{id}")
    Call<RemoveRequestResponse> deleteRequest(@Path("id") int id);

    @POST("/convoadd")
    Call<RootCreateConvoResponse> createConvo(@Query("convo_cat") int convo_cat,
                                              @Query("convo_by") int convo_by);

    @POST("/cate/add")
    Call<CreateCategoryResponse> createCategory(@Query("cat_name") String cat_name,
                                             @Query("cat_description") String cat_description);
}
