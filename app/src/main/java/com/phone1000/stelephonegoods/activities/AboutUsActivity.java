package com.phone1000.stelephonegoods.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.phone1000.stelephonegoods.R;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.about_us_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
