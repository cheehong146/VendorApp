package com.example.itp.vendorapp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentVoucherUsageBinding;
import com.example.itp.vendorapp.model.VoucherItem;

import org.w3c.dom.Text;

public class VoucherUsageFragment extends BaseFragment implements View.OnClickListener {

    VoucherItem item;

    FragmentVoucherUsageBinding binding;

    public static VoucherUsageFragment newInstance(VoucherItem item) {
        Bundle args = new Bundle();
        args.putParcelable("voucherItem", item);
        VoucherUsageFragment fragment = new VoucherUsageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            item = getArguments().getParcelable("voucherItem");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_voucher_usage, container, false);

        setupListener();
        setupViewWithData();

        return binding.getRoot();
    }

    private void setupViewWithData() {
        Glide.with(this)
                .asBitmap()
                .transform(new CenterCrop(), new RoundedCorners(32))
                .load(item.getImgUrl())
                .into(binding.ivVoucherUsageItem);

        //todo code data entry
    }

    @Override
    public void setupListener() {
        binding.toolbarVoucherUsage.ibBack.setOnClickListener(this);
        binding.btnVoucherUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_voucher_use:
                listener.onVoucherUsedClick();
                break;
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

        void onVoucherUsedClick();
    }
}
