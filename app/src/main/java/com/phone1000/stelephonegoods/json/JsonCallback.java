package com.phone1000.stelephonegoods.json;

import android.content.pm.LauncherApps;
import android.os.UserHandle;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/21.
 */
public abstract  class JsonCallback<T> extends Callback<T> {
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String result = response.body().string();
        Gson gson = new Gson();
        // 使用反射获取类上的泛型参数
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        return gson.fromJson(result,typeArguments[0]);
    }

}
