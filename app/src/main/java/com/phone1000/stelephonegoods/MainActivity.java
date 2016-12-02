package com.phone1000.stelephonegoods;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.phone1000.stelephonegoods.fragments.FragmentHomePage;
import com.phone1000.stelephonegoods.fragments.FragmentMine;
import com.phone1000.stelephonegoods.fragments.FragmentReadBook;
import com.phone1000.stelephonegoods.fragments.FragmentSuperBack;
import com.phone1000.stelephonegoods.model.OtherEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;
    private Fragment currentFragment;
    private float downX;
    private float downY;
    private float upX;
    private float upY;
    private String TAG=MainActivity.class.getSimpleName();
    private RadioButton mRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.main_framelayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioButton = (RadioButton) findViewById(R.id.main_rg_rb_mine);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        currentFragment = new FragmentHomePage();
        transaction.add(R.id.main_framelayout, currentFragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.main_rg_rb_homepage:
                addFragment(new FragmentHomePage());
                break;
            case R.id.main_rg_rb_readbooks:
                addFragment(new FragmentReadBook());
                break;
            case R.id.main_rg_rb_superback:
                addFragment(new FragmentSuperBack());
                break;
            case R.id.main_rg_rb_mine:
                addFragment(new FragmentMine());
                break;
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragment);
        currentFragment = fragment;
        if (manager.getFragments().contains(currentFragment)) {
            transaction.show(currentFragment);
        } else {
            transaction.add(R.id.main_framelayout, currentFragment);
        }
        transaction.commit();
    }
    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void onMessageEvent(OtherEvent event){

        mRadioButton.setChecked(true);
        Log.e(TAG, "onMessageEvent: " );
       // addFragment(new FragmentMine());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
