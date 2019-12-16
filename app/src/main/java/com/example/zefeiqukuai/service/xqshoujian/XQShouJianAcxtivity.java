package com.example.zefeiqukuai.service.xqshoujian;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;

import butterknife.BindView;

@Route(path = "/zefeiqukuai/XQShouJianAcxtivity")
//收件箱详情
public class XQShouJianAcxtivity extends BaseActivity<XQShouJIanView, XQShouJianPresenter> implements XQShouJIanView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.in_box_detail_content)
    TextView inBoxDetailContent;
    @BindView(R.id.in_box_detail_time)
    TextView inBoxDetailTime;
    @Autowired(name = "content")
    String content;
    @Autowired(name = "time")
    String time;
    @Override
    public int getLayoutId() {
        return R.layout.inbox_detail_layout;
    }

    @Override
    public void initData() {
        includeTitle.setText("系统消息");
        ARouter.getInstance().inject(this);
        inBoxDetailContent.setText("\t\t\t\t"+content);
        inBoxDetailTime.setText(time);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public XQShouJIanView createView() {
        return this;
    }

    @Override
    public XQShouJianPresenter createPresenter() {
        return new XQShouJianPresenter(this);
    }
}
