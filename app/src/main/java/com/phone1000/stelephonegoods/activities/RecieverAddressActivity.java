package com.phone1000.stelephonegoods.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;

public class RecieverAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever_address);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.address_back);
        mListView = ((ListView) findViewById(R.id.address_lv));
        View empty = findViewById(R.id.address_empty);
        Button btnAdd = (Button) findViewById(R.id.address_add);
        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.address_back:
                finish();
                break;
            case R.id.address_add:
                Toast.makeText(RecieverAddressActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
