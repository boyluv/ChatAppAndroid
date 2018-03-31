package com.example.tuanle.chatapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tuanle.chatapplication.Adapters.DetailConservationAdapter;
import com.example.tuanle.chatapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailConservationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DetailConservationAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_conservation);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_conservation);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setStackFromEnd(true); //always show list from bottom
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new DetailConservationAdapter(new ArrayList<String>(Arrays.asList("Msg 1","Msg 2","Msg 3","Msg 4","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5","Msg 5")));
        mRecyclerView.setAdapter(mAdapter);
    }
}
