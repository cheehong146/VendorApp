package com.example.itp.vendorapp.base.helpers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {

    Activity activity;

    public FragmentHelper(Activity activity) {
        this.activity = activity;
    }


    /**
     *  change fragment of the current tab
     * @param fm
     * @param fragment
     * @param parentLayout
     * @param frameLayout
     */
    public void loadFragment(FragmentManager fm, Fragment fragment, String parentLayout, int frameLayout) {
        Fragment curFrame = fm.findFragmentById(frameLayout);

        //create a fragment manager
        FragmentTransaction ft = fm.beginTransaction();

        //replace the frameLayout with a new fragment
        ft.add(frameLayout, fragment);
        ft.hide(curFrame);
        ft.addToBackStack(parentLayout);
        ft.commit(); // save the changes
    }

    public void replaceFragment(FragmentManager fm, Fragment fragment, int frameLayout) {
        FragmentTransaction ft = fm.beginTransaction();

        //replacing curring frag
        ft.replace(frameLayout, fragment);
        ft.commit();
    }

    public void replaceFragment(FragmentManager fm, Fragment fragment, int frameLayout, String fragNameInBackStack) {
        FragmentTransaction ft = fm.beginTransaction();

        //replacing curring frag
        ft.replace(frameLayout, fragment);
        ft.addToBackStack(fragNameInBackStack);
        ft.commit();
    }

    /**
     * pop frame from backstack
     * @param fm
     * @return
     */
    public boolean popFragment(FragmentManager fm) {
        boolean isPop = false;
        if (fm.getBackStackEntryCount() > 0) {
            isPop = fm.popBackStackImmediate();//return true if the top frag on the stack is pop
        }
        return isPop;
    }

    /**
     * init fragment for new tab
     * @param fm
     * @param fragment
     * @param frameLayout
     */
    public void initFragment(FragmentManager fm, Fragment fragment, int frameLayout) {
        FragmentTransaction ft = fm.beginTransaction();

        //add a frag onto the frameLayout
        ft.add(frameLayout, fragment);
        ft.commit();
    }

//    public void initFragmentAndAddToBackStack (FragmentManager fm, Fragment fragment, int frameLayout, String fragNameInStack){
//        FragmentTransaction ft = fm.beginTransaction();
//
//        //add a frag onto the frameLayout
//        ft.add(frameLayout, fragment);
//        ft.addToBackStack(fragNameInStack);
//        ft.commit();
//    }

    public void showDialogFragment(FragmentManager fm, Fragment dialogFragment, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(dialogFragment, tag);
        ft.commit();
    }
}
