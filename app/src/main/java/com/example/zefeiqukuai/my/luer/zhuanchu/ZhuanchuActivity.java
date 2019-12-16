package com.example.zefeiqukuai.my.luer.zhuanchu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.SPUtil;
import com.example.zefeiqukuai.R;

import butterknife.BindView;

public class ZhuanchuActivity extends BaseActivity<ZhuanchuView, ZhuanchuPresenter> implements ZhuanchuView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.zhuanchu_fen)
    TextView zhuanchuFen;
    @BindView(R.id.chonghzi_type_lin)
    LinearLayout chonghziTypeLin;
    @BindView(R.id.zhuanchu_zh)
    EditText zhuanchuZh;
    @BindView(R.id.zhuanchu_name)
    EditText zhuanchuName;
    @BindView(R.id.chongzhi_money)
    EditText chongzhiMoney;
    @BindView(R.id.zhaunchu_btn)
    TextView zhaunchuBtn;
    private String weifen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuanchu;
    }

    @Override
    public void initData() {
        includeTitle.setText("转出");
        weifen = SPUtil.getStringValue("weifen");
        if (TextUtils.isEmpty(weifen)) {
            zhuanchuFen.setText("0");
        } else {
            zhuanchuFen.setText(weifen + "");
        }
        if (!TextUtils.isEmpty(zhuanchuZh.getText().toString())) {
            String s = zhuanchuZh.getText().toString();
            presenter.getNickNa(s);
        } else {
            zhuanchuZh.setHint("请输入对方账户");
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
        zhuanchuZh.addTextChangedListener(new TextWatcher() {
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
                        zhuanchuZh.setText("");
                    } else {
                        String s = editable.toString();

                        presenter.getNickNa(s);
                    }
                } else {
                    zhuanchuZh.setHint("请输入对方账户");
                }
            }
        });
        //转出数量
        chongzhiMoney.addTextChangedListener(new TextWatcher() {
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
                        chongzhiMoney.setText("");
                    } else {
                        Integer integer = (Integer.valueOf(editable.toString()));
                        if (integer%10==0)
                        {
                            Double numberWei = Double.valueOf(weifen);
                            if (integer > numberWei) {
                                chongzhiMoney.setText(weifen.split("\\.")[0] + "");
                            }
                        }else{
                            Toast.makeText(ZhuanchuActivity.this, "输入的数量必须是10的倍数", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                }
            }
        });
        //点金提交
        zhaunchuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = LayoutInflater.from(ZhuanchuActivity.this).inflate(R.layout.pop_tankuang01, null);
                EditText tanPsw = view.findViewById(R.id.tan_psw);
                EditText tanQuePsw = view.findViewById(R.id.tan_que_psw);
                TextView cancel = view.findViewById(R.id.tv_cancel);
                TextView ok = view.findViewById(R.id.tv_ok);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) ZhuanchuActivity.this.getResources().getDimension(R.dimen.dp_233), true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
                popupWindow.showAtLocation(new View(ZhuanchuActivity.this), Gravity.CENTER, 0, 0);
                setTransparency(ZhuanchuActivity.this, 0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setTransparency(ZhuanchuActivity.this, 1f);
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
                            Toast.makeText(ZhuanchuActivity.this, "请输入交易密码", Toast.LENGTH_SHORT).show();
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
    public ZhuanchuView createView() {
        return this;
    }

    @Override
    public ZhuanchuPresenter createPresenter() {
        return new ZhuanchuPresenter(this);
    }


    @Override
    public void loadNickName(String nick_name) {
        if (TextUtils.isEmpty(nick_name)) {
            zhuanchuName.setHint("请输入对方昵称");
        } else {
            zhuanchuName.setText(nick_name);
        }
    }

    @Override
    public void paycommit() {
        if (TextUtils.isEmpty(zhuanchuName.getText().toString())){
            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(zhuanchuZh.getText().toString())){
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(chongzhiMoney.getText().toString()))
        {
            Toast.makeText(this, "转账数量不能为空", Toast.LENGTH_SHORT).show();
        }else {
            presenter.transferCommit(1,Integer.valueOf(chongzhiMoney.getText().toString()),zhuanchuZh.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(weifen)) {
            zhuanchuFen.setText("0");
        } else {
            zhuanchuFen.setText(weifen + "");
        }
    }
}
