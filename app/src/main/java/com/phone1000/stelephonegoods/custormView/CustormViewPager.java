package com.phone1000.stelephonegoods.custormView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CustormViewPager extends ViewPager {
    private int preX=0;
    public CustormViewPager(Context context) {
        super(context);
    }

    public CustormViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent even) {
        if(even.getAction()==MotionEvent.ACTION_DOWN)
        {
            preX=(int) even.getX();
        }else
        {
            if(Math.abs((int)even.getX()-preX)>10)
            {
                return true;
            }else
            {
                preX=(int) even.getX();
            }
        }
        return super.onInterceptTouchEvent(even);
    }
}
