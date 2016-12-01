package com.phone1000.stelephonegoods.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.adapters.SuperImageAdapters;
import com.phone1000.stelephonegoods.adapters.SuperTitleAdapters;
import com.phone1000.stelephonegoods.json.JsonCallback;
import com.phone1000.stelephonegoods.model.SupertwoModel;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;

public class SuperBacKTwoActivity extends AppCompatActivity implements View.OnClickListener ,PlatformActionListener {

    private static final String TAG = SuperBacKTwoActivity.class.getSimpleName();
    private TextView mGoodname;
    private TextView mPromotionPrice;
    private TextView mPeroidInstalmentAmount;
    private TextView mDirectPaymentAmount;
    private ImageView mThumbnailUrl;
    private ImageView mSourcePlatformPicurl;
    private TextView mGoodsSourceStoreName;
    private ImageView mBrand_logo;
    private TextView mBrandintroduce;
    private LinearLayout mLinreaHintOpen;
    private TextView mSupeeGoodsOpenDetail;
    private boolean flag =true;
    private static  String goodsCode ="";
    private static final String  SUPER_URL_TWO="http://api.xiaoxiangyoupin.com/v2/good/";
    private static final String SUPER_URL_TWO_BOTTOM=".json";
    private ListView mListImage;
    private SuperImageAdapters adapterImage;
    private ListView mListTitle;
    private SuperTitleAdapters superTitleAdapters;
    private TextView mFenxiangTitle;
    private ImageView mImageback;
    private Button ceshiceshi;
    private String mFenthumbnailUrl;
    private String mFengoodsName;
    private Button mImmediatelyBuy;
    private int promotionPrice;
    private int peroidInstalmentAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_bac_ktwo);
        initView();
        getData(goodsCode);
    }

    private void initView() {
        Intent intent= getIntent();
        String goodcode = intent.getStringExtra("goodcode");
        String rebateCash = intent.getStringExtra("rebateCash");
        goodsCode =goodcode;
        //Log.e(TAG, "initView: "+goodcode );
        mGoodname = (TextView) findViewById(R.id.good_name);
        mPromotionPrice = (TextView) findViewById(R.id.goods_promotionPrice);
        mPeroidInstalmentAmount = (TextView) findViewById(R.id.goos_peroidInstalmentAmount);
        mDirectPaymentAmount = (TextView) findViewById(R.id.good_directPaymentAmount);
        mThumbnailUrl = (ImageView) findViewById(R.id.goods_img_thumbnailUrl);
        mSourcePlatformPicurl = (ImageView) findViewById(R.id.goods_sourcePlatformPicurl);
        mGoodsSourceStoreName = (TextView) findViewById(R.id.goods_source_goodsSourceStoreName);
        mBrand_logo = (ImageView) findViewById(R.id.goods_brand_logo);
        mBrandintroduce = (TextView) findViewById(R.id.tv_brand_introduce);

        mLinreaHintOpen = (LinearLayout) findViewById(R.id.linrea_hint_open);

        mSupeeGoodsOpenDetail = (TextView) findViewById(R.id.supeegoods_open_detail);
        mSupeeGoodsOpenDetail.setOnClickListener(this);


        mListImage = (ListView) findViewById(R.id.goods_lv_image);
        adapterImage = new SuperImageAdapters(this,null);
        mListImage.setAdapter(adapterImage);

        mListTitle = (ListView) findViewById(R.id.goods_lv_title);
        superTitleAdapters = new SuperTitleAdapters(this,null);
        mListTitle.setAdapter(superTitleAdapters);

        mFenxiangTitle = (TextView) findViewById(R.id.super_fenxiangvalue);

        mFenxiangTitle.setText("赚￥"+rebateCash);
        mFenxiangTitle.setOnClickListener(this);


        mImageback = (ImageView) findViewById(R.id.super_back_image);
        mImageback.setOnClickListener(this);

        ceshiceshi = (Button) findViewById(R.id.btn_add);
        ceshiceshi.setOnClickListener(this);


        mImmediatelyBuy = (Button) findViewById(R.id.btn_immediately_buy);
        mImmediatelyBuy.setOnClickListener(this);
    }




    private void getData(String url) {
        OkHttpUtils.get()
                .url(SUPER_URL_TWO+url+SUPER_URL_TWO_BOTTOM)

                .build()
                .execute(new JsonCallback<SupertwoModel>() {




                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: sdasdasdasd" );
                    }

                    @Override
                    public void onResponse(SupertwoModel response, int id) {
                        mFengoodsName = response.getBody().getGood().getGoodsName();
                        mGoodname.setText(mFengoodsName);

                        promotionPrice = response.getBody().getGood().getPromotionPrice();
                        double v2 = promotionPrice / 100.00;
                        String string ="￥"+v2;
                        SpannableString sp = new SpannableString(string);
                        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        mPromotionPrice.setText(sp);
                        peroidInstalmentAmount = response.getBody().getGood().getPeroidInstalmentAmount();
                        double v = peroidInstalmentAmount / 100.0;
                        mPeroidInstalmentAmount.setText("￥"+v+"x12期");
                        int directPaymentAmount = response.getBody().getGood().getDirectPaymentAmount();
                        double v1 = directPaymentAmount / 100.0;
                        mDirectPaymentAmount.setText("直付立减￥"+v1);
                        mFenthumbnailUrl = response.getBody().getGood().getThumbnailUrl();
                        Picasso.with(mThumbnailUrl.getContext()).load(mFenthumbnailUrl).into(mThumbnailUrl);
                        Picasso.with( mSourcePlatformPicurl.getContext()).load(response.getBody().getGood().getSourcePlatformPicurl()).into( mSourcePlatformPicurl);
                        mGoodsSourceStoreName.setText(response.getBody().getGood().getGoodsSourceStoreName());
                        Picasso.with(mBrand_logo.getContext()).load(response.getBody().getGood().getBrand().getLogo()).into(mBrand_logo);
                        mBrandintroduce.setText(response.getBody().getGood().getBrand().getIntroduce());
                        SupertwoModel.BodyBean.GoodBean body = response.getBody().getGood();
                        List<SupertwoModel.BodyBean.GoodBean.ImagesBean> images = body.getImages();

                        adapterImage.updateRes(images);

                        List<SupertwoModel.BodyBean.GoodBean.PropertyValuesBean> propertyValues = body.getPropertyValues();
                        superTitleAdapters.updateRes(propertyValues);



                    }
                });

        
    }
    private void shareSdkone() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle( mFengoodsName);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://www.baidu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText( mFengoodsName);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mFenthumbnailUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        //oks.setUrl("http://cdn.xiaoxiangyoupin.com/prod/app/superbate/index.html");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://cdn.xiaoxiangyoupin.com");

// 启动分享GUI
        oks.show(this);
    }
    private void login() {
        //首先初始化 SharedKey
        ShareSDK.initSDK(this);
        //获取指定的平台
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        ShareSDK.getPlatform(this, SinaWeibo.NAME);

        //为平台操作动作添加监听
        platform.setPlatformActionListener(this);
        //调用平台认证
        platform.authorize();
        //showuser
        platform.showUser(null);
    }
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }
    @Override
    public void onCancel(Platform platform, int i) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supeegoods_open_detail:
                if (flag) {
                    mLinreaHintOpen.setVisibility(View.VISIBLE);
                    flag =false;
                }else {
                    mLinreaHintOpen.setVisibility(View.GONE);

                    flag =true;
                }
                break;
            case  R.id.super_fenxiangvalue:
                shareSdkone();
                break;

            case R.id.super_back_image:
                finish();
                break;
            case R.id.btn_add:

               break;

            case R.id.btn_immediately_buy:
                Intent intent = new Intent(this, BuyCostActivity.class);
                intent.putExtra("image",mFenthumbnailUrl);
                intent.putExtra("title",mFengoodsName);
                intent.putExtra("value",promotionPrice);
                intent.putExtra("valuefen",peroidInstalmentAmount);
                startActivity(intent);
                break;
        }
    }
}
