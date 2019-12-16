package com.example.zefeiqukuai.elkmarket;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BaseFragment;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MarqueeView;
import com.example.base.utils.ProcessDialogUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.PetListAdapter;
import com.example.zefeiqukuai.bean.EventBusBean;
import com.example.zefeiqukuai.bean.WebSocketBean;
import com.example.zefeiqukuai.bean.WebSocketBean2;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ElkMarketFragment extends BaseFragment<ElkMarketView, ElkMarketPresenter> implements ElkMarketView {

    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.home_marquee)
    MarqueeView homeMarquee;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.elk_market_recy)
    RecyclerView elkMarketRecy;
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.elk_market_banner)
    XBanner elkMarketBanner;
    WebSocketBean webSocketBean = new WebSocketBean();
    @BindView(R.id.smart_data)
    SmartRefreshLayout smartData;

    @Override
    public int getLayoutId() {
        return R.layout.elkmarket_fragment;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        includeBack.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        elkMarketRecy.setLayoutManager(layoutManager);
        includeTitle.setText("麋鹿市场");
        smartData.setRefreshHeader(new MaterialHeader(getContext()));
        smartData.setRefreshFooter(new ClassicsFooter(getContext()));
        List<String> data = new ArrayList<>();
        data.add("恭喜王**兑换宠物成功,扣除0.25动态收益...");
        data.add("恭喜赵**兑换宠物成功,扣除1.5动态收益...");
        data.add("恭喜李**兑换宠物成功,扣除2.2动态收益...");
        data.add("恭喜孙**兑换宠物成功,扣除10.5动态收益...");
        data.add("恭喜马**兑换宠物成功,扣除8.0动态收益...");
        data.add("恭喜魏**兑换宠物成功,扣除6.7动态收益...");
        data.add("恭喜张**兑换宠物成功,扣除10.2动态收益...");
        data.add("恭喜白**兑换宠物成功,扣除1.25动态收益...");
        presenter.setViewSingleLine(data);
        presenter.getBanner(elkMarketBanner);
        presenter.getPetList();
        //  presenter.getNotice("2019-12-1 00:00:00");
    }

    @Override
    public void initClick() {
        //设置上拉刷新下拉加载
        smartData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                presenter.getPetList();

            }
        });
        smartData.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getPetList();
            }
        });

    }
    @Override
    public void refresh() {
        smartData.finishLoadMore();
        smartData.finishRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getText(EventBusBean text) {
        LogUtil.e("是否接收到消息" + text);
        String type = text.getType();
        if ("beforeRobNotice".equals(type)) {
//            WebSocketBean webSocketBean = JSON.parseObject(text, WebSocketBean.class);
            ProcessDialogUtil.showProcessDialog(getContext());
            presenter.getPetList();

        } else if ("robNotice".equals(type)) {
//            WebSocketBean webSocketBean = JSON.parseObject(text, WebSocketBean.class);
//            ProcessDialogUtil.showProcessDialog(getContext());
//            presenter.getPetList();
        } else if ("convertNotice".equals(type)) {
//            WebSocketBean2 webSocketBean2 = JSON.parseObject(text, WebSocketBean2.class);
            WebSocketBean2 parseObject = JSON.parseObject(text.getClient_id(), WebSocketBean2.class);
            List<String> data = parseObject.getData();
            presenter.setViewSingleLine(data);
        } else if (CommonResource.QIANGGOU.equals(type)) {
            presenter.getResult(text.getClient_id());
        }
    }

    @Override
    public ElkMarketView createView() {
        return this;
    }

    @Override
    public ElkMarketPresenter createPresenter() {
        return new ElkMarketPresenter(getContext());
    }

    @Override
    public void lodeMarquee(List<View> views) {
        homeMarquee.setViews(views);
    }

    @Override
    public void loadUI(PetListAdapter petListAdapter) {
        elkMarketRecy.setAdapter(petListAdapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见
            LogUtil.e("HomeFragment" + "hidden:" + hidden);
            homeMarquee.stopFlipping();
            elkMarketBanner.stopAutoPlay();

        } else {
            //可见
            LogUtil.e("HomeFragment" + "hidden:" + hidden);
            homeMarquee.startFlipping();
            elkMarketBanner.startAutoPlay();

        }
    }
}
