package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.HandpickModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<HandpickModel.BodyBean.GoodGroupsBean.SlideGoodsBean> data;

    public MyRecyclerAdapter(Context context, List<HandpickModel.BodyBean.GoodGroupsBean.SlideGoodsBean> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.handpick_linear, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        int originalPrice = data.get(position).getPeroidInstalmentAmount();
        Double price = originalPrice / 100.0;
        holder.price.setText(price + "");
        Picasso.with(holder.img.getContext()).load(data.get(position).getThumbnailUrl()).into(holder.img);
        if (data.get(position).getDirectPaymentAmount() > 0) {
            holder.pay.setVisibility(View.VISIBLE);
            int directPaymentAmount = data.get(position).getDirectPaymentAmount();
            int money = directPaymentAmount / 100;
            holder.pay.setText("直付立减" + money);
        } else {
            holder.pay.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title, price, pay;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.linear_img);
            title = (TextView) itemView.findViewById(R.id.linear_title);
            price = (TextView) itemView.findViewById(R.id.linear_price);
            pay = (TextView) itemView.findViewById(R.id.pay);
        }
    }
}
