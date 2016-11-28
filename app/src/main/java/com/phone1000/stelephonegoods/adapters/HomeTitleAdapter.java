package com.phone1000.stelephonegoods.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.stelephonegoods.model.HomeTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HomeTitleAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;
    private List<HomeTitle.BodyBean.PageViewsBean> titles;

    public HomeTitleAdapter(FragmentManager fm, List<Fragment> data,List<HomeTitle.BodyBean.PageViewsBean> titles) {
        super(fm);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        if (titles != null) {
            this.titles = titles;
        } else {
            this.titles = new ArrayList<>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getTitleName();
    }

    public void updataRes(List<HomeTitle.BodyBean.PageViewsBean> pageViews) {
        if (pageViews != null) {
            this.titles.clear();
            this.titles.addAll(pageViews);
            notifyDataSetChanged();
        }
    }
}
