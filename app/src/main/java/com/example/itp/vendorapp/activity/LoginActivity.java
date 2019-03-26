package com.example.itp.vendorapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityLoginBinding;
import com.example.itp.vendorapp.fragment.login.SignInFragment;
import com.example.itp.vendorapp.fragment.login.SignUpFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    boolean isSignInFragInit;

    SignInFragment signInFragment;
    SignUpFragment signUpFragment;
    /**
     * callback for facebook login api
     */
    CallbackManager callbackManager;
    boolean isFbCallbackSetup = false;

    String accessTokenStr;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        setupListener();
        initSignInFragment();

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
                if (isSignInFragInit) {
                    fragmentHelper.replaceFragment(getSupportFragmentManager(), signInFragment, R.id.frame_login);
                } else {
                    signInFragment = initSignInFragment();
                }
                setTabTextViewBold("Sign in");
                break;
            case R.id.tv_sign_up:
                if (signUpFragment == null) {
                    signUpFragment = initSignUpFragment();
                } else {
                    fragmentHelper.replaceFragment(getSupportFragmentManager(), signUpFragment, R.id.frame_login);
                }
                setTabTextViewBold("Sign up");
                break;

        }
    }

    private void setTabTextViewBold(String fragmentName) {
        if (fragmentName.equalsIgnoreCase("Sign in")) {
            binding.tvSignIn.setTypeface(null, Typeface.BOLD);
            binding.tvSignUp.setTypeface(null, Typeface.NORMAL);
        } else if (fragmentName.equalsIgnoreCase("Sign up")) {
            binding.tvSignIn.setTypeface(null, Typeface.NORMAL);
            binding.tvSignUp.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public void onBackPressed() {
        //TODO implement double click to exit
        toastMsg("TESTER", false);
    }

    private SignInFragment initSignInFragment() {
        SignInFragment signInFragment = SignInFragment.newInstance();
        signInFragment.setupListener(new SignInFragment.FragmentListener() {
            @Override
            public void onFacebookLoginClick(LoginButton fbLoginButton) {
                //TODO FB login
                //simulate a click on facebook login button and set callback, to retrieve user facebook data
                if (!isFbCallbackSetup) {
                    setFbLoginCallback(fbLoginButton);
                    isFbCallbackSetup = true;
                }
                fbLoginButton.performClick();
            }

            @Override
            public void onLoginClick() {
                startNewActivityWithFinish(LoginActivity.this, MainActivity.class);
            }

            @Override
            public void onForgetPasswordClick() {

            }
        });
        fragmentHelper.initFragment(getSupportFragmentManager(), signInFragment, R.id.frame_login);
        return signInFragment;
    }

    private SignUpFragment initSignUpFragment() {
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        fragmentHelper.replaceFragment(getSupportFragmentManager(), signUpFragment, R.id.frame_login, "SignUpFragment");
        return signUpFragment;
    }

    private void setFbLoginCallback(LoginButton fbLoginButton) {
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton.setReadPermissions("public_profile", "email");

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken != null) {
                    //users is logged in
                    toastMsg("user is logged in ", false);
                    accessTokenStr = currentAccessToken.getToken();
                    loadUserProfile(currentAccessToken);
                } else {
                    //users is logged out
                    toastMsg("user is logged out ", false);
                    accessTokenStr = oldAccessToken.getToken();
//                    facebookListItem.setFb_token(oldAccessToken.getToken());
                }
            }
        };

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                loadUserProfile(accessToken);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * init a new GraphRequest to get users data from an accessToken
     *
     * @param accessToken
     */
    private void loadUserProfile(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    //Get user data from facebook API here
                    String firstName = object.getString("first_name");
                    String lastName = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String profilePicUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";


                    Log.d(TAG, "loadUserProfile: " + id);
                    Log.d(TAG, "loadUserProfile: " + firstName);
                    Log.d(TAG, "loadUserProfile: " + lastName);
                    Log.d(TAG, "loadUserProfile: " + email);
                    Log.d(TAG, "loadUserProfile: " + profilePicUrl);
                } catch (JSONException e) {
                    e.printStackTrace();//TODO EH
                }
            }
        });
        Bundle args = new Bundle();
        args.putString("fields", "id, name, first_name, last_name, email, gender, birthday, picture.type(large)");
        graphRequest.setParameters(args);
        graphRequest.executeAsync();
    }


//    private void onSuccessfulLogin(LoginUser user){
//
//    }

//    TODO implement a login user model based on api


}
