package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.example.itp.vendorapp.databinding.ActivityMenuBinding;
import com.example.itp.vendorapp.fragment.menu.MenuFragment;
import com.example.itp.vendorapp.fragment.menu.MenuParentFragment;
import com.example.itp.vendorapp.model.Menu;

import java.util.ArrayList;

public class MenuActivity extends BaseActivity {

    ActivityMenuBinding binding;

    FragmentHelper fragmentHelper = new FragmentHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

        initFirstFragment(getMenu());
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void bindComponents() {

    }

    @Override
    public void setupListener() {

    }

    private void initFirstFragment(ArrayList<Menu> menuArrayList) {
        MenuParentFragment fragment = MenuParentFragment.newInstance(menuArrayList);
        fragment.setupListener(new MenuParentFragment.FragmentListener() {
            @Override
            public void back() {
                startActivityClearTop(MenuActivity.this, MainActivity.class);
            }
        });
        fragmentHelper.initFragment(getSupportFragmentManager(), fragment, R.id.frame_menu_parent);
    }

    //dummy data
    private ArrayList<Menu> getMenu() {
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu("1", "Set lunch", "https://images.wallpaperscraft.com/image/sandwiches_coffee_sandwich_cheese_tomatoes_96532_1920x1080.jpg"));
        menus.add(new Menu("2", "A la carte", "https://www.sunbeach-rhodes.gr/_res/img/592/g/64/0/Plate-1.jpeg"));
        menus.add(new Menu("3", "Snack", "https://recipes.timesofindia.com/photo/54659021.cms?imgsize=275086"));
        menus.add(new Menu("4", "Rice", "https://www.jessicagavin.com/wp-content/uploads/2018/09/fried-rice-8-1200.jpg"));
        menus.add(new Menu("5", "Noodles", "https://kirbiecravings.com/wp-content/uploads/2018/02/garlic-noodles-61-700x680.jpg"));
        menus.add(new Menu("6", "Drinks", "https://1.bp.blogspot.com/-1zHKHzGNFSQ/WIXkQzRcaeI/AAAAAAAAAF0/aJJZ2l77eKgabWgIPFfu-yWb0UEuPhgiACLcB/s640/milo.jpeg"));
        return menus;
    }
}
