package io.github.blist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/* Modified at
    Nov 29, 2019 */
public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent activity = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(activity);
                finish();
            }
        }, 2000);
    }
}