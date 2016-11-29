package com.phone1000.stelephonegoods.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.BaseFragment;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.HandPickHeaderAdapter;
import com.phone1000.stelephonegoods.adapters.HandPickListAdapter;
import com.phone1000.stelephonegoods.adapters.HandPickRecyclerAdapter;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.custormView.CustormViewPager;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HandPick extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final int GO = 59;
    private static final int JUMP = 60;
    private ListView mList;
    private HandPickListAdapter adapter;
    private SwipeRefreshLayout mRefresh;
    private LayoutInflater inflater;
    private View view;
    private CustormViewPager mPager;
    private ImageView mImg;
    private RecyclerView mRecycler;
    private LinearLayout mLinear;
    private int i = 0;
    private int j = 0;
    private HandPickHeaderAdapter adapterPager;
    private HandPickHeaderAdapter handPickHeaderAdapter;
    private List<ImageView> imgs;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case JUMP:
                    i++;
                    i = i % 7;
                    mPager.setCurrentItem(i);
                    handler.sendEmptyMessageDelayed(JUMP, 3000);
                    break;
                case GO:
                    break;
            }
        }
    };
    private HandPickRecyclerAdapter adapterRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.handpick_layout, container, false);
        handler.sendEmptyMessageDelayed(JUMP, 3000);
        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inflater = LayoutInflater.from(getContext());
        mList = ((ListView) layout.findViewById(R.id.handpick_list));
        mRefresh = ((SwipeRefreshLayout) layout.findViewById(R.id.handpick_refresh));
        adapter = new HandPickListAdapter(getContext(), null, R.layout.handpick_item);
        mRefresh.setOnRefreshListener(this);
        mList.setAdapter(adapter);
        view = inflater.inflate(R.layout.handpick_hand, null, false);
        mPager = ((CustormViewPager) view.findViewById(R.id.handpick_pager));
        mImg = ((ImageView) view.findViewById(R.id.handpick_hot));
        mRecycler = ((RecyclerView) view.findViewById(R.id.handpick_navigation));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(manager);
        adapterRecycler = new HandPickRecyclerAdapter(getContext(),null);
        mRecycler.setAdapter(adapterRecycler);
        mLinear = ((LinearLayout) view.findViewById(R.id.handpick_point));
        getImg();
        mList.addHeaderView(view);
        setupView();

    }


    private void getImg() {
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
                        imgs = new ArrayList<ImageView>();
                        ImageView img1 = new ImageView(getContext());
                        ImageView img2 = new ImageView(getContext());
                        ImageView img3 = new ImageView(getContext());
                        ImageView img4 = new ImageView(getContext());
                        ImageView img5 = new ImageView(getContext());
                        ImageView img6 = new ImageView(getContext());
                        ImageView img7 = new ImageView(getContext());
                        Picasso.with(img1.getContext()).load(list.getBody().getCarousels().get(0).getThumbnailUrl()).into(img1);
                        Picasso.with(img2.getContext()).load(list.getBody().getCarousels().get(1).getThumbnailUrl()).into(img2);
                        Picasso.with(img3.getContext()).load(list.getBody().getCarousels().get(2).getThumbnailUrl()).into(img3);
                        Picasso.with(img4.getContext()).load(list.getBody().getCarousels().get(3).getThumbnailUrl()).into(img4);
                        Picasso.with(img5.getContext()).load(list.getBody().getCarousels().get(4).getThumbnailUrl()).into(img5);
                        Picasso.with(img6.getContext()).load(list.getBody().getCarousels().get(5).getThumbnailUrl()).into(img6);
                        Picasso.with(img7.getContext()).load(list.getBody().getCarousels().get(6).getThumbnailUrl()).into(img7);
                        imgs.add(img1);
                        imgs.add(img2);
                        imgs.add(img3);
                        imgs.add(img4);
                        imgs.add(img5);
                        imgs.add(img6);
                        imgs.add(img7);
                        adapterPager = new HandPickHeaderAdapter(imgs);
                        mPager.setAdapter(adapterPager);

                        for (int i = 0; i < imgs.size(); i++) {
                            ImageView point = new ImageView(getContext());
                            if (i > 0) {
                                point.setImageResource(R.mipmap.point);
                                mLinear.addView(point);
                            } else {
                                point.setImageResource(R.mipmap.point_select);
                                mLinear.addView(point, 0);
                            }

                        }
                        mPager.setOnTouchListener(new View.OnTouchListener() {

                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()) {
                                    case MotionEvent.ACTION_MOVE:
                                        mRefresh.setEnabled(false);
                                        break;
                                    case MotionEvent.ACTION_UP:
                                    case MotionEvent.ACTION_CANCEL:
                                        mRefresh.setEnabled(true);
                                        break;
                                }

                                return false;
                            }
                        });
                        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                            }

                            @Override
                            public void onPageSelected(int position) {
                                ImageView childAt = (ImageView) mLinear.getChildAt(position);
                                childAt.setImageResource(R.mipmap.point_select);
                                ((ImageView) mLinear.getChildAt(i)).setImageResource(R.mipmap.point);
                                i = position;
                                Log.e("NMB", "onPageScrolled: " + i);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {
                                Log.e("NNLGB", "onPageScrollStateChanged: " + state);
                            }
                        });
                    }
                });
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
                        Picasso.with(mImg.getContext()).load(list.getBody().getHotBanner().getTitle()).into(mImg);
                        adapter.updateRes(list.getBody().getGoodGroups());
                        adapterRecycler.updataRes(list.getBody().getHotBanner().getHotBanners());
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
