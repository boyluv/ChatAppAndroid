package com.example.tuanle.chatapplication.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tuanle.chatapplication.Adapters.ConservationListAdapter;
import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Response.ConvoResponse;
import com.example.tuanle.chatapplication.Response.CreateConvoResponse;
import com.example.tuanle.chatapplication.Response.KeyResponse;
import com.example.tuanle.chatapplication.Response.ListConvoResponse;
import com.example.tuanle.chatapplication.Response.RemoveRequestResponse;
import com.example.tuanle.chatapplication.Response.RequestCommingResponse;
import com.example.tuanle.chatapplication.Response.RequestDetail;
import com.example.tuanle.chatapplication.Retrofit.ApiUtils;
import com.example.tuanle.chatapplication.Retrofit.SOService;
import com.example.tuanle.chatapplication.Utils.Constants.ExtraKey;
import com.example.tuanle.chatapplication.Utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConservationListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView mRvCategories;
    private ConservationListAdapter mAdapter;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        String userId = PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,null);
        Log.d("UserLogin", "New activity " + userId);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_conservation_lists);
//        TODO
//        mRvCategories = (RecyclerView) findViewById(R.id.rv_vertical_conservation_lists);

//        //Initialize list categories
//        mRvCategories.setHasFixedSize(true);
//        mRvCategories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        mAdapter = new ConservationListAdapter(getBaseContext(),new ArrayList<ConvoResponse>());
//        mRvCategories.setAdapter(mAdapter);


        //Initialize history chat
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ConservationListAdapter(getBaseContext(),new ArrayList<ConvoResponse>());
        mRecyclerView.setAdapter(mAdapter);
        loadListConvo();

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestToChat();
                //testMethod();
            }
        });
        new ListenRequest().execute("","","");


    }

    private void testMethod(){
        SOService createService = ApiUtils.getSOService();
        createService.createConvo(9,8).enqueue(new Callback<CreateConvoResponse>() {
            @Override
            public void onResponse(Call<CreateConvoResponse> call, Response<CreateConvoResponse> response) {
                if(response.isSuccessful()){
                    Log.d("RequestSend","create convo failed");

//                    final int convo_id = response.body().getConvo_id();
//                    //Delete Request
//                    SOService curService = ApiUtils.getSOService();
//                    curService.deleteRequest(curUserId).enqueue(new Callback<RemoveRequestResponse>() {
//                        @Override
//                        public void onResponse(Call<RemoveRequestResponse> call, Response<RemoveRequestResponse> response) {
//                            //Add new Request
//                            int idUser = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));
//                            mService = ApiUtils.getSOService();
//                            mService.sendRequest(idUser,receiverInSecondRequest,"Second_CurKey_1").enqueue(new Callback<RequestDetail>() {
//                                @Override
//                                public void onResponse(Call<RequestDetail> call, Response<RequestDetail> response) {
//                                    Log.d("Request","Send success");
//
//                                    Intent intent = new Intent(getBaseContext(), DetailConservationActivity.class);
//                                    Log.d("DetailConvo", "This is convo Id " + convo_id+"");
//                                    intent.putExtra(ExtraKey.CONSERVATION_ID, convo_id);
//                                    startActivity(intent);
//
//                                    Toast.makeText(getBaseContext(),"Now you (Receiver) can change activity to "+convo_id,Toast.LENGTH_LONG).show();
//                                }
//
//                                @Override
//                                public void onFailure(Call<RequestDetail> call, Throwable t) {
//                                    Log.d("RequestSend","failed");
//
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<RemoveRequestResponse> call, Throwable t) {
//                            Log.d("RequestSend","delete failed");
//
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<CreateConvoResponse> call, Throwable t) {
                Log.d("RequestSend","create convo failed");

            }
        });
    }
    private void sendRequestToChat(){
        mService = ApiUtils.getSOService();
        int idUser = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));
        //TODO--Get Receiver Id
        mService.sendRequest(idUser,9,"First_CurKey").enqueue(new Callback<RequestDetail>() {
            @Override
            public void onResponse(Call<RequestDetail> call, Response<RequestDetail> response) {
                Log.d("Request","Send success");
            }

            @Override
            public void onFailure(Call<RequestDetail> call, Throwable t) {

            }
        });
    }
    //TODO--Important
    private String tt="temp";
    private Boolean isHaveNotification;

    RequestCommingResponse curResponse = null;
    private class ListenRequest extends AsyncTask<String, String, String> {
        protected String doInBackground(String... strings) {
            isHaveNotification = false;
            Log.d("Request","Start");

            mService = ApiUtils.getSOService();
            //This is your id
            int idUser = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));
            mService.requestComming(idUser).enqueue(new Callback<RequestCommingResponse>() {
                @Override
                public void onResponse(Call<RequestCommingResponse> call, Response<RequestCommingResponse> response) {
                    Log.d("Request","inside");
                    Log.d("Request","Receive " + response.body().isHaveNotification());
                    isHaveNotification = response.body().isHaveNotification();
                    if(isHaveNotification){
                        curResponse  = response.body();
                        Log.d("Request","Change activity to "+curResponse.getData().get(0).getReq_sender());

                        //TODO---Check this variable
                        final int receiverInSecondRequest =  Integer.parseInt(curResponse.getData().get(0).getReq_sender());
                        //Delete and change activity
                        String mess = curResponse.getData().get(0).getMessagedata();
                        final String[] separated = mess.split("_");
                        if(separated[0].equals("First")){
                            final int curUserId = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));

                            int curCat = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_CAT,"1"));
                            int curConvoBy = Integer.parseInt(curResponse.getData().get(0).getReq_sender());
                            //Create Convo_id

                            //TODO--Right now
                            SOService createService = ApiUtils.getSOService();
                            createService.createConvo(curCat,curConvoBy).enqueue(new Callback<CreateConvoResponse>() {
                                @Override
                                public void onResponse(Call<CreateConvoResponse> call, Response<CreateConvoResponse> response) {
                                    if(response.isSuccessful()){

                                        final int convo_id = response.body().getConvo_id();
                                        //Delete Request
                                        SOService curService = ApiUtils.getSOService();
                                        curService.deleteRequest(curUserId).enqueue(new Callback<RemoveRequestResponse>() {
                                            @Override
                                            public void onResponse(Call<RemoveRequestResponse> call, Response<RemoveRequestResponse> response) {
                                                //Add new Request
                                                int idUser = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));
                                                mService = ApiUtils.getSOService();
                                                mService.sendRequest(idUser,receiverInSecondRequest,"Second_CurKey_1").enqueue(new Callback<RequestDetail>() {
                                                    @Override
                                                    public void onResponse(Call<RequestDetail> call, Response<RequestDetail> response) {
                                                        Log.d("Request","Send success");

                                                        Intent intent = new Intent(getBaseContext(), DetailConservationActivity.class);
                                                        Log.d("DetailConvo", "This is convo Id " + convo_id+"");
                                                        intent.putExtra(ExtraKey.CONSERVATION_ID, convo_id);
                                                        startActivity(intent);

                                                        Toast.makeText(getBaseContext(),"Now you (Receiver) can change activity to "+convo_id,Toast.LENGTH_LONG).show();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<RequestDetail> call, Throwable t) {
                                                        Log.d("RequestSend","failed");

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(Call<RemoveRequestResponse> call, Throwable t) {
                                                Log.d("RequestSend","delete failed");

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<CreateConvoResponse> call, Throwable t) {
                                    Log.d("RequestSend","create convo failed");

                                }
                            });

                        }
                        else if(separated[0].equals("Second")){
                            if(separated[1].equals("CurKey"))
                            {
                                int curUserId = Integer.parseInt(PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,"1"));
                                // curUserId = 1;
                                SOService curService = ApiUtils.getSOService();
                                curService.deleteRequest(curUserId).enqueue(new Callback<RemoveRequestResponse>() {
                                    @Override
                                    public void onResponse(Call<RemoveRequestResponse> call, Response<RemoveRequestResponse> response) {
                                        int convo_id = Integer.parseInt(separated[2]);
                                        //Change to Activity with that id
                                        Intent intent = new Intent(getBaseContext(), DetailConservationActivity.class);
                                        Log.d("DetailConvo", "This is convo Id " + convo_id);
                                        intent.putExtra(ExtraKey.CONSERVATION_ID, convo_id+"");
                                        startActivity(intent);
                                        Toast.makeText(getBaseContext(),"Now you (Sender) can change activity to "+convo_id,Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call<RemoveRequestResponse> call, Throwable t) {

                                    }
                                });

                            }
                        }
                    }
                    else{
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                new ListenRequest().execute("","","");
                            }
                        }, 5000);
                    }
                        curResponse = null;
                }

                @Override
                public void onFailure(Call<RequestCommingResponse> call, Throwable t) {
                    Log.d("Request","failed inside");

                }
            });
            Log.d("Request","End");
            return null;
            //else
            //    return null;
        }

        protected void onProgressUpdate(String... String) {

        }

        protected void onPostExecute(String message) {

        }

    }

    private void loadListConvo(){
        mService = ApiUtils.getSOService();
        String userId = PreferenceUtils.getStringPref(getBaseContext(),ExtraKey.USER_ID,null);
        mService.getListConvo(userId).enqueue(new Callback<ListConvoResponse>() {
            @Override
            public void onResponse(Call<ListConvoResponse> call, Response<ListConvoResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("listConvo", response.body().getResults().get(0).getRep_message().toString());
                    mAdapter.setListConvo(new ArrayList<>(response.body().getResults()));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ListConvoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadListConvo();
        new ListenRequest().execute("","","");
    }
}
