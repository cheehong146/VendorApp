package com.example.itp.vendorapp.fragment.voucher;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.adapter.VoucherAdapter;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentVoucherBinding;
import com.example.itp.vendorapp.model.VoucherItem;

import java.util.ArrayList;
import java.util.List;

public class VoucherFragment extends BaseFragment implements View.OnClickListener {

    //dummy data
    List<VoucherItem> vouchers;

    FragmentVoucherBinding binding;
    /**
     * Listener declaration and callback methods
     **/
    FragmentListener listener;

    public static VoucherFragment newInstance(List<VoucherItem> voucherItemList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("vouchers", new ArrayList(voucherItemList));//TODO take customer
        VoucherFragment fragment = new VoucherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            vouchers = getArguments().getParcelableArrayList("vouchers");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_voucher, container, false);

        setupListener();
        setupVoucherRv();
        return binding.getRoot();
    }

    private void setupVoucherRv() {
        VoucherAdapter voucherAdapter = new VoucherAdapter(vouchers, getActivity());
        voucherAdapter.setupListener(new VoucherAdapter.FragmentListener() {
            @Override
            public void onItemClick(VoucherItem item) {
                listener.onItemClick(item);
            }
        });

        binding.rvVoucher.setAdapter(voucherAdapter);
        binding.rvVoucher.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void setupListener() {
        binding.toolbarVoucher.ibBack.setOnClickListener(this);
        binding.customerHeader.civHomeHeaderProfilePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                listener.back();
                break;
            case R.id.civ_home_header_profile_pic:
                listener.goToProfile();
        }
    }

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onItemClick(VoucherItem item);

        void goToProfile();

        void back();
    }
}
