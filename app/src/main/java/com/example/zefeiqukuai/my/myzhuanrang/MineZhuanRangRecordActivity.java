package com.example.zefeiqukuai.my.myzhuanrang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.base.adapter.BaseVPAdapter;
import com.example.base.mvp.BaseFragmentActivity;
import com.example.zefeiqukuai.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MineZhuanRangRecordActivity extends BaseFragmentActivity<MineZhuanRangRecordView, MineZhuanRangRecordPresenter> implements MineZhuanRangRecordView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.zhuanrang_tab)
    TabLayout zhuanrangTab;
    @BindView(R.id.zhuanrang_viewpager)
    ViewPager zhuanrangViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuanrang;
    }

    @Override
    public void initData() {
        includeTitle.setText("转让记录");
        presenter.initTabLayout(zhuanrangTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        zhuanrangViewpager.setOffscreenPageLimit(3);
        //tablayout联动viewpager
        zhuanrangTab.setupWithViewPager(zhuanrangViewpager);

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
    public MineZhuanRangRecordView createView() {
        return this;
    }

    @Override
    public MineZhuanRangRecordPresenter createPresenter() {
        return new MineZhuanRangRecordPresenter(this);
    }



    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        zhuanrangViewpager.setAdapter(baseVPAdapter);
        zhuanrangTab.setTabsFromPagerAdapter(baseVPAdapter);
    }
}
