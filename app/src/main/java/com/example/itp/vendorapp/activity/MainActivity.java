package com.example.itp.vendorapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.base.helpers.FragmentHelper;
import com.example.itp.vendorapp.databinding.ActivityMainBinding;
import com.example.itp.vendorapp.fragment.home.HomeParentFragment;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.PromotionItem;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    BottomNavigationView navBar;

    //helpers
    FragmentHelper fragmentHelper = new FragmentHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initComponents();
        initHomePage();
    }


    private void initHomePage() {
        HomeParentFragment homeParentFragment = HomeParentFragment.newInstance(getDummyCustomer(), getDummyData());
        homeParentFragment.setupListener(new HomeParentFragment.FragmentListener() {
            @Override
            public void onProfileClick() {
                startNewActivityWithoutFinish(getProfileIntent(getDummyCustomer()));
            }
        });

        fragmentHelper.initFragment(getSupportFragmentManager(), homeParentFragment, R.id.frame_main_activity);
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        navBar = binding.navBar;
    }

    @Override
    public void setupListener() {
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_foodmenu:
                        startNewActivityWithoutFinish(new Intent(MainActivity.this, MenuActivity.class));
                        return true;
                    case R.id.menu_item_redeem:
//                        startActivityClearTop(MainActivity.this, RedeemActivity.class);
                        startNewActivityWithoutFinish(new Intent(MainActivity.this, RedeemActivity.class));
                        return true;
                    case R.id.menu_item_voucher:
                        startNewActivityWithoutFinish(new Intent(MainActivity.this, VoucherActivity.class));
                        return true;
                    case R.id.menu_item_located:
                        startNewActivityWithoutFinish(new Intent(MainActivity.this, LocationActivity.class));
                        return true;
                    case R.id.menu_item_setting:
                        startNewActivityWithoutFinish(new Intent(MainActivity.this, SettingActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    private Intent getProfileIntent(Customer customer) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("customer", customer);
        return intent;
    }


    //Dummy data generator
    private Customer getDummyCustomer() {
        Customer customer = new Customer("1", "Adam Settler", "https://www.morpht.com/sites/morpht/files/styles/landscape/public/dalibor-matura_1.jpg?itok=gxCAhwAV", "9999");
        return customer;
    }

    private ArrayList<PromotionItem> getDummyData() {
        ArrayList<PromotionItem> items = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            items.add(new PromotionItem());
        }
        String dummyTerms = "You must make sure that everyone understands that although each Contributor hereby grants to the article or articles you contributed to," +
                " b) through a hyperlink (where possible) or URL to an updated version of the Modifications you create or use a modified version of this software without specific prior " +
                "written permission. THE SOFTWARE IS PROVIDED UNDER THIS LICENSE OR YOUR USE OR INABILITY TO USE THE PROGRAM OR THE EXERCISE OF ANY KIND, EXPRESS OR IMPLIED WARRANTIES, " +
                "INCLUDING, BUT NOT LIMITATION, CNRI MAKES NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, " +
                "PROCUREMENT OF SUBSTITUTE GOODS OR SERVICE; DAMAGES ARISING OUT OF OR RELATING TO THIS LICENSE OR YOUR USE OR OTHER PARTY WHO MAY MODIFY AND/OR REDISTRIBUTE THE" +
                " PROGRAM IS PROVIDED \"AS IS\" basis. CNRI MAKES NO AND DISCLAIMS ANY REPRESENTATION OR WARRANTY OF MERCHANTABILITY AND FITNESS FOR ANY DIRECT, INDIRECT, INCIDENTAL," +
                " OR CONSEQUENTIAL DAMAGES OR LOSSES, EVEN IF ADVISED OF THE USE OF ANY OF SUCH DAMAGES. EXPORT CONTROL Recipient agrees to pass maintenance of the possibility of such noncompliance.";
        items.get(0).setName("Salmon seared");
        items.get(0).setImgUrl("https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        items.get(0).setDesc("Pan Seared Salmon are super crispy on the outside, while soft, tender and flakey on the inside, finished with a silky, flavourful buttery sauce. " +
                "Our family favourite low carb salmon recipe is now yours!");
        items.get(0).setValidityDate("16/4/2019");
        items.get(0).setTermsAndCondition(dummyTerms);

        items.get(1).setName("French Fries");
        items.get(1).setImgUrl("https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        items.get(1).setValidityDate("26/9/2019");
        items.get(1).setTermsAndCondition(dummyTerms);
        items.get(1).setDesc("One family, a chip truck and Australia’s festival scene. Our fries are made from locally sourced, fresh Aussie potatoes, cooked with their skins " +
                "on in a sustainable non-gmo cottonseed sunflower oil blend and ready to be enjoyed with any of our internationally inspired dipping sauces. They aren’t just incredibly " +
                "delicious, they’re also 100% vegan without chemicals, toxic preservatives or other nasties. ");

        items.get(2).setName("Burrito");
        items.get(2).setImgUrl("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2013/2/14/0/FNK_breakfast-burrito_s4x3.jpg.rend.hgtvcom.616.462.suffix/1382542427230.jpeg");
        items.get(2).setValidityDate("29/3/2019");
        items.get(2).setDesc("Roast pork, beans and cheese with verde sauce and melted cheese.");
        items.get(2).setTermsAndCondition(dummyTerms);

        items.get(3).setName("Fried Noodles");
        items.get(3).setImgUrl("https://static.independent.co.uk/s3fs-public/thumbnails/image/2017/02/07/15/chinese.jpg?w968h681");
        items.get(3).setValidityDate("14/June-1997");
        items.get(3).setTermsAndCondition(dummyTerms);
        items.get(3).setDesc("Stir fried flat rice noodles with sweet soya sauce, egg, chilli jam paste, bok choy and cashew nuts");
        return items;
    }
}
