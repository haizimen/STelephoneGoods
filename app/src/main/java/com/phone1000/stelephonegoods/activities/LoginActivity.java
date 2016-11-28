package com.phone1000.stelephonegoods.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.LoginViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.login_back);
        TextView btnRegist = (TextView) findViewById(R.id.login_registe);
        TabLayout tablayout = (TabLayout) findViewById(R.id.login_tablayout);
        ViewPager viewPger = (ViewPager) findViewById(R.id.login_viewpager);
        tablayout.addTab(tablayout.newTab().setText("手机快捷登陆"));
        tablayout.addTab(tablayout.newTab().setText("账号密码登陆"));
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.fragment_phone_fast_login_layout, null);
        View view2 = inflater.inflate(R.layout.fragment_number_password_login_layout, null);
        List<View> data=new ArrayList<>();
        data.add(view1);
        data.add(view2);
        viewPger.setAdapter(new LoginViewPagerAdapter(data));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPger));
        viewPger.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        btnBack.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
        EditText editTextPhone = (EditText) view1.findViewById(R.id.login_phonefast_edittext_phone);
        EditText editTextIdentifyCode = (EditText) view1.findViewById(R.id.login_phonefast_edittext_identifycode);
        //view1.findViewById(R.id.login_)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:

                finish();
                break;
            case R.id.login_registe:

                break;
        }
    }

}
