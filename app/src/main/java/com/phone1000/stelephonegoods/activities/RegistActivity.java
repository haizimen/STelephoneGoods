package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPhoneNum;
    private EditText mPassword;
    private Button mSubmit;
    private CheckBox mAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.regist_back);
        mPhoneNum = (EditText) findViewById(R.id.regist_edittext_phone);
        mPassword = (EditText) findViewById(R.id.regist_edittext_password);
        mSubmit = (Button) findViewById(R.id.regist_btn);
        CheckBox isShow = (CheckBox) findViewById(R.id.regist_password_show);
        mAgree = (CheckBox) findViewById(R.id.regist_protocol_agree);
        TextView protocol = (TextView) findViewById(R.id.regist_protocol);
        btnBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        protocol.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_btn:

                break;
            case R.id.regist_back:
                finish();
                break;
            case R.id.regist_protocol:
                Intent intent = new Intent(this, ReadDetailActivity.class);
                intent.putExtra("url", ReadUrl.XIAOXIANG_PROTOCOL_URL);
                startActivity(intent);
                break;
        }
    }
}
