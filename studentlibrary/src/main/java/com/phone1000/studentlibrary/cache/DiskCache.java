package com.phone1000.studentlibrary.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.phone1000.studentlibrary.utils.SDCardUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 从磁盘中存.
 */
public class DiskCache {

    private static final String TAG = DiskCache.class.getSimpleName();

    public static void put(String url, Bitmap bitmap) {
        File urlToPath = SDCardUtil.urlToPath(url);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(urlToPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Log.e(TAG, "put: 磁盘缓存成功" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "put: 磁盘缓存异常" );
        }finally {
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     /* @param url
     * @return
     */
    public static Bitmap get(String url){
        File urlToPath = SDCardUtil.urlToPath(url);
        if(!urlToPath.exists()){
            return null;
        }
        return BitmapFactory.decodeFile(urlToPath.getAbsolutePath());
    }
}
