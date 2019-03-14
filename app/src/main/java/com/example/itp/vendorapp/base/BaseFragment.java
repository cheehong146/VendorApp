package com.example.itp.vendorapp.base;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    FragmentHelper fragmentHelper = new FragmentHelper(getActivity());


    @Override
    public void onClick(View v) {

    }

    public abstract void initComponents();

    public abstract void bindComponents();

    public abstract void setupListener();

    public void loadFragment(Fragment fragment, String parentLayout, int frameLayout) {
        fragmentHelper.loadFragment(getChildFragmentManager(), fragment, parentLayout, frameLayout);
    }

    public boolean popFragment() {
        boolean isPop = false;
        isPop = fragmentHelper.popFragment(getChildFragmentManager());
        return isPop;
    }

    public void initFragment(Fragment fragment, int frameToBePopulated) {
        fragmentHelper.initFragment(getChildFragmentManager(), fragment, frameToBePopulated);
    }

    public void replaceFragment(Fragment fragment, int frameLayout) {
        fragmentHelper.replaceFragment(getChildFragmentManager(), fragment, frameLayout);
    }

    public void replaceFragment(Fragment fragment, int frameLayout, String fragNameInBackStack) {
        fragmentHelper.replaceFragment(getChildFragmentManager(), fragment, frameLayout, fragNameInBackStack);
    }

}
