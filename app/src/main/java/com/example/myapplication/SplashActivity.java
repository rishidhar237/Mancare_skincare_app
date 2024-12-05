package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Navigate to Login or Signup screen after 15 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After splash screen, go to the LoginActivity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish(); // Finish the SplashActivity so user can't return to it
            }
        }, 3000); // 3000ms = 3 seconds
    }
}
