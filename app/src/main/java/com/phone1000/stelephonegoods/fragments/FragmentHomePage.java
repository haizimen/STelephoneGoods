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
import com.phone1000.stelephonegoods.model.LogoModel;
import com.squareup.picasso.Picasso;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImg = ((ImageView) layout.findViewById(R.id.floatbutton));
        getImg();
        getTitles();

    }

    private void getImg() {
        OkHttpUtils.get()
                .url(ReadUrl.HANDPICKLOGO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        LogoModel logoModel = gson.fromJson(response, LogoModel.class);
                        String thumbnailUrl = logoModel.getBody().getHandle().getThumbnailUrl();
                        Picasso.with(mImg.getContext()).load(thumbnailUrl).into(mImg);
                    }
                });
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HandPick());
        fragments.add(new Digtail());
        fragments.add(new Accessories());
        fragments.add(new Beauty());
        fragments.add(new Fashion());
        fragments.add(new House());
        fragments.add(new Poem());
        return fragments;
    }

    private void getTitles() {
        titles=new ArrayList<>();
        OkHttpUtils.get()
                .url(ReadUrl.HOMETITLEURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Gson gson = new Gson();
                        HomeTitle homeTitle = gson.fromJson(response, HomeTitle.class);
//                        Log.e(TAG, "onResponse: "+homeTitle.getBody().getPageViews().get(0).getTitleName());
                        titles=homeTitle.getBody().getPageViews();
                        mPager = ((ViewPager) layout.findViewById(R.id.home_pager));
                        adapter = new HomeTitleAdapter(getChildFragmentManager(), getFragments(),titles);
                        mPager.setAdapter(adapter);
                        mTab = ((TabLayout) layout.findViewById(R.id.home_tab));
                        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
                        mTab.setupWithViewPager(mPager);
                    }
                });
    }
}
