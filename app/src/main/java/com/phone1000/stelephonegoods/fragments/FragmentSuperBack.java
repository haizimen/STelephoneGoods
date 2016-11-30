package com.phone1000.stelephonegoods.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.activities.SuperBacKTwoActivity;
import com.phone1000.stelephonegoods.adapters.SuperBackAdapters;
import com.phone1000.stelephonegoods.constant.SuperBackUrl;
import com.phone1000.stelephonegoods.json.JsonCallback;
import com.phone1000.stelephonegoods.model.SuperManModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by my on 2016/11/28.
 */
public class FragmentSuperBack extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = FragmentSuperBack.class.getSimpleName();
    View layout;
    private SuperBackAdapters adapter;
    private GridView mGridView;
    private SwipeRefreshLayout mSwipLayout;
    private ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_superback_layout, container, false);
        initView();
        return layout;
    }

    private void setupView() {
        OkHttpUtils.get()
                .url(SuperBackUrl.SUPER_URL)
                .build()
                .execute(new JsonCallback<SuperManModel>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(SuperManModel response, int id) {
                        List<SuperManModel.BodyBean.RebateGoodsBean> body = response.getBody().getRebateGoods();
                        adapter.updateRes(body);
                    }
                });
    }

    private void initView() {
        mSwipLayout = (SwipeRefreshLayout)layout.findViewById(R.id.swipe_container);

         mGridView = (GridView)layout.findViewById(R.id.superback_lv);
       // if (isTop(mGridView)==true) {
            mSwipLayout.setOnRefreshListener(this);
        mImage = (ImageView)layout.findViewById(R.id.super_image);

        //}


        mSwipLayout.setColorSchemeResources(android.R.color.holo_orange_dark,
                                    android.R.color.holo_orange_light);
           adapter =  new SuperBackAdapters(getContext(),null);
            mGridView.setAdapter(adapter);
            setupView();


    }

    private boolean isTop(GridView gridView){
        View firstView =  null;
        if(gridView.getCount() ==0){
            return true;
        }
        firstView = gridView.getChildAt(0);
        if (firstView!=null) {
            if (gridView.getFirstVisiblePosition()==0&&firstView.getTop()==gridView.getListPaddingTop()) {
                return true;
            }
        }else {
            return true;
        }
        return false;
    }
    @Override
    public void onRefresh() {
        setupView();
        mSwipLayout.setRefreshing(false);
    }




}
