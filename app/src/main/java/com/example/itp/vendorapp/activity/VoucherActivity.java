package com.example.itp.vendorapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.example.itp.vendorapp.databinding.ActivityVoucherBinding;
import com.example.itp.vendorapp.fragment.VoucherUsageFragment;
import com.example.itp.vendorapp.fragment.redeem.SuccessFragment;
import com.example.itp.vendorapp.fragment.voucher.VoucherFragment;
import com.example.itp.vendorapp.model.VoucherItem;

import java.util.ArrayList;
import java.util.List;

public class VoucherActivity extends BaseActivity {
    private static final String TAG = "VoucherActivity";

    RecyclerView rvVoucher;

    ActivityVoucherBinding binding;

    FragmentHelper fragmentHelper = new FragmentHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_voucher);

        intiFirstFragment();
    }

    private void intiFirstFragment() {
        final VoucherFragment fragment = VoucherFragment.newInstance(getDummyData());
        fragment.setupListener(new VoucherFragment.FragmentListener() {
            @Override
            public void onItemClick(VoucherItem item) {
                VoucherUsageFragment voucherUsageFragment = VoucherUsageFragment.newInstance(item);
                voucherUsageFragment.setupListener(new VoucherUsageFragment.FragmentListener() {
                    @Override
                    public void back() {
                        fragmentHelper.popFragment(getSupportFragmentManager());
                    }

                    @Override
                    public void onVoucherUsedClick() {
                        SuccessFragment successFragment = SuccessFragment.newInstance("Lekkers Cafe", "Voucher", "Voucher Used", "Voucher is marked as used", "");
                        successFragment.setupListener(new SuccessFragment.FragmentListener() {
                            @Override
                            public void back() {
                                fragmentHelper.popAllFragment(getSupportFragmentManager());
                            }
                        });
                        fragmentHelper.loadFragment(getSupportFragmentManager(), successFragment, TAG, R.id.frame_voucher_activity);
                    }
                });
                fragmentHelper.loadFragment(getSupportFragmentManager(), voucherUsageFragment, TAG, R.id.frame_voucher_activity);
            }

            @Override
            public void goToProfile() {
                startActivity(new Intent(VoucherActivity.this, ProfileActivity.class));
            }

            @Override
            public void back() {
                startActivityClearTop(VoucherActivity.this, MainActivity.class);
            }
        });
        fragmentHelper.initFragment(getSupportFragmentManager(), fragment, R.id.frame_voucher_activity);
    }

    private List<VoucherItem> getDummyData() {
        ArrayList<VoucherItem> voucherItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            voucherItems.add(new VoucherItem("1", "Used", "https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "French Fries", "1010"));
        }
        voucherItems.get(0).setStatus("");
        return voucherItems;
    }

    @Override
    public void onBackPressed() {
        //if current frame is voucher, go back to home
        //else pop the remaining frag
        if (getSupportFragmentManager().findFragmentById(R.id.frame_voucher_activity) instanceof VoucherFragment) {
            startActivityClearTop(VoucherActivity.this, MainActivity.class);
        } else {
            fragmentHelper.popAllFragment(getSupportFragmentManager());
        }
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setupListener() {

    }
}
