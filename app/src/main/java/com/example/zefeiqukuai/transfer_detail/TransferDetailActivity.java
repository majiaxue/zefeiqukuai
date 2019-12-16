package com.example.zefeiqukuai.transfer_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.OnGetPayListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.DealBean;
import com.example.zefeiqukuai.bean.LingZhongBean;
import com.example.zefeiqukuai.bean.PetDetailBean;
import com.example.zefeiqukuai.shen_detail.ShenActivity;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferDetailActivity extends BaseActivity<TransferDetailView, TransferDetailPreenter> implements TransferDetailView {


    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.content_gong1_img)
    ImageView contentGong1Img;
    @BindView(R.id.content_gong1_name)
    TextView contentGong1Name;
    @BindView(R.id.content_gong_bh)
    TextView contentGongBh;
    @BindView(R.id.bh)
    LinearLayout bh;
    @BindView(R.id.content_gong_value)
    TextView contentGongValue;
    @BindView(R.id.content_gong_time)
    TextView contentGongTime;
    @BindView(R.id.content_gong_qiang)
    TextView contentGongQiang;
    @BindView(R.id.content_gong_shouyi)
    TextView contentGongShouyi;
    @BindView(R.id.content_gong_hkt)
    TextView contentGongHkt;
    @BindView(R.id.content_gong1_btn)
    TextView contentGong1Btn;
    @BindView(R.id.detail_order_id)
    TextView detailOrderId;
    @BindView(R.id.chonghzi_type_lin)
    LinearLayout chonghziTypeLin;
    @BindView(R.id.detail_zh)
    TextView detailZh;
    @BindView(R.id.detail_type_zh)
    TextView detailTypeZh;
    @BindView(R.id.detail_nick)
    TextView detailNick;
    @BindView(R.id.detail_pay_type)
    TextView detailPayType;
    @BindView(R.id.chonghzi_type_ma)
    LinearLayout chonghziTypeMa;
    @BindView(R.id.detail_type_bizhong)
    TextView detailTypeBizhong;
    @BindView(R.id.detail_bank)
    TextView detailBank;
    @BindView(R.id.detail_bank_zhjihang)
    TextView detailBankZhjihang;
    @BindView(R.id.detail_money)
    TextView detailMoney;
    @BindView(R.id.detail_bank_card)
    TextView detailBankCard;
    @BindView(R.id.detail_moeny_bank)
    TextView detailMoenyBank;
    @BindView(R.id.detail_bank_shangchuan)
    ImageView detailBankShangchuan;
    @BindView(R.id.detail_shagnchuan_recy)
    RecyclerView detailShagnchuanRecy;
    @BindView(R.id.detail_type_sumbit_bank)
    TextView detailTypeSumbitBank;
    @BindView(R.id.detail_type_shengyu_time_bank)
    TextView detailTypeShengyuTimeBank;
    @BindView(R.id.detail_btn_bank)
    LinearLayout detailBtnBank;
    @BindView(R.id.detail_pay_type_bank)
    LinearLayout detailPayTypeBank;
    @BindView(R.id.detail_lingyang_zh)
    TextView detailLingyangZh;
    @BindView(R.id.detail_lingyang_nickname)
    TextView detailLingyangNickname;
    @BindView(R.id.detail_lingyang_pay)
    ImageView detailLingyangPay;
    @BindView(R.id.detail_type_sumbit)
    TextView detailTypeSumbit;
    @BindView(R.id.detail_type_shengyu_time)
    TextView detailTypeShengyuTime;
    @BindView(R.id.detail_btn)
    LinearLayout detailBtn;
    @BindView(R.id.detail_pay_type_zhifubao_lin)
    LinearLayout detailPayTypeZhifubaoLin;
    private PetDetailBean petDetailBean;
    private DealBean.DataBean bean;
    private int flag;


    @Override
    public int getLayoutId() {
        return R.layout.detail_lingyang;
    }

    @Override
    public void initData() {
        ProcessDialogUtil.showProcessDialog(TransferDetailActivity.this);
        includeTitle.setText("交易详情");
        Intent intent = getIntent();
        bean = (DealBean.DataBean) intent.getSerializableExtra("beans");
        flag = intent.getIntExtra("flag", 0);
        contentGongValue.setText(bean.getPrice() + "");
        contentGongBh.setText(bean.getId() + "");
        contentGongTime.setText(bean.getStart_time());
        contentGongQiang.setText(bean.getAppoint() + "/" + bean.getPurchase());
        contentGongShouyi.setText(bean.getDay() + "/" + ArithUtil.exact(bean.getBonus_rate() * 100, 2) + "%");
        contentGongHkt.setText(ArithUtil.exact(bean.getCoin_hkt(), 2) + "枚");
        contentGong1Name.setText(bean.getName());
        Glide.with(TransferDetailActivity.this).load(CommonResource.BASEURL_8089 + bean.getImage()).into(contentGong1Img);
        if (bean.getStatus() == 0) {
            contentGong1Btn.setText("交易中");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_zhong);
        } else if (bean.getStatus() == 1) {
            contentGong1Btn.setText("已付款");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_zhong);
        } else if (bean.getStatus() == 2) {
            contentGong1Btn.setText("已完成");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_zhong);
        } else if (bean.getStatus() == 3) {
            contentGong1Btn.setText("申诉中");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_zhong);
        } else if (bean.getStatus() == 4) {
            contentGong1Btn.setText("申诉成功");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_success);
        } else if (bean.getStatus() == 5) {
            contentGong1Btn.setText("申诉失败");
            contentGong1Btn.setBackgroundResource(R.drawable.bg_btn_shen_fail);
        }
        presenter.loadData(bean.getId());
    }


    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ("取消申诉".equals(detailTypeSumbit.getText().toString())) {
                    presenter.getAppeal(petDetailBean.getId(), 2, "");
                } else {
                    presenter.getData(petDetailBean.getId());
                }
            }
        });
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferDetailActivity.this, ShenActivity.class);
                intent.putExtra("petDetailBean", petDetailBean);
                intent.putExtra("beans", bean);
                startActivity(intent);
            }
        });

    }

    @Override
    public TransferDetailView createView() {
        return this;
    }

    @Override
    public TransferDetailPreenter createPresenter() {
        return new TransferDetailPreenter(this);
    }

    public static String getTime(long second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        return hms;
    }

    @Override
    public void loadData(PetDetailBean petDetailBean) {
        this.petDetailBean = petDetailBean;
        detailLingyangZh.setText(petDetailBean.getBuyer_member());
        detailLingyangNickname.setText(petDetailBean.getBuyer_nickname());
        if (TextUtils.isEmpty(petDetailBean.getProof())) {
            Glide.with(TransferDetailActivity.this).load(R.drawable.shoukuan_shangchuan).into(detailLingyangPay);
        } else {
            Glide.with(TransferDetailActivity.this).load(CommonResource.BASEURL_8089 + petDetailBean.getProof()).into(detailLingyangPay);
        }
        new CountDownTimer(petDetailBean.getRest_time() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                detailTypeShengyuTime.setText(getTime(l) + "");
            }

            @Override
            public void onFinish() {

            }
        }.start();
        if (petDetailBean.getStatus() == 0) {
            detailTypeSumbit.setText("待付款");
        } else if (petDetailBean.getStatus() == 1) {
            if (petDetailBean.getIs_buyer() == 1) {
                detailTypeSumbit.setEnabled(false);
            } else {
                detailTypeSumbit.setEnabled(true);
            }
            if (flag == 0) {
                includeRightBtn.setVisibility(View.VISIBLE);
                includeRightBtn.setText("申诉");
                includeRightBtn.setEnabled(true);
            } else {
                includeRightBtn.setVisibility(View.GONE);
            }
            detailTypeSumbit.setText("待确认");
        } else if (petDetailBean.getStatus() == 2) {
            detailTypeSumbit.setText("已完成");
            includeRightBtn.setEnabled(true);
            detailBtn.setEnabled(false);
            detailTypeShengyuTime.setVisibility(View.GONE);
        } else if (petDetailBean.getStatus() == 3) {
            if (petDetailBean.getIs_buyer() == 1) {
                detailTypeSumbit.setText("卖家申诉");
                detailBtn.setEnabled(false);
            } else {
                detailTypeSumbit.setText("取消申诉");
                detailBtn.setEnabled(true);
            }
            detailTypeShengyuTime.setVisibility(View.GONE);
        } else if (petDetailBean.getStatus() == 4) {
            detailTypeSumbit.setText("申诉成功");

            detailBtn.setEnabled(false);
            detailTypeShengyuTime.setVisibility(View.GONE);
        } else if (petDetailBean.getStatus() == 5) {
            detailTypeSumbit.setText("申诉失败");
            detailBtn.setEnabled(false);
            detailTypeShengyuTime.setVisibility(View.GONE);
        } else if (petDetailBean.getStatus() == 6) {
            detailTypeSumbit.setText("自动完成");
            detailBtn.setEnabled(false);
            detailTypeShengyuTime.setVisibility(View.GONE);
        } else {
            detailTypeSumbit.setText("订单超时");
            detailBtn.setEnabled(false);
            detailTypeShengyuTime.setVisibility(View.GONE);
        }
    }
}
