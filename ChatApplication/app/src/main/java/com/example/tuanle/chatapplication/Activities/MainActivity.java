package com.example.tuanle.chatapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tuanle.chatapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_signIn;
    private Button btn_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
