package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import static com.example.quiz.enums.Constants.FIRST_LOGIN;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.screenWorkflow();
    }

    private void screenWorkflow() {
        Boolean firstLogin = this.isFirstLogin();
        if (firstLogin) {
            int timer = this.splashScreenTime(true);
            this.onDebouncedStartApp(timer);
        } else {
            setTheme(R.style.AppTheme);
            this.onStartApp();
        }
    }

    private boolean isFirstLogin() {
        SharedPreferences store = getPreferences(Context.MODE_PRIVATE);
        return store.getBoolean(FIRST_LOGIN.getValor(), true);
    }

    private int splashScreenTime(Boolean mustShow) {
        SharedPreferences store = getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = store.edit();
        if (mustShow) {
            editor.putBoolean(FIRST_LOGIN.getValor(), false);
            editor.commit();
            return 1000;
        }
        return 0;
    }

    private void onStartApp() {
        Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void onDebouncedStartApp(int time) {
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                onStartApp();
            }
        }, time);
    }
}