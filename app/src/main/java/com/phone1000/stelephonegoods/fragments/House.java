package com.phone1000.stelephonegoods.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.BaseFragment;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.HandPickListAdapter;
import com.phone1000.stelephonegoods.constant.HttpParams;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28.
 */
public class House extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefresh;
    private ListView mList;
    private HandPickListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.house, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRefresh = ((SwipeRefreshLayout) layout.findViewById(R.id.house_refresh));
        mList = ((ListView) layout.findViewById(R.id.house_list));
        adapter = new HandPickListAdapter(getContext(), null, R.layout.handpick_item);
        mList.setAdapter(adapter);
        setupView();
        mRefresh.setOnRefreshListener(this);
    }

    private void setupView() {
        OkHttpUtils.post()
                .url(ReadUrl.HANDPICKURL)
                .addParams("titleId", "10")
                .addParams("isHome", "0")
                .addHeader(HttpParams.CACHE_CONTROL,"only-if-cache,max-stale" + 5 * 60 * 60)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.e("NMB", "onResponse: " + response);
                        Gson gson = new Gson();
                        HandpickModel list = gson.fromJson(response, HandpickModel.class);
                        Log.e("NMB", "onResponse: " + list.getBody().getGoodGroups().get(0).getSlideGoods().get(0).getTitle());
                        adapter.updateRes(list.getBody().getGoodGroups());
                    }
                });
    }

    @Override
    public void onRefresh() {
        setupView();
        mRefresh.setRefreshing(false);
    }
}
