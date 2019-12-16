package com.example.zefeiqukuai.my.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ProcessDialogUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.MyAdapter;
import com.example.zefeiqukuai.my.myrecord.MyRecordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPetActivity extends BaseActivity<MyPetView, MyPetPresenter> implements MyPetView {
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
        ProcessDialogUtil.showProcessDialog(MyPetActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyPetActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        elkMarketRecy.setLayoutManager(layoutManager);
        includeTitle.setText("兑换宠物");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("兑换记录");
        presenter.getPet();


    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //兑换记录
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPetActivity.this, MyRecordActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public MyPetView createView() {
        return this;
    }

    @Override
    public MyPetPresenter createPresenter() {
        return new MyPetPresenter(this);
    }


    @Override
    public void loadData(MyAdapter myAdapter) {
        elkMarketRecy.setAdapter(myAdapter);
    }
}
