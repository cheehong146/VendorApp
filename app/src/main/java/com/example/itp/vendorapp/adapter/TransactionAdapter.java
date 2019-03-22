package com.example.itp.vendorapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.databinding.RowTransactionListBinding;
import com.example.itp.vendorapp.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactionList;

    private Context context;

    RowTransactionListBinding binding;

    public TransactionAdapter(List<Transaction> transactionList, Context context) {
        this.transactionList = transactionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_transaction_list, viewGroup, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(transactionList.get(i).getTitle());
        viewHolder.tvDate.setText(transactionList.get(i).getDate());

        viewHolder.tvPoints.setText(transactionList.get(i).getPoints());
        if (isPointNegative(transactionList.get(i).getPoints())) {
            viewHolder.tvPoints.setTextColor(context.getResources().getColor(R.color.color_transaction_negative_point));
        } else {
            viewHolder.tvPoints.setTextColor(context.getResources().getColor(R.color.color_transaction_positive_point));
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    private boolean isPointNegative(String points) {
        char symbol = points.charAt(0);
        return symbol == '-' ? true : false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = binding.tvTransactionTitle;
            this.tvDate = binding.tvTransactionDate;
            this.tvPoints = binding.tvTransactionPoints;
        }
    }
}
