package com.example.zefeiqukuai.my.minelingyangjilu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.base.adapter.BaseVPAdapter;
import com.example.base.mvp.BaseActivity;
import com.example.base.mvp.BaseFragmentActivity;
import com.example.zefeiqukuai.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MineLingYangRecordActivity extends BaseFragmentActivity<MineLingYangRecordView, MineLingYangRecordPresenter> implements MineLingYangRecordView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.lingyang_tab)
    TabLayout lingyangTab;
    @BindView(R.id.lingyang_viewpager)
    ViewPager lingyangViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lingyang;
    }

    @Override
    public void initData() {
        includeTitle.setText("领养记录");
        presenter.initTabLayout(lingyangTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        lingyangViewpager.setOffscreenPageLimit(2);
        //tablayout联动viewpager
        lingyangTab.setupWithViewPager(lingyangViewpager);
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
    public MineLingYangRecordView createView() {
        return this;
    }

    @Override
    public MineLingYangRecordPresenter createPresenter() {
        return new MineLingYangRecordPresenter(this);
    }

    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        lingyangViewpager.setAdapter(baseVPAdapter);
        lingyangTab.setTabsFromPagerAdapter(baseVPAdapter);
    }
}
