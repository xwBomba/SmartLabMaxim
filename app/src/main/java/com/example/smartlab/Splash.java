package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smartlab.R;
import com.example.smartlab.Session;

public class Splash extends AppCompatActivity
{
    private static final int SPLASH_SCREEN_TIMEOUT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(Splash.this, Session.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT * 1000);

    }
}