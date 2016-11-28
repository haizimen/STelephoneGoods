package com.phone1000.stelephonegoods;

import android.app.Application;

import org.xutils.x;

/**
 * Created by my on 2016/11/28.
 */
public class SElephant extends Application {
    public static boolean isLogin=false;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
