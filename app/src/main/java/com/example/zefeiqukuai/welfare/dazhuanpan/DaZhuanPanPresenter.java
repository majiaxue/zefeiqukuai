package com.example.zefeiqukuai.welfare.dazhuanpan;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.WheelRecordAdapter;
import com.example.zefeiqukuai.bean.ChouJiangResultBean;
import com.example.zefeiqukuai.bean.DaZhuanPanBean;
import com.example.zefeiqukuai.bean.UserAcountBean;
import com.example.zefeiqukuai.bean.WheelRecordBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class DaZhuanPanPresenter extends BasePresenter<DaZhuanPanView> {

    private WheelRecordAdapter wheelRecordAdapter;

    public DaZhuanPanPresenter(Context context) {
        super(context);
    }

    private int nextPage;
    ArrayList<WheelRecordBean.DataBean> dataBeans = new ArrayList<>();

    //大转盘设置
    public void getZhuan() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.WHEELCONFIG, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("大转盘设置" + result);
                DaZhuanPanBean daZhuanPanBean = JSON.parseObject(result, DaZhuanPanBean.class);
                if (getView() != null) {
                    getView().loadData(daZhuanPanBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("大转盘设置onError-->" + errorCode);
            }
        }));
    }

    //抽奖记录
    public void getWheelRecord() {
        Map build = MapUtil.getInstance().addParms("page", "1").addParms("pagesize", "10").build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.WHEELRECOED, build, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("抽奖记录" + result);
                if (result != null) {
                    dataBeans.clear();
                    WheelRecordBean wheelRecordBean = JSON.parseObject(result, WheelRecordBean.class);
                    dataBeans.addAll(wheelRecordBean.getData());
                    if (wheelRecordAdapter == null) {
                        wheelRecordAdapter = new WheelRecordAdapter(mContext, dataBeans, R.layout.rv_points_record);
                        if (getView() != null) {
                            getView().loadRv(wheelRecordAdapter);
                        }
                    } else {
                        wheelRecordAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    //获取大转盘抽奖结果
    public void getWheelDo() {
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.WHEELDO, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("获取抽奖结果" + result);
                ChouJiangResultBean resultBean = JSON.parseObject(result, ChouJiangResultBean.class);
                getView().loadResult(resultBean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
    //获取用户资产
    public void getUserAcount(){
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.USERACCOUNT, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("用户资产"+result);
                UserAcountBean userAcountBean = JSON.parseObject(result, UserAcountBean.class);
                if (getView()!=null){
                    getView().loadUserAcount(userAcountBean);
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
