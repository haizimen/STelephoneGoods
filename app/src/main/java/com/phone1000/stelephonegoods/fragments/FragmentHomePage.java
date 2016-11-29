package com.phone1000.stelephonegoods.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.BaseFragment;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.HomeTitleAdapter;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.HomeTitle;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by my on 2016/11/28.
 */
public class FragmentHomePage extends BaseFragment {
    private static final String TAG = FragmentHomePage.class.getSimpleName();
    private ViewPager mPager;
    private TabLayout mTab;
    private HomeTitleAdapter adapter;
    private List<HomeTitle.BodyBean.PageViewsBean> titles;
    private ImageView mImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_homepage_layout, container, false);
        return layout;
    }
}
