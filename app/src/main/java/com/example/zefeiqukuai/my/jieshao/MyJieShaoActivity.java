package com.example.zefeiqukuai.my.jieshao;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;

public class MyJieShaoActivity extends BaseActivity<MyJieShaoView, MyJieShaoPresenter> implements MyJieShaoView {
    @Override
    public int getLayoutId() {
        return R.layout.jieshao;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public MyJieShaoView createView() {
        return this;
    }

    @Override
    public MyJieShaoPresenter createPresenter() {
        return new MyJieShaoPresenter(this);
    }
}
