package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.alipay.PayResult;
import com.phone1000.stelephonegoods.alipay.SignUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BuyCostActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = BuyCostActivity.class.getSimpleName();
    private ImageView mImage;
    private TextView mTitle;
    private TextView mZhifuValue;
    private TextView mFenqi;
    private TextView mBuycostyuefuvalue;
    private ImageView mBackimage;
    private Button mCommitBut;

    private double aDouble;
    private int alipayjian;
    private int value;

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
        value = intent.getIntExtra("value",0);
        int valuefen = intent.getIntExtra("valuefen",0);
        alipayjian = intent.getIntExtra("alipayjian",0);
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

        aDouble = valuefen / 100.0;
        mFenqi.setText("￥"+aDouble+"x12期");

        mBuycostyuefuvalue = (TextView)findViewById(R.id.buycostyuefu_value);

        mBuycostyuefuvalue.setText("￥"+aDouble);

        mBackimage = (ImageView) findViewById(R.id.super_back_image);
        mBackimage.setOnClickListener(this);


        mCommitBut = (Button) findViewById(R.id.super_buycost_commitc);
        mCommitBut.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.super_back_image:
                finish();

                break;
            case R.id.super_buycost_commitc:

                Intent intent1 = new Intent(this, BuyAliPayActivity.class);
                intent1.putExtra("value",value);
                intent1.putExtra("alipayjian",alipayjian);
                startActivity(intent1);
                break;
        }
    }
}
