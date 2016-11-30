package com.phone1000.stelephonegoods.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.NavigationModel;
import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class UsualGridAdapter extends BaseAdapter {
    private NavigationModel.BodyBean.DataBean data;
    private LayoutInflater inflater;

    public UsualGridAdapter(Context context, NavigationModel.BodyBean.DataBean data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new NavigationModel.BodyBean.DataBean();
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.getGoods() == null ? 0 : data.getGoods().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getGoods().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.usualgrid_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (data.getGoods().get(position).getDirectPaymentAmount() != 0) {
            holder.navigation_favorable.setVisibility(View.VISIBLE);
            int amount = data.getGoods().get(position).getDirectPaymentAmount();
            holder.navigation_favorable.setText("直付立减" + (amount / 100));
        }
        holder.usual_title.setText(data.getGoods().get(position).getGoodTitle());
        int periods = data.getGoods().get(position).getPeroidInstalmentAmount();
        holder.usul_price.setText("￥" + (periods / 100.0));
        Picasso.with(holder.img.getContext()).load(data.getGoods().get(position).getThumbnailUrl()).into(holder.img);
        return convertView;
    }

    public void updataRes(NavigationModel.BodyBean.DataBean arr) {
        if (arr != null) {
//            this.data.getGoods().clear();
            this.data = arr;
            notifyDataSetChanged();
        }
    }


    private static class ViewHolder {
        ImageView img;
        TextView navigation_favorable, usual_title, usul_price;

        public ViewHolder(View itemView) {
            img = (ImageView) itemView.findViewById(R.id.usual_img);
            navigation_favorable = (TextView) itemView.findViewById(R.id.navigation_favorable);
            usual_title = (TextView) itemView.findViewById(R.id.usual_title);
            usul_price = (TextView) itemView.findViewById(R.id.usul_price);
        }
    }
}
