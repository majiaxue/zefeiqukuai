package com.example.zefeiqukuai.my.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.LogUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.VipBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyVipActivity extends BaseActivity<MyVipView, MyVipPresenter> implements MyVipView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.huiyuan_text)
    ImageView huiyuanText;
    @BindView(R.id.vip_putong)
    TextView vipPutong;
    @BindView(R.id.vip_putong_content)
    TextView vipPutongContent;
    @BindView(R.id.vip_vip)
    TextView vipVip;
    @BindView(R.id.vip_vip_content)
    TextView vipVipContent;
    @BindView(R.id.vip_name1)
    TextView vipName1;
    @BindView(R.id.vip_tui_shouyi)
    TextView vipTuiShouyi;
    @BindView(R.id.vip_tui_dai)
    TextView vipTuiDai;
    @BindView(R.id.vip_tui_content)
    TextView vipTuiContent;
    @BindView(R.id.vip_name2)
    TextView vipName2;
    @BindView(R.id.vip_fu_shouyi)
    TextView vipFuShouyi;
    @BindView(R.id.fu_vip_dai)
    TextView fuVipDai;
    @BindView(R.id.vip_fu_content)
    TextView vipFuContent;
    @BindView(R.id.vip_name3)
    TextView vipName3;
    @BindView(R.id.vip_he_shouyi)
    TextView vipHeShouyi;
    @BindView(R.id.vip_he_dai)
    TextView vipHeDai;
    @BindView(R.id.vip_he_content)
    TextView vipHeContent;

    @Override
    public int getLayoutId() {
        return R.layout.avtivity_vip;
    }

    @Override
    public void initData() {
        includeTitle.setText("会员中心");
        presenter.getRankList();

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
    public MyVipView createView() {
        return this;
    }

    @Override
    public MyVipPresenter createPresenter() {
        return new MyVipPresenter(this);
    }


    @Override
    public void loadRank(List<VipBean> vipBeans) {
        //普通
        vipPutong.setText(vipBeans.get(1).getName());
        vipPutongContent.setText(vipBeans.get(1).getRemark());
        //vip会员
        vipVip.setText(vipBeans.get(2).getName());
        vipVipContent.setText(vipBeans.get(2).getRemark());
        //推广
        vipName1.setText(vipBeans.get(3).getName());
        vipTuiShouyi.setText(vipBeans.get(3).getRate()+"%收益");
        vipTuiDai.setText(vipBeans.get(3).getFloor()+"代");
        vipTuiContent.setText(vipBeans.get(3).getRemark());
        //服务商
        vipName2.setText(vipBeans.get(4).getName());
        vipFuShouyi.setText(vipBeans.get(4).getRate()+"%收益");
        fuVipDai.setText(vipBeans.get(4).getFloor()+"代");
        vipFuContent.setText(vipBeans.get(4).getRemark());
        //合作商
        vipName3.setText(vipBeans.get(5).getName());
        vipHeShouyi.setText(vipBeans.get(5).getRate()+"%收益");
        vipHeDai.setText(vipBeans.get(5).getFloor()+"代");
        vipHeContent.setText(vipBeans.get(5).getRemark());
    }
}
