package com.example.itp.vendorapp.fragment.menu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.adapter.MenuAdapter;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentMenuBinding;
import com.example.itp.vendorapp.model.Menu;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends BaseFragment {
    private static final String TAG = "MenuFragment";

    List<Menu> menus;

    FragmentMenuBinding binding;

    MenuAdapter menuTypeAdapter;

    public static MenuFragment newInstance(ArrayList<Menu> menus) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("menus", menus);
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setArguments(args);
        return menuFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            menus = getArguments().getParcelableArrayList("menus");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);


        setupListener();
        setupMenuAdapter();

        return binding.getRoot();
    }

    private void setupMenuAdapter() {
        menuTypeAdapter = new MenuAdapter(new ArrayList<Menu>(menus), getActivity());
        binding.rvMenuType.setLayoutManager(getGridLayoutManager());//span of 2
        binding.rvMenuType.setAdapter(menuTypeAdapter);

        menuTypeAdapter.setupListener(new MenuAdapter.FragmentListener() {
            @Override
            public void onItemClick(Menu item) {
                listener.onItemClick(item);
            }
        });
    }

    private GridLayoutManager getGridLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        return gridLayoutManager;
    }

    @Override
    public void setupListener() {
        //toolbar back btn
        binding.toolbarMenu.ibBack.setOnClickListener(this);

        binding.rvMenuType.setOnClickListener(this);
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
     * Listener declaration and callback method
     */
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void back();

        void onItemClick(Menu item);
    }
}
