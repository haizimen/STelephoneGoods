package com.phone1000.studentlibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by my on 2016/11/2.
 */
public abstract class StudentMutiTypeAdapter<T> extends BaseAdapter {

    private List<T> data;
    private int[] layoutIds;
    private LayoutInflater inflater;

    public StudentMutiTypeAdapter(Context context, List<T> data, int... layoutIds) {
        inflater = LayoutInflater.from(context);
        this.layoutIds = layoutIds;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<T> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<T> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
    public int getViewTypeCount() {
        return layoutIds.length;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        //根据位置
        T item = getItem(position);
        Class<?> itemClass = item.getClass();
        try {
            Field field = itemClass.getDeclaredField("type");
            field.setAccessible(true);
            type = field.getInt(item);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            //写布局时，布局id在传入时要和type一致
            convertView = inflater.inflate(layoutIds[getItemViewType(position)], parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //加载数据
        bindData(holder, getItem(position), position);

        return convertView;
    }

    public abstract void bindData(ViewHolder holder, T item, int position);

    protected static class ViewHolder {
        private View itemView;

        private Map<Integer, View> cacheMap;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            cacheMap = new HashMap<>();
        }

        public View getView(int resId) {
            View view = null;
            if (cacheMap.containsKey(resId)) {
                view = cacheMap.get(resId);
            } else {
                view = itemView.findViewById(resId);
                cacheMap.put(resId, view);
            }
            return view;
        }
    }
}
