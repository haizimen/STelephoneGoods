package com.phone1000.stelephonegoods;

import android.app.Application;
import android.os.Environment;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.DbManager;
import org.xutils.config.DbConfigs;
import org.xutils.x;

import okhttp3.OkHttpClient;

/**
 * Created by my on 2016/11/28.
 */
public class SElephant extends Application {
    public static boolean isLogin = false;
    public static boolean isCertigition = false;
    public static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("address.db")
            .setDbDir(Environment.getExternalStorageDirectory());

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpUtils.initClient(okHttpClient);

    }
}
