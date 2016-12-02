package com.phone1000.stelephonegoods.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;
import com.phone1000.stelephonegoods.activities.AccountActivity;
import com.phone1000.stelephonegoods.activities.CashUpdateActivity;
import com.phone1000.stelephonegoods.activities.CertificationActivity;
import com.phone1000.stelephonegoods.activities.CommenProblemActivity;
import com.phone1000.stelephonegoods.activities.LoginActivity;
import com.phone1000.stelephonegoods.activities.MinePayActivity;
import com.phone1000.stelephonegoods.activities.OrderActivity;
import com.phone1000.stelephonegoods.activities.ReadDetailActivity;
import com.phone1000.stelephonegoods.activities.RecieverAddressActivity;
import com.phone1000.stelephonegoods.activities.SettingsActivity;
import com.phone1000.stelephonegoods.constant.ConstantStr;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.phone1000.stelephonegoods.model.MineMessage;
import com.phone1000.stelephonegoods.model.MyEvent;
import com.phone1000.stelephonegoods.model.OtherEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import okhttp3.Call;

/**
 * Created by my on 2016/11/28.
 */
public class FragmentMine extends Fragment implements View.OnClickListener {

    private View layout;
    private String TAG=FragmentMine.class.getSimpleName();
    private TextView mineorderNumber;
    private TextView waitpayNum;
    private TextView waitgoodsNum;
    private TextView cashNum;
    private TextView freeNum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SElephant.isLogin) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!SElephant.isLogin) {
            RadioButton viewById = (RadioButton) getActivity().findViewById(R.id.main_rg_rb_homepage);
            viewById.setChecked(true);
        }
        initView();
        setupData();
    }

    private void setupData() {
        OkHttpUtils.get()
                .url(ReadUrl.MINE_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: " );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: " );
                        Gson gson = new Gson();
                        MineMessage mineMessage = gson.fromJson(response, MineMessage.class);
                        if (mineMessage.getBody().getData().getMxCount()==0) {
                            freeNum.setVisibility(View.GONE);
                        }else{
                            freeNum.setVisibility(View.VISIBLE);
                            freeNum.setText(mineMessage.getBody().getData().getMxCount()+"");
                        }
                        if (mineMessage.getBody().getData().getXjCount()==0) {
                            cashNum.setVisibility(View.GONE);
                        }else{
                            cashNum.setVisibility(View.VISIBLE);
                            cashNum.setText(mineMessage.getBody().getData().getXjCount()+"");
                        }

                    }
                });
    }

    private void initView() {
        ImageView image = (ImageView) layout.findViewById(R.id.mine_imageview_image);
        TextView upgrade = (TextView) layout.findViewById(R.id.mine_textview_upgrade);
        TextView mineOrder = (TextView) layout.findViewById(R.id.mine_textview_mineorder);
        TextView waitPay = (TextView) layout.findViewById(R.id.mine_textview_waitpay);
        TextView waitGoods = (TextView) layout.findViewById(R.id.mine_textview_waitgoods);
        TextView cash = (TextView) layout.findViewById(R.id.mine_textview_cash);
        TextView free = (TextView) layout.findViewById(R.id.mine_textview_free);

        LinearLayout minePay = (LinearLayout) layout.findViewById(R.id.mine_ll_minepay);
        LinearLayout cashupdate = (LinearLayout) layout.findViewById(R.id.mine_ll_cashupdate);
        LinearLayout account = (LinearLayout) layout.findViewById(R.id.mine_ll_account);
        LinearLayout reciverAddress = (LinearLayout) layout.findViewById(R.id.mine_ll_reciveraddress);
        LinearLayout contact = (LinearLayout) layout.findViewById(R.id.mine_ll_contact);
        LinearLayout commonProblem = (LinearLayout) layout.findViewById(R.id.mine_ll_commonproblem);
        LinearLayout settings = (LinearLayout) layout.findViewById(R.id.mine_ll_settings);

        mineorderNumber =(TextView) layout.findViewById(R.id.mine_textview_mineorder_number);
        waitpayNum = (TextView)layout.findViewById(R.id.mine_textview_waitpay_number);
        waitgoodsNum = (TextView)layout.findViewById(R.id.mine_textview_waitgoods_number);
        cashNum =(TextView) layout.findViewById(R.id.mine_textview_cash_number);
        freeNum =(TextView) layout.findViewById(R.id.mine_textview_free_number);


        image.setOnClickListener(this);
        upgrade.setOnClickListener(this);
        mineOrder.setOnClickListener(this);
        waitPay.setOnClickListener(this);
        waitGoods.setOnClickListener(this);
        cash.setOnClickListener(this);
        free.setOnClickListener(this);
        minePay.setOnClickListener(this);
        cashupdate.setOnClickListener(this);
        account.setOnClickListener(this);
        reciverAddress.setOnClickListener(this);
        contact.setOnClickListener(this);
        commonProblem.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_imageview_image:
                if (SElephant.isCertigition){
                    Intent intent = new Intent(getContext(), CashUpdateActivity.class);
                    startActivity(intent);
                }else{
                    Intent certificaiton = new Intent(getContext(), CertificationActivity.class);
                    startActivity(certificaiton);
                }
                break;
            case  R.id.mine_textview_upgrade:
                Intent cashUpdate = new Intent(getContext(), CashUpdateActivity.class);
                startActivity(cashUpdate);
                break;
            case  R.id.mine_textview_mineorder:
                Intent mineOrder = new Intent(getContext(), OrderActivity.class);
                mineOrder.putExtra("title", ConstantStr.MINEORDER);
                startActivity(mineOrder);
                break;
            case  R.id.mine_textview_waitpay:
                Intent waitPay = new Intent(getContext(), OrderActivity.class);
                waitPay.putExtra("title",ConstantStr.WAITPAY);
                startActivity(waitPay);
                break;
            case  R.id.mine_textview_waitgoods:
                Intent waitGoods = new Intent(getContext(), OrderActivity.class);
                waitGoods.putExtra("title",ConstantStr.WAITGOODS);
                startActivity(waitGoods);
                break;
            case  R.id.mine_textview_cash:
                Intent cash = new Intent(getContext(), OrderActivity.class);
                cash.putExtra("title",ConstantStr.CASH);
                startActivity(cash);
                break;
            case  R.id.mine_textview_free:
                Intent free = new Intent(getContext(), OrderActivity.class);
                free.putExtra("title",ConstantStr.FREE);
                startActivity(free);
                break;
            case R.id.mine_ll_minepay:
                Intent minePay = new Intent(getContext(), MinePayActivity.class);
                startActivity(minePay);
                break;
            case R.id.mine_ll_cashupdate:
                Intent intent = new Intent(getContext(), CashUpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_ll_account:
                Intent account = new Intent(getContext(), AccountActivity.class);
                startActivity(account);
                break;
            case R.id.mine_ll_reciveraddress:
                Intent reciecverAddress = new Intent(getContext(), RecieverAddressActivity.class);
                startActivity(reciecverAddress);
                break;
            case R.id.mine_ll_contact:
                Intent intent1 = new Intent(getContext(), ReadDetailActivity.class);
                intent1.putExtra("url", ReadUrl.CONTACT_CUSTOMER_URL);
                startActivity(intent1);
                break;
            case R.id.mine_ll_commonproblem:
                Intent commenProblem = new Intent(getContext(), CommenProblemActivity.class);
                startActivity(commenProblem);
                break;
            case R.id.mine_ll_settings:
                Intent settings = new Intent(getContext(), SettingsActivity.class);
                startActivity(settings);
                break;

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
