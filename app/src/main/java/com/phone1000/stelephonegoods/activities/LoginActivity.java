package com.phone1000.stelephonegoods.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.LoginViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean fastPhoneIsOk = false;

    private boolean fastIdentifyIsOk = false;

    private boolean userPhoneIsOk = false;

    private boolean usePasswordIsOk = false;
    private String TAG=LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ImageView btnBack = (ImageView) findViewById(R.id.login_back);
        TextView btnRegist = (TextView) findViewById(R.id.login_registe);
        TabLayout tablayout = (TabLayout) findViewById(R.id.login_tablayout);
        ViewPager viewPger = (ViewPager) findViewById(R.id.login_viewpager);
        tablayout.addTab(tablayout.newTab().setText("手机快捷登陆"));
        tablayout.addTab(tablayout.newTab().setText("账号密码登陆"));
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.fragment_phone_fast_login_layout, null);
        View view2 = inflater.inflate(R.layout.fragment_number_password_login_layout, null);
        List<View> data = new ArrayList<>();
        data.add(view1);
        data.add(view2);
        viewPger.setAdapter(new LoginViewPagerAdapter(data));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPger));
        viewPger.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        btnBack.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
        EditText editTextPhone = (EditText) view1.findViewById(R.id.login_phonefast_edittext_phone);
        EditText editTextIdentifyCode = (EditText) view1.findViewById(R.id.login_phonefast_edittext_identifycode);
        EditText userPhone = (EditText) view2.findViewById(R.id.login_user_password_edittext_phone);
        final EditText userPassword = (EditText) view2.findViewById(R.id.login_user_password_edittext_password);
        final Button btnUser = (Button) view2.findViewById(R.id.login_user_password_btn);
        CheckBox btnShow = (CheckBox) view2.findViewById(R.id.login_user_password_iscansee);
        final Button btnPhone = (Button) view1.findViewById(R.id.login_phonefast_btn);
        final Button sendIdentify = (Button) view1.findViewById(R.id.login_phonefast_checkbox);
        btnShow.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                   Log.e(TAG, "onCheckedChanged: " );
                                                   if (isChecked) {
                                                       userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                                   } else {
                                                       userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                                                       Editable text = userPassword.getText();
                                                       Selection.setSelection(text, text.length());
                                                   }
                                               }
                                           }

        );
        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length > 10) {
                    userPhoneIsOk = true;
                } else {
                    userPhoneIsOk = false;
                }
                if (userPhoneIsOk && usePasswordIsOk) {
                    btnUser.setEnabled(true);
                } else {
                    btnUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length > 10) {
                    usePasswordIsOk = true;
                } else {
                    usePasswordIsOk = false;
                }
                if (userPhoneIsOk && usePasswordIsOk) {
                    btnUser.setEnabled(true);
                } else {
                    btnUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length > 10) {
                    sendIdentify.setEnabled(true);
                    fastPhoneIsOk = true;
                } else {
                    sendIdentify.setEnabled(false);
                    fastPhoneIsOk = false;
                }
                if (fastPhoneIsOk && fastIdentifyIsOk) {
                    btnPhone.setEnabled(true);
                } else {
                    btnPhone.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextIdentifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length > 5) {
                    fastIdentifyIsOk = true;
                } else {
                    fastIdentifyIsOk = false;
                }
                if (fastPhoneIsOk && fastIdentifyIsOk) {
                    btnPhone.setEnabled(true);
                } else {
                    btnPhone.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_registe:

                break;
        }
    }

}
