package com.example.zefeiqukuai.welfare.dazhuanpan;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.PopUtils;
import com.example.view.LuckyView;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.WheelRecordAdapter;
import com.example.zefeiqukuai.bean.ChouJiangResultBean;
import com.example.zefeiqukuai.bean.DaZhuanPanBean;
import com.example.zefeiqukuai.bean.UserAcountBean;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import butterknife.BindView;

public class DaZhuanPanActivity extends BaseActivity<DaZhuanPanView, DaZhuanPanPresenter> implements DaZhuanPanView {
    @BindView(R.id.dazhuanpan_shouyi)
    TextView dazhuanpanShouyi;
    @BindView(R.id.dazhuanpan_luck)
    LuckyView dazhuanpanLuck;
    @BindView(R.id.dazhuanpan_rule)
    TextView dazhuanpanRule;
    @BindView(R.id.dazhuanpan_recy)
    RecyclerView dazhuanpanRecy;
    @BindView(R.id.luck_bg)
    RelativeLayout luckBg;
    @BindView(R.id.dazhuanpan_back)
    ImageView dazhuanpanBack;
    private DaZhuanPanBean daZhuanPanBean;
    private AnimationDrawable animation;
    ChouJiangResultBean resultBean;
    //private String[] mLuckyPrizes =new String[8];
    private String[] mPrizeDescription = new String[8];

    @Override
    public int getLayoutId() {
        return R.layout.activity_dazhuanpan;
    }

    @Override
    public void initData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dazhuanpanRecy.setLayoutManager(linearLayoutManager);
        presenter.getZhuan();
        luckBg.setBackgroundResource(R.drawable.animation_drawable1);
        animation = (AnimationDrawable) luckBg.getBackground();
        animation.start();
        presenter.getWheelRecord();



    }

    @Override
    public void initClick() {
        dazhuanpanRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.zhuanPop(DaZhuanPanActivity.this, daZhuanPanBean.getEveryday(), daZhuanPanBean.getCost());
            }
        });
        dazhuanpanBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dazhuanpanLuck.setLuckAnimationEndListener(new LuckyView.OnLuckAnimationEndListener() {
            @Override
            public void onLuckAnimationEnd(int pos, String msg) {
                PopUtils.createPopResult(DaZhuanPanActivity.this,resultBean.getText());
                presenter.getWheelRecord();
            }

            @Override
            public void onClick() {
                presenter.getWheelDo();

            }
        });

    }

    @Override
    public DaZhuanPanView createView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserAcount();
    }

    @Override
    public DaZhuanPanPresenter createPresenter() {
        return new DaZhuanPanPresenter(this);
    }

    @Override
    public void loadRv(WheelRecordAdapter wheelRecordAdapter) {
        dazhuanpanRecy.setAdapter(wheelRecordAdapter);
    }

    @Override
    public void loadData(DaZhuanPanBean daZhuanPanBean) {
        this.daZhuanPanBean = daZhuanPanBean;
        mPrizeDescription[0] = daZhuanPanBean.getLevel1().getText();
        mPrizeDescription[1] = daZhuanPanBean.getLevel2().getText();
        mPrizeDescription[2] = daZhuanPanBean.getLevel3().getText();
        mPrizeDescription[3] = daZhuanPanBean.getLevel4().getText();
        mPrizeDescription[4] = daZhuanPanBean.getLevel5().getText();
        mPrizeDescription[5] = daZhuanPanBean.getLevel6().getText();
        mPrizeDescription[6] = daZhuanPanBean.getLevel7().getText();
        mPrizeDescription[7] = daZhuanPanBean.getLevel8().getText();
        //mPrizeDescription[8] =daZhuanPanBean.getLevel8().getText();
        dazhuanpanLuck.setPrizeDescription(mPrizeDescription);
//        mLuckyPrizes[0]= CommonResource.BASEURL_8089+daZhuanPanBean.getLevel1().getImage();
//        mLuckyPrizes[1]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel2().getImage();
//        mLuckyPrizes[2]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel3().getImage();
//        mLuckyPrizes[3]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel4().getImage();
//        mLuckyPrizes[4]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel5().getImage();
//        mLuckyPrizes[5]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel6().getImage();
//        mLuckyPrizes[6]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel7().getImage();
//        mLuckyPrizes[7]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel8().getImage();
        // mLuckyPrizes[8]=CommonResource.BASEURL_8089+daZhuanPanBean.getLevel8().getImage();
        //LogUtil.e("------------------->是不是空的2"+mLuckyPrizes[0]);
        // dazhuanpanLuck.setLuckyPrizes(mLuckyPrizes);

    }

    @Override
    public void loadResult(ChouJiangResultBean resultBean) {
        this.resultBean =resultBean;
        for (int i = 0; i < mPrizeDescription.length; i++) {
            if (resultBean.getText().equals(mPrizeDescription[i])) {
                dazhuanpanLuck.startAnim(i);
                break;
            }
        }
    }

    @Override
    public void loadUserAcount(UserAcountBean userAcountBean) {
        dazhuanpanShouyi.setText("当前账户剩余动态收益："+ ArithUtil.exact(userAcountBean.getIncome(), 2) + "");
    }
}
