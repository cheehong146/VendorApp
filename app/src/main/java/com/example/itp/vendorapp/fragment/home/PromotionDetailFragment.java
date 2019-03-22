package com.example.itp.vendorapp.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class PromotionDetailFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PromotionDetailedFrag";

    private PromotionItem item;
    private Customer customer;
    String vendorName;

    //toolbar ui view
    View toolbar;
    TextView tvToolbarTitle;
    ImageButton ibToolbarBack;

    //customer header ui view
    TextView tvCustomerUsername;
    TextView tvCustomerAvailablePoints;
    ImageView ivCustomerProfilePicture;

    //content ui view
    TextView tvFoodName, tvValidDate;
    ImageView ivFoodImage;
    ExpandableTextView etvDescription, etvTermsCondition;

    /***
     *    DATA BINDING IS DISABLED FOR THIS PAGE DUE TO THE EXPANDABLE TEXT VIEW LIBRARY CRASHING WITH IT
     */

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
        View view = inflater.inflate(R.layout.fragment_promotion_detailed, container, false);

        initComponents(view);
        setupViewWithData();

        return view;
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
        etvDescription.setText(item.getDesc());
        tvValidDate.setText(item.getValidityDate());
        etvTermsCondition.setText(item.getTermsAndCondition());

        Glide.with(this)
                .asBitmap()
                .transform(new CenterCrop(), new RoundedCorners(16))
                .load(item.getImgUrl())
                .into(ivFoodImage);
    }

    public void initComponents(View view) {
        bindComponents(view);
        setupListener();
    }

    public void bindComponents(View view) {
        //toolbar
        toolbar = view.findViewById(R.id.toolbar_home);
        tvToolbarTitle = toolbar.findViewById(R.id.tv_title);
        ibToolbarBack = toolbar.findViewById(R.id.ib_back);
        //customer header
        View customerHeader = view.findViewById(R.id.customer_header);
        tvCustomerUsername = customerHeader.findViewById(R.id.tv_home_header_username);
        tvCustomerAvailablePoints = customerHeader.findViewById(R.id.tv_home_header_points_val);
        ivCustomerProfilePicture = customerHeader.findViewById(R.id.iv_home_header_profile_pic);
        //content
        tvFoodName = view.findViewById(R.id.tv_promotion_detailed_title);
        ivFoodImage = view.findViewById(R.id.iv_promotion_detailed);
        tvValidDate = view.findViewById(R.id.tv_promotion_detailed_valid_date_content);
        //content, expandableTextView
        etvDescription = view.findViewById(R.id.expandable_text_details);
        etvTermsCondition = view.findViewById(R.id.expandable_text_terms_condition);
    }

    public void setupListener() {
        //toolbar
        ibToolbarBack.setOnClickListener(this);

        //customer header
//        tvCustomerUsername.setOnClickListener(this);
//        tvCustomerAvailablePoints.setOnClickListener(this); TODO determine if user can click on available point to go to profile
////        ivCustomerProfilePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                listener.back();
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
