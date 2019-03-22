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
import com.example.itp.vendorapp.databinding.FragmentSignInBinding;

public class SignInFragment extends BaseFragment {

    FragmentSignInBinding binding;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);

        setupListener();

        return binding.getRoot();
    }

    @Override
    public void setupListener() {
        binding.btnFacebookLogin.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        binding.tvForgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_facebook_login:
                listener.onFacebookLoginClick();
                break;
            case R.id.btn_login:
                listener.onLoginClick();
                break;
            case R.id.tv_forget_pass:
                listener.onForgetPasswordClick();
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
        void onFacebookLoginClick();

        void onLoginClick();

        void onForgetPasswordClick();
    }
}
