package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.finsh;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;

import com.example.zefeiqukuai.adapter.YiLingYangAdapter;
import com.example.zefeiqukuai.bean.DealBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;
import com.example.zefeiqukuai.bean.YiLingYangBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.PUT;

public class LingFinshPresenter extends BasePresenter<LingFinshView> {

    private YiLingYangBean dealBean;

    public LingFinshPresenter(Context context) {
        super(context);
    }

    private int nextPage = 1;
    ArrayList<YiLingYangBean.DataBean> dataBeans = new ArrayList<>();
    private YiLingYangAdapter dealAdapter;

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

    //获取交易中数据
    public void getStay(int pageindex, int pagesize) {

        Map build = MapUtil.getInstance().addParms("pageindex", pageindex).addParms("pagesize", pagesize).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.LINGYANG, build, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("领养完成数据" + result);
                if (getView() != null) {
                    getView().refreshSuccesss();
                }
                if (result != null) {
                    if (pageindex == 1) {
                        dataBeans.clear();
                    }
                    dealBean = JSON.parseObject(result, YiLingYangBean.class);
                    dataBeans.addAll(dealBean.getData());
                    if (dealAdapter == null) {

                        dealAdapter = new YiLingYangAdapter(mContext, dataBeans, R.layout.content_gong1);
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
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sellPet(dataBeans.get(position).getId());
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }
    //提前出售接口
    public void  sellPet(int  id) {
        Map map = MapUtil.getInstance().addParms("id", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.SELL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("宠物提前售出"+result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode()==0){
                    getStay(nextPage,20);
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("宠物提前售出onError"+errorCode+errorMsg);
            }
        }));
    }


    @Override
    protected void onViewDestroy() {

    }
}
