package com.example.zefeiqukuai.my.mysafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.my.myzhuanrang.MineZhuanRangRecordActivity;
import com.example.zefeiqukuai.my.updatepassword.UpdatePasswordActivity;
import com.example.zefeiqukuai.my.updatepaypwd.UpdatePasswordPayActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineSafeActivity extends BaseActivity<MineSafeView, MineSafePresenter> implements MineSafeView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.mysafe_update_password)
    RelativeLayout mysafeUpdatePassword;
    @BindView(R.id.mysafe_update_twopassword)
    RelativeLayout mysafeUpdateTwopassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mysafe;
    }

    @Override
    public void initData() {
        includeTitle.setText("安全中心");

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mysafeUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdatePasswordActivity.class);
                startActivity(intent);
            }
        });
        mysafeUpdateTwopassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdatePasswordPayActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public MineSafeView createView() {
        return this;
    }

    @Override
    public MineSafePresenter createPresenter() {
        return null;
    }


}
