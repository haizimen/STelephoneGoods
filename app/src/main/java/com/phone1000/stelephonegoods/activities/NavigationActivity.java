package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.RecyclerGridAdapter;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.NavigationModel;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    private int id;
    private LinearLayout mLinear;
    private RecyclerView mGrid;
    private RecyclerGridAdapter adapter;
    private ImageView mBack;
    private ImageView mCart;
    private ImageView mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.e("QNMLGB", "onCreate: " + id);
        mBack = (ImageView) findViewById(R.id.navigation_back);
        mCart = (ImageView) findViewById(R.id.navigation_cart);
        mGrid = (RecyclerView) findViewById(R.id.navigation_grid);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mGrid.setLayoutManager(manager);
        adapter = new RecyclerGridAdapter(this, null);
        mGrid.setAdapter(adapter);
        setupView();
        mBack.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mHeader = (ImageView) findViewById(R.id.navigation_header);
    }

    private void setupView() {
        OkHttpUtils.get()
                .url(ReadUrl.HANDPICKNAVIGATIONURL + id + ReadUrl.NONEURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        NavigationModel list = gson.fromJson(response, NavigationModel.class);
                        Picasso.with(mHeader.getContext()).load(list.getBody().getData().getBannerUrl()).into(mHeader);
                        adapter.updataRes(list.getBody().getData());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_back:
                finish();
                break;
            case R.id.navigation_cart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
