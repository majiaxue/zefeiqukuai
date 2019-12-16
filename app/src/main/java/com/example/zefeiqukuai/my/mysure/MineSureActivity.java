package com.example.zefeiqukuai.my.mysure;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.IDCardUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineSureActivity extends BaseActivity<MineSureView, MineSurePresenter> implements MineSureView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.minesure_card)
    EditText minesureCard;
    @BindView(R.id.minesure_name)
    EditText minesureName;
    @BindView(R.id.login_btn_login)
    TextView loginBtnLogin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_minesure;
    }
    @Override
    public void initData() {
        includeTitle.setText("实名认证");
    }

    @Override
    public void initClick() {
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(minesureName.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"请输入姓名",Toast.LENGTH_LONG).show();
                }else if (!IDCardUtil.testing(minesureCard.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"身份证输入格式有误",Toast.LENGTH_LONG).show();
                }else {
                   presenter.getLoad(minesureCard.getText().toString(),minesureName.getText().toString());

                }
            }
        });
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public MineSureView createView() {
        return this;
    }

    @Override
    public MineSurePresenter createPresenter() {
        return new MineSurePresenter(this);
    }


}
