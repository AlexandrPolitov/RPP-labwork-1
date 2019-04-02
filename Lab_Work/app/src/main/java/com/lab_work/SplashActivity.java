package com.lab_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final long SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(SPLASH_TIME);
                        if(SplashActivity.this.hasWindowFocus()) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }

    }
}

