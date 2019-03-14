package com.example.itp.vendorapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.itp.vendorapp.model.RedeemItem;

public class RedeemRvAdapter extends RecyclerView.Adapter<RedeemRvAdapter.ViewHolder> {

    private Context context;
    private List<RedeemItem> redeemItemList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivRedeemItem;
        TextView tvRedeemStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            ivRedeemItem = itemView.findViewById(R.id.iv_redeem_item);TODO UNCOMMENT
//            tvRedeemStatus = itemView.findViewById(R.id.tv_redeem_status);
        }
    }

    @NonNull
    @Override
    public RedeemRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemRvAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvRedeemStatus.setText("Redeem");

        Glide.get(context)//TODO here
//                .as
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
