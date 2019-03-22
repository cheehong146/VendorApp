package com.example.itp.vendorapp.fragment.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentHomePromotionBinding;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;

public class HomePromotionFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "HomePromotionFragment";

    Customer customer;
    PromotionItem promotionItem;

    FragmentHomePromotionBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home_promotion, container, false);
        View view = binding.getRoot();

        setupListener();

        //TODO CHANGE TO VIEW MODEL
        promotionItem = getArguments().getParcelable("promotionItem");
        customer = getArguments().getParcelable("customer");

        if (promotionItem != null)
            setupPromotionAdapter();//TODO EH , views still visible, dots and validity date, with user header. imageview put placeholder

        if (customer != null)
            setupCustomerHeader();

        return view;
    }

    private void setupCustomerHeader() {
//        CustomerHeaderFragment customerHeaderFragment = CustomerHeaderFragment.newInstance(customer);
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//        ft.replace(R.id.frame_promotion_customer_header, customerHeaderFragment);
//        ft.commit();
        //TODO customer header dynamic
    }

    private void setupPromotionAdapter() {
        //TODO DATA BIND INSTEAD OF SET TEXT
        try {

            handleValidityText();

            binding.tvPromotionTitle.setText(promotionItem.getName());
            Glide.with(this)
                    .asBitmap()
                    .transform(new CenterCrop(), new RoundedCorners(32))
                    .load(promotionItem.getImgUrl())
                    .into(binding.ivPromotion);
        } catch (NullPointerException e) {
            e.printStackTrace();
            //TODO HANDLE ERROR
        }

    }

    private void handleValidityText() {
        if (promotionItem.getValidityDate() == null) {
            binding.tvPromotionValidity.setVisibility(View.INVISIBLE);
        } else {
            if (!promotionItem.getValidityDate().equals("")) {
                binding.tvPromotionValidity.setText("Validity Date: " + promotionItem.getValidityDate());
            } else {
                binding.tvPromotionValidity.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setupListener() {
        binding.tvPromotionValidity.setOnClickListener(this);
        binding.tvPromotionTitle.setOnClickListener(this);
        binding.ivPromotion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_promotion:
                //callback method to display the promotion item in details
                listener.onPromotionItemClick(promotionItem);
        }
    }

    /**
     * listener declaration / callbacks method
     */
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onPromotionItemClick(PromotionItem promotionItem);
    }
}
