package com.example.zefeiqukuai.forgetpassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.CountDownTimerUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordView, ForgetPasswordPresenter> implements ForgetPasswordView {
    @BindView(R.id.forget_name)
    EditText forgetName;
    @BindView(R.id.forget_sms)
    EditText forgetSms;
    @BindView(R.id.register_get_code)
    TextView registerGetCode;
    @BindView(R.id.forget_password)
    EditText forgetPassword;
    @BindView(R.id.forget_new_password)
    EditText forgetNewPassword;
    @BindView(R.id.forget_btn_forget)
    TextView forgetBtnForget;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initData() {


    }

    @Override
    public void initClick() {
        //提交
        forgetBtnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PhoneNumUtil.isMobileNO(forgetName.getText().toString()))
                {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(forgetSms.getText().toString()))
                {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(forgetPassword.getText().toString())){
                    Toast.makeText(ForgetPasswordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(forgetNewPassword.getText().toString()))
                {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else
                {
                    if (forgetPassword.getText().toString().equals(forgetNewPassword.getText().toString()))
                    {
                        presenter.loadData(forgetName.getText().toString(),forgetNewPassword.getText().toString(),forgetSms.getText().toString());
                    }else {
                        Toast.makeText(ForgetPasswordActivity.this, "请确认密码输入是否一致", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        //获取验证码
        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(forgetName.getText().toString())||!PhoneNumUtil.isMobileNO(forgetName.getText().toString()))
                {
                    presenter.getCodeNum(forgetName.getText().toString());
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public ForgetPasswordView createView() {
        return this;
    }

    @Override
    public ForgetPasswordPresenter createPresenter() {
        return new ForgetPasswordPresenter(this);
    }


    @Override
    public void getCodeSuccess() {
        registerGetCode.setBackgroundResource(R.drawable.bg_get_code);
        CountDownTimerUtil.startTimer(this, registerGetCode);
    }
}
