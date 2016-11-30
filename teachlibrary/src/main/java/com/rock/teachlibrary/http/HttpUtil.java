package com.rock.teachlibrary.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建一个Library，在Library中添加常用工具类
 */
public class HttpUtil {

    private static final String TAG = HttpUtil.class.getSimpleName();
    private static final int REQUEST_FAIL = 99;
    private static final int REQUEST_SUCCESS = 100;

    public static OkHttpClient okHttpClient = new OkHttpClient();

    public static String getString(String url) {
        // ① 获取一个OkHttpClient
        // ② 实例化一个Request
        Request request = new Request.Builder()
                .url(url)
                .build();
        // ③ 发送网络请求(执行Request)
        try {
            Log.e(TAG, "getString: " + url );
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e(TAG, "getString: 请求成功" );
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getString: 请求异常" );
        }
        Log.e(TAG, "getString: 请求失败" );
        return null;
    }

    /**
     *  ifn   if (xx == null)
     */
    public static void getStringAsync(final String url, final RequestCallback callback){
        // 进入准备状态
        callback.prepare();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // 主线程中
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        callback.success((String) msg.obj);
                        break;
                    case REQUEST_FAIL:
                        callback.fail();
                        break;
                }
                // 回调结束
                callback.finish();
            }
        };
        // 构建一条线程
        Thread thread = new Thread(){
            @Override
            public void run() {
                String result = getString(url);
                if (result == null) {
                    // 发送一条消息回去
                    handler.sendEmptyMessage(REQUEST_FAIL);
                }else{
                    Message message = handler.obtainMessage();
                    message.what = REQUEST_SUCCESS;
                    message.obj = result;
                    handler.sendMessage(message);
                }
            }
        };
        thread.start();
    }

    public static byte[] getBytes(String url){
        if (url == null) {
            return null;
        }
        // ① 获取OkHttpClient

        // ② 获取Request
        Request request = new Request.Builder()
                .url(url)
                .build();
        // ③ 请求网络
        try {
            Log.e(TAG, "getBytes: "+url );
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e(TAG, "getBytes: 请求成功" );
                return response.body().bytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getBytes: 请求异常" );
        }
        Log.e(TAG, "getBytes: 请求失败" );
        return null;
    }


    public interface RequestCallback{

        void prepare();

        void fail();

        void success(String result);

        void finish();
    }

}
