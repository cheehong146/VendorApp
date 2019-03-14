package com.example.itp.vendorapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.databinding.ItemMenuBinding;
import com.example.itp.vendorapp.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static final String TAG = "MenuAdapter";

    private ArrayList<Menu> menuArrayList;
    private Context context;

    ItemMenuBinding binding;

    ImageView ivItem;

    public MenuAdapter(ArrayList<Menu> menus, Context context) {
        this.menuArrayList = menus;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvMenuTypeName;
        public ImageView ivMenuType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuTypeName = binding.tvMenuTypeName;
            ivMenuType = binding.ivMenuType;
        }

        public void bind(final Menu menuItem, final FragmentListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(menuItem);
                }
            });
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.item_menu, viewGroup, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvMenuTypeName.setText(menuArrayList.get(i).getName());

        Glide.with(context)
                .asBitmap()
                .transform(new CenterCrop())
                .load(menuArrayList.get(i).getImgUrl())
                .into(viewHolder.ivMenuType);

        viewHolder.bind(menuArrayList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    /**
     * Listener declaration and callback methods
     **/
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onItemClick(Menu item);
    }

}
