package com.example.itp.vendorapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.BlurMaskFilter;
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
import com.example.itp.vendorapp.databinding.ItemVoucherBinding;
import com.example.itp.vendorapp.model.VoucherItem;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static android.view.View.GONE;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder> {

    private Context context;
    private List<VoucherItem> voucherItemList;

    ItemVoucherBinding binding;

    public VoucherAdapter(List<VoucherItem> voucherItemList, Context context) {
        this.context = context;
        this.voucherItemList = voucherItemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivVoucherItem;
        TextView tvVoucherStatus;

        TextView tvVoucherTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivVoucherItem = binding.ivVoucherItem;
            tvVoucherStatus = binding.tvVoucherItemStatus;
            tvVoucherTitle = binding.tvVoucherItemTitle;
        }

        public void bind(final VoucherItem item, final VoucherAdapter.FragmentListener listener) {
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
    public VoucherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        binding = DataBindingUtil.inflate(inflater, R.layout.item_voucher, viewGroup, false);

        return new VoucherAdapter.ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.ViewHolder viewHolder, int i) {

        String status = voucherItemList.get(i).getStatus();
        viewHolder.tvVoucherStatus.setText(status);

        if (status.equals("")) {
            Glide.with(context)
                    .asBitmap()
                    .transform(new CenterCrop())
                    .load("https://www.goodfood.com.au/content/dam/images/h/0/f/a/q/i/image.related.wideLandscape.940x529.h0fa4n.png/1515456591895.jpg")
                    .into(viewHolder.ivVoucherItem);
            viewHolder.tvVoucherStatus.setVisibility(GONE);
            //bind item that have no status to a listener
            viewHolder.bind(voucherItemList.get(i), listener);

        } else {
            //image blur if status is expired, redeemed
            Glide.with(context)
                    .asBitmap()
                    .transform(new CenterCrop(), new BlurTransformation())
                    .load("https://www.goodfood.com.au/content/dam/images/h/0/f/a/q/i/image.related.wideLandscape.940x529.h0fa4n.png/1515456591895.jpg")
                    .into(viewHolder.ivVoucherItem);

        }


    }

    @Override
    public int getItemCount() {
        return voucherItemList.size();
    }


    /**
     * Listener declaration and callback methods
     **/
    VoucherAdapter.FragmentListener listener;

    public void setupListener(VoucherAdapter.FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
        void onItemClick(VoucherItem item);
    }

}
