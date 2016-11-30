package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.NavigationModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/11/30.
 */
public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.ViewHolder> {
    private NavigationModel.BodyBean.DataBean data;
    private LayoutInflater inflater;

    public RecyclerGridAdapter(Context context, NavigationModel.BodyBean.DataBean data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new NavigationModel.BodyBean.DataBean();
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.usualgrid_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int directPaymentAmount = data.getGoods().get(position).getDirectPaymentAmount();
        holder.navigation_favorable.setText("直付立减" + (directPaymentAmount) / 100);
        holder.usual_title.setText(data.getGoods().get(position).getGoodTitle());
        int peroidInstalmentAmount = data.getGoods().get(position).getPeroidInstalmentAmount();
        holder.usul_price.setText("￥" + (peroidInstalmentAmount / 100));
        Picasso.with(holder.img.getContext()).load(data.getGoods().get(position).getThumbnailUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.getGoods() == null ? 0 : data.getGoods().size();
    }

    public void updataRes(NavigationModel.BodyBean.DataBean data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView navigation_favorable, usual_title, usul_price;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.usual_img);
            navigation_favorable = (TextView) itemView.findViewById(R.id.navigation_favorable);
            usual_title = (TextView) itemView.findViewById(R.id.usual_title);
            usul_price = (TextView) itemView.findViewById(R.id.usul_price);

        }
    }
}
