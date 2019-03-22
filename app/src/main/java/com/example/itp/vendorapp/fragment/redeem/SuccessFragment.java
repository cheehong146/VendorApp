package com.example.itp.vendorapp.fragment.redeem;

import com.example.itp.vendorapp.R;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentSuccessBinding;

public class SuccessFragment extends BaseFragment {

    String toolbarTitle;
    String title;
    String headerMsg;
    String bodyMsg;
    String subBodyMsg;

    FragmentSuccessBinding binding;

    public static SuccessFragment newInstance(String toolbarTitle, String title, String headerMsg, String bodyMsg, String subBodyMsg) {
        Bundle args = new Bundle();
        args.putString("toolbarTitle", toolbarTitle);
        args.putString("title", title);
        args.putString("headerMsg", headerMsg);
        args.putString("bodyMsg", bodyMsg);
        args.putString("subBodyMsg", subBodyMsg);
        SuccessFragment fragment = new SuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            toolbarTitle = getArguments().getString("toolbarTitle");
            title = getArguments().getString("title");
            headerMsg = getArguments().getString("headerMsg");
            bodyMsg = getArguments().getString("bodyMsg");
            subBodyMsg = getArguments().getString("subBodyMsg");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success, container, false);

        setupListener();
        setupViewsWithData();

        return binding.getRoot();
    }

    private void setupViewsWithData() {
        binding.toolbarSuccessPage.tvTitle.setText(toolbarTitle);
        binding.tvSuccessPageTitle.setText(title);
        binding.tvSuccessHeader.setText(headerMsg);
        binding.tvSuccessBody.setText(bodyMsg);
        binding.tvSuccessBodySecondary.setText(subBodyMsg);
    }

    @Override
    public void setupListener() {
        binding.toolbarSuccessPage.ibBack.setOnClickListener(this);
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
        void back();
    }
}
