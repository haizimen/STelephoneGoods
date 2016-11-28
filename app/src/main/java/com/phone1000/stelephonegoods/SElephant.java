package com.phone1000.stelephonegoods;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * Created by my on 2016/11/28.
 */
public class SElephant extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
