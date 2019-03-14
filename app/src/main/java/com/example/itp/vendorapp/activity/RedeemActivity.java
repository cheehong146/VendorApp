package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityRedeemBinding;

public class RedeemActivity extends BaseActivity implements View.OnClickListener {

    ActivityRedeemBinding binding;

    //toolbar
    TextView tvToolbarTitle;
    ImageButton ibBackBtn;

    //customer card

    //content
    RecyclerView rvRedeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_redeem);

        initComponents();

        setupRvRedeemAdapter();
    }

    private void setupRvRedeemAdapter(){
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        //toolbar
        ibBackBtn = binding.toolbarRedeem.ibToolbarBackBtn;

        //content
        rvRedeem = binding.rvRedeem;
    }

    @Override
    public void setupListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
