package com.example.itp.vendorapp.fragment.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentSignUpBinding;

public class SignUpFragment extends BaseFragment {

    FragmentSignUpBinding binding;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);

        setupListener();

        return binding.getRoot();
    }

    @Override
    public void setupListener() {
        binding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
        }
    }

    /**
     * OnFragmentInteractionListener declaration, callback methods
     **/
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onSignUpClick();
    }
}
