package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.activities.NavigationActivity;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class HandPickRecyclerAdapter extends RecyclerView.Adapter<HandPickRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private List<HandpickModel.BodyBean.HotBannerBean.HotBannersBean> data;
    private LayoutInflater inflater;
    private RecyclerView mRecycler;
    private Context context;

    public HandPickRecyclerAdapter(Context context, List<HandpickModel.BodyBean.HotBannerBean.HotBannersBean> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.img.getContext()).load(data.get(position).getSmallImgUrl()).into(holder.img);
        holder.img.setOnClickListener(this);
        holder.img.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void updataRes(List<HandpickModel.BodyBean.HotBannerBean.HotBannersBean> hotBanners) {
        if (hotBanners != null) {
            this.data.clear();
            this.data.addAll(hotBanners);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        int childLayoutPosition= (int) v.getTag();
        int id = data.get(childLayoutPosition).getId();
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.recycler_img);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler = recyclerView;
    }
}
