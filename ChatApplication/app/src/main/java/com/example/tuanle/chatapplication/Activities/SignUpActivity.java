package com.example.tuanle.chatapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Request.SignupRequest;
import com.example.tuanle.chatapplication.Retrofit.ApiUtils;
import com.example.tuanle.chatapplication.Retrofit.SOService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSignUp;
    private EditText edtUserName;
    private EditText edtPass;
    private SOService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = (Button) findViewById(R.id.signup_btn);
        edtUserName = (EditText) findViewById(R.id.edt_name);
        edtPass = (EditText) findViewById(R.id.edt_password);

        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_btn:
                mService = ApiUtils.getSOService();
                //TODO-----Binh
                String name = edtUserName.getText().toString();
                String pass = edtPass.getText().toString();
                mService.signUp(name,pass,true).enqueue(new Callback<SignupRequest>() {
                    @Override
                    public void onResponse(Call<SignupRequest> call, Response<SignupRequest> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(), "Sign up success ",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        else
                            Toast.makeText(getBaseContext(), "Sign up failed",
                                    Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SignupRequest> call, Throwable t) {

                    }
                });
                break;
            default:
                    break;
        }
    }
}
