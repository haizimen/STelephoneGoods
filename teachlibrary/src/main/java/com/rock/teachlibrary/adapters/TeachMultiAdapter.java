package com.rock.teachlibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
public abstract class TeachMultiAdapter<T> extends BaseAdapter {
    private List<T> data;
    private int[] layoutIds;
    private LayoutInflater inflater;

    public TeachMultiAdapter(Context context,List<T> data,int... layoutIds) {
        inflater=LayoutInflater.from(context);
        this.layoutIds=layoutIds;
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }
    public void updataRes(List<T> list){
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<T> list){
        if (list != null) {
            //data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
        ViewHolder holder;
        if (convertView == null) {
            //写布局时，布局ID在传入时要和type对应  type0对应索引0，type1对应...
            convertView=inflater.inflate(layoutIds[getItemViewType(position)],parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //数据加载
        bindData(holder,getItem(position),position);
        return null;
    }

    public abstract void bindData(ViewHolder holder, T item, int position) ;

    protected static class ViewHolder {
        private View itemView;
        private Map<Integer, View> cacheView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public View getView(int resId) {
            View view = null;
            if (cacheView.containsKey(resId)) {
                view=cacheView.get(resId);
            }else {
                view=itemView.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        //根据位置获取相应对象
        T item = getItem(position);
        //获取对象的class
        Class<?> itemClass = item.getClass();
        try {
            //获取class中的type字段
            Field field = itemClass.getDeclaredField("type");
            //添加访问权限
            field.setAccessible(true);
            //获取字段值
            type = field.getInt(item);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return layoutIds.length;
    }
}
