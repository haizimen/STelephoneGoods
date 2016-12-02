package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.model.SupertwoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private static final int SEND = 59;
    private List<SupertwoModel.BodyBean.GoodBean> data;
    private LayoutInflater inflater;
    private int text;
    private View layout;
    private static ViewHolder viewHolder;
    private int i = 1;
    private static RecyclerView recyclerView;
    private BtnInterface anInterface;

    public CartRecyclerAdapter(Context context, List<SupertwoModel.BodyBean.GoodBean> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layout = inflater.inflate(R.layout.cart_item, parent, false);
        viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int stag = Integer.parseInt((String) holder.cart_count.getText());
        holder.cart_title.setText(data.get(position).getGoodsName());
        int price = data.get(position).getPromotionPrice();
        holder.cart_old_price.setText("￥" + (price / 100.0));
        holder.cart_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        int amount = data.get(position).getPeroidInstalmentAmount();
        holder.cart_new_price.setText("￥" + ((amount / 100.0) * stag) + "x12期");
        Picasso.with(holder.cart_img.getContext()).load(data.get(position).getThumbnailUrl()).into(holder.cart_img);
        text = Integer.parseInt((String) holder.cart_count.getText());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInterface.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("NMLGBA", "getItemCount: " + data.size());
        return data == null ? 0 : data.size();
    }

    public void updataRes(List<SupertwoModel.BodyBean.GoodBean> all) {
        if (all != null) {
            this.data.clear();
            this.data.addAll(all);
            notifyDataSetChanged();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cart_img;
        TextView cart_title, cart_count, cart_old_price, cart_new_price;
        Button cart_add, cart_claering, cart_subtract;
        int i = 1;
        private List<SupertwoModel.BodyBean.GoodBean> data;

        public ViewHolder(View itemView) {
            super(itemView);
            this.data = data;
            cart_img = (ImageView) itemView.findViewById(R.id.cart_img);
            cart_title = (TextView) itemView.findViewById(R.id.cart_title);
            cart_count = (TextView) itemView.findViewById(R.id.cart_count);
            cart_old_price = (TextView) itemView.findViewById(R.id.cart_old_price);
            cart_new_price = (TextView) itemView.findViewById(R.id.cart_new_price);
            cart_add = (Button) itemView.findViewById(R.id.cart_add);
            cart_claering = (Button) itemView.findViewById(R.id.cart_claering);
            cart_subtract = (Button) itemView.findViewById(R.id.cart_subtract);

        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public void buttonSetOnclick(BtnInterface buttonInterface) {
        this.anInterface = buttonInterface;
    }

    public interface BtnInterface {
        void onClick(View v, int position);
    }
}
