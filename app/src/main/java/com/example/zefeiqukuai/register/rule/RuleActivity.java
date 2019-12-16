package com.example.zefeiqukuai.register.rule;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.RuleBean;

import butterknife.BindView;

public class RuleActivity extends BaseActivity<RuleView, RulePresenter> implements RuleView {

    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.rule_title)
    TextView ruleTitle;
    @BindView(R.id.rule_content)
    TextView ruleContent;

    @Override
    public int getLayoutId() {
        return R.layout.rule;
    }

    @Override
    public void initData() {
        includeTitle.setText("用户协议" );
        presenter.getRegRule();

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
    public RuleView createView() {
        return this;
    }

    @Override
    public RulePresenter createPresenter() {
        return new RulePresenter(this);
    }

    @Override
    public void getSuccess(RuleBean ruleBean) {
          ruleTitle.setText(ruleBean.getTitle()+"");
          ruleContent.setText(ruleBean.getContent()+"");

    }


}
