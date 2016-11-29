package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phone1000.stelephonegoods.R;

public class ReadDetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_detail);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.read_detail_webview);
        WebViewClient client = new WebViewClient();
        mWebView.setWebViewClient(client);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
    }
}
