package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.AddressContent;
import com.phone1000.stelephonegoods.model.ProvinceContent;
import com.phone1000.stelephonegoods.utils.MD5;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class AddNewAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText locationDetail;
    private EditText phoneNum;
    private CheckBox isDefault;
    private Button verify;
    private Spinner province;
    private Spinner city;
    private Spinner area;
    private String TAG=AddNewAddressActivity.class.getSimpleName();
    private   List<Map<String,String>> data;
    private   List<Map<String,String>> data1;
    private   List<Map<String,String>> data2;
    private String provinceName;
    private String cityName;
    private String areaName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        initView();
        setupData();
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
                        SimpleAdapter simpleAdapter = new SimpleAdapter(AddNewAddressActivity.this, data, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                        province.setAdapter(simpleAdapter);
                    }
                });



    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.add_address_back);
        name = (EditText) findViewById(R.id.add_address_name);
        phoneNum = (EditText) findViewById(R.id.add_address_phonenum);
        locationDetail = (EditText) findViewById(R.id.add_address_location_detail);
        isDefault = (CheckBox) findViewById(R.id.add_address_isdefault);
        verify = (Button) findViewById(R.id.add_address_verify);
        province = (Spinner) findViewById(R.id.add_address_province);
        city = (Spinner) findViewById(R.id.add_address_city);
        area = (Spinner) findViewById(R.id.add_address_area);
        btnBack.setOnClickListener(this);
        verify.setOnClickListener(this);
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                SimpleAdapter simpleAdapter = new SimpleAdapter(AddNewAddressActivity.this, data1, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                                city.setAdapter(simpleAdapter);
                            }
                        });
                area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                SimpleAdapter simpleAdapter = new SimpleAdapter(AddNewAddressActivity.this, data2, R.layout.location_item_layout, new String[]{"name"}, new int[]{R.id.name});
                                area.setAdapter(simpleAdapter);
                            }
                        });

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_back:
                finish();
                break;
            case R.id.add_address_verify:
                String userName = this.name.getText().toString();
                String phoneNumber = phoneNum.getText().toString();
                String location=cityName+cityName+areaName;
                String detail = locationDetail.getText().toString();
                boolean checked = isDefault.isChecked();
                if (TextUtils.equals(userName,"")) {
                    Toast.makeText(AddNewAddressActivity.this, "收件人不能为空，不然你就收不到了", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(phoneNumber,"")) {
                    Toast.makeText(AddNewAddressActivity.this, "联系电话不能不填啊，不然我们就不好找你了", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (TextUtils.equals(detail,"")) {
                    Toast.makeText(AddNewAddressActivity.this, "亲，请填写详细信息", Toast.LENGTH_SHORT).show();
                    break;
                }
                AddressContent addressContent = new AddressContent();
                addressContent.setName(userName);
                addressContent.setDefault(checked);
                addressContent.setLocation(location);
                addressContent.setLocationDetail(detail);
                addressContent.setPhoneNum(phoneNumber);
                // holder.namePhone.setText("   "+data.get(position).getName()+"   "+data.get(position).getPhoneNum());
                //holder.location.setText("   "+data.get(position).getLocation()+"  "+data.get(position).getLocationDetail());
                addressContent.setId(MD5.GetMD5Code("   "+userName+"   "+phoneNumber+"   "+location+"  "+detail));
                Intent intent = getIntent();
                intent.putExtra("address",addressContent);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

}
