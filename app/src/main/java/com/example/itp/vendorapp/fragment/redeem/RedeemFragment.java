package com.example.itp.vendorapp.fragment.redeem;

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

import com.example.itp.vendorapp.adapter.RedeemAdapter;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentRedeemBinding;
import com.example.itp.vendorapp.model.RedeemItem;

import java.util.ArrayList;
import java.util.List;

import com.example.itp.vendorapp.R;

public class RedeemFragment extends BaseFragment implements View.OnClickListener {


    //toolbar
    TextView tvToolbarTitle;
    ImageButton ibBackBtn;

    //customer card

    //content
    RecyclerView rvRedeem;

    //dummy data
    List<RedeemItem> redeemItemList;

    FragmentRedeemBinding binding;

    public static RedeemFragment newInstance(List<RedeemItem> redeemItemList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("redeemItems", new ArrayList(redeemItemList));//TODO take customer
        RedeemFragment fragment = new RedeemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            redeemItemList = getArguments().getParcelableArrayList("redeemItems");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_redeem, container, false);

        initComponents();
        setupRedeemRv();

        return binding.getRoot();
    }

    private void setupRedeemRv() {
        RedeemAdapter redeemAdapter = new RedeemAdapter(redeemItemList, getActivity());
        redeemAdapter.setupListener(new RedeemAdapter.FragmentListener() {
            @Override
            public void onItemClick(RedeemItem item) {
                listener.onItemClick(item);
            }
        });

        rvRedeem.setAdapter(redeemAdapter);
        rvRedeem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        ibBackBtn = binding.toolbarRedeem.ibBack;
        tvToolbarTitle = binding.toolbarRedeem.tvTitle;
        rvRedeem = binding.rvRedeem;
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
        void onItemClick(RedeemItem item);

        void back();
    }
}