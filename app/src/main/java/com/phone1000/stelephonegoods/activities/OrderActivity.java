package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ConstantStr;
import com.phone1000.stelephonegoods.constant.ReadUrl;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private TextView mConfige;
    private ListView mListView;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.order_back);
        mTitle = (TextView) findViewById(R.id.order_title);
        mConfige = (TextView) findViewById(R.id.order_confige);
        mListView = (ListView) findViewById(R.id.order_lv);
        LinearLayout empty = (LinearLayout) findViewById(R.id.order_empty);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        mTitle.setText(title);
        mListView.setEmptyView(empty);
        switch (title){
            case ConstantStr.MINEORDER:
                //加载订单的数据
                break;
            case ConstantStr.WAITGOODS:
                //加载付款的数据
                break;
            case ConstantStr.WAITPAY:
                //加载收货的数据
                break;
            case ConstantStr.CASH:
                //加载现金券的数据
                mConfige.setVisibility(View.VISIBLE);
                break;
            case ConstantStr.FREE:
                //加载免息券的数据
                mConfige.setVisibility(View.VISIBLE);
                break;
        }
        btnBack.setOnClickListener(this);
        mConfige.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_back:
                finish();
                break;
            case R.id.order_confige:
                Intent intent = new Intent(this, ReadDetailActivity.class);
                if (title.equals(ConstantStr.CASH)) {
                    intent.putExtra("url", ReadUrl.CASH_USED_INTRODUCE_URL);
                }else if(title.equals(ConstantStr.FREE)){
                    intent.putExtra("url",ReadUrl.FREE_USED_INTRODUCE_URL);
                }
                startActivity(intent);
                break;
        }
    }
}
