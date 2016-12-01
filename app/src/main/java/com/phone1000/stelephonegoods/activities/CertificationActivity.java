package com.phone1000.stelephonegoods.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.phone1000.stelephonegoods.R;

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

        btnVerify = (Button) findViewById(R.id.certification_btn_verify);
        Button sendCode = (Button) findViewById(R.id.certification_button_sendCode);
        btnBack.setOnClickListener(this);
        userContactor.setOnClickListener(this);
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
                cursor.moveToFirst();
                Log.e(TAG, "onActivityResult: " + phone);
                while (phone.moveToNext()) {
                    phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phoneNumber += "(" + username + ")";
                Log.e(TAG, "onActivityResult: " + phoneNumber);
                userContactor.setText(phoneNumber);
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
        }
    }
}
