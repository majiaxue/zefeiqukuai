package com.example.zefeiqukuai.my.introduce;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;

import butterknife.BindView;


public class IntroduceActivity extends BaseActivity<IntroduceView, IntroducePresenter> implements IntroduceView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_introduce;
    }

    @Override
    public void initData() {
        includeTitle.setText("玩法介绍");

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public IntroduceView createView() {
        return this;
    }

    @Override
    public IntroducePresenter createPresenter() {
        return new IntroducePresenter(this);
    }


}
