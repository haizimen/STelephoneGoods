package com.phone1000.stelephonegoods.fragments;

import android.content.Intent;
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
import com.phone1000.stelephonegoods.activities.CartActivity;
import com.phone1000.stelephonegoods.activities.MovieActivity;
import com.phone1000.stelephonegoods.adapters.HomeTitleAdapter;
import com.phone1000.stelephonegoods.constant.HttpParams;
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
public class FragmentHomePage extends BaseFragment implements View.OnClickListener {
    private static final String TAG = FragmentHomePage.class.getSimpleName();
    private ViewPager mPager;
    private TabLayout mTab;
    private HomeTitleAdapter adapter;
    private List<HomeTitle.BodyBean.PageViewsBean> titles;
    private ImageView mImg;
    private String webUrl;
    private ImageView mCart;

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
        mImg.setOnClickListener(this);
        mCart = ((ImageView) layout.findViewById(R.id.cart));
        mCart.setOnClickListener(this);
        getTitles();
        getImg();
    }

    private void getImg() {
        OkHttpUtils.get()
                .url(ReadUrl.HOMELOGOURL)
                .addHeader(HttpParams.CACHE_CONTROL,"only-if-cache,max-stale" + 5 * 60 * 60)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        LogoModel logoModel = gson.fromJson(response, LogoModel.class);
                        Picasso.with(mImg.getContext()).load(logoModel.getBody().getHandle().getThumbnailUrl()).into(mImg);
                        webUrl = logoModel.getBody().getHandle().getGotoUrl();
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
        fragments.add(new PoemTwo());
        return fragments;
    }

    private void getTitles() {
        titles = new ArrayList<>();
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
                        titles = homeTitle.getBody().getPageViews();
                        mPager = ((ViewPager) layout.findViewById(R.id.home_pager));
                        adapter = new HomeTitleAdapter(getChildFragmentManager(), getFragments(), titles);
                        mPager.setAdapter(adapter);
                        mTab = ((TabLayout) layout.findViewById(R.id.home_tab));
                        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
                        mTab.setupWithViewPager(mPager);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatbutton:
                Intent intent = new Intent(getContext(), MovieActivity.class);
                intent.putExtra("url", webUrl);
                startActivity(intent);
                break;
            case R.id.cart:
                Intent intent1 = new Intent(getContext(), CartActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
