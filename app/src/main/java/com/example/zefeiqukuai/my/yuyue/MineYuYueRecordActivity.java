package com.example.zefeiqukuai.my.yuyue;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.YuYueAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineYuYueRecordActivity extends BaseActivity<MineYuYueRecordView, MineYuYueRecordPresenter> implements MineYuYueRecordView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.yuyue_recy)
    RecyclerView yuyueRecy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yuyue;
    }

    @Override
    public void initData() {
        includeTitle.setText("预约记录");
        presenter.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yuyueRecy.setLayoutManager(linearLayoutManager);

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
    public MineYuYueRecordView createView() {
        return this;
    }

    @Override
    public MineYuYueRecordPresenter createPresenter()  {
        return new MineYuYueRecordPresenter(this);
    }

    @Override
    public void loadAdapter(YuYueAdapter yuYueAdapter) {
        yuyueRecy.setAdapter(yuYueAdapter);
    }
}
