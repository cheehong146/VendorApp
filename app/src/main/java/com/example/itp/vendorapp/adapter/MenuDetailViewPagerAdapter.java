package com.example.itp.vendorapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.itp.vendorapp.fragment.menu.MenuDetailItemFragment;
import com.example.itp.vendorapp.model.MenuDetail;

import java.util.ArrayList;

public class MenuDetailViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<MenuDetail> menuDetailList;

    public MenuDetailViewPagerAdapter(FragmentManager fm, ArrayList<MenuDetail> menuDetailList) {
        super(fm);
        this.menuDetailList = menuDetailList;
    }

    @Override
    public Fragment getItem(int i) {
        MenuDetailItemFragment fragment = MenuDetailItemFragment.newInstance(menuDetailList.get(i));
        return fragment;
    }

    @Override
    public int getCount() {
        return menuDetailList.size();
    }

}
