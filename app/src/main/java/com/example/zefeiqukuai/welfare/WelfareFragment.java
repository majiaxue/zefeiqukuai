package com.example.zefeiqukuai.welfare;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.mvp.BaseFragment;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.welfare.dazhuanpan.DaZhuanPanActivity;

import butterknife.BindView;

public class WelfareFragment extends BaseFragment<WelfareView, WelfarePresenter> implements WelfareView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.service_dazhuanpan)
    ImageView serviceDazhuanpan;
    @BindView(R.id.service_tuguan)
    ImageView serviceTuguan;
    @BindView(R.id.service_fulizhuanxiang)
    ImageView serviceFulizhuanxiang;

    @Override
    public int getLayoutId() {
        return R.layout.welfare_fragment;
    }

    @Override
    public void initData() {
        includeBack.setVisibility(View.GONE);
        includeTitle.setText("鹿粉福利");
    }

    @Override
    public void initClick() {
        serviceDazhuanpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DaZhuanPanActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public WelfareView createView() {
        return this;
    }

    @Override
    public WelfarePresenter createPresenter() {
        return new WelfarePresenter(getContext());
    }
}
