package com.phone1000.stelephonegoods.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.LoginViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MinePayActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ListView mListViewOne;
    private ListView mListViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pay);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.mine_pay_back);
        TextView confige = (TextView) findViewById(R.id.mine_pay_confige);
        mTablayout = (TabLayout) findViewById(R.id.mine_pay_tablyout);
        mViewPager = (ViewPager) findViewById(R.id.mine_pay_viewpage);
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewOne = inflater.inflate(R.layout.mine_pay_pageview_item, null);
        View viewTwo = inflater.inflate(R.layout.mine_pay_pageview_item, null);
        mListViewOne = ((ListView) viewOne.findViewById(R.id.mine_pay_lv));
        mListViewTwo = ((ListView) viewTwo.findViewById(R.id.mine_pay_lv));
        //给两个Listview 添加适配器
        List<View> data=new ArrayList<>();
        data.add(viewOne);
        data.add(viewTwo);
        mViewPager.setAdapter(new LoginViewPagerAdapter(data));
        mTablayout.addTab(mTablayout.newTab().setText("未结清"));
        mTablayout.addTab(mTablayout.newTab().setText("已结清"));
        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
    }
}
