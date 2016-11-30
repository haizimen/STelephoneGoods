package com.rock.teachlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.DashPathEffect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rock.teachlibrary.cache.DiskCache;
import com.rock.teachlibrary.cache.RamCache;
import com.rock.teachlibrary.http.HttpUtil;
import com.rock.teachlibrary.utils.ImageUtil;
import com.rock.teachlibrary.utils.SDCardUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 图片加载的工具类
 */
public class ImageLoader {
    private static final int PICTURE_NULL = 80;
    private static final int PICTURE_ERROR = 90;
    private static final int PICTURE_SUCCESS = 100;
    private static final String TAG = ImageLoader.class.getSimpleName();

    private static Context mContext;

    // CPU核心数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int POOL_COUNT = CPU_COUNT + 1;
    private static final int MAX_POOL_COUNT = CPU_COUNT * 2 + 1;
    private static final int KEEP_TIME = 5;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>(128);

    // 声明线程池
    /**
     * 线程池，用来管理多线程并发，使线程有序可控的进行操作
     *          节约性能
     * ① 线程池的核心线程数  线程池闲下来的时候，核心线程不会被干掉
     * ② 最大线程数  线程池闲下来的时候，并且超过了存活时间，就被干掉了，当核心不够使用的时候会被重新创建
     * ③ 存活时间，这个参数是修饰最大线程中超过核心线程的 那些子线程
     * ④ 时间单位，这个参数用来作为第三个参数的单位
     * ⑤ 阻塞队列，当提交的任务超出最大线程数之后，超出的线程需要进行排队
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(POOL_COUNT,MAX_POOL_COUNT,
           KEEP_TIME, TimeUnit.SECONDS,BLOCKING_QUEUE);

    /**
     * 初始化方法   为框架的初始化提供统一的入口
     *
     * @param context
     */
    public static void init(Context context) {
        SDCardUtil.init(context);
        mContext = context;
    }


    /**
     * 在ImageView上加载一张图片
     * ① 我们是需要ImageView
     * ② 加载地址
     */
    public static void display(final ImageView container, final String url, int defaultResId, final int nullRestId, final int errorResId) {
        // 当url不为null的时候，将url作为标记设置到ImageView上
        if (url != null) {
            container.setTag(url);
            Bitmap bitmap = RamCache.getInstance().get(url);
            if (bitmap != null) {
                Log.e(TAG, "display: 从RamCache中加载");
                container.setImageBitmap(bitmap);
                return;
            }
        }

        // 恢复默认图片
        container.setImageResource(defaultResId);
        // 加载图片
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (container.getTag() != null && container.getTag().equals(url)) {
                    switch (msg.what) {
                        case PICTURE_NULL:
                            container.setImageResource(nullRestId);
                            break;
                        case PICTURE_ERROR:
                            container.setImageResource(errorResId);
                            break;
                        case PICTURE_SUCCESS:
                            container.setImageBitmap((Bitmap) msg.obj);
                            break;
                    }
                    // 加个动画
                    container.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.scale));

                }

            }
        };
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 去SD卡中查找
                Bitmap diskBitmap = DiskCache.get(url);
                if (diskBitmap != null) {
                    Message obtain = Message.obtain();
                    obtain.what = PICTURE_SUCCESS;
                    obtain.obj = diskBitmap;
                    handler.sendMessage(obtain);
                    return;
                }

                // 获取请求的数据
                byte[] bytes = HttpUtil.getBytes(url);
                if (bytes == null) {
                    // 设置错误图片
//                    container.setImageResource(errorResId);
                    handler.sendEmptyMessage(PICTURE_NULL);
                } else {
                    // 通过数据进行decode 转换成图片
                    Bitmap bitmap = ImageUtil.sample(bytes);
                    if (bitmap == null) {
                        handler.sendEmptyMessage(PICTURE_ERROR);
                    } else {
                        // 添加到RamCache
                        RamCache.getInstance().put(url, bitmap);
                        // 添加到DiskCache
                        DiskCache.put(url, bitmap);

                        Message message = Message.obtain();
                        message.what = PICTURE_SUCCESS;
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    }
                }
            }
        });

//        final Thread thread = new Thread() {
//            @Override
//            public void run() {

//            }
//        };
//        thread.start();
    }

    public static void display(ImageView container, String url) {
        Log.e(TAG, "display: " + url);
        display(container, url, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

}
