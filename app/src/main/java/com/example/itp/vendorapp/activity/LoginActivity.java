package com.example.itp.vendorapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityLoginBinding;
import com.example.itp.vendorapp.fragment.login.SignInFragment;
import com.example.itp.vendorapp.fragment.login.SignUpFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

//    /**
//     * callback for facebook login api
//     */
//    CallbackManager callbackManager;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

//        callbackManager = CallbackManager.Factory.create();

        setupListener();
//        setupFacebookLogin();

        initSignInFragment();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setupListener() {
        binding.tvSignIn.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in:
                initSignInFragment();
                break;
            case R.id.tv_sign_up:
                setSignUpFragment();
                break;

        }
    }

    private void initSignInFragment() {
        SignInFragment signInFragment = SignInFragment.newInstance();
        signInFragment.setupListener(new SignInFragment.FragmentListener() {
            @Override
            public void onFacebookLoginClick() {
                //TODO FB login
            }

            @Override
            public void onLoginClick() {
                startActivityClearTop(LoginActivity.this, MainActivity.class);
            }

            @Override
            public void onForgetPasswordClick() {

            }
        });
        fragmentHelper.initFragment(getSupportFragmentManager(), signInFragment, R.id.frame_login);
    }

    private void setSignUpFragment(){
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        fragmentHelper.replaceFragment(getSupportFragmentManager(), signUpFragment, R.id.frame_login, "SignUpFragment");
    }

//    private void setupFacebookLogin() {
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                //TODO finish facebook login
//
//                AccessToken accessToken = loginResult.getAccessToken();
//                GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        parseFacebookUserData(object);
//                    }
//                });
//
//                Bundle args = new Bundle();
//                args.putString("fields", "id, name, first_name, last_name, email, gender, birthday, picture.type(large)");
//                graphRequest.setParameters(args);
//                graphRequest.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//    }

//    private void parseFacebookUserData(JSONObject object) {
//        try {
//            Log.d(TAG, "parseFacebookUserData: " + object.getString("id"));
//            Log.d(TAG, "parseFacebookUserData: " + object.getString("name"));
//            Log.d(TAG, "parseFacebookUserData: " + object.getString("email"));
//            Log.d(TAG, "parseFacebookUserData: " + object.get("birthday"));
//
//            object.getJSONObject("picture").getJSONObject("data").getString("url");
//        } catch (JSONException e) {
//            e.printStackTrace();//TODO EH
//        }
//    }
}
