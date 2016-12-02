package com.phone1000.stelephonegoods.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.CartRecyclerAdapter;
import com.phone1000.stelephonegoods.model.SupertwoModel;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private CartRecyclerAdapter adapter;
    private List<SupertwoModel.BodyBean.GoodBean> all;
    private int i = 1;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.cart_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);

        DbManager db = x.getDb(SuperBacKTwoActivity.config);


        try {
            all = db.selector(SupertwoModel.BodyBean.GoodBean.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < all.size(); i++) {
            Log.e("CNM", "initView: " + all.get(i).getGoodsName());
        }
        adapter = new CartRecyclerAdapter(this, null);
        adapter.onAttachedToRecyclerView(mRecycler);
        mRecycler.setAdapter(adapter);
        adapter.updataRes(all);

//        adapter.buttonSetOnclick(new CartRecyclerAdapter.BtnInterface() {
//
//
//            //            View view = LayoutInflater.from(CartActivity.this).inflate(R.layout.cart_item, null, false);
////            TextView count = (TextView) view.findViewById(R.id.cart_count);
//            @Override
//////            public void onClick(View v, int position) {
////                count = (TextView) v.findViewById(R.id.cart_count);
////                v.findViewById(R.id.cart_add).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        i++;
////                        count.setText(i+"");
////                    }
////                });
////                v.findViewById(R.id.cart_subtract).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        if (i>1) {
////                            i--;
////                            count.setText(i+"");
////                        }else {
////                            i=1;
////                            count.setText(i+"");
////                        }
////                    }
////                });
//////                Button sub = (Button) v.findViewById(R.id.cart_subtract);
//////                sub.setTag(position);
//////                Button add = (Button) v.findViewById(R.id.cart_add);
//////                add.setTag(position);
//////                sub.setOnClickListener(CartActivity.this);
//////                add.setOnClickListener(CartActivity.this);
////            }
////        });
//    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.cart_add:
//                i++;
//                count.setText(i+"");
//                break;
//            case R.id.cart_subtract:
//                if (i>1) {
//                    i--;
//                    count.setText(i + "");
//                } else {
//                    i = 1;
//                    count.setText(i + "");
//                }
//                break;
//        }
//    }
    }
}
