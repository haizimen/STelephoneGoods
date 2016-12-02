package com.phone1000.stelephonegoods.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.alipay.PayResult;
import com.phone1000.stelephonegoods.alipay.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BuyAliPayActivity extends AppCompatActivity implements View.OnClickListener,Handler.Callback {
    // 商户PID
    public static final String PARTNER = "2088111278561763";
    // 商户收款账号
    public static final String SELLER = "gaoyandingzhi@126.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEClM9ja39OuhbiFcPYG8nUt19TIGvnBjC2CGMV3BKY2pTolVuicMfM0yyxvwtewe7Wkk+06Zl8fjgIWZS8SsfOeznQZbJq236CbcFYIhDsorDllDwQ0Uk409WSjaOCDJamOjGeQjYqy3D7v+z+Z48ZvCOPleX2h415mHQeHWVdAgMBAAECgYB6FrHqOr7uTIRzHXltPu1shi7fJeWIYhjBl3NqvbghvNvho8KrFkYez8yDDQj1kVJjOz+YA6t4lrn77RS2xw4+fRJgBy/LD9ILectaThysuFt84yKooSuFAv1AQKMeVXkpnFuzzBFtxyuRPtPUYXftSvEm/9BapFHGEVCuT7RvAQJBAP9yq18VFhPQAfngld9n0NwmCO33kdbFYqVIWBNKZdvVZIqwIvnmTqsgQacrvWutsWauukKT7VzySkht/uE63j0CQQDRdjgqx4H7SfMjkaZK5nJ6ptuFgR19HkakOJZSIM78Ot3PzfHcnfYuCRjs8lIEWmhYqj2FE+BcZ9cejphGuTWhAkB0XimBXBq9ldGAonXD2whDcbQ5q8EtJKgmgUlWKFs0hQaTQ1/7lZYa0Mv3uq5EwlCBZXGGaNsFr351dl5Y/jdFAkA6D2DmSsL22rqwo1DK9jHJWbMDwJRh+CBwqNbSERIOzGprjZR7KLXycMcd9tVRK5Y87YN7/dR1CLuSVshS4kfBAkAW6ls9/RlBA6gOpDuq+Qn4CZUng3h7OJsDgzCY95RtuMISJNuVFcGC/XVKB+urkyfhR/H7I8HIPXQtNJenH9f2";

    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int PAY_FLAG = 200;
    private Handler mHandler = new Handler(this);
    private TextView viewById;
    private int value;
    private TextView mAlipAyzhi;
    private int alipayjian;
    private RelativeLayout mCost;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ali_pay);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        value = intent.getIntExtra("value", 0);
        alipayjian = intent.getIntExtra("alipayjian", 0);
        viewById = (TextView) findViewById(R.id.buyalipay_values);
        viewById.setText("￥"+value/100.0);
        mAlipAyzhi = (TextView) findViewById(R.id.alipayzhifujian_value);
        mAlipAyzhi.setText("直付立减￥"+alipayjian/100.0);

        mCost = (RelativeLayout) findViewById(R.id.alizhifu_relrea_btn);
        mCost.setOnClickListener(this);
        mBack = (ImageView) findViewById(R.id.buyalipay_back_image);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alizhifu_relrea_btn:
                //①生成签名并并编码后的订单
                String orderInfo = getOrderInfo("小象优品商品", "走向人生巅峰", String.valueOf(value/100.0));

                String sign = sign(orderInfo);

                try {
                    sign = URLEncoder.encode(sign,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //②拼接完整订单
                final String payInfo = orderInfo + "&sign=\"" + sign +"\"&sign_type=\"RSA\"";
                //构建PayTask支付

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        //
                        PayTask payTask = new PayTask(BuyAliPayActivity.this);

                        String payResult = payTask.pay(payInfo, true);

                        Message message = Message.obtain();
                        message.what = PAY_FLAG;
                        message.obj = payResult;
                        mHandler.sendMessage(message);
                    }
                };
                thread.start();
                break;
            case R.id.buyalipay_back_image:


//               new AlertDialog.Builder(BuyAliPayActivity.this)
//                        // 设置对话框标题
//                        .setTitle("确定要放弃付款")
//                        .setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1) {
//                                arg0.dismiss();
//
//                                Intent intent = new Intent(BuyAliPayActivity.this,CartActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("取消",null)
//                        .create().show();


                final AlertDialog dlg = new AlertDialog.Builder(this).create();
                dlg.show();
                Window window = dlg.getWindow();
                // *** 主要就是在这里实现这种效果的.
                // 设置窗口的内容页面,login_error_butt.xml文件中定义view内容
                window.setContentView(R.layout.pupwindow_login_error);
                Button login_err = (Button) window.findViewById(R.id.login_error_butt1 );
                Button logein_e = (Button)window.findViewById(R.id.login_error_butt);
                login_err.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        dlg.dismiss();
                        Intent intent = new Intent(BuyAliPayActivity.this,CartActivity.class);
                                startActivity(intent);

                    }
                });
                logein_e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        dlg.dismiss();
                        dlg.cancel();
                    }
                });

                break;
        }

    }
    /**
     *
     * creat the order info 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case PAY_FLAG:
                PayResult payResult = new PayResult((String) msg.obj);
                //获取支付状态
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus,"9000")) {
                    Toast.makeText(BuyAliPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.equals(resultStatus,"8000")){
                    Toast.makeText(BuyAliPayActivity.this, "支付确认中", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BuyAliPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
