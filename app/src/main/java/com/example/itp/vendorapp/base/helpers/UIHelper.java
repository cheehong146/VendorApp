package com.example.itp.vendorapp.base.helpers;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class UIHelper {

    Activity activity;

    public UIHelper(Activity activity) {
        this.activity = activity;
    }

    public void setFullScreen() {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void setStatusBarColor(int color) {
        Window window = activity.getWindow();
        window.setStatusBarColor(color);
    }

    public void setStatusBarTransparent(boolean changeStatusBarTintToDark) {
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        boolean on = true;
        boolean set = false;

        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            on = true;
            set = true;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            on = false;
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            set = true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = activity.getWindow().getDecorView();
            if (changeStatusBarTintToDark) {
                decor.setSystemUiVisibility(decor.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        if (set) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    public void setPaddingTopBelowStatusBar(View v) {
        //get status bar height
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.height = result;
        v.setLayoutParams(layoutParams);
    }

    public int getActionBarHeight() {
        // Calculate ActionBar's height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
