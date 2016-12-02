package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phone1000.stelephonegoods.R;

public class WebViewTestActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        String url="http://cdn.xiaoxiangyoupin.com/prod/app/superbate/index.html";
        WebSettings settings = mWebView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        mWebView.loadUrl(url);
        mWebView.addJavascriptInterface(new ScJs(),"小象优品");
        mWebView.setWebViewClient(new MyWebViewClient());
         /* 设置为使用webview推荐的窗口 */
        mWebView.getSettings().setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        mWebView.getSettings().setLoadWithOverviewMode(true);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            //view.loadUrl("file:///android_asset/xiaoxiangyoupin.htm");
            return true;
        }

    }
    public class ScJs{
        @JavascriptInterface
        public void testClick(String msg){
            //Log.e(TAG,Thread.currentThread().getName() +"testClick: "+msg );
        }

    }
}