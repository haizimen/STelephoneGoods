package com.phone1000.studentlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by my on 2016/10/31.
 */
public class ImageUtil {
    //二次采样
    public static Bitmap sample(byte[] images, int expandHeight, int expandWidth){
        //图片加载的配置选项
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置选项 使decode只读取边缘
        options.inJustDecodeBounds=true;
        //BitmapFactory 在decode之后 图片不回加载到内存中而是返回null， 但是图片的宽高信息存到option中
        BitmapFactory.decodeByteArray(images,0,images.length,options);
        int outWidth=options.outWidth;
        int outHeight= options.outHeight;
        //采样
        //设置采样比率
        int sampleWidth = outWidth / expandHeight;
        int sampleHeight = outHeight / expandHeight;
        //设置采样比率  当设置的值《=1 的时候会被当做1来对待，大于1 的话会对宽高同时进行采样，取值有一个规律，  2的n次幂 n 0,2,4,6,8....
        options.inSampleSize=Math.max(sampleHeight,sampleWidth);
        // 将 decode 边缘关闭
        options.inJustDecodeBounds=false;
        //重新加载
        return BitmapFactory.decodeByteArray(images, 0, images.length, options);
    }
    public static Bitmap sample(byte[] images ){
        return sample(images,150,150);
    }
}
