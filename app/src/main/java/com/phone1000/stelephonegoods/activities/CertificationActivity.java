package com.phone1000.stelephonegoods.activities;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;

public class CertificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CertificationActivity.class.getSimpleName();
    private Button btnVerify;
    private EditText username;
    private EditText userId;
    private EditText userContactor;
    private EditText bankCard;
    private EditText bankName;
    private EditText phoneNum;
    private EditText identifyCode;
    private String phoneNumber;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        initView();
    }

    private void initView() {
        Log.e(TAG, "initView: ");
        ImageView btnBack = (ImageView) findViewById(R.id.certification_back);
        username = (EditText) findViewById(R.id.certification_textview_username);
        userId = (EditText) findViewById(R.id.certification_textview_userid);
        userContactor = (EditText) findViewById(R.id.certification_textview_usercontractor);
        bankCard = (EditText) findViewById(R.id.certification_textview_bankcard);
        bankName = (EditText) findViewById(R.id.certification_textview_bank);
        phoneNum = (EditText) findViewById(R.id.certification_textview_phonenum);
        identifyCode = (EditText) findViewById(R.id.certification_textview_identifycode);
        username.clearFocus();
        btnVerify = (Button) findViewById(R.id.certification_btn_verify);
        btnSend = (Button) findViewById(R.id.certification_button_sendCode);
        btnBack.setOnClickListener(this);
        userContactor.setOnClickListener(this);
        bankName.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnVerify.setOnClickListener(this);
        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length==11) {
                    btnSend.setEnabled(true);
                }else{
                    btnSend.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                ContentResolver contentResolver = getContentResolver();
                Uri contactData = data.getData();
                Cursor cursor = contentResolver.query(contactData, null, null, null, null);
                cursor.moveToFirst();
                String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Log.e(TAG, "onActivityResult: " + contactId);
                String section = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId;
                ContentResolver contentResolver1 = getContentResolver();
                Cursor phone = contentResolver1.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, section, null, null);
                Log.e(TAG, "onActivityResult: " + phone);
                while (phone.moveToNext()) {
                    phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phoneNumber += "(" + username + ")";
                Log.e(TAG, "onActivityResult: " + phoneNumber);
                userContactor.setText(phoneNumber);
            }
        }
        if(requestCode==1){
            if (resultCode==RESULT_OK) {
                String name = data.getStringExtra("bankName");
                bankName.setText(name);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certification_back:
                finish();
                break;
            case R.id.certification_textview_usercontractor:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 0);
                break;
            case R.id.certification_textview_bank:
                Intent intent1 = new Intent(this, BankActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.certification_btn_verify:
                String username = this.username.getText().toString();
                String userId = this.userId.getText().toString();
                String usrContacts = userContactor.getText().toString();
                String bankCard = this.bankCard.getText().toString();
                String bankName = this.bankName.getText().toString();
                String phoneNum = this.phoneNum.getText().toString();
                String identifyCode = this.identifyCode.getText().toString();
                if (TextUtils.equals(username,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请输入用户名。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(userId,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请输入身份证号。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(usrContacts,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请选择常用联系人。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(bankCard,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请输入银行卡号。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(bankName,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请选择银行。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(phoneNum,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请输入手机号码。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(identifyCode,"")) {
                    Toast.makeText(CertificationActivity.this, "亲，请输入验证码。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.equals(identifyCode,"123456")){
                    Toast.makeText(CertificationActivity.this, "认证成功", Toast.LENGTH_SHORT).show();
                    SElephant.isCertigition=true;
                    new AlertDialog.Builder(this)
                            .setMessage("是否立即提额")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(CertificationActivity.this, CashUpdateActivity.class);
                                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent2);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create().show();


                }else{
                    Toast.makeText(CertificationActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.certification_button_sendCode:

                break;
        }
    }
}
