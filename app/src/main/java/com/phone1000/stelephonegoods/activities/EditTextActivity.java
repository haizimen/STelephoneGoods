package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.AddressContent;
import com.phone1000.stelephonegoods.model.ProvinceContent;
import com.phone1000.stelephonegoods.utils.MD5;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mName;
    private EditText mPhoneNum;
    private EditText mDetail;
    private Spinner mProvince;
    private Spinner mCity;
    private Spinner mArea;
    private CheckBox mIsdefault;

    private List<Map<String,String>> data;
    private   List<Map<String,String>> data1;
    private   List<Map<String,String>> data2;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String TAG=EditTextActivity.class.getSimpleName();
    private Button mVerify;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initView();
        setupData();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.edit_address_back);
        mName = (EditText) findViewById(R.id.edit_address_name);
        mPhoneNum = (EditText) findViewById(R.id.edit_address_phonenum);
        mDetail = (EditText) findViewById(R.id.edit_address_location_detail);
        mProvince = (Spinner) findViewById(R.id.edit_address_province);
        mCity = (Spinner) findViewById(R.id.edit_address_city);
        mArea = (Spinner) findViewById(R.id.edit_address_area);
        mIsdefault = (CheckBox) findViewById(R.id.edit_address_isdefault);
        mVerify = (Button) findViewById(R.id.edit_address_verify);
        mVerify.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        Intent intent = getIntent();
        AddressContent content = (AddressContent) intent.getParcelableExtra("content");
        mName.setText(content.getName());
        mPhoneNum.setText(content.getPhoneNum());
        mDetail.setText(content.getLocationDetail());
        id=MD5.GetMD5Code("   "+content.getName()+"   "+content.getPhoneNum()+"   "+content.getLocation()+"  "+content.getLocationDetail());

        mProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String code = data.get(position).get("code");
                provinceName =data.get(position).get("name");
                Log.e(TAG, "onItemSelected: "+code+provinceName );
                OkHttpUtils.post()
                        .url(ReadUrl.LOCATION_URL)
                        .addParams("code",code)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "onError: " );
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "onResponse: " );
                                Gson gson = new Gson();
                                ProvinceContent content = gson.fromJson(response, ProvinceContent.class);
                                data1=new ArrayList<>();
                                for (int i = 0; i < content.getBody().getData().size(); i++) {
                                    Map<String,String> map=new HashMap<String, String>();
                                    String fullName = content.getBody().getData().get(i).getFullName();
                                    map.put("name", fullName);
                                    String code = content.getBody().getData().get(i).getCode();
                                    map.put("code", code);
                                    data1.add(map);
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(EditTextActivity.this, data1, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                                mCity.setAdapter(simpleAdapter);
                            }
                        });
                mArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        areaName=data2.get(position).get("name");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String code = data1.get(position).get("code");
                cityName = data1.get(position).get("name");
                OkHttpUtils.post()
                        .url(ReadUrl.LOCATION_URL)
                        .addParams("code",code)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "onError: " );
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "onResponse: " );
                                Gson gson = new Gson();
                                ProvinceContent content = gson.fromJson(response, ProvinceContent.class);
                                data2=new ArrayList<>();
                                for (int i = 0; i < content.getBody().getData().size(); i++) {
                                    Map<String,String> map=new HashMap<String, String>();
                                    String fullName = content.getBody().getData().get(i).getFullName();
                                    map.put("name", fullName);
                                    String code = content.getBody().getData().get(i).getCode();
                                    map.put("code", code);
                                    data2.add(map);
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(EditTextActivity.this, data2, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                                mArea.setAdapter(simpleAdapter);
                            }
                        });

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupData() {
        OkHttpUtils.post()
                .url(ReadUrl.LOCATION_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError:"  );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: " );
                        Gson gson = new Gson();
                        ProvinceContent content = gson.fromJson(response, ProvinceContent.class);
                        data=new ArrayList<>();
                        for (int i = 0; i < content.getBody().getData().size(); i++) {
                            Map<String,String> map=new HashMap<String, String>();
                            String fullName = content.getBody().getData().get(i).getFullName();
                            map.put("name", fullName);
                            String code = content.getBody().getData().get(i).getCode();
                            map.put("code", code);
                            data.add(map);
                        }
                        SimpleAdapter simpleAdapter = new SimpleAdapter(EditTextActivity.this, data, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                        mProvince.setAdapter(simpleAdapter);
                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_address_verify:
                String userName = this.mName.getText().toString();
                String phoneNumber = mPhoneNum.getText().toString();
                String location=cityName+cityName+areaName;
                String detail = mDetail.getText().toString();
                boolean checked = mIsdefault.isChecked();
                if (TextUtils.equals(userName,"")) {
                    Toast.makeText(EditTextActivity.this, "收件人不能为空，不然你就收不到了", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(phoneNumber,"")) {
                    Toast.makeText(EditTextActivity.this, "联系电话不能不填啊，不然我们就不好找你了", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(detail,"")) {
                    Toast.makeText(EditTextActivity.this, "亲，请填写详细信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddressContent addressContent = new AddressContent();
                addressContent.setName(userName);
                addressContent.setDefault(checked);
                addressContent.setLocation(location);
                addressContent.setLocationDetail(detail);
                Log.e(TAG, "onClick: "+phoneNumber );
               addressContent.setPhoneNum(phoneNumber);
                // holder.namePhone.setText("   "+data.get(position).getName()+"   "+data.get(position).getPhoneNum());
                //holder.location.setText("   "+data.get(position).getLocation()+"  "+data.get(position).getLocationDetail());
                DbManager db = x.getDb(SElephant.daoConfig);
                try {
                    db.save(addressContent);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this,RecieverAddressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
                break;
            case R.id.edit_address_back:
                finish();
                break;
        }
    }
}
