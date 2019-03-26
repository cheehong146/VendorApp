package com.example.itp.vendorapp.activity;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.itp.vendorapp.CONSTANTS;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.example.itp.vendorapp.databinding.ActivityRedeemBinding;
import com.example.itp.vendorapp.fragment.redeem.RedeemFragment;
import com.example.itp.vendorapp.fragment.redeem.SuccessFragment;
import com.example.itp.vendorapp.model.RedeemItem;

import java.util.ArrayList;
import java.util.List;

public class RedeemActivity extends BaseActivity {

    private static final String TAG = "RedeemActivity";

    ActivityRedeemBinding binding;

    FragmentHelper fragmentHelper = new FragmentHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_redeem);

        initFirstFragment();
    }

    private void initFirstFragment() {
        RedeemFragment fragment = RedeemFragment.newInstance(getRedeemDummyData());


        fragment.setupListener(new RedeemFragment.FragmentListener() {
            @Override
            public void onItemClick(RedeemItem item) {
                showRedeemConfirmationDialog();
            }

            @Override
            public void onProfileClick() {
                startActivityClearTop(RedeemActivity.this, ProfileActivity.class);
            }

            @Override
            public void back() {
                startActivityClearTop(RedeemActivity.this, MainActivity.class);
            }
        });
        fragmentHelper.initFragment(getSupportFragmentManager(), fragment, R.id.frame_redeem_activity, CONSTANTS.REDEEM_FRAGMENT);
    }


    private void showRedeemConfirmationDialog() {
        final Dialog dialog = new Dialog(this);

        //set dialog to full screen with transparent bg
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7000000));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.setContentView(R.layout.dialog_redeem_confirmation);

        //individual component
        ImageButton ibClose = dialog.findViewById(R.id.ib_close);
        Button btnProceed = dialog.findViewById(R.id.btn_proceed);

        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showRedeemSuccessfulFragment();
            }
        });
        dialog.show();
    }

    private void showRedeemSuccessfulFragment() {
        SuccessFragment fragment = SuccessFragment.newInstance("Lekkers Cafe", "Redeem",
                "Congragulation!", "Redemption completed", "VoucherItem have been added to your voucher list");
        fragment.setupListener(new SuccessFragment.FragmentListener() {
            @Override
            public void back() {
                fragmentHelper.popFragment(getSupportFragmentManager());
            }
        });
        fragmentHelper.loadFragment(getSupportFragmentManager(), fragment, "RedeemAcitivity", R.id.frame_redeem_activity);
    }

    private List<RedeemItem> getRedeemDummyData() {
        List<RedeemItem> redeemItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            redeemItems.add(new RedeemItem());
        }
        return redeemItems;
    }

    @Override
    public void initComponents() {
        setupListener();
    }

    @Override
    public void setupListener() {
    }

    /**
     * Listener declaration and callback methods
     **/
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void back();
    }
}
