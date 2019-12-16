package com.example.zefeiqukuai.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.CountDownTimerUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.register.rule.RuleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.reg_id)
    EditText regId;
    @BindView(R.id.reg_nick_name)
    EditText regNickName;
    @BindView(R.id.reg_phone)
    EditText regPhone;
    @BindView(R.id.reg_by_phone)
    EditText regByPhone;
    @BindView(R.id.reg_tui_nick_name)
    EditText regTuiNickName;
    @BindView(R.id.reg_tui_zhanghao)
    EditText regTuiZhanghao;
    @BindView(R.id.reg_password)
    EditText regPassword;
    @BindView(R.id.reg_sure_password)
    TextView regSurePassword;
    @BindView(R.id.reg_pay_password)
    EditText regPayPassword;
    @BindView(R.id.reg_sure_pay_password)
    EditText regSurePayPassword;
    @BindView(R.id.reg_sms)
    EditText regSms;
    @BindView(R.id.reg_btn)
    TextView regBtn;
    @BindView(R.id.reg_tongyi)
    ImageView regTongyi;
    @BindView(R.id.register_get_code)
    TextView registerGetCode;
    @BindView(R.id.reg_rule)
    TextView registerRule;
    @BindView(R.id.reg_tui_nick_name_code)
    EditText regTuiNickNameCode;


    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //复选框按钮
        regTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.check();
            }
        });
        //阅读协议
        registerRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RuleActivity.class);
                startActivity(intent);


            }
        });
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取验证码
        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(regPhone.getText().toString()) || !PhoneNumUtil.isMobileNO(regPhone.getText().toString())) {
                    presenter.getCodeNum(regPhone.getText().toString());
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册按钮
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!TextUtils.isEmpty(regId.getText().toString()) || regId.getText().length() < 6) {
//                    Toast.makeText(RegisterActivity.this, "请输入正确的ID", Toast.LENGTH_SHORT).show();
//                } else
                if (TextUtils.isEmpty(regNickName.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(regPhone.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(regByPhone.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regTuiNickName.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入推荐人昵称", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regTuiZhanghao.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入推荐人账号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regSurePassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regPayPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regSurePayPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regSms.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regTuiNickNameCode.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "请输入推荐码", Toast.LENGTH_SHORT).show();
                }else {
                    presenter.getReg(regTuiZhanghao.getText().toString(), regPhone.getText().toString(), regPassword.getText().toString(), regPayPassword.getText().toString(), regNickName.getText().toString(), regSms.getText().toString(), regByPhone.getText().toString());
                }
            }
        });

    }

    @Override
    public RegisterView createView() {
        return this;
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void readed() {
        regTongyi.setImageResource(R.drawable.zhuce_tongyi);
    }

    @Override
    public void getCodeSuccess() {
        registerGetCode.setEnabled(false);
        registerGetCode.setBackgroundResource(R.drawable.bg_get_code);
        CountDownTimerUtil.startTimer(this, registerGetCode);
    }

    @Override
    public void noRead() {
        regTongyi.setImageResource(R.drawable.zhuce_weixuan);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
