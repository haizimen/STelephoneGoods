package com.rock.teachlibrary.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.rock.teachlibrary.utils.SDCardUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Rock on 2016/10/31.
 */
public class DiskCache {
    private static final String TAG = DiskCache.class.getSimpleName();

    /**
     * 向磁盘中进行存储
     * @param url
     * @param bitmap
     */
    public static void put(String url, Bitmap bitmap){
        File urlToPath = SDCardUtil.urlToPath(url);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(urlToPath);
            bitmap.compress(Bitmap.CompressFormat.PNG,100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "put: 缓存异常" );
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 从磁盘中进行获取
     * @param url
     * @return
     */
    public static Bitmap get(String url){
        File urlToPath = SDCardUtil.urlToPath(url);
        // 如果缓存文件不存在
        if (!urlToPath.exists()) {
            return null;
        }
        Log.e(TAG, "get:从Disk缓存中加载" );
        return BitmapFactory.decodeFile(urlToPath.getAbsolutePath());
    }

}
