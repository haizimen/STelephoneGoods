package com.phone1000.stelephonegoods.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ListViewImage extends ListView {
    public ListViewImage(Context context) {
        super(context);
    }

    public ListViewImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
