package com.phone1000.stelephonegoods.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HandPick extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final int GO = 59;
    private ListView mList;
    private HandPickListAdapter adapter;
    private SwipeRefreshLayout mRefresh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.handpick_layout, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = ((ListView) layout.findViewById(R.id.handpick_list));
        mRefresh = ((SwipeRefreshLayout) layout.findViewById(R.id.handpick_refresh));
        adapter = new HandPickListAdapter(getContext(), null, R.layout.handpick_item);
        mRefresh.setOnRefreshListener(this);
        mList.setAdapter(adapter);
        setupView();

    }

    private void setupView() {
        OkHttpUtils.get()
                .url(ReadUrl.HANDPICKURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        HandpickModel list = gson.fromJson(response, HandpickModel.class);
                        adapter.updateRes(list.getBody().getGoodGroups());
                    }
                });
    }

    @Override
    public void onRefresh() {
        Log.e("QNMLGB", "onRefresh: " + Thread.currentThread().getName());
        setupView();
        mRefresh.setRefreshing(false);
    }
}
