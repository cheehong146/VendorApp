package com.example.itp.vendorapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.itp.vendorapp.fragment.home.HomePromotionFragment;
import com.example.itp.vendorapp.model.PromotionItem;

import java.util.List;


public class PromotionViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "PromotionViewPagerAdapter";
    private List<PromotionItem> promotionItemList;//will contain a list of images that is used to populate the viewpager

    public PromotionViewPagerAdapter(FragmentManager fm, List<PromotionItem> promotionItemList) {
        super(fm);
        this.promotionItemList = promotionItemList;
    }

    @Override
    public Fragment getItem(int i) {
        HomePromotionFragment fragment = initHomePromotionFragment(i);

        return fragment;
    }

    @Override
    public int getCount() {
        return promotionItemList.size();
    }

    private HomePromotionFragment initHomePromotionFragment(int position) {
        HomePromotionFragment fragment = new HomePromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("promotionItem", promotionItemList.get(position));
        fragment.setArguments(bundle);

        fragment.setupListener(new HomePromotionFragment.FragmentListener() {
            @Override
            public void onPromotionItemClick(PromotionItem promotionItem) {
                listener.onPromotionItemClick(promotionItem);
            }
        });
        return fragment;
    }

    /**
     * listener declaration, callback methods
     */
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onPromotionItemClick(PromotionItem promotionItem);
    }
}

