package com.phone1000.stelephonegoods.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.phone1000.stelephonegoods.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.cart_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);

    }
}
