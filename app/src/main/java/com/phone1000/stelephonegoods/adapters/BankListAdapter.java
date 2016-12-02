package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.BankContent;
import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class BankListAdapter extends TeachBaseAdapter <BankContent.BodyBean.DataBean> {
    public BankListAdapter(Context context, List<BankContent.BodyBean.DataBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, BankContent.BodyBean.DataBean item, int position) {
        ImageView image = (ImageView) holder.getView(R.id.bank_image);
        TextView name = (TextView) holder.getView(R.id.bank_name);
        name.setText(item.getBankName());
       // Picasso.with(image.getContext()).load(item.getBankLogo()).into(image);
       // x.image().bind(image,item.getBankLogo());
    }
}
