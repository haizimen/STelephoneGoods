package com.phone1000.studentlibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by my on 2016/11/1.
 */
public abstract class StudentBaseAdapter<T> extends BaseAdapter {

    private List<T> data;
    private LayoutInflater inflater;
    private int layoutResId;

    public StudentBaseAdapter(Context context,List<T>data,int layoutResId){
        inflater=LayoutInflater.from(context);
        this.layoutResId=layoutResId;
        if(data!=null){
            this.data=data;
        }else{
            this.data=new ArrayList<T>();
        }

    }

    public void updataRes(List<T>data){
        if(data!=null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<T>data){
        if(data!=null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        //做一个map缓存 ，专门缓存已经实例化的View

        if (convertView == null) {
            convertView=inflater.inflate(layoutResId,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);

        }else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        //数据加载
        bindData(holder,getItem(position),position);
        return convertView;
    }

    protected abstract void bindData(ViewHolder holder, T item, int position);
    public static class ViewHolder{
        Map<Integer,View> cacheView;
        View itemView;
        public ViewHolder(View itemView){
            this.itemView=itemView;
            cacheView=new HashMap<>();
        }
        public View getView(int resId){
            View view=null;
            if(cacheView.containsKey(resId)){
                view=cacheView.get(resId);
            }else{
                view=itemView.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }
        public void setText(int resId,String content){
            TextView textView= (TextView) getView(resId);
            textView.setText(content);
        }
    }
}
