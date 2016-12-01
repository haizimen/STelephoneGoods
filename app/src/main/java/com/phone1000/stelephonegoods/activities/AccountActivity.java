package com.phone1000.stelephonegoods.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.account_back);
        LinearLayout phoneNum = (LinearLayout) findViewById(R.id.account_phoneNum);
        LinearLayout loginPassword = (LinearLayout) findViewById(R.id.account_login_password);
        LinearLayout payPassword = (LinearLayout) findViewById(R.id.account_pay_password);
        LinearLayout idCardCertification = (LinearLayout)findViewById(R.id.account_idcard_certification);
        LinearLayout vadioCertification = (LinearLayout) findViewById(R.id.account_vadio_certification);
        LinearLayout BankCardManger = (LinearLayout) findViewById(R.id.account_bankcard_manager);
        btnBack.setOnClickListener(this);
        phoneNum.setOnClickListener(this);
        loginPassword.setOnClickListener(this);
        payPassword.setOnClickListener(this);
        idCardCertification.setOnClickListener(this);
        vadioCertification.setOnClickListener(this);
        BankCardManger.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_back:
                finish();
                break;
            case R.id.account_phoneNum:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_login_password:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_pay_password:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_idcard_certification:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_vadio_certification:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_bankcard_manager:
                Toast.makeText(AccountActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
