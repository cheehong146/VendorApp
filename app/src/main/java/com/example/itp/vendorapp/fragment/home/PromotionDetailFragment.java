package com.example.itp.vendorapp.fragment.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentPromotionDetailedBinding;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;

public class PromotionDetailFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "PromotionDetailedFrag";

    private PromotionItem item;
    private Customer customer;
    String vendorName;

    FragmentPromotionDetailedBinding binding;

    //toolbar ui view
    TextView tvToolbarTitle;
    ImageView ivToolbarBack;

    //customer header ui view
    TextView tvCustomerUsername;
    TextView tvCustomerAvailablePoints;
    ImageView ivCustomerProfilePicture;

    //content ui view
    TextView tvDescription, tvTermsAndCondition;//TODO Expandable textview
    TextView tvFoodName, tvValidDate;
    ImageView ivFoodImage;

    public static PromotionDetailFragment newInstance(Customer customer, PromotionItem item, String vendorName) {
        Bundle args = new Bundle();
        args.putParcelable("promotionItem", item);
        args.putParcelable("customer", customer);
        args.putString("vendorName", vendorName);
        PromotionDetailFragment fragment = new PromotionDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            item = getArguments().getParcelable("promotionItem");
            customer = getArguments().getParcelable("customer");
            vendorName = getArguments().getString("vendorName");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_promotion_detailed, container, false);

        initComponents();
        setupViewWithData();


        return binding.getRoot();
    }

    private void setupViewWithData() {
        setupToolbarData();
        setupCustomerHeaderData();
        setupContentData();
    }

    private void setupToolbarData() {
        tvToolbarTitle.setText(vendorName);
    }

    private void setupCustomerHeaderData() {
        tvCustomerUsername.setText(customer.getUsername());
        tvCustomerAvailablePoints.setText(customer.getPoints());
        Glide.with(this)
                .asBitmap()
                .transform(new CenterCrop(), new CircleCrop())
                .load(customer.getImgUrl())
                .into(ivCustomerProfilePicture);
    }

    private void setupContentData() {
        tvFoodName.setText(item.getName());
        tvDescription.setText(item.getDesc());
        tvValidDate.setText(item.getValidityDate());
        tvTermsAndCondition.setText(item.getTermsAndCondition());

        Glide.with(this)
                .asBitmap()
                .transform(new CenterCrop(), new RoundedCorners(16))
                .load(item.getImgUrl())
                .into(ivFoodImage);
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        //toolbar
        tvToolbarTitle = binding.toolbarHome.tvToolbarTitle;
        ivToolbarBack = binding.toolbarHome.ivToolbarBackArrow;

        //customer header
        tvCustomerUsername = binding.customerHeader.tvHomeHeaderUsername;
        tvCustomerAvailablePoints = binding.customerHeader.tvHomeHeaderPointsVal;
        ivCustomerProfilePicture = binding.customerHeader.ivHomeHeaderProfilePic;

        //content
        tvFoodName = binding.tvPromotionDetailedTitle;
        ivFoodImage = binding.ivPromotionDetailed;
        tvValidDate = binding.tvPromotionDetailedValidDateContent;
        //not using data binding cause expand text view library doesn't support it
        tvDescription = binding.getRoot().findViewById(R.id.tv_promotion_detailed_description);
        tvTermsAndCondition = binding.getRoot().findViewById(R.id.tv_promotion_detailed_terms_condition_content);
    }

    @Override
    public void setupListener() {
        //toolbar
        ivToolbarBack.setOnClickListener(this);

        //customer header
        tvCustomerUsername.setOnClickListener(this);
//        tvCustomerAvailablePoints.setOnClickListener(this); TODO determine if user can click on available point to go to profile
        ivCustomerProfilePicture.setOnClickListener(this);

        //content
        tvDescription.setOnClickListener(this);
        tvTermsAndCondition.setOnClickListener(this);
        tvValidDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toolbar_back_arrow:
                listener.back();
                break;

            case R.id.tv_promotion_detailed_description:
                Log.d(TAG, "onClick: tv_showcase_detailed_description");
                break;

            case R.id.tv_promotion_detailed_terms_condition_content:
                Log.d(TAG, "onClick: tv_showcase_detailed_description");
                break;
        }
    }

    /**
     * listener declaration, callback methods
     */

    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void back();
    }
}
