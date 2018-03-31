package com.example.tuanle.chatapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Response.LogInResponse;
import com.example.tuanle.chatapplication.Retrofit.ApiUtils;
import com.example.tuanle.chatapplication.Retrofit.SOService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_signIn;
    private Button btn_signUp;
    private SOService mService;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.edt_name);
        password = (EditText) findViewById(R.id.edt_password);
        btn_signIn =  findViewById(R.id.signin_btn);
        btn_signUp =  findViewById(R.id.signup_btn);
        btn_signIn.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
    }
    private void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
//        send Intent with message if you need
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private boolean validateUser(){

        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin_btn:
                if(validateUser())
                {
                    mService = ApiUtils.getSOService();
                    mService.getDetail(userName.getText().toString(),password.getText().toString()).enqueue(new Callback<LogInResponse>() {
                        @Override
                        public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                            if(response.isSuccessful()) {
                                if(response.body().isSignin()){
                                    try{
                                        Log.d("UserLogin", response.body().getResults().get(0).getUser_id());

                                    }
                                    catch (NullPointerException e){
                                        Log.d("UserLogin", "Null add ress");

                                    }
                                }
                                else
                                    Log.e("UserLogin","Wrong user or password");

                            }
                            else
                                Log.d("UserLogin",response.toString());
                        }

                        @Override
                        public void onFailure(Call<LogInResponse> call, Throwable t) {
                            Log.d("UserLogin","Log in failed");
                        }
                    });


                    Intent intent = new Intent(this, ConservationListActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.signup_btn:
                signUp();
                break;
            default:
                break;
        }
    }
}
