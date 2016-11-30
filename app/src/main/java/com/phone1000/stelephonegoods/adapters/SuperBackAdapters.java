package com.phone1000.stelephonegoods.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.activities.ShareSdkActivity;
import com.phone1000.stelephonegoods.activities.SuperBacKTwoActivity;
import com.phone1000.stelephonegoods.model.SuperManModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2016/11/28.
 */
public class SuperBackAdapters extends BaseAdapter implements PlatformActionListener {
    private static final String TAG = SuperBackAdapters.class.getSimpleName();
    private LayoutInflater inflater;
    private List<SuperManModel.BodyBean.RebateGoodsBean> data;
    private Context context;
    private String mTitlegoodsName;
    private String mImagethumbnailUrl;

    public  SuperBackAdapters(Context context, List<SuperManModel.BodyBean.RebateGoodsBean> data){
        inflater = LayoutInflater.from(context);
        this.context = context;
        if (this.data != data) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }
    public void updateRes(List<SuperManModel.BodyBean.RebateGoodsBean> data){
        if (data!= null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public SuperManModel.BodyBean.RebateGoodsBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView==null) {
            convertView =inflater.inflate(R.layout.superback_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.super_value_title);
            viewHolder.imageView =(ImageView)convertView.findViewById(R.id.super_image);


            viewHolder.relativeLayout =(RelativeLayout)convertView.findViewById(R.id.relative_onclick);
            viewHolder.title = (TextView)convertView.findViewById(R.id.superback_title_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position).getRebateCash());
        viewHolder.title.setText(data.get(position).getGoodsName());
        Picasso.with(viewHolder.imageView.getContext()).load(data.get(position).getThumbnailUrl()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, SuperBacKTwoActivity.class);
                String goodsCode = data.get(position).getGoodsCode();
                String rebateCash = data.get(position).getRebateCash();
                intent.putExtra("goodcode",goodsCode);
                intent.putExtra("rebateCash",rebateCash);
                context.startActivity(intent);
                Log.e(TAG, "onClick: "+data.get(position).getGoodsCode() );
            }

        });
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ShareSdkActivity.class);
                mImagethumbnailUrl = data.get(position).getThumbnailUrl();
                mTitlegoodsName = data.get(position).getGoodsName();
                //intent.putExtra("thumbnailUrl",thumbnailUrl);
               // intent.putExtra("goodsName",goodsName);
               // context.startActivity(intent);
                initView();
            }
        });
        return convertView;
    }

    private void initView() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

    // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mTitlegoodsName);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://www.baidu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mTitlegoodsName);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mImagethumbnailUrl);
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
        oks.show(context);
    }
    private void login() {
        //首先初始化 SharedKey
        ShareSDK.initSDK(context);
        //获取指定的平台
        Platform platform = ShareSDK.getPlatform(context, QQ.NAME);
        ShareSDK.getPlatform(context,SinaWeibo.NAME);

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

    public class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView title;
        RelativeLayout relativeLayout;

    }

}
