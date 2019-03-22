package com.example.itp.vendorapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;

public abstract class BaseActivity extends AppCompatActivity {

    public final String SHARED_PREF_USER = "UserDataFile";

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

    /**
     * toast a msg
     *
     * @param msg
     * @param isLongToast
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


    public void initViewModel() {
    }

    public void bindComponents() {
    }

    public void initComponents() {

    }

    public abstract void setupListener();

}
