package com.phone1000.stelephonegoods.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/11/25.
 */
public class NetWorkUtil {
    public static boolean isConnect(Context context) {
        //获取连接的管理者
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }
}
