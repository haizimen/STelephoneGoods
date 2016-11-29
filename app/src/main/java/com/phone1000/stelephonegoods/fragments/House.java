package com.phone1000.stelephonegoods.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.stelephonegoods.BaseFragment;
import com.phone1000.stelephonegoods.R;

/**
 * Created by Administrator on 2016/11/28.
 */
public class House extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.house,container,false);
        return layout;
    }
}
