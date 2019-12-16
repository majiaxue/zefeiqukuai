package com.example.zefeiqukuai.my.myrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ProcessDialogUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.MyRecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecordActivity extends BaseActivity<MyRecordView, MyRecordPresenter> implements MyRecordView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.elk_market_recy)
    RecyclerView elkMarketRecy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mypet;
    }

    @Override
    public void initData() {
        ProcessDialogUtil.showProcessDialog(MyRecordActivity.this);
        includeTitle.setText("兑换记录");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyRecordActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        elkMarketRecy.setLayoutManager(layoutManager);
        presenter.loadData();

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
    public MyRecordView createView() {
        return this;
    }

    @Override
    public MyRecordPresenter createPresenter() {
        return new MyRecordPresenter(this);
    }

    @Override
    public void loadData(MyRecordAdapter myRecordAdapter) {
        elkMarketRecy.setAdapter(myRecordAdapter);
    }


}
