package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.SuperManModel;
import com.phone1000.stelephonegoods.model.SupertwoModel;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/11/29.
 */
public class SuperImageAdapters extends BaseAdapter {
    private static final String TAG = SuperImageAdapters.class.getSimpleName();
    private LayoutInflater inflater;
    private List<SupertwoModel.BodyBean.GoodBean.ImagesBean> data;

    private Context context;
    public  SuperImageAdapters(Context context, List<SupertwoModel.BodyBean.GoodBean.ImagesBean> data){
        inflater = LayoutInflater.from(context);
        this.context=context;
        if (this.data != null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }
    public void updateRes(List<SupertwoModel.BodyBean.GoodBean.ImagesBean> data){
        if (data!= null) {

            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
            Log.e(TAG, "updateRes: "+this.data.size() );
        }
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public SupertwoModel.BodyBean.GoodBean.ImagesBean getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder=null;
        if (holder == null) {
            convertView=inflater.inflate(R.layout.super_item_image,parent,false);
            holder=new ViewHolder1(convertView);
            convertView.setTag(holder);
        }else{
          holder= (ViewHolder1) convertView.getTag();
        }
        //夹在数据
        Log.e(TAG, "getView: "+data.get(position).getImageUrl());
        Picasso.with(context).load(data.get(position).getImageUrl()).into(holder.imageView);
        //x.image().bind(holder.imageView,data.get(position).getImageUrl());
        return convertView;
    }
    private static class ViewHolder1{
        ImageView imageView;

        public ViewHolder1(View convertView) {
            imageView= (ImageView) convertView.findViewById(R.id.super_item_iamge);
        }
    }
}
