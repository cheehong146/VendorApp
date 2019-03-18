package com.example.itp.vendorapp.fragment.voucher;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itp.vendorapp.adapter.VoucherAdapter;
import com.example.itp.vendorapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.databinding.FragmentVoucherBinding;
import com.example.itp.vendorapp.model.VoucherItem;

public class VoucherFragment extends BaseFragment implements View.OnClickListener {


    //toolbar
    TextView tvToolbarTitle;
    ImageButton ibBackBtn;

    //customer card

    //content
    RecyclerView rvVoucher;

    //dummy data
    List<VoucherItem> vouchers;

    FragmentVoucherBinding binding;

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

        initComponents();
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

        rvVoucher.setAdapter(voucherAdapter);
        rvVoucher.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        ibBackBtn = binding.toolbarVoucher.ibBack;
        tvToolbarTitle = binding.toolbarVoucher.tvTitle;
        rvVoucher = binding.rvVoucher;
    }

    @Override
    public void setupListener() {
        ibBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                listener.back();
        }
    }

    /**
     * Listener declaration and callback methods
     **/
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onItemClick(VoucherItem item);

        void back();
    }
}
