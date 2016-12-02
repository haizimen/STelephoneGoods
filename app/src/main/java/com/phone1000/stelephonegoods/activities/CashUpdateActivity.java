package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.CashUpdatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CashUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private ImageView mImageBack;
    private LayoutInflater inflater;
    public static boolean isCertigition=false;
    private TabLayout mTabLayout;
    private int currentPositon=0;
    private String TAG=CashUpdateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_update);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.cashupdate_viewpager);
        mImageBack = (ImageView) findViewById(R.id.cashupdate_back);
        mTabLayout = ((TabLayout) findViewById(R.id.crashupdate_tablayout));
        inflater=LayoutInflater.from(this);
        View one = inflater.inflate(R.layout.cash_update_item_one, null);
        View two = inflater.inflate(R.layout.cash_update_item_two, null);
        View three = inflater.inflate(R.layout.cash_update_item_three, null);
        View four = inflater.inflate(R.layout.cash_update_item_four, null);
        View five = inflater.inflate(R.layout.cash_update_item_five, null);
        View six = inflater.inflate(R.layout.cash_update_item_six, null);
        TextView btnOne = (TextView) one.findViewById(R.id.cash_update_item_one_btn);
        TextView btnTwo = (TextView) two.findViewById(R.id.cash_update_item_two_btn);
        TextView btnThree = (TextView) three.findViewById(R.id.cash_update_item_three_btn);
        TextView btnFour = (TextView) four.findViewById(R.id.cash_update_item_four_btn);
        TextView btnFive = (TextView) five.findViewById(R.id.cash_update_item_five_btn);
        TextView btnSix = (TextView) six.findViewById(R.id.cash_update_item_six_btn);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        mImageBack.setOnClickListener(this);
        if (!isCertigition) {
            btnOne.setEnabled(true);
            btnTwo.setEnabled(false);
            btnThree.setEnabled(false);
            btnFour.setEnabled(false);
            btnFive.setEnabled(false);
            btnSix.setEnabled(false);

        }else{
            btnOne.setEnabled(false);
            btnTwo.setEnabled(true);
            btnThree.setEnabled(true);
            btnFour.setEnabled(true);
            btnFive.setEnabled(true);
            btnSix.setEnabled(true);
        }
        List<View> data=new ArrayList<>();
        data.add(one);
        data.add(two);
        data.add(three);
        data.add(four);
        data.add(five);
        data.add(six);
        mViewPager.setAdapter(new CashUpdatePagerAdapter(data));

        for (int i = 0; i < data.size(); i++) {
            TabLayout.Tab tab;
            if (i==-1){
                tab = mTabLayout.newTab().setCustomView(R.layout.tablayout_item_selection);
            }else{
                tab = mTabLayout.newTab().setCustomView(R.layout.tablayout_item);
            }
            mTabLayout.addTab(tab);
        }
         mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cash_update_item_one_btn:
                Intent intent = new Intent(this, CertificationActivity.class);
                startActivity(intent);
                break;
            case R.id.cash_update_item_two_btn:
                Toast.makeText(CashUpdateActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cash_update_item_three_btn:
                Toast.makeText(CashUpdateActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cash_update_item_four_btn:
                Toast.makeText(CashUpdateActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cash_update_item_five_btn:
                Toast.makeText(CashUpdateActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cash_update_item_six_btn:
                Toast.makeText(CashUpdateActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cashupdate_back:
                finish();
                break;
        }
    }
}
