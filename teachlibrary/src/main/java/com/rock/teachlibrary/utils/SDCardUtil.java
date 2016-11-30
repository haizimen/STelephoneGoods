package com.rock.teachlibrary.utils;

import android.content.Context;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rock on 2016/10/31.
 */
public class SDCardUtil {

    private static Context mContext;
    // 初始化工具
    public static void init(Context context){
        mContext = context;
    }
    /**
     * 制作一个 url 和 文件的映射关系
     * @param url
     * @return
     */
    public static File urlToPath(String url){
        String urlToPath = getCacheDir().getAbsolutePath() + File.separator + md5(url);
        return new File(urlToPath);
    }

    /**
     * 获取Apk的的缓存路径
     * @return   data/data/xxx/cache  + / + md5(url)
     */
    public static File getCacheDir(){
        if (mContext == null) {
            throw new NullPointerException("ImageLoader 未初始化");
        }
        return mContext.getCacheDir();
    }

    /**
     * MD5 是一种摘要算法
     * 类似于数字指纹，通常用来验证数据的完整性
     * 只要文件发生丝毫变化，md5值就会发生变化
     * 单向不可逆
     * MD5 会将输入内容转换为 128位的二进制 0,1
     * md.digest 之后 产出32位的16进制
     */
    public static String md5(String url){
        if (url == null) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 将要进行摘要的数据传递进来
            md5.update(url.getBytes());
            // 使用 digest方法，获取输入内容进行转换后的摘要信息
            byte[] digest = md5.digest();
            // 需要修改的字符串我们可以使用StringBuilder，StringBuffer
            // StringBuilder线程不安全，但是性能高
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%2x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return url;
    }


}
