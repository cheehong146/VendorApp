package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivitySplashScreenBinding;
import com.facebook.AccessToken;

public class SplashScreenActivity extends BaseActivity implements View.OnClickListener {

    private final int SPLASH_SCREEN_TIME = 2000;

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SplashScreenActivity.this, R.layout.activity_splash_screen);

        setupNextActivity();
    }

    private void setupNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoginBefore()) {
                    startNewActivityWithFinish(SplashScreenActivity.this, MainActivity.class);
                } else {
                    startNewActivityWithFinish(SplashScreenActivity.this, LoginActivity.class);
                }
            }
        }, SPLASH_SCREEN_TIME);
    }

    @Override
    public void setupListener() {

    }


    @Override
    public void onClick(View v) {

    }

    private boolean isLoginBefore() {
        return false;
    }

    private boolean isLoginBeforeUsingFb() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        return isLoggedIn;
    }

    private boolean isLoginBeforeUsingEmail() {
//        boolean isLoginBefore =
        return false;
    }

}
