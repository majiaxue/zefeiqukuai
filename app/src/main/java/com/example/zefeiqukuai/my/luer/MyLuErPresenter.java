package com.example.zefeiqukuai.my.luer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.SPUtil;
import com.example.base.utils.SelfDialog;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.JiaoYiBean;
import com.example.zefeiqukuai.my.luer.adapter.LuErListAdapter;


import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;

//微分界面
public class MyLuErPresenter extends BasePresenter<MyLuErView> {

    private LuErListAdapter adapter;
    private JiaoYiBean bean;
    private ArrayList<JiaoYiBean.DataBean> dataBeans = new ArrayList<>();

    public MyLuErPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    //交易明细
    public void getJiFen(int currency_id,int page,int pagesize){
        Map build = MapUtil.getInstance().addParms("currency_id", currency_id).addParms("page", page).addParms("pagesize", pagesize).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.JIAOYILIST, build, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("这是交易明细-------------------"+result);
                if (getView()!=null)
                {
                    getView().refresh();
                }
                if (page ==1)
                {
                    dataBeans.clear();
                }
                bean = JSON.parseObject(result, JiaoYiBean.class);
                SPUtil.addParm("weifen", bean.getTotal_account()+"");
                getView().loadData(bean);
                dataBeans.addAll(bean.getData());
                if (adapter == null)
                {
                    adapter = new LuErListAdapter(mContext, dataBeans, R.layout.item_luer_list);
                    if (result!=null){
                        getView().loadUI(adapter);
                    }
                }else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                    if (getView()!=null){
                        getView().refresh();
                    }
            }
        }));
    }

}
