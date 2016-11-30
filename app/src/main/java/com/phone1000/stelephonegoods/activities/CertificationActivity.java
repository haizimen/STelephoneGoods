package com.phone1000.stelephonegoods.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.phone1000.stelephonegoods.R;

public class CertificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnVerify;
    private EditText username;
    private EditText userId;
    private EditText userContactor;
    private EditText bankCard;
    private EditText bankName;
    private EditText phoneNum;
    private EditText identifyCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.certification_back);
        EditText username = (EditText) findViewById(R.id.certification_textview_username);
        EditText userId = (EditText) findViewById(R.id.certification_textview_userid);
        EditText userContactor = (EditText) findViewById(R.id.certification_textview_usercontractor);
        EditText bankCard = (EditText) findViewById(R.id.certification_textview_bankcard);
        EditText bankName = (EditText) findViewById(R.id.certification_textview_bank);
        EditText phoneNum = (EditText) findViewById(R.id.certification_textview_phonenum);
        EditText identifyCode = (EditText) findViewById(R.id.certification_textview_identifycode);

        btnVerify = (Button) findViewById(R.id.certification_btn_verify);
        Button sendCode = (Button) findViewById(R.id.certification_button_sendCode);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certification_back:
                finish();
                break;
        }
    }
}
