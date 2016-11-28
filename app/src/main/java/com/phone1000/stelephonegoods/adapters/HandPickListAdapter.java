package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.AbstractSequentialList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HandPickListAdapter extends TeachBaseAdapter<HandpickModel.BodyBean.GoodGroupsBean> {
    private Context context;


    public HandPickListAdapter(Context context, List<HandpickModel.BodyBean.GoodGroupsBean> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, HandpickModel.BodyBean.GoodGroupsBean item, int position) {
        ImageView img = (ImageView) holder.getView(R.id.handpick_img);
        Picasso.with(context).load(getItem(position).getSubject().getThumbnailUrl()).into(img);
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.handpick_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(new MyRecyclerAdapter(context, data.get(position).getSlideGoods()));
    }
}
