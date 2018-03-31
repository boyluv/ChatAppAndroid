package com.example.tuanle.chatapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tuanle.chatapplication.Adapters.DetailConservationAdapter;
import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Request.MessageRequest;
import com.example.tuanle.chatapplication.Response.DetailConvoResponse;
import com.example.tuanle.chatapplication.Response.Message;
import com.example.tuanle.chatapplication.Retrofit.ApiUtils;
import com.example.tuanle.chatapplication.Retrofit.SOService;
import com.example.tuanle.chatapplication.Utils.Constants.ExtraKey;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailConservationActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private DetailConservationAdapter mAdapter;
    private SOService mService;
    private ImageButton imgBtnSend;
    private EditText mMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_conservation);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_conservation);
        imgBtnSend = (ImageButton) findViewById(R.id.imgbtn_send);
        mMessage = (EditText) findViewById(R.id.edt_message);
        imgBtnSend.setOnClickListener(this);

        String convo_id = getIntent().getStringExtra(ExtraKey.CONSERVATION_ID);
        Log.d("DetailConvo", "This is convo Id " + convo_id);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setStackFromEnd(true); //always show list from bottom
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new DetailConservationAdapter(new ArrayList<Message>());
        mRecyclerView.setAdapter(mAdapter);

        loadMessage();



    }

    private void loadMessage(){
        mService = ApiUtils.getSOService();
        mService.getDetailConvo(1).enqueue(new Callback<DetailConvoResponse>() {
            @Override
            public void onResponse(Call<DetailConvoResponse> call, Response<DetailConvoResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("listConvo", response.body().getResults().get(0).getUser_name());
                    mAdapter.setConservation(new ArrayList<Message>(response.body().getResults()));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DetailConvoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_send:
                final String mes = mMessage.getText().toString();
                mService =ApiUtils.getSOService();
                //TODO------Update and remove id Message
                mService.addMessage(69,mes ,1,1).enqueue(new Callback<MessageRequest>() {
                    @Override
                    public void onResponse(Call<MessageRequest> call, Response<MessageRequest> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(), "Send success " + mes,
                                    Toast.LENGTH_SHORT).show();
                            mMessage.setText("");
                            loadMessage();
                        }

                        else
                            Toast.makeText(getBaseContext(), "Send failed",
                                    Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MessageRequest> call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
