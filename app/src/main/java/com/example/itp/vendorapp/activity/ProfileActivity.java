package com.example.itp.vendorapp.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.adapter.TransactionAdapter;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityProfileBinding;
import com.example.itp.vendorapp.model.Customer;
import com.example.itp.vendorapp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    private Customer customer;
    private List<Transaction> transactionList;

    private TransactionAdapter transactionAdapter;

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ProfileActivity.this, R.layout.activity_profile);

        if (getIntent() != null) {
            customer = getIntent().getParcelableExtra("customer");
            //TODO DELETE next line
            transactionList = getTransactionDummyData();
        }

        setupListener();
        setupTransactionAdapter();
    }

    @Override
    public void setupListener() {
        binding.toolbarProfile.ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();//pop Profile activity off the top of the stack
        }
    }

    private void setupTransactionAdapter() {
        transactionAdapter = new TransactionAdapter(transactionList, this);
        binding.rvProfileTransaction.setAdapter(transactionAdapter);
        binding.rvProfileTransaction.setLayoutManager(getRvLayoutManager());
    }

    private LinearLayoutManager getRvLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    private void setupViewsWithData() {
        //toolbar
        binding.toolbarProfile.tvTitle.setText("Lekkars Cafe");

        //customer
//        binding.customerCardProfile.ivCustomerCardUsername = customer
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private List<Transaction> getTransactionDummyData() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            transactions.add(new Transaction("Black Hair Dye", "12 Feb 2018", "+800"));
        }
        for (int i = 0; i < 5; i++) {
            transactions.add(new Transaction("Black Hair Dye", "12 Feb 2018", "-800"));
        }
        return transactions;
    }
}
