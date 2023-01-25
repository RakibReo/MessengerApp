package com.example.sayhi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sayhi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextPage();
            }

        },2000);
    }
    private void nextPage() {
        if (firebaseUser != null){      //firebase jodi null data na thake mean login thakle direct dashboard jaite hbe
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }
    }
}