package com.example.itp.vendorapp.fragment.menu;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentMenuDetailItemBinding;
import com.example.itp.vendorapp.model.MenuDetail;


public class MenuDetailItemFragment extends BaseFragment {

    FragmentMenuDetailItemBinding binding;
    private MenuDetail menuDetail;

    public static MenuDetailItemFragment newInstance(MenuDetail menuDetail) {
        Bundle args = new Bundle();
        args.putParcelable("menuDetail", menuDetail);
        MenuDetailItemFragment fragment = new MenuDetailItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            menuDetail = getArguments().getParcelable("menuDetail");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_detail_item, container, false);

        setupViewsData();

        return binding.getRoot();
    }

    @Override
    public void setupListener() {

    }

    private void setupViewsData() {
        binding.tvMenuDetailTitle.setText(menuDetail.getName());
        binding.tvMenuDetailDesc.setText(menuDetail.getDescription());

        if (shouldPriceViewAppear(menuDetail)) {
            binding.tvMenuDetailPrice.setVisibility(View.VISIBLE);
            binding.tvMenuDetailPrice.setText(menuDetail.getPrice());
        } else {
            binding.tvMenuDetailPrice.setVisibility(View.GONE);
        }

        Glide.with(this)
                .asBitmap()
                .transform(new CenterCrop())
                .load(menuDetail.getImgUrl())
                .into(binding.ivMenuDetail);

    }

    /**
     * return false if price is null or is a empty string
     *
     * @param menuItem
     * @return
     */
    private boolean shouldPriceViewAppear(MenuDetail menuItem) {
        try {
            String menuItemPrice = menuItem.getPrice();
            if (menuItemPrice != null || !menuItemPrice.equals("")) {
                return true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
