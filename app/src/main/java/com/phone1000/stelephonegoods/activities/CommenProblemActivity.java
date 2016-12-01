package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;

public class CommenProblemActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen_problem);

        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.problem_back);
        LinearLayout hot = (LinearLayout) findViewById(R.id.problem_ll_hot);
        LinearLayout safe = (LinearLayout) findViewById(R.id.problem_ll_safe);
        LinearLayout credit = (LinearLayout) findViewById(R.id.problem_ll_credit);
        LinearLayout buy = (LinearLayout) findViewById(R.id.problem_ll_buy);
        LinearLayout service = (LinearLayout) findViewById(R.id.problem_ll_service);
        LinearLayout installment = (LinearLayout) findViewById(R.id.problem_ll_installment);
        btnBack.setOnClickListener(this);
        hot.setOnClickListener(this);
        safe.setOnClickListener(this);
        credit.setOnClickListener(this);
        buy.setOnClickListener(this);
        service.setOnClickListener(this);
        installment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReadDetailActivity.class);
        switch (v.getId()) {
            case R.id.problem_back:
                finish();
                break;
            case R.id.problem_ll_hot:
                intent.putExtra("url", ReadUrl.PROBLEM_URL_ONE);
                startActivity(intent);
                break;
            case R.id.problem_ll_safe:
                intent.putExtra("url",ReadUrl.PROBLEM_URL_TWO);
                startActivity(intent);
                break;
            case R.id.problem_ll_credit:
                intent.putExtra("url",ReadUrl.PROBLEM_URL_THREE);
                startActivity(intent);
                break;
            case R.id.problem_ll_buy:
                intent.putExtra("url",ReadUrl.PROBLEM_URL_FOUR);
                startActivity(intent);
                break;
            case R.id.problem_ll_service:
                intent.putExtra("url",ReadUrl.PROBLEM_URL_FIVE);
                startActivity(intent);
                break;
            case R.id.problem_ll_installment:
                intent.putExtra("url",ReadUrl.PROBLEM_URL_SIX);
                startActivity(intent);
                break;
        }

    }
}
