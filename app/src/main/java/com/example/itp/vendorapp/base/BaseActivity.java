package com.example.itp.vendorapp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class BaseActivity extends AppCompatActivity {

    public final String SHARED_PREF_USER = "UserDataFile";

    public FragmentHelper fragmentHelper = new FragmentHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void startNewActivityWithFinish(Activity toFinish, Class goToClass) {
        finish();
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

    /**
     * toast a msg
     *
     * @param msg   the message to be displayed
     * @param isLongToast true for long toast, false for short toast
     */
    public void toastMsg(String msg, boolean isLongToast) {
        final int TOAST_LENGTH = isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(this, msg, TOAST_LENGTH);
    }

    /**
     * store data in SharedPreference
     *
     * @param sharedPrefFile the shared preference name to be accesses and edited, should be calling the final value in the BaseActivity class
     * @param dataKey        data key
     * @param dataValue      data value
     */
    public void storeDataInSharedPreference(String sharedPrefFile, String dataKey, String dataValue) {
        SharedPreferences.Editor editor = getSharedPreferences(sharedPrefFile, MODE_PRIVATE).edit();
        editor.putString(dataKey, dataValue);
        editor.apply();
    }

    public void createKProgressHUD(Context context) {
        KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Loading...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.2f)
                .show();
    }


    public void initViewModel() {
    }

    public void bindComponents() {
    }

    public void initComponents() {

    }

    public abstract void setupListener();

}
