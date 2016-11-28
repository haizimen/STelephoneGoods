package com.phone1000.stelephonegoods.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;
import com.phone1000.stelephonegoods.activities.LoginActivity;

/**
 * Created by my on 2016/11/28.
 */
public class FragmentMine extends Fragment {

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
        View layout = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!SElephant.isLogin) {
            RadioButton viewById = (RadioButton) getActivity().findViewById(R.id.main_rg_rb_homepage);
            viewById.setChecked(true);
        }
    }
}
