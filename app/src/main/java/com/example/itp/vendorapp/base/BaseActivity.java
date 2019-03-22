package com.example.itp.vendorapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;

public abstract class BaseActivity extends AppCompatActivity {

    public FragmentHelper fragmentHelper = new FragmentHelper(this);

    public void startNewActivityWithFinish(Activity toFinish, Class goToClass) {
      startActivity(new Intent(toFinish, goToClass));
    }

    public void startNewActivityWithoutFinish(Intent intent) {
        startActivity(intent);
    }

    public void startActivityClearTop(Context context, Class goToClass) {
        Intent intent = new Intent(context, goToClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void initViewModel() {
    }

    public void bindComponents() {
    }

    public void initComponents() {

    }

    public abstract void setupListener();

}
