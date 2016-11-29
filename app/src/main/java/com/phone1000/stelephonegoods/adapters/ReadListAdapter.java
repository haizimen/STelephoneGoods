package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.ReadListContent;
import com.phone1000.studentlibrary.adapters.StudentMutiTypeAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by my on 2016/11/28.
 */
public class ReadListAdapter extends StudentMutiTypeAdapter<ReadListContent> {
    public ReadListAdapter(Context context, List<ReadListContent> data, int... layoutIds) {
        super(context, data, layoutIds);
    }

    @Override
    public void bindData(ViewHolder holder, ReadListContent item, int position) {
        ImageView imageZero = (ImageView) holder.getView(R.id.fragment_read_item_zero_image);
        TextView titleZero = (TextView) holder.getView(R.id.fragment_read_item_zero_title);
        TextView summaryZero = (TextView) holder.getView(R.id.fragment_read_item_zero_summary);
        ImageView imageOne = (ImageView) holder.getView(R.id.fragment_read_item_one_image);
        TextView titleOne = (TextView) holder.getView(R.id.fragment_read_item_one_title);
        TextView summaryOne = (TextView) holder.getView(R.id.fragment_read_item_one_summary);

        if (titleZero!=null) {
            titleZero.setText(item.getTitle());
        }
        if (titleOne!=null) {
            titleOne.setText(item.getTitle());
        }
        if (summaryOne!=null) {
            summaryOne.setText(item.getSummary());
        }
        if (summaryZero!=null) {
            summaryZero.setText(item.getSummary());
        }
        if (imageOne!=null) {
            x.image().bind(imageOne,item.getCover());
        }
        if (imageZero!=null) {
            x.image().bind(imageZero,item.getCover());
        }
    }
}
