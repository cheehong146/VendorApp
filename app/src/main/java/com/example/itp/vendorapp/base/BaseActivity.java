package com.example.itp.vendorapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public final String SHARED_PREF_USER = "UserDataFile";
    //loading dialog related
    public KProgressHUD hud;
    public FragmentHelper fragmentHelper = new FragmentHelper(this);
    //for double tap on back to close
    boolean close;

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

    /**
     * Destroy all activity after the goToClass init and re-init it
     *
     * @param context   cur Context
     * @param goToClass class to init
     */
    public void startActivityClearTop(Context context, Class goToClass) {
        Intent intent = new Intent(context, goToClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Destroy all activity after the goToClass init and re-init it
     *
     * @param intent
     */
    public void startActivityClearTop(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /**
     * Destroy all activity after the goToClass init; brings back the goToClass activity without being re-init
     *
     * @param context   cur Context
     * @param goToClass class to init
     */
    public void startActivitySingleClearTop(Context context, Class goToClass) {
        Intent intent = new Intent(context, goToClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /**
     * toast a msg
     *
     * @param msg         the message to be displayed
     * @param isLongToast true for long toast, false for short toast
     */
    public void toastMsg(String msg, boolean isLongToast) {
        final int TOAST_LENGTH = isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(this, msg, TOAST_LENGTH).show();
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

    /**
     * show loading dialog
     *
     * @param title
     * @param msg
     */
    public void showLoadingDialog(String title, String msg) {
        if (hud != null) {
            hud.dismiss();
            hud = null;
        }
        if (msg != null) {
            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(title)
                    .setDetailsLabel(msg)
                    .setCancellable(false)
                    .setDimAmount(0.5f)
                    .show();
        } else {
            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(title)
                    .setDimAmount(0.5f)
                    .setCancellable(false)
                    .show();
        }
    }


    /**
     * Dismiss loading dialog
     */
    public void dismissLoadingDialog() {
        try {
            hud.dismiss();
            hud = null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * double tap the back button within 2 second to finish the cur activity
     */
    public void doubleBackToClose() {
        if (close) {
            finish();
        }
        close = true;
        toastMsg("Press again to exit", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                close = false;
            }
        }, 2000);
    }

    public boolean isCurFragmentInFrame(int frameId, String fragName) {
        if (getSupportFragmentManager().findFragmentByTag(fragName).equals("")) {

        }
        return false;
    }


    public void initViewModel() {
    }

    public void bindComponents() {
    }

    public void initComponents() {

    }

    public abstract void setupListener();

}
