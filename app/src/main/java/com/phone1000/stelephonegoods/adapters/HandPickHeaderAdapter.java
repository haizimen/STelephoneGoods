package com.phone1000.stelephonegoods.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class HandPickHeaderAdapter extends PagerAdapter {
    private List<ImageView> imgs;

    public HandPickHeaderAdapter(List<ImageView> data) {
        if (data != null) {
            this.imgs = data;
        } else {
            this.imgs = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgs.get(position));
        return imgs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));
    }


}
