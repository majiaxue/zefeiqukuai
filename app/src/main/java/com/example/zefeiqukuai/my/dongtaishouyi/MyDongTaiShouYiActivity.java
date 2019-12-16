package com.example.zefeiqukuai.my.dongtaishouyi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SpaceItemDecoration;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.JiaoYiBean;
import com.example.zefeiqukuai.my.luer.adapter.LuErListAdapter;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class MyDongTaiShouYiActivity extends BaseActivity<MyDongTaiShouYiView, MyDongTaiShouYiPresenter> implements MyDongTaiShouYiView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.dongtaishouyi_count)
    TextView dongtaishouyiCount;
    @BindView(R.id.dongtaishouyi_recy)
    RecyclerView dongtaishouyiRecy;
    @BindView(R.id.smart_data)
    SmartRefreshLayout smartData;
    private int page=1;
    private int pagesize=20;
    private JiaoYiBean bean=new JiaoYiBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_mydongtaishopuyi;
    }

    @Override
    public void initData() {
        includeTitle.setText("动态收益");
        presenter.getJiFen(2,page,pagesize);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dongtaishouyiRecy.setLayoutManager(layoutManager);
        dongtaishouyiRecy.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) this.getResources().getDimension(R.dimen.dp_8)));
        smartData.setRefreshHeader(new MaterialHeader(this));
        smartData.setRefreshFooter(new ClassicsFooter(this));
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置上拉刷新下拉加载
        smartData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                presenter.getJiFen(2, page,pagesize);

            }
        });
        smartData.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getJiFen(2, page,pagesize);
            }
        });

    }

    @Override
    public MyDongTaiShouYiView createView() {
        return this;
    }

    @Override
    public MyDongTaiShouYiPresenter createPresenter() {
        return new MyDongTaiShouYiPresenter(this);
    }

    @Override
    public void loadUI(LuErListAdapter adapter) {
        dongtaishouyiRecy.setAdapter(adapter);
    }

    @Override
    public void loadData(JiaoYiBean bean) {
        this.bean=bean;
        dongtaishouyiCount.setText(bean.getTotal_account() == null ? "0" : ArithUtil.exact(bean.getTotal_account(),2) +"");
        LogUtil.e("是我的动态收益-----"+bean.getTotal_account());
    }

    @Override
    public void refresh() {
        smartData.finishLoadMore();
        smartData.finishRefresh();
    }
}
