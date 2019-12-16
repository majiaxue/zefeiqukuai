package com.example.zefeiqukuai.service;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.FaJianXiangBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ServicePresenter extends BasePresenter<ServiceView> {
    public ServicePresenter(Context context) {
        super(context);
    }
    private List<FaJianXiangBean> faJianXiangBeans;
    @Override
    protected void onViewDestroy() {

    }

    public void getData(){
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.FALEIMU, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data,new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("发件箱类目"+result);
                faJianXiangBeans = JSON.parseArray(result, FaJianXiangBean.class);
                if (getView()!=null)
                {
                    getView().loadSuccess(faJianXiangBeans);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }
}
