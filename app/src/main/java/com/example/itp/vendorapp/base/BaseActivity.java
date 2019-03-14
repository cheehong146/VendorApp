package com.example.itp.vendorapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    public void startNewActivityWithFinish(Activity toFinish, Intent intentToStart){
        toFinish.finish();
        startActivity(intentToStart);
    }

    public void startNewActivityWithoutFinish(Intent intent){
        startActivity(intent);
    }

    public void startActivityClearTop(Context context, Class goToClass){
        Intent intent = new Intent(context, goToClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public abstract void initComponents();

    public abstract void bindComponents();

    public abstract void setupListener();

}
