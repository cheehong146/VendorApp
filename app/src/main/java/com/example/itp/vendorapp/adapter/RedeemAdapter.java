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
import com.example.itp.vendorapp.databinding.ItemRedeemBinding;
import com.example.itp.vendorapp.model.RedeemItem;

import java.util.List;

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.ViewHolder> {

    private Context context;
    private List<RedeemItem> redeemItemList;

    ItemRedeemBinding binding;

    public RedeemAdapter(List<RedeemItem> redeemItemList, Context context) {
        this.context = context;
        this.redeemItemList = redeemItemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRedeemItem;
        TextView tvRedeemStatus;

        TextView tvRedeemTitle;
        TextView tvPointsRequired;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivRedeemItem = binding.ivRedeemItem;
            tvRedeemStatus = binding.tvRedeemItemStatus;
            tvRedeemTitle = binding.tvRedeemItemTitle;
            tvPointsRequired = binding.tvRedeemItemPointsRequired;
        }

        public void bind(final RedeemItem item, final FragmentListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @NonNull
    @Override
    public RedeemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        binding = DataBindingUtil.inflate(inflater, R.layout.item_redeem, viewGroup, false);


        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvRedeemStatus.setText("Redeem");

        viewHolder.bind(redeemItemList.get(i), listener);

        Glide.with(context)
                .asBitmap()
                .transform(new CenterCrop())
                .load("https://www.goodfood.com.au/content/dam/images/h/0/f/a/q/i/image.related.wideLandscape.940x529.h0fa4n.png/1515456591895.jpg")//TODO change to dynamic
                .into(viewHolder.ivRedeemItem);
    }

    @Override
    public int getItemCount() {
        return redeemItemList.size();
    }


    /**
     * Listener declaration and callback methods
     **/
    FragmentListener listener;

    public void setupListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onItemClick(RedeemItem item);
    }
}
