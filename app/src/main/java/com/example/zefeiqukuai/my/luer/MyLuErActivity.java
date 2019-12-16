package com.example.zefeiqukuai.my.luer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.PopUtils;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SpaceItemDecoration;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.JiaoYiBean;
import com.example.zefeiqukuai.my.luer.adapter.LuErListAdapter;
import com.example.zefeiqukuai.my.luer.chongzhi.ChongZhiActivity;
import com.example.zefeiqukuai.my.luer.zhuanchu.ZhuanchuActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLuErActivity extends BaseActivity<MyLuErView, MyLuErPresenter> implements MyLuErView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.luer_count)
    TextView luerCount;
    @BindView(R.id.lu_chongzhi_btn)
    TextView luChongzhiBtn;
    @BindView(R.id.luer_recy)
    RecyclerView luerRecy;
    @BindView(R.id.samrt_data)
    SmartRefreshLayout smartData;
    @BindView(R.id.luer_huoqu)
    LinearLayout luerHuoqu;
    private JiaoYiBean bean = new JiaoYiBean();
    private int page = 1;
    private int pagesize = 20;

    @Override
    public int getLayoutId() {
        return R.layout.activity_luer;
    }

    @Override
    public void initData() {
        ProcessDialogUtil.showProcessDialog(MyLuErActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        luerRecy.setLayoutManager(layoutManager);
        luerRecy.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) this.getResources().getDimension(R.dimen.dp_8)));
        smartData.setRefreshHeader(new MaterialHeader(this));
        smartData.setRefreshFooter(new ClassicsFooter(this));
        includeTitle.setText("鹿耳");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("转出");


    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //立即充值
        luChongzhiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChongZhiActivity.class);
                startActivity(intent);
            }
        });
        //转出
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ZhuanchuActivity.class);
                startActivity(intent);
            }
        });
        //设置上拉刷新下拉加载
        smartData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getJiFen(1, page, pagesize);

            }
        });
        smartData.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getJiFen(1, page, pagesize);
            }
        });
        luerHuoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.parmsPop(MyLuErActivity.this);
            }
        });

    }

    @Override
    public MyLuErView createView() {
        return this;
    }

    @Override
    public MyLuErPresenter createPresenter() {
        return new MyLuErPresenter(this);
    }

    @Override
    public void loadUI(LuErListAdapter adapter) {
        luerRecy.setAdapter(adapter);
    }

    @Override
    public void loadData(JiaoYiBean bean) {
        this.bean = bean;
        luerCount.setText(bean.getTotal_account() == null ? "0" : ArithUtil.exact( bean.getTotal_account(),2)+"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getJiFen(1, page, pagesize);
    }

    @Override
    public void refresh() {
        smartData.finishLoadMore();
        smartData.finishRefresh();
    }
}
