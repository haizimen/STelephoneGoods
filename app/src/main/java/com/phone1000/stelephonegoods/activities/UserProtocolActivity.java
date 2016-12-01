
package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;

public class UserProtocolActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_protocol);

        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.user_protocol_back);
        TextView buy = (TextView) findViewById(R.id.user_protocol_buy);
        TextView installment = (TextView) findViewById(R.id.user_protocol_installment);
        btnBack.setOnClickListener(this);
        buy.setOnClickListener(this);
        installment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReadDetailActivity.class);
        switch (v.getId()) {
            case R.id.user_protocol_back:
                finish();
                break;
            case R.id.user_protocol_buy:
             intent.putExtra("url", ReadUrl.USER_SERVICE_PROTOCOL);
                startActivity(intent);
                break;
            case R.id.user_protocol_installment:
                intent.putExtra("url",ReadUrl.USER_INSTALLMENT_PROTOCOL);
                startActivity(intent);
                break;
        }
    }
}
