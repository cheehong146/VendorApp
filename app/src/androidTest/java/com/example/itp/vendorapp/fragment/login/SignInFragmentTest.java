package com.example.itp.vendorapp.fragment.login;

import android.support.test.rule.ActivityTestRule;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class SignInFragmentTest {


    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Before
    public void setUp() throws Exception {
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void onClick() {
        onView(withId(R.id.btn_facebook_login)).perform(click());
    }
}