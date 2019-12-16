package com.example.zefeiqukuai.my.luer.chongzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.example.base.utils.LogUtil;
import com.example.base.utils.OnGetPayListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.BankCardBean;
import com.example.zefeiqukuai.bean.RealMoneyBean;
import com.example.zefeiqukuai.bean.WeChatBean;
import com.example.zefeiqukuai.bean.ZFBBean;

import java.security.interfaces.DSAPublicKey;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChongZhiActivity extends BaseActivity<ChongZhiView, ChongZhiPresenter> implements ChongZhiView {


    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.chongzhi_type)
    TextView chongzhiType;
    @BindView(R.id.chonghzi_type_lin)
    LinearLayout chonghziTypeLin;
    @BindView(R.id.chongzhi_type_name)
    TextView chongzhiTypeName;
    @BindView(R.id.chongzhi_name)
    TextView chongzhiName;
    @BindView(R.id.chongzhi_type_zh)
    TextView chongzhiTypeZh;
    @BindView(R.id.chongzhi_zh)
    TextView chongzhiZh;
    @BindView(R.id.chonghzi_type_ma)
    LinearLayout chonghziTypeMa;
    @BindView(R.id.chongzhi_type_bizhong)
    TextView chongzhiTypeBizhong;
    @BindView(R.id.chongzhi_luer_count)
    TextView chongzhiLuerCount;
    @BindView(R.id.chongzhi_number)
    EditText chongzhiNumber;
    @BindView(R.id.chongzhi_money)
    TextView chongzhiMoney;
    @BindView(R.id.chongzhi_bh)
    TextView chongzhiBh;
    @BindView(R.id.chongzhi_shangchuan)
    ImageView chongzhiShangchuan;
    @BindView(R.id.chongzhi_shagnchuan_recy)
    RecyclerView chongzhiShagnchuanRecy;
    @BindView(R.id.chongzhi_btn)
    TextView chongzhiBtn;
    @BindView(R.id.chongzhi_bank_name)
    TextView chongzhiBankName;
    @BindView(R.id.chongzhi_bank_name_lin)
    LinearLayout chongzhiBankNameLin;
    @BindView(R.id.chongzhi_bank_number)
    TextView chongzhiBankNumber;
    @BindView(R.id.chongzhi_bank_number_lin)
    LinearLayout chongzhiBankNumberLin;
    @BindView(R.id.chongzhi_erweima)
    LinearLayout chongzhiErweima;
    @BindView(R.id.chongzhi_erweima_img)
    ImageView chongzhiErweimaImg;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    private int payType ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chongzhi;
    }

    @Override
    public void initData() {
        includeTitle.setText("充值");
        presenter.getGathing(1);
        payType =1;
        String weifen = SPUtil.getStringValue("weifen");
        if (TextUtils.isEmpty(weifen)) {
            chongzhiLuerCount.setText("鹿耳(当前数量0)");
        } else {
            chongzhiLuerCount.setText("鹿耳(当前数量" + weifen + ")");
        }

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
        chonghziTypeLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.payPop(ChongZhiActivity.this, new OnGetPayListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 2) {
                            payType =type;
                            chongzhiType.setText("微信");
                            chongzhiTypeName.setText("微信昵称");
                            chongzhiTypeZh.setText("微信账号");
                            presenter.getGathing(type);
                        } else if (type == 1) {
                            payType =type;
                            chongzhiType.setText("支付宝");
                            chongzhiTypeName.setText("支付宝姓名");
                            chongzhiTypeZh.setText("支付宝账号");
                            presenter.getGathing(type);
                        } else if (type == 3) {
                            payType =type;
                            chongzhiType.setText("银行卡");
                            chongzhiTypeName.setText("开户银行");
                            chongzhiTypeZh.setText("开户支行");
                            presenter.getGathing(type);
                            chongzhiErweima.setVisibility(View.GONE);
                            chongzhiBankNameLin.setVisibility(View.VISIBLE);
                            chongzhiBankNumberLin.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });
        chongzhiNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable) && !TextUtils.isEmpty(editable.toString())) {
                    if (editable.toString().equals("0")) {
                        chongzhiNumber.setText("0");
                        chongzhiMoney.setText("0");
                    } else {
                        Double aDouble = Double.valueOf(editable.toString());
                        presenter.getMoney(aDouble);

                    }
                } else {
                    chongzhiMoney.setHint("(输入数量自动获取金额)");
                }
            }
        });
        //上传支付凭证
        chongzhiShangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateHeader();
            }
        });
        //点击提交
        chongzhiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = LayoutInflater.from(ChongZhiActivity.this).inflate(R.layout.pop_tankuang01, null);
                EditText tanPsw = view.findViewById(R.id.tan_psw);
                EditText tanQuePsw = view.findViewById(R.id.tan_que_psw);
                TextView cancel = view.findViewById(R.id.tv_cancel);
                TextView ok = view.findViewById(R.id.tv_ok);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) ChongZhiActivity.this.getResources().getDimension(R.dimen.dp_233), true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
                popupWindow.showAtLocation(new View(ChongZhiActivity.this), Gravity.CENTER, 0, 0);
                setTransparency(ChongZhiActivity.this, 0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setTransparency(ChongZhiActivity.this, 1f);
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
                        if (TextUtils.isEmpty(tanQuePsw.getText().toString())){
                            Toast.makeText(ChongZhiActivity.this, "请输入交易密码", Toast.LENGTH_SHORT).show();
                        }else {
                            presenter.checkPassword(tanQuePsw.getText().toString());
                            popupWindow.dismiss();
                        }
                    }
                });

            }
        });

    }

    @Override
    public ChongZhiView createView() {
        return this;
    }

    @Override
    public ChongZhiPresenter createPresenter() {
        return new ChongZhiPresenter(this);
    }


    @Override
    public void loadZfbData(ZFBBean zfbBean) {
        chongzhiName.setText(zfbBean.getAliname());
        chongzhiZh.setText(zfbBean.getAlino());
        Glide.with(ChongZhiActivity.this).load(CommonResource.BASEURL_8089+zfbBean.getAliqrcode()).into(chongzhiErweimaImg);


    }

    @Override
    public void loadWxData(WeChatBean weChatBean) {
        chongzhiName.setText(weChatBean.getWxname());
        chongzhiZh.setText(weChatBean.getWxno());
        Glide.with(ChongZhiActivity.this).load(CommonResource.BASEURL_8089+weChatBean.getWxqrcode()).into(chongzhiErweimaImg);
    }

    @Override
    public void loadBank(BankCardBean bankCardBean) {
        chongzhiName.setText(bankCardBean.getBankname());
        chongzhiZh.setText(bankCardBean.getBranch());
        chongzhiBankName.setText(bankCardBean.getCardname());
        chongzhiBankNumber.setText(bankCardBean.getCardno());
    }

    @Override
    public void loadMoney(RealMoneyBean realMoneyBean) {
        if (TextUtils.isEmpty(String.valueOf(realMoneyBean.getMoney()))) {
            chongzhiMoney.setHint("(输入数量自动获取金额)");
        } else {
            chongzhiMoney.setText(realMoneyBean.getMoney() + "");

        }
    }

    @Override
    public void paycommit() {
        if (TextUtils.isEmpty(chongzhiMoney.getText().toString())){
            Toast.makeText(this, "充值金额不能为空", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(chongzhiNumber.getText().toString()))
        {
            Toast.makeText(this, "充值数量不能为空", Toast.LENGTH_SHORT).show();
        }else{
            LogUtil.e("00000--->"+Integer.valueOf(chongzhiNumber.getText().toString()));
            LogUtil.e("000002---?"+payType);
            LogUtil.e("000003--->"+SPUtil.getStringValue("weifenimg"));
            presenter.paycommit(1,Integer.valueOf(chongzhiNumber.getText().toString()),Integer.valueOf(chongzhiNumber.getText().toString()),SPUtil.getStringValue("weifenimg"),payType);
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
        Glide.with(this).load(fileUri).into(chongzhiShangchuan);

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

}
