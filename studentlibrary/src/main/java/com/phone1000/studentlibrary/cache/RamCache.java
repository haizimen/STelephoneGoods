package com.phone1000.studentlibrary.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Android中使用LRUCache 进行内存缓存
 * LRU 最近最少使用原则
 *
 * 单例
 */
public class RamCache {
    private static RamCache mRamCache;

    private LruCache<String,Bitmap> mLruCache;
    public static RamCache getInstance(){
        /**
         * 双重判断，内部加锁的方式
         * 解决了多线程同步访问不安全问题
         * 直接添加同步频率低下的问题
         */
        if(mRamCache==null){
            synchronized (RamCache.class){
                if(mRamCache==null){
                    mRamCache=new RamCache();
                }
            }
        }
        return mRamCache;
    }
    //私有化构造器
    private RamCache(){
        /**
         * 通常Andorid中会分配最大可用内存的八分之一
         */
        long maxMemory = Runtime.getRuntime().maxMemory();
        int maxSize= (int) (maxMemory/8);
        mLruCache=new LruCache<String ,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    /**
     * 存入缓存
     * @param url
     * @param bitmap
     */
    public void put(String url,Bitmap bitmap){
        mLruCache.put(url,bitmap);
    }
    /**
     * 从缓存中取
     */
    public Bitmap get(String url){
        return mLruCache.get(url);
    }
}
