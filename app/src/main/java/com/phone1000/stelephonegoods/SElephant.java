package com.phone1000.stelephonegoods;

import android.app.Application;
import android.os.Environment;


import com.phone1000.stelephonegoods.utils.NetWorkUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.http.params.HttpParams;
import org.xutils.x;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by my on 2016/11/28.
 */
public class SElephant extends Application {
    public static boolean isLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        //实例化请求缓存
        Cache cache = new Cache(Environment.getDownloadCacheDirectory(), 1024 * 1024 * 1024);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isConnect(SElephant.this)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetWorkUtil.isConnect(SElephant.this)) {
                    String cacheControl = request.cacheControl().toString();
                    response = response.newBuilder()
                            .removeHeader(com.phone1000.stelephonegoods.constant.HttpParams.CACHE_CONTROL)
                            .removeHeader(com.phone1000.stelephonegoods.constant.HttpParams.PARMA)
                            .addHeader(com.phone1000.stelephonegoods.constant.HttpParams.CACHE_CONTROL, cacheControl)
                            .build();
                } else {
                    response = response.newBuilder()
                            .removeHeader(com.phone1000.stelephonegoods.constant.HttpParams.CACHE_CONTROL)
                            .removeHeader(com.phone1000.stelephonegoods.constant.HttpParams.PARMA)
                            .addHeader(com.phone1000.stelephonegoods.constant.HttpParams.CACHE_CONTROL, "only-if-cached,max-stale\"+2*24")
                            .build();
                }
                return response;
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .build();
        OkHttpUtils.initClient(client);
    }
}
