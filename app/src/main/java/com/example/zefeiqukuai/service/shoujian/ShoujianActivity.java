package com.example.zefeiqukuai.service.shoujian;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.SpaceItemDecoration;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.service.adapter.ShouJianAdapter;

import butterknife.BindView;


public class ShoujianActivity extends BaseActivity<ShoujianView, ShoujianPresenter> implements ShoujianView {


    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.in_box_recy)
    RecyclerView inBoxRecy;

    @Override
    public int getLayoutId() {
        return R.layout.in_box_layout;
    }

    @Override
    public void initData() {
        includeTitle.setText("收件箱");
        presenter.getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        inBoxRecy.setLayoutManager(layoutManager);
        inBoxRecy.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) this.getResources().getDimension(R.dimen.dp_8)));
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
    public ShoujianView createView() {
        return this;
    }

    @Override
    public ShoujianPresenter createPresenter() {
        return new ShoujianPresenter(this);
    }

    @Override
    public void loadRv(ShouJianAdapter adapter) {
        inBoxRecy.setAdapter(adapter);
    }
}
