package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.zhy.http.okhttp.OkHttpUtils;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    private TextView mTitle;
    private ProgressBar mBar;
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.e("QNMLGB", "initView: " + url);
        mImg = (ImageView) findViewById(R.id.movie_back);
        mTitle = (TextView) findViewById(R.id.movie_title);
        mBar = (ProgressBar) findViewById(R.id.movie_bar);
        mWeb = (WebView) findViewById(R.id.movie_web);
        mImg.setOnClickListener(this);
        WebViewClient client = new WebViewClient();
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.setWebViewClient(client);
        WebChromeClient client1 = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mBar.setProgress(newProgress);
                if (mBar.getProgress() == 100) {
                    mBar.setProgress(0);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitle.setText(title);
            }
        };
        mWeb.setWebChromeClient(client1);
        mWeb.loadUrl("http://cdn.xiaoxiangyoupin.com/prod/activity/huiyuan/index.html");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_back:
                finish();
                break;
        }
    }
}
