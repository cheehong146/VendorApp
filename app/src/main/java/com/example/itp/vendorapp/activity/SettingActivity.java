package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivitySettingBinding;
import com.google.gson.internal.bind.DateTypeAdapter;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        initComponents();
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {

    }

    @Override
    public void setupListener() {
        binding.toolbarSetting.ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                startActivityClearTop(SettingActivity.this, MainActivity.class);
                break;
        }
    }
}
