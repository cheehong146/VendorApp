package com.example.itp.vendorapp.fragment.menu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseFragment;
import com.example.itp.vendorapp.databinding.FragmentMenuParentBinding;
import com.example.itp.vendorapp.model.Menu;
import com.example.itp.vendorapp.model.MenuDetail;

import java.util.ArrayList;

public class MenuParentFragment extends BaseFragment {

    private static final String TAG = "MenuParentFragment";

    FragmentMenuParentBinding binding;

    ArrayList<Menu> menuArrayList;

    public static MenuParentFragment newInstance(ArrayList<Menu> menuArrayList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("menuList", menuArrayList);
        MenuParentFragment fragment = new MenuParentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            menuArrayList = getArguments().getParcelableArrayList("menuList");
        } catch (NullPointerException e) {
            e.printStackTrace();//TODO EH
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_parent, container, false);

        initFirstFragment();

        return binding.getRoot();
    }

    public void initFirstFragment() {
        MenuFragment menuFragment = MenuFragment.newInstance(menuArrayList);
        menuFragment.setupListener(new MenuFragment.FragmentListener() {
            @Override
            public void back() {
                listener.back();
            }

            @Override
            public void onItemClick(Menu item) {
                //TODO get menu detail object in a list from menu object
                ArrayList<MenuDetail> menuDetails;
                menuDetails = getMenuDetails(item.getId());
                MenuDetailFragment fragment = MenuDetailFragment.newInstance(menuDetails);
                fragment.setupListener(new MenuDetailFragment.FragmentListener() {
                    @Override
                    public void back() {
                        //pop top fragment which is MenuDetailFragment to go back to MenuFragment;
                        popFragment();
                    }
                });
                replaceFragment(fragment, R.id.frame_menu_parent, "MenuDetail");
            }
        });
        initFragment(menuFragment, R.id.frame_menu_parent);
    }

    private ArrayList<MenuDetail> getMenuDetails(String menuId) {//TODO delete dummy data
        ArrayList<MenuDetail> arrayList = new ArrayList<>();
        switch (menuId) {
            case "1":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("Set Lunch Breakfast");
                arrayList.get(0).setDescription("Sandwich with chicken meat & a cup of nescafe");
                arrayList.get(0).setImgUrl("https://media-cdn.tripadvisor.com/media/photo-s/0e/e8/b5/86/coffee-and-sandwich.jpg");

                arrayList.get(1).setName("Set Dinner");
                arrayList.get(1).setDescription("Steak with rice, medium rare by default");
                arrayList.get(1).setImgUrl("https://img1.cookinglight.timeinc.net/sites/default/files/styles/medium_2x/public/image/2015/09/main/1509p22-beef-tenderloin-steaks_0.jpg?itok=nslveSny");
                arrayList.get(1).setPrice("RM 20.50");

                arrayList.get(2).setName("Set Appetizer");
                arrayList.get(2).setDescription("Ham and Cheese with biscuit topped with olives");
                arrayList.get(2).setImgUrl("https://www.tasteofhome.com/wp-content/uploads/2017/10/Ham-n-Cheese-Biscuit-Stacks_EXPS_HC17_31947_D10_19_8b-696x696.jpg");

                arrayList.get(3).setName("Set Sushi");
                arrayList.get(3).setDescription("California Roll made fresh from the seas where the fish lives");
                arrayList.get(3).setImgUrl("https://peasandcrayons.com/wp-content/uploads/2012/10/homemade-sushi-tutorial-recipe-peas-and-crayons-1250.jpg");
                break;
            case "2":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("Lemon Fish");
                arrayList.get(0).setDescription("Fish with citrus of a lemon");
                arrayList.get(0).setImgUrl("https://cdn.ruled.me/wp-content/uploads/2017/11/zingy-lemon-fish-featured.jpg");
                arrayList.get(0).setPrice("RM 99.99");

                arrayList.get(1).setName("Whole Chicken leg");
                arrayList.get(1).setDescription("Whole chicken leg with sides of veggies");
                arrayList.get(1).setImgUrl("https://101.enderuncolleges.com/wp-content/uploads/2015/12/101-A-la-Carte-Nov-2015-9.jpg");

                arrayList.get(2).setName("Duck meat");
                arrayList.get(2).setDescription("Duck strip cut with care and dribble in our special sauce");
                arrayList.get(2).setImgUrl("https://www.penninemanor.com/app/uploads/2015/01/Duck-1.jpg");
                arrayList.get(2).setPrice("RM 99.99");

                arrayList.get(3).setName("Pan seared scallop");
                arrayList.get(3).setDescription("Scallop seared on a pan which cost a thousand dollars");
                arrayList.get(3).setImgUrl("https://gbc-cdn-public-media.azureedge.net/img48073.1426x713.jpg");
                break;
            case "3":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("French Fries");
                arrayList.get(0).setDescription("The classic french fries with a pinch of salt");
                arrayList.get(0).setImgUrl("https://www.rewardsnetwork.com/wp-content/uploads/2017/12/FIRSTSnacksAsDaypart_1.jpg");

                arrayList.get(1).setName("Fried wonton");
                arrayList.get(1).setDescription("Handmade wonton fried with oil");
                arrayList.get(1).setImgUrl("https://i.ytimg.com/vi/y_iPjuncrII/maxresdefault.jpg");

                arrayList.get(2).setName("Churros");
                arrayList.get(2).setDescription("Churros made in-house!");
                arrayList.get(2).setImgUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRctjKNkOQjmDLh2TyyfMyUvDqYoEVU8dB0o7T1xooZiqa5LTAy");

                arrayList.get(3).setName("Mini special burger");
                arrayList.get(3).setDescription("Our homemade speciality burger-like snack!");
                arrayList.get(3).setImgUrl("https://peasandcrayons.com/wp-content/uploads/2012/10/homemade-sushi-tutorial-recipe-peas-and-crayons-1250.jpg");
                break;
            case "4":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("Curry Fried Rice");
                arrayList.get(0).setDescription("Fried rice with a little dab of indian spice");
                arrayList.get(0).setImgUrl("https://www-tc.pbs.org/food/wp-content/blogs.dir/2/files/2012/11/curry-fried-rice640x360.jpg");

                arrayList.get(1).setName("Shrimp Fried Rice");
                arrayList.get(1).setDescription("Fried rice with shrimp");
                arrayList.get(1).setImgUrl("https://natashaskitchen.com/wp-content/uploads/2018/09/Shrimp-Fried-Rice-3.jpg");
                break;
            case "5":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("Vegetable Hakka Noodle");
                arrayList.get(0).setDescription("Chinese noodles suitable for vegetarian");
                arrayList.get(0).setImgUrl("https://www.cookwithmanali.com/wp-content/uploads/2014/11/Hakka-Noodles-1.jpg");

                arrayList.get(1).setName("Thai Green Curry Noodle");
                arrayList.get(1).setDescription("Thai delicacy green curry with our famous noodle ");
                arrayList.get(1).setImgUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvzQj5aEQ9lQIFsK11-l8q3DW8aHzLYO8doviGL3_MBzY_hwSP");

                arrayList.get(2).setName("Charsiew Ramen");
                arrayList.get(2).setDescription("Ramen with char siew pork meat and a running egg");
                arrayList.get(2).setImgUrl("http://seonkyounglongest.com/wp-content/uploads/2018/04/shoyu-ramen-1.jpg");
                break;
            case "6":
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());
                arrayList.add(new MenuDetail());

                arrayList.get(0).setName("Chocolate Milkshake");
                arrayList.get(0).setDescription("Milkshake with chocolate flavor");
                arrayList.get(0).setImgUrl("https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe/recipe-image/2016/05/milkshake.jpg?itok=n_3au7e1");

                arrayList.get(1).setName("Latte");
                arrayList.get(1).setDescription("Coffee with foamy milk");
                arrayList.get(1).setImgUrl("https://www.sciencenews.org/sites/default/files/main/articles/100315_coffee_opener_NEW_0.jpg");

                arrayList.get(2).setName("Americano");
                arrayList.get(2).setDescription("Just black coffee");
                arrayList.get(2).setImgUrl("https://thumbor.forbes.com/thumbor/1280x868/https%3A%2F%2Fspecials-images.forbesimg.com%2Fdam%2Fimageserve%2F1072007868%2F960x0.jpg%3Ffit%3Dscale");

                arrayList.get(3).setName("White russian mocha");
                arrayList.get(3).setDescription("Mocha made in russian style");
                arrayList.get(3).setImgUrl("https://www.completelydelicious.com/wp-content/uploads/2017/01/white-russian-mocha-cocktail-11.jpg");
                break;
        }
        return arrayList;
    }

    @Override
    public void setupListener() {

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
    }
}
