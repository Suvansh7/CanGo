package com.example.cango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        lottie = findViewById(R.id.animationView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, splash.class));
                finish();
            }
        }, 5000);
    }
}