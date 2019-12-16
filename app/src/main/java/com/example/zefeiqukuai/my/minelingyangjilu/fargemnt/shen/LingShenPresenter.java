package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.shen;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.DealAdapter;
import com.example.zefeiqukuai.adapter.LingZhongAdapter;
import com.example.zefeiqukuai.bean.DealBean;
import com.example.zefeiqukuai.bean.LingZhongBean;
import com.example.zefeiqukuai.pet_detail.PetDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;

public class LingShenPresenter extends BasePresenter<LingShenView> {

    private LingZhongBean dealBean;

    public LingShenPresenter(Context context) {
        super(context);
    }
    private int nextPage =1;
    ArrayList<LingZhongBean.DataBean> dataBeans = new ArrayList<>();
    private DealAdapter dealAdapter;

    public void loadMyRecord(SmartRefreshLayout myIntegralSmart) {
        //设置上拉刷新下拉加载
        myIntegralSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                nextPage = 1;
                getStay(2, nextPage, 20);

            }
        });
        myIntegralSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                nextPage++;
                getStay(2, nextPage, 20);
            }
        });
    }
    //获取交易中数据
    public void getStay(int type, int pageindex, int pagesize) {

        Map build = MapUtil.getInstance().addParms("type", type).addParms("pageindex", pageindex).addParms("pagesize", pagesize).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.LINGZHONG, build, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            private LingZhongAdapter dealAdapter;

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("领养记录申诉数据" + result);
                if (getView() != null) {
                    getView().refreshSuccesss();
                }
                if (result != null) {
                    if (pageindex == 1) {
                        dataBeans.clear();
                    }
                    dealBean = JSON.parseObject(result, LingZhongBean.class);
                    dataBeans.addAll(dealBean.getData());
                    if (dealAdapter == null) {

                        dealAdapter = new LingZhongAdapter(mContext, dataBeans, R.layout.content_gong1);
                        if (getView() != null) {
                            getView().loadDealAdapter(dealAdapter);
                        }
                    } else {
                        dealAdapter.notifyDataSetChanged();
                    }
                }
                dealAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Intent intent = new Intent(mContext, PetDetailActivity.class);
                        intent.putExtra("bean",dataBeans.get(position));
                        mContext.startActivity(intent);
                    }
                });
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
