package com.phone1000.stelephonegoods.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.constant.ReadUrl;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by my on 2016/11/28.
 */
public class FragmentReadBook extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_readbook_layout, container,false);
//        OkHttpUtils.get()
//                .url(ReadUrl.READ_URL)
//                .
        return layout;
    }

}
