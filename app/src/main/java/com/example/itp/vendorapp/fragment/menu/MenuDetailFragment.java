package com.example.itp.vendorapp.fragment.menu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.adapter.MenuDetailViewPagerAdapter;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentMenuDetailBinding;
import com.example.itp.vendorapp.model.MenuDetail;

import java.util.ArrayList;

public class MenuDetailFragment extends BaseFragment implements View.OnClickListener {

    ArrayList<MenuDetail> dummyData;

    FragmentMenuDetailBinding binding;

    //toolbar
    TextView tvToolbarTitle;

    //content
    ViewPager viewPagerMenuDetail;
    TabLayout tabDots;
    ImageView ivCloseBtn;

    public static MenuDetailFragment newInstance(ArrayList<MenuDetail> menuDetailArrayList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("menuDetailList", menuDetailArrayList);
        MenuDetailFragment fragment = new MenuDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            dummyData = getArguments().getParcelableArrayList("menuDetailList");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_detail, container, false);

        initComponents();

        setupViewPager();

        return binding.getRoot();
    }

    private void setupViewPager() {
        //data adapter
        viewPagerMenuDetail.setAdapter(new MenuDetailViewPagerAdapter(getChildFragmentManager(), dummyData));

        //animation
        viewPagerMenuDetail.setPageMargin((int) (getResources().getDisplayMetrics().widthPixels * -0.33));
        viewPagerMenuDetail.setOffscreenPageLimit(5);
        viewPagerMenuDetail.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setScaleX(0.7f - Math.abs(position * 0.4f));
                page.setScaleY(0.8f - Math.abs(position * 0.6f));
                page.setAlpha(1.0f - Math.abs(position * 0.5f));
            }
        });

        //tab dot
        tabDots.setupWithViewPager(viewPagerMenuDetail);
    }


    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        //toolbar
        tvToolbarTitle = binding.toolbarMenuDetail.tvToolbarTitle;

        //content
        viewPagerMenuDetail = binding.viewpagerMenuDetail;
        tabDots = binding.tabDots;

        //close btn
        ivCloseBtn = binding.btnClose;
    }

    @Override
    public void setupListener() {
        ivCloseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                listener.back();
        }
    }

    /**
     * Listener declaration and callbacks method
     */
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void back();
    }
}
