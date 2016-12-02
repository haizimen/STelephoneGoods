package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG=SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {

        ImageView btnBack = (ImageView) findViewById(R.id.settings_back);
        TextView protocol = (TextView) findViewById(R.id.settings_user_protocol);
        TextView privacy = (TextView) findViewById(R.id.settings_user_privacy);
        TextView aboutUs = (TextView) findViewById(R.id.settings_about_us);
        TextView update = (TextView) findViewById(R.id.settings_check_update);
        Button exit = (Button) findViewById(R.id.settings_exit);
        btnBack.setOnClickListener(this);
        protocol.setOnClickListener(this);
        privacy.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        update.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_back:
                finish();
                break;
            case R.id.settings_user_protocol:
                Intent intent2 = new Intent(this, UserProtocolActivity.class);
                startActivity(intent2);
                break;
            case R.id.settings_user_privacy:
                Intent intent = new Intent(this, ReadDetailActivity.class);
                intent.putExtra("url", ReadUrl.USER_PRIVACY_URL);
                startActivity(intent);
                break;
            case R.id.settings_about_us:
                Intent intent1 = new Intent(this, AboutUsActivity.class);
                startActivity(intent1);
                break;
            case R.id.settings_check_update:
                Toast.makeText(SettingsActivity.this, "已是最新版本 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_exit:
                Snackbar.make(v,"确定要退出吗？（右划取消）",Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3 = new Intent(SettingsActivity.this, LoginActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent3);
                    }
                }).show();
                Log.e(TAG, "onClick: ");
                break;
        }
    }
}
