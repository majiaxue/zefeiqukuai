package com.example.zefeiqukuai.my.myzhuanrang.fragment.stay;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.StaySaleAdapter;
import com.example.zefeiqukuai.bean.StaySaleBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;

public class StayPresenter extends BasePresenter<StayView> {
    private int nextPage = 1;
    private StaySaleBean staySaleBean;
    ArrayList<StaySaleBean.DataBean> dataBeans = new ArrayList<>();
    private StaySaleAdapter staySaleAdapter;

    public StayPresenter(Context context) {
        super(context);
    }

    public void loadMyRecord(SmartRefreshLayout myIntegralSmart) {
        //设置上拉刷新下拉加载
        myIntegralSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                nextPage = 1;
                getStay(nextPage, 20);

            }
        });
        myIntegralSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                nextPage++;
                getStay(nextPage, 20);
            }
        });
    }

    //获取代售出数据
    public void getStay(int pageindex, int pagesize) {

        Map build = MapUtil.getInstance().addParms("pageindex", pageindex).addParms("pagesize", pagesize).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.SALE, build, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("转让记录代售出数据" + result);
                if (getView() != null) {
                    getView().refreshSuccesss();
                }
                if (result != null) {
                    if (pageindex == 1) {
                        dataBeans.clear();
                    }
                    staySaleBean = JSON.parseObject(result, StaySaleBean.class);
                    dataBeans.addAll(staySaleBean.getData());
                    if (staySaleAdapter == null) {
                        staySaleAdapter = new StaySaleAdapter(mContext, dataBeans, R.layout.content_gong);
                        if (getView() != null) {
                            getView().loadStaySaleAdapter(staySaleAdapter);
                        }
                    } else {
                        staySaleAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }

    @Override
    protected void onViewDestroy() {

    }
}
