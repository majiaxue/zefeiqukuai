package com.example.zefeiqukuai.my.updatepassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.CountDownTimerUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordView, UpdatePasswordPresenter> implements UpdatePasswordView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.updatepwd_phone)
    EditText updatepwdPhone;
    @BindView(R.id.setting_msg)
    RelativeLayout settingMsg;
    @BindView(R.id.updatepwd_code)
    EditText updatepwdCode;
    @BindView(R.id.update_get_code)
    TextView updateGetCode;
    @BindView(R.id.mymsg_name_lin)
    RelativeLayout mymsgNameLin;
    @BindView(R.id.updatepwd_new)
    EditText updatepwdNew;
    @BindView(R.id.mymsg_zh_lin)
    RelativeLayout mymsgZhLin;
    @BindView(R.id.updatepwd_new_sure)
    EditText updatepwdNewSure;
    @BindView(R.id.login_btn_login)
    TextView loginBtnLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatepassword;
    }

    @Override
    public void initData() {
        includeTitle.setText("修改登录密码");

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //获取验证码
        updateGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(updatepwdPhone.getText().toString())||!PhoneNumUtil.isMobileNO(updatepwdPhone.getText().toString()))
                {
                    presenter.getCodeNum(updatepwdPhone.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PhoneNumUtil.isMobileNO(updatepwdPhone.getText().toString()))
                {
                    Toast.makeText(UpdatePasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(updatepwdCode.getText().toString()))
                {
                    Toast.makeText(UpdatePasswordActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(updatepwdNew.getText().toString()))
                {
                    Toast.makeText(UpdatePasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(updatepwdNewSure.getText().toString()))
                {
                    Toast.makeText(UpdatePasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else {
                    presenter.updatePassword(updatepwdCode.getText().toString(),updatepwdNewSure.getText().toString());
                }



            }
        });
    }

    @Override
    public UpdatePasswordView createView() {
        return this;
    }

    @Override
    public UpdatePasswordPresenter createPresenter() {
        return new UpdatePasswordPresenter(this);
    }

    @Override
    public void getCodeSuccess() {
        updateGetCode.setBackgroundResource(R.drawable.bg_get_code);
        CountDownTimerUtil.startTimer(this, updateGetCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
