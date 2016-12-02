package com.phone1000.stelephonegoods.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.BankListAdapter;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.BankContent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class BankActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private String TAG=BankActivity.class.getSimpleName();
    private BankListAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
        }
    };
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        dialog = new ProgressDialog(this);
        dialog .setMessage("正在加载中....");
        dialog.setMax(100);
        dialog.show();
        initView();
        setupData();
    }

    private void setupData() {
        OkHttpUtils.get()
                .url(ReadUrl.BANK_NAME_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: " );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        BankContent bankContent = gson.fromJson(response, BankContent.class);
                        adapter.updateRes(bankContent.getBody().getData());
                        handler.sendEmptyMessage(0x100);
                    }
                });
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.bank_back);
        mListView = (ListView) findViewById(R.id.bank_lv);
        adapter = new BankListAdapter(this,null, R.layout.bank_item_layout);
        mListView.setAdapter(adapter);
        btnBack.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = getIntent();
        intent.putExtra("bankName",adapter.getItem(position).getBankName());
        setResult(RESULT_OK,intent);
        finish();
    }
}
