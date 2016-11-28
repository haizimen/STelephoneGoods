package com.rock.teachlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Rock on 2016/10/31.
 */
public class ImageUtil {
    // 二次采样
    public static Bitmap sample(byte[] data,int expandHeight,int expandWidth){
        // 图片编码的配置选项
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置选项 使decode 只读取边缘
        options.inJustDecodeBounds = true;
        // BitmapFactory在decode之后，图片不会加载到内存中，只会返回null，但是图片的宽高基本
        // 信息会被存储到options中
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        // 图片的真实宽高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        // 计算采样比率
        int sampleWidth = outWidth / expandWidth;
        int sampleHeight = outHeight / expandHeight;
        // 设置采样比率  当设置的值 <= 1的时候会被当作1来对待，大于 1 的时候会对宽高同时采样， 取值
        // 有一个规律   2的n次幂   n  0，1,2,3...
        options.inSampleSize = Math.max(sampleHeight,sampleWidth);
        // 将 decode 边缘关闭
        options.inJustDecodeBounds = false;
        // 重新加载
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static Bitmap sample(byte[] data){
        return sample(data,300,300);
    }

}
