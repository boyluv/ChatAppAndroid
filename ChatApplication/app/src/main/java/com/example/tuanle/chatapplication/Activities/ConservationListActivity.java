package com.example.tuanle.chatapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tuanle.chatapplication.Adapters.ConservationListAdapter;
import com.example.tuanle.chatapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConservationListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ConservationListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_conservation_lists);

        mRecyclerView.setHasFixedSize(true);



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> list = new ArrayList<>(Arrays.asList("B","H","T","B","H","T","B","H","T","B","H","T"));
        mAdapter = new ConservationListAdapter(getBaseContext(),list);
        mRecyclerView.setAdapter(mAdapter);
    }
}
