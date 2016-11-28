package com.rock.teachlibrary.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 *  Android 使用LRUCache 进行内存缓存
 *  LRU 最近最少使用原则
 *
 *
 */
public class RamCache {

    private static RamCache mRamCache;

    private LruCache<String,Bitmap> mLruCache;

    /**
     * 双重判断，内部加锁的形势解决了
     * 单例多线程访问不安全问题
     * 直接添加同步锁效率低下的问题
     */
    public static RamCache getInstance(){
        if (mRamCache == null) {
//            mRamCache = new RamCache();
            synchronized (RamCache.class){
                if (mRamCache == null) {
                    mRamCache = new RamCache();
                }
            }
        }
        return mRamCache;
    }

    // 私有化构造
    private RamCache(){
        /**
         * 通常Android中会分配最大可用内存的八分之一作为缓存空间
         */
        long maxMemory = Runtime.getRuntime().maxMemory();
        int maxSize = (int) (maxMemory / 8);
        mLruCache = new LruCache<String,Bitmap>(maxSize){
            // 计算大小
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 高度 * 一行所占的大小
                return bitmap.getHeight() * bitmap.getRowBytes();
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
     * 根据url从缓存中取出
     * @param url
     * @return
     */
    public Bitmap get(String url){
        return mLruCache.get(url);
    }

}
