package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;
import com.phone1000.stelephonegoods.adapters.RecieverAdapter;
import com.phone1000.stelephonegoods.model.AddressContent;
import com.phone1000.stelephonegoods.model.MyEvent;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class RecieverAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = RecieverAddressActivity.class.getSimpleName();
    private RecyclerView recycler;
    private RecieverAdapter adapter;
    private DbManager db;
    private Button btnVerify;
    private View empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=x.getDb(SElephant.daoConfig);
        setContentView(R.layout.activity_reciever_address);
        EventBus.getDefault().register(this);
        initView();
        setupData();
    }

    private void initView() {
        Log.e(TAG, "initView: " );
        ImageView btnBack = (ImageView) findViewById(R.id.address_back);
        recycler = ((RecyclerView) findViewById(R.id.address_lv));
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        adapter = new RecieverAdapter(this,null);
        recycler.setAdapter(adapter);
        empty = findViewById(R.id.address_empty);
        Button btnAdd = (Button) findViewById(R.id.address_add);
        btnVerify = (Button) findViewById(R.id.reciever_add_btn);
        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnVerify.setOnClickListener(this);

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public  void onMessageEvent(MyEvent event){
        empty.setVisibility(View.VISIBLE);
        btnVerify.setVisibility(View.GONE);
    }
    private void setupData() {
        try {
            List<AddressContent> all = db.selector(AddressContent.class).findAll();
            if (all!=null&&all.size()!=0) {
                List<AddressContent> data=new ArrayList<>();
                empty.setVisibility(View.GONE);
                btnVerify.setVisibility(View.VISIBLE);
                adapter.updateRes(all);
            }else{
                empty.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.GONE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1) {
            if (resultCode==RESULT_OK) {
                AddressContent address = (AddressContent) data.getParcelableExtra("address");
                addressDBOperation(address);
            }
        }
    }

    private void addressDBOperation(AddressContent address) {
        if (address.isDefault()) {
            try {
                AddressContent isDefault = db.selector(AddressContent.class).where("isDefault", "=", true).findFirst();
                if (isDefault==null) {
                    db.save(address);
                }else{
                    WhereBuilder whereBuilder =  WhereBuilder.b("isDefault", "=", true);
                    KeyValue keyValue = new KeyValue("isDefault",false);
                    db.update(AddressContent.class, whereBuilder, keyValue);
                    db.save(address);
                }

            } catch (DbException e) {
                e.printStackTrace();
            }
        }else{
            try {
                db.save(address);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        setupData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.address_back:
                finish();
                break;
            case R.id.address_add:
                Intent intent = new Intent(this, AddNewAddressActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.reciever_add_btn:
                Intent intent1 = new Intent(this, AddNewAddressActivity.class);
                startActivityForResult(intent1,1);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
