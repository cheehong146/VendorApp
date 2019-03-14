package com.example.itp.vendorapp.fragment.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.adapter.PromotionViewPagerAdapter;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentHomeBinding;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    //data
    Customer customer;
    List<PromotionItem> promotionList;

    //binding
    FragmentHomeBinding binding;

    //UI view components
    //toolbar title
    TextView tvToolbarVendorName;//TODO debug data binding error in converting toolbar to view
    //customer header
    TextView tvCustomerUsername;
    TextView tvCustomerAvailablePoints;
    ImageView ivCustomerProfilePicture;
    //showcase
    ViewPager viewpagerPromotion;
    TabLayout tabDotsPromotion;
    PromotionViewPagerAdapter adapter;

    //buffer, for which item is showing on the screen now (viewpager), by default it's 0
    static int onCurItem = 0;

    public static HomeFragment newInstance(Customer customer, List<PromotionItem> item) {
        Bundle args = new Bundle();
        args.putParcelable("customer", customer);
        args.putParcelableArrayList("promotionItemList", new ArrayList<Parcelable>(item));
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
        } catch (ClassCastException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            customer = getArguments().getParcelable("customer");
            promotionList = getArguments().getParcelableArrayList("promotionItemList");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        initComponents();

        //populate the view with data
        initPromotionAdapter();
        setupCustomerHeaderData();


        return binding.getRoot();
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        //toolbar title
//        tvToolbarVendorName = binding.toolbarHome;
        //customer header
        tvCustomerUsername = binding.customerHeader.tvHomeHeaderUsername;
        tvCustomerAvailablePoints = binding.customerHeader.tvHomeHeaderPointsVal;
        ivCustomerProfilePicture = binding.customerHeader.ivHomeHeaderProfilePic;
        //showcase
        viewpagerPromotion = binding.viewpagerPromotion;
        tabDotsPromotion = binding.tabDots;
    }

    @Override
    public void setupListener() {
        viewpagerPromotion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onCurItem = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * init promotion adapter and its' listener
     */
    private void initPromotionAdapter() {
        //      viewpager and tabDots linkage
        tabDotsPromotion.setupWithViewPager(viewpagerPromotion, true);

        adapter = new PromotionViewPagerAdapter(getChildFragmentManager(), promotionList);
        viewpagerPromotion.setAdapter(adapter);

        adapter.setupListener(new PromotionViewPagerAdapter.FragmentListener() {
            @Override
            public void onPromotionItemClick(PromotionItem promotionItem) {
                listener.onPromotionItemClick(promotionItem);
            }
        });

        adapter = new PromotionViewPagerAdapter(getChildFragmentManager(), promotionList);
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


    /**
     * listener declaration and callback methods
     */
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void goBack();

        void onPromotionItemClick(PromotionItem item);
    }
}
