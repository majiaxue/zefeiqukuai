package com.example.zefeiqukuai.pet_detail;

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
import com.example.zefeiqukuai.bean.LingZhongBean;
import com.example.zefeiqukuai.bean.PetDetailBean;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetDetailActivity extends BaseActivity<PetDetailView, PetDetailPreenter> implements PetDetailView {


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
    @BindView(R.id.detail_zhifubao_order_id)
    TextView detailZhifubaoOrderId;
    @BindView(R.id.detail_zhifubao_zh)
    TextView detailZhifubaoZh;
    @BindView(R.id.detail_nick_zhifuba)
    TextView detailNickZhifuba;
    @BindView(R.id.detail_pay_type_zhifubao)
    TextView detailPayTypeZhifubao;
    @BindView(R.id.detail_bank_name_zhifubao)
    TextView detailBankNameZhifubao;
    @BindView(R.id.detail_zhifubao_zhm)
    TextView detailZhifubaoZhm;
    @BindView(R.id.detail_zhifubao_erweima)
    ImageView detailZhifubaoErweima;
    @BindView(R.id.detail_moeny)
    TextView detailMoeny;
    @BindView(R.id.detail_shangchuan)
    ImageView detailShangchuan;
    @BindView(R.id.detail_chuan_recy)
    RecyclerView detailChuanRecy;
    @BindView(R.id.detail_type_sumbit)
    TextView detailTypeSumbit;
    @BindView(R.id.detail_type_shengyu_time)
    TextView detailTypeShengyuTime;
    @BindView(R.id.detail_btn)
    LinearLayout detailBtn;
    @BindView(R.id.detail_pay_type_linear)
    LinearLayout detailPayTypeLinear;
    @BindView(R.id.petdetail_type)
    TextView petdetailType;
    @BindView(R.id.petdetail_zh)
    TextView petdetailZh;
    @BindView(R.id.detail_bank_name)
    TextView detailBankName;
    @BindView(R.id.detail_bank_lin)
    LinearLayout detailBankLin;
    @BindView(R.id.detail_bank_name_card)
    TextView detailBankNameCard;
    @BindView(R.id.detail_bank_number_lin)
    LinearLayout detailBankNumberLin;
    @BindView(R.id.detail_erweima)
    LinearLayout detailErweima;

    private PetDetailBean petDetailBean;
    private LingZhongBean.DataBean bean;
    private int flag = 0;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    private int paytype = 1;
    Uri fileUri;
    private String pay_type;

    @Override
    public int getLayoutId() {
        return R.layout.detail_zhuanrang;
    }

    @Override
    public void initData() {
        ProcessDialogUtil.showProcessDialog(PetDetailActivity.this);
        includeTitle.setText("交易详情");
        Intent intent = getIntent();

        bean = (LingZhongBean.DataBean) intent.getSerializableExtra("bean");
        pay_type = bean.getPay_type();
        if (pay_type ==null){
            pay_type="1";
        }

        contentGongValue.setText(bean.getPrice() + "");
        contentGongBh.setText(bean.getId()+"");
        contentGongTime.setText(bean.getAddtime());
        contentGongQiang.setText(bean.getAppoint() + "/" + bean.getPurchase());
        contentGongShouyi.setText(bean.getDay() + "/" + ArithUtil.exact(bean.getBonus_rate() * 100, 2) + "%");
        contentGongHkt.setText(ArithUtil.exact(bean.getCoin_hkt(), 2) + "枚");
        contentGong1Name.setText(bean.getName());
        Glide.with(PetDetailActivity.this).load(CommonResource.BASEURL_8089 + bean.getImage()).into(contentGong1Img);
        detailMoeny.setText(bean.getPrice() + "");
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

    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //二维码大图
        detailZhifubaoErweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    PopUtils.seeBigImg(PetDetailActivity.this, CommonResource.BASEURL_8089 + petDetailBean.getAliqrcode());
                } else {
                    PopUtils.seeBigImg(PetDetailActivity.this, CommonResource.BASEURL_8089 + petDetailBean.getWxqrcode());
                }
            }
        });
        //选择支付方式
        detailPayTypeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.payPop(PetDetailActivity.this, new OnGetPayListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 1) {
                            paytype = 1;
                            detailBankLin.setVisibility(View.GONE);
                            detailBankNumberLin.setVisibility(View.GONE);
                            detailPayTypeZhifubao.setText("支付宝");
                        } else if (type == 2) {
                            paytype = 2;
                            detailBankLin.setVisibility(View.GONE);
                            detailBankNumberLin.setVisibility(View.GONE);
                            detailPayTypeZhifubao.setText("微信");
                            petdetailType.setText("微信姓名");
                            detailBankNameZhifubao.setText(petDetailBean.getWxname());
                            petdetailZh.setText("微信账号");
                            detailZhifubaoZhm.setText(petDetailBean.getWxno());
                            Glide.with(PetDetailActivity.this).load(CommonResource.BASEURL_8089 + petDetailBean.getWxqrcode()).into(detailZhifubaoErweima);
                        } else if (type == 3) {
                            paytype = 3;
                            detailErweima.setVisibility(View.GONE);
                            detailBankLin.setVisibility(View.VISIBLE);
                            detailBankNumberLin.setVisibility(View.VISIBLE);
                            detailPayTypeZhifubao.setText("银行卡");
                            petdetailType.setText("开户银行");
                            detailBankNameZhifubao.setText(petDetailBean.getBankname());
                            petdetailZh.setText("开户支行");
                            detailZhifubaoZhm.setText(petDetailBean.getBranch());
                            detailBankNameCard.setText(petDetailBean.getCardno());
                            detailBankName.setText(petDetailBean.getCardname());
                        }

                    }
                });
            }
        });
        //上传支付凭证
        detailShangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateHeader();
            }
        });
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = LayoutInflater.from(PetDetailActivity.this).inflate(R.layout.pop_tankuang01, null);
                EditText tanPsw = view.findViewById(R.id.tan_psw);
                EditText tanQuePsw = view.findViewById(R.id.tan_que_psw);
                TextView cancel = view.findViewById(R.id.tv_cancel);
                TextView ok = view.findViewById(R.id.tv_ok);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) PetDetailActivity.this.getResources().getDimension(R.dimen.dp_233), true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
                popupWindow.showAtLocation(new View(PetDetailActivity.this), Gravity.CENTER, 0, 0);
                setTransparency(PetDetailActivity.this, 0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setTransparency(PetDetailActivity.this, 1f);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(tanQuePsw.getText().toString())) {
                            Toast.makeText(PetDetailActivity.this, "请输入交易密码", Toast.LENGTH_SHORT).show();
                        } else {
                            presenter.checkPassword(tanQuePsw.getText().toString());
                            popupWindow.dismiss();
                        }
                    }
                });

            }
        });

    }

    @Override
    public PetDetailView createView() {
        return this;
    }

    @Override
    public PetDetailPreenter createPresenter() {
        return new PetDetailPreenter(this);
    }

    @Override
    public void loadData(PetDetailBean petDetailBean) {
        if (petDetailBean != null) {
            this.petDetailBean = petDetailBean;
            detailZhifubaoOrderId.setText(petDetailBean.getOrdernum() + "");
            detailZhifubaoZh.setText(petDetailBean.getSeller_member() + "");
            detailNickZhifuba.setText(petDetailBean.getSeller_nickname());
            if (TextUtils.isEmpty(petDetailBean.getPay_type())) {
                detailPayTypeZhifubao.setText("支付宝");
                detailBankNameZhifubao.setText(petDetailBean.getAliname());
                detailZhifubaoZhm.setText(petDetailBean.getAlino());
                Glide.with(PetDetailActivity.this).load(CommonResource.BASEURL_8089 + petDetailBean.getAliqrcode()).into(detailZhifubaoErweima);
            } else if ("1".equals(petDetailBean.getPay_type())) {
                detailShangchuan.setEnabled(false);
                Glide.with(this).load(CommonResource.BASEURL_8089 + petDetailBean.getProof()).into(detailShangchuan);
                detailPayTypeLinear.setEnabled(false);
                detailPayTypeZhifubao.setText("支付宝");
                detailBankNameZhifubao.setText(petDetailBean.getAliname());
                detailZhifubaoZhm.setText(petDetailBean.getAlino());
                Glide.with(PetDetailActivity.this).load(CommonResource.BASEURL_8089 + petDetailBean.getAliqrcode()).into(detailZhifubaoErweima);
            } else if ("2".equals(petDetailBean.getPay_type())) {
                detailShangchuan.setEnabled(false);
                Glide.with(this).load(CommonResource.BASEURL_8089 + petDetailBean.getProof()).into(detailShangchuan);
                detailPayTypeLinear.setEnabled(false);
                detailBankLin.setVisibility(View.GONE);
                detailBankNumberLin.setVisibility(View.GONE);
                detailPayTypeZhifubao.setText("微信");
                petdetailType.setText("微信姓名");
                detailBankNameZhifubao.setText(petDetailBean.getWxname());
                petdetailZh.setText("微信账号");
                detailZhifubaoZhm.setText(petDetailBean.getWxno());
                Glide.with(PetDetailActivity.this).load(CommonResource.BASEURL_8089 + petDetailBean.getWxqrcode()).into(detailZhifubaoErweima);
            } else if ("3".equals(petDetailBean.getPay_type())) {
                detailShangchuan.setEnabled(false);
                Glide.with(this).load(CommonResource.BASEURL_8089 + petDetailBean.getProof()).into(detailShangchuan);
                detailPayTypeLinear.setEnabled(false);
                detailErweima.setVisibility(View.GONE);
                detailBankLin.setVisibility(View.VISIBLE);
                detailBankNumberLin.setVisibility(View.VISIBLE);
                detailPayTypeZhifubao.setText("银行卡");
                petdetailType.setText("开户银行");
                detailBankNameZhifubao.setText(petDetailBean.getBankname());
                petdetailZh.setText("开户支行");
                detailZhifubaoZhm.setText(petDetailBean.getBranch());
                detailBankNameCard.setText(petDetailBean.getCardno());
                detailBankName.setText(petDetailBean.getCardname());
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
                detailTypeSumbit.setText("待确认");
                detailTypeShengyuTime.setVisibility(View.GONE);
                detailBtn.setEnabled(false);
            } else if (petDetailBean.getStatus() == 2) {
                detailTypeSumbit.setText("已完成");
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

    @Override
    public void takePhoto(Intent captureIntent) {
        startActivityForResult(captureIntent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void showHeader(Uri fileUri) {
        this.fileUri = fileUri;
        Glide.with(this).load(fileUri).into(detailShangchuan);

    }

    @Override
    public void paycommit() {
        LogUtil.e("-----------" + petDetailBean.getId());
        LogUtil.e("-----------" + SPUtil.getStringValue("zhifupingzheng"));
        LogUtil.e("-----------" + paytype);
        presenter.payCommit(petDetailBean.getId(), SPUtil.getStringValue("zhifupingzheng"), paytype);
    }

    @Override
    public void cropPhoto(Intent intent) {
        startActivityForResult(intent, CROP_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.uploadPhoto();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                presenter.uploadPhoto();
                break;
        }
    }

    public static String getTime(long second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        return hms;
    }
}
