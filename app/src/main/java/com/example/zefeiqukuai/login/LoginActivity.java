package com.example.zefeiqukuai.login;

import android.content.Intent;

import android.content.res.Configuration;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.mvp.BaseActivity;
import com.example.base.utils.LogUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.base.utils.PopUtils;
import com.example.base.utils.net_change_util.OnGetListener;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.forgetpassword.ForgetPasswordActivity;
import com.example.zefeiqukuai.register.RegisterActivity;

import java.util.Locale;

import butterknife.BindView;

@Route(path = "/zefeiqukuai/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_text)
    TextView loginText;
    @BindView(R.id.login_xiayige)
    ImageView loginXiayige;
    @BindView(R.id.login_btn_login)
    TextView loginBtnLogin;
    @BindView(R.id.login_forget_password)
    TextView loginForgetPassword;
    @BindView(R.id.login_reg_btn)
    TextView loginRegBtn;
    @BindView(R.id.login_eye)
    ImageView loginEye;
    @Autowired(name = "phone")
    String phone;
    @Autowired(name = "password")
    String password;
    @BindView(R.id.xiala_lin)
    RelativeLayout xialaLin;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        LogUtil.e("传值+" + phone + password);
        loginName.setText(phone);
        loginPassword.setText(password);
    }

    @Override
    public void initClick() {
        //忘记密码
        loginForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        //登录点击事件
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PhoneNumUtil.isMobileNO(loginName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(loginPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.getLogin(loginName.getText().toString(), loginPassword.getText().toString());
                }

            }
        });
        //注册点击事件
        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        //隐藏显示密码
        loginEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHideFirst == true) {
                    loginEye.setImageResource(R.drawable.icon_xianshi);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    loginPassword.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    loginEye.setImageResource(R.drawable.icon_yincang);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    loginPassword.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = loginPassword.getText().toString().length();
                loginPassword.setSelection(index);
            }
        });
        xialaLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.getLangure(LoginActivity.this, new OnGetListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 2) {
                            loginText.setText("English");
//                            Locale.setDefault(Locale.ENGLISH);
//                            Configuration configuration = getBaseContext().getResources().getConfiguration();
//                            configuration.locale = Locale.ENGLISH;
//                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
//                            recreate();

                        } else {
                            loginText.setText("中文简体");
//                            Locale.setDefault(Locale.CHINESE);
//                            Configuration configuration = getBaseContext().getResources().getConfiguration();
//                            configuration.locale = Locale.CHINESE;
//                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
//                            recreate();

                        }

                    }
                });
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.getLangure(LoginActivity.this, new OnGetListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 2) {
//                            Locale.setDefault(Locale.ENGLISH);
//                            Configuration configuration = getBaseContext().getResources().getConfiguration();
//                            configuration.locale = Locale.ENGLISH;
//                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
//                            recreate();
                            loginText.setText("English");
                        } else {
                            loginText.setText("中文简体");
//                            Locale.setDefault(Locale.CHINESE);
//                            Configuration configuration = getBaseContext().getResources().getConfiguration();
//                            configuration.locale = Locale.CHINESE;
//                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
//                            recreate();
                        }
                    }
                });
            }
        });

    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

}
