package com.phone1000.stelephonegoods.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.activities.ReadDetailActivity;
import com.phone1000.stelephonegoods.adapters.ReadListAdapter;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.ReadListContent;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by my on 2016/11/28.
 */
public class FragmentReadBook extends Fragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {
    private String TAG=FragmentReadBook.class.getSimpleName();
    private View layout;
    private ListView mListView;
    private ReadListAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_readbook_layout, container, false);
        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupData(true);
    }

    private void setupData(boolean isUpdate) {
        try {
            JSONObject jsonObject = new JSONObject(ReadUrl.jsonStr);
            JSONArray data = jsonObject.getJSONArray("data");
            Type type = new TypeToken<List<ReadListContent>>() {
            }.getType();
         List<ReadListContent>lists=   new Gson().fromJson(data.toString(),type);
            for (int i = 0; i < lists.size(); i++) {
                if (i%3==0) {
                    lists.get(i).setType(0);
                }else{
                    lists.get(i).setType(1);
                }
            }
            if (isUpdate){
                adapter.updateRes(lists);
            }else{
                adapter.addRes(lists);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mListView = (ListView) layout.findViewById(R.id.fragment_read_listview);
        refreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.fragment_read_refresh);
        adapter = new ReadListAdapter(getContext(),null, R.layout.fragment_read_layout_zero,R.layout.fragment_read_layout_one);
        mListView.setAdapter(adapter);
        mListView.setDividerHeight(0);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReadListContent item = adapter.getItem(position);
        Intent intent = new Intent(getContext(), ReadDetailActivity.class);
        intent.putExtra("url",item.getContent());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                setupData(true);
            }
        },2000);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem+visibleItemCount==totalItemCount) {
            setupData(false);
        }
    }
}
