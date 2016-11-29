package com.phone1000.stelephonegoods.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phone1000.stelephonegoods.R;

public class CashUpdateActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ImageView mImageBack;
    private LinearLayout mll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_update);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.cashupdate_viewpager);
        mImageBack = (ImageView) findViewById(R.id.cashupdate_back);
        mll = (LinearLayout) findViewById(R.id.crashupdate_ll);

    }
}
