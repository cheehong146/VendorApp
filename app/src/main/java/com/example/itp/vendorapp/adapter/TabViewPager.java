package com.example.itp.vendorapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

public class TabViewPager extends ViewPager {
    public TabViewPager(@NonNull Context context) {
        super(context);
    }

//    class TabViewPagerAdapter extends FragmentStatePagerAdapter {
//        private Context context;
//
//        public TabViewPagerAdapter(FragmentManager fm, Context context, List<ShowcaseItem> showcaseItemList) {
//            super(fm);
//            this.context = context;
//            this.showcaseItemList = showcaseItemList;
//        }
//
//        @Override
//        public Fragment getItem(int i) {
//            HomePromotionFragment fragment = new HomePromotionFragment();
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("item", showcaseItemList.get(i));
//            fragment.setArguments(bundle);
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return showcaseItemList.size();
//        }
//    }
}


