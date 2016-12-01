package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.squareup.picasso.Picasso;

public class BuyCostActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;
    private TextView mTitle;
    private TextView mZhifuValue;
    private TextView mFenqi;
    private TextView mBuycostyuefuvalue;
    private ImageView mBackimage;
    private Button mCommitBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cost);
        initView();
    }

    private void initView() {

        Intent intent= getIntent();
        String image = intent.getStringExtra("image");


        String title = intent.getStringExtra("title");
        int value = intent.getIntExtra("value",0);
        int valuefen = intent.getIntExtra("valuefen",0);
        mImage = (ImageView) findViewById(R.id.buycost_image);
        Picasso.with(this).load(image).into(mImage);

        mTitle = (TextView) findViewById(R.id.buycost_title);
        mTitle.setText(title);


        mZhifuValue = (TextView) findViewById(R.id.buycost_zhifuvalue);
        double v2 = value / 100.00;
        String string ="￥"+v2;
        SpannableString sp = new SpannableString(string);
        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mZhifuValue.setText(sp);


        mFenqi = (TextView) findViewById(R.id.buycost_fenqifu);

        double v = valuefen / 100.0;
        mFenqi.setText("￥"+v+"x12期");

        mBuycostyuefuvalue = (TextView)findViewById(R.id.buycostyuefu_value);

        mBuycostyuefuvalue.setText("￥"+v);

        mBackimage = (ImageView) findViewById(R.id.super_back_image);
        mBackimage.setOnClickListener(this);


        mCommitBut = (Button) findViewById(R.id.super_buycost_commit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.super_back_image:
                Intent intent = new Intent(this, SuperBacKTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.super_buycost_commit:


                break;
        }
    }
}
