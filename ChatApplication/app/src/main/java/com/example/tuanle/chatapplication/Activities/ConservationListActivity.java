package com.example.tuanle.chatapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tuanle.chatapplication.Adapters.ConservationListAdapter;
import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Response.ConvoResponse;
import com.example.tuanle.chatapplication.Response.ListConvoResponse;
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
    }
}
