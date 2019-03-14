package com.example.itp.vendorapp.fragment.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentHomeParentBinding;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;

import java.util.ArrayList;
import java.util.List;

public class HomeParentFragment extends BaseFragment {

    private static final String TAG = "HomeParentFragment";

    HomeFragment homeFragment;

    private Customer customer;
    private List<PromotionItem> promotionList;

    FragmentHomeParentBinding binding;

    FrameLayout frameHomeParent;

    public static HomeParentFragment newInstance(Customer customer, List<PromotionItem> promotionList) {
        Bundle args = new Bundle();
        args.putParcelable("customer", customer);
        args.putParcelableArrayList("promotionList", new ArrayList<Parcelable>(promotionList));
        HomeParentFragment homeParentFragment = new HomeParentFragment();
        homeParentFragment.setArguments(args);
        return homeParentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            customer = getArguments().getParcelable("customer");
            promotionList = getArguments().getParcelableArrayList("promotionList");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_parent, container, false);

        initFirstFragment();

        return binding.getRoot();
    }

    @Override
    public void initComponents() {
        bindComponents();
    }

    @Override
    public void bindComponents() {
    }

    @Override
    public void setupListener() {
    }

    public void initFirstFragment() {
        homeFragment = HomeFragment.newInstance(customer, promotionList);
        homeFragment.setupListener(new HomeFragment.FragmentListener() {
            @Override
            public void goBack() {
                popFragment();
            }

            @Override
            public void onPromotionItemClick(PromotionItem promotionList) {
                replaceFragment(initPromotionDetailFragment(promotionList), R.id.frame_home_parent, "PromotionDetailFragment");
            }
        });
        initFragment(homeFragment, R.id.frame_home_parent);
    }

    /**
     * init promotion item(from item that the user clicks) and setup listener
     *
     * @param item
     * @return
     */
    private PromotionDetailFragment initPromotionDetailFragment(PromotionItem item) {
        PromotionDetailFragment fragment = PromotionDetailFragment.newInstance(customer, item, "Lekkers Cafe");//TODO fix hardcode of vendor cafe

        fragment.setupListener(new PromotionDetailFragment.FragmentListener() {
            @Override
            public void back() {
                //replace promotion item details with home fragment
                popFragment();
            }
        });
        return fragment;
    }

    //setup HomeFragment listener
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onPromotionItemClick();
    }
}
