package com.phone1000.studentlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.phone1000.studentlibrary.Http.HttpUtil;
import com.phone1000.studentlibrary.cache.DiskCache;
import com.phone1000.studentlibrary.cache.RamCache;
import com.phone1000.studentlibrary.utils.ImageUtil;
import com.phone1000.studentlibrary.utils.SDCardUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ImageLoader图片加载的工具类
 */
public class ImageLoader {

    private static final int BITMAP_NULL = 80;
    private static final int BITMAP_ERR = 90;
    private static final int BITMAP_SUCCESS = 100;
    private static  Context mcontext;
    //CPU核心线程数
    private static final int CPU_COUNT=Runtime.getRuntime().availableProcessors();
    private static final  int POOL_COUNT=CPU_COUNT+1;
    private static final int MAX_POOL_COUNT=CPU_COUNT*2+1;
    private static final int KEEP_TIME=5;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE=new LinkedBlockingDeque<>(128);

    /**
     * ①线程池的核心线程数  线程闲下来时不会被干掉
     * ②最大线程数
     * ③存活时间
     * ④时间单位
     * ⑤阻塞队列 当提交的任务超出最大线程数后，超出的线程进行排队
     */
    private static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(POOL_COUNT,MAX_POOL_COUNT,KEEP_TIME, TimeUnit.SECONDS,BLOCKING_QUEUE);
    /**
     * 初始化方法 为框架提供一个统一初始化入口
     * @param context
     */
    public static void init(Context context){
        SDCardUtil.init(context);
        mcontext=context;
    }
    /**
     * 在imageView上加载一张图片
     * 1.我们需要ImageVIew
     * 2.加载地址
     * <p/>
     * //图片错乱的真正原因
     * ①图片的容器复用
     * ②图片的异步加载
     * <p/>
     * 解决方案
     * 在图片加载前对ImageView设置一个标记
     * 判断当标记是要求的就设置imageView的图片，否则就不设置了
     */
    public static void display(final ImageView container, final String url,final int nullResId,final int errResId, final int defaultResId) {
        //加载图片

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (container.getTag() != null && container.getTag().equals(url)) {
                    switch (msg.what) {
                        case BITMAP_NULL:
                            container.setImageResource(nullResId);
                            break;
                        case BITMAP_ERR:
                            container.setImageResource(errResId);
                            break;
                        case BITMAP_SUCCESS:
                            container.setImageBitmap((Bitmap) msg.obj);
                            break;
                    }
                    container.startAnimation(AnimationUtils.loadAnimation(mcontext,R.anim.scale));
                }

            }
        };
        if (url != null) {
            container.setTag(url);
            Bitmap bitmap = RamCache.getInstance().get(url);
            if (bitmap != null) {
                container.setImageBitmap(bitmap);
                return;
            }
        }
        //恢复默认图片
        container.setImageResource(defaultResId);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap diskBitmap = DiskCache.get(url);
                if (diskBitmap!=null) {
                    Message msg=Message.obtain();
                    msg.what=BITMAP_SUCCESS;
                    msg.obj=diskBitmap;
                    handler.sendMessage(msg);
                    return;
                }
                byte[] bytes = HttpUtil.getBytes(url);
                if (bytes == null) {
                    handler.sendEmptyMessage(BITMAP_NULL);
                } else {
                    Bitmap bitmap = ImageUtil.sample(bytes);
                    if (bitmap == null) {
                        handler.sendEmptyMessage(BITMAP_ERR);
                    } else {
                        //添加到RamCache中
                        RamCache.getInstance().put(url, bitmap);
                        DiskCache.put(url,bitmap);
                        Message msg = Message.obtain();
                        msg.what = BITMAP_SUCCESS;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    }

                }
            }
        });
    }

    public static void display(ImageView container, String url) {
        display(container, url, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher);
    }
}
