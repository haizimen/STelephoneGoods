package com.phone1000.studentlibrary.utils;

import android.content.Context;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 制作一个url和文件的映射关系
 */
public class SDCardUtil {
   private static Context mcontext;

    public static void init(Context context){
        mcontext=context;
    }

    public static File urlToPath(String url){
        String urlPath = getCacheDir().getAbsolutePath() + File.separator + md5(url);
        return new File(urlPath);
    }

    /**
     * 获取缓存路径
     * @return
     */
    public static File getCacheDir(){
        if(mcontext==null){
            throw new NullPointerException("ImageLoader还没有初始化");
        }
        return mcontext.getCacheDir();
    }

    /**
     *
     * MD5是一种摘要算法
     * 类似于数字指纹，通常用来验证数据的完整性
     * 只要文件发生丝毫变化MD5值就会发生变化
     * 单项不可逆
     * MD5会将输入的内容转换为128为的二进制数
     * md.digest之后 产出为32位的16进制
     * @param url
     * @return
     */
    public static String md5(String url){
        if(url==null){
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //将要进行摘要的数据传进来
            md5.update(url.getBytes());
            //使用digest方法获取输入内容进行转换后的摘要信息
            byte[] digest = md5.digest();
            //需要修改的字符串我们需要用StringBuilder,StringBuffer;
            //StringBuffer线程不安全但是性能高
            StringBuilder sb=new StringBuilder();
            for (byte b:digest){
                sb.append(String.format("%2x",b));

            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return url;
    }

}
