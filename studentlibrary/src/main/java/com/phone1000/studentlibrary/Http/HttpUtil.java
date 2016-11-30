package com.phone1000.studentlibrary.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求工具类
 */
public class HttpUtil {

    private static final String TAG = HttpUtil.class.getSimpleName();
    private static final int REQUEST_FAIL = 99;
    private static final int REQUEST_SUCCESS = 100;
    public static OkHttpClient okHttpClient=new OkHttpClient();
    public static String getString(String url){
        //①获取一个okHttp客户端

        //②实例化一个请求对象
        Request request = new Request.Builder().url(url).build();
        //③执行请求获取一个response对象
        try {
            Log.e(TAG, "getString: "+url );
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e(TAG, "getString: 请求成功" );
                return response.body().string();
            }else{
                Log.e(TAG, "getString: 请求失败" );
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getString: 请求异常" );
        }
        return null;
    }
    public static void getStringAsync(final String url, final RequestCallBack callBack){
        //②声明一个handler
        callBack.prepare();
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        callBack.success((String) msg.obj);
                        break;
                    case REQUEST_FAIL:
                        callBack.fail();
                        break;
                }
                callBack.finish();
            }
        };
        //①创建一个子线程
        Thread thread = new Thread(){
            @Override
            public void run() {
                String result = getString(url);
                if (result==null) {
                    Log.e(TAG, "run: result 为空" );
                    handler.sendEmptyMessage(REQUEST_FAIL);
                }else{
                    Message msg=handler.obtainMessage();
                    msg.what=REQUEST_SUCCESS;
                    msg.obj=result;
                    handler.sendMessage(msg);
                }

            }
        };
        thread.start();
    }
    public interface  RequestCallBack{
        void prepare();

        void fail();

        void success(String result);

        void finish();
    }
    public static byte[] getBytes(String url){
        if (url==null) {
            return null;
        }
        Request request = new Request.Builder().url(url).build();
        try {
            Log.e(TAG, "getBytes: "+url );
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e(TAG, "getBytes: 数据请求成功");
                return response.body().bytes();
            }else{
                Log.e(TAG, "getBytes: 数据请求失败" );
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getBytes: 数据请求异常" );
        }
        return null;
    }
}
