package com.example.itp.vendorapp.base;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itp.vendorapp.base.helpers.FragmentHelper;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    FragmentHelper fragmentHelper = new FragmentHelper(getActivity());


    @Override
    public void onClick(View v) {

    }

    /**
     * bind each view components
     *
     * @param rootView
     */
    public void bindComponents(ViewGroup rootView) {
    }

    /**
     * inflate layout and return it
     *
     * @param inflater
     * @param container
     * @param layout
     * @return
     */
    public ViewGroup getRootView(LayoutInflater inflater, ViewGroup container, int layout) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(layout, container, false);

        //bind components to xml
        bindComponents(rootView);

        setupListener();
        return rootView;
    }

    /**
     * setup listener for  each view component
     */
    public abstract void setupListener();

    /**
     * load fragment into frameLayout while keeping parentLayout as a reference
     *
     * @param fragment
     * @param parentLayout
     * @param frameLayout
     */
    public void loadFragment(Fragment fragment, String parentLayout, int frameLayout) {
        fragmentHelper.loadFragment(getChildFragmentManager(), fragment, parentLayout, frameLayout);
    }

    /**
     * pop a fragment from the child fragment manager and return true if popped, else return false
     *
     * @return
     */
    public boolean popFragment() {
        boolean isPop = false;
        isPop = fragmentHelper.popFragment(getChildFragmentManager());
        return isPop;
    }

    /**
     * init the first fragment for the frame, use loadFragment() when putting child fragment after the intiFragment, unless it's a completely non-related fragment
     *
     * @param fragment fragment to be init
     * @param frameToBePopulated frame of the fragment target
     * @param fragName  name of the fragment to be init
     */
    public void initFragment(Fragment fragment, int frameToBePopulated, String fragName) {
        fragmentHelper.initFragment(getChildFragmentManager(), fragment, frameToBePopulated, fragName);
    }

    /**
     * replace fragment for frameLayout, this does not destroy other frame
     *
     * @param fragment
     * @param frameLayout
     */
    public void replaceFragment(Fragment fragment, int frameLayout) {
        fragmentHelper.replaceFragment(getChildFragmentManager(), fragment, frameLayout);
    }

    /**
     * replace fragment for frameLayout, this does not destroy other frame, with reference to the frag name in the backstack
     *
     * @param fragment
     * @param frameLayout
     * @param fragNameInBackStack
     */
    public void replaceFragment(Fragment fragment, int frameLayout, String fragNameInBackStack) {
        fragmentHelper.replaceFragment(getChildFragmentManager(), fragment, frameLayout, fragNameInBackStack);
    }


}
