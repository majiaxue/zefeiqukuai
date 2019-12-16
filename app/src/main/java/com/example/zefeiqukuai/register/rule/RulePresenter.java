package com.example.zefeiqukuai.register.rule;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.RuleBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class RulePresenter extends BasePresenter<RuleView> {
    public RulePresenter(Context context) {
        super(context);
    }
    //注册协议
    public void getRegRule()
    {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getDataWithout(CommonResource.TREATY);
        RetrofitUtil.getInstance().toSubscribe(dataWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("注册协议"+result);
                RuleBean ruleBean = JSON.parseObject(result, RuleBean.class);
                if(getView()!=null)
                {
                    getView().getSuccess(ruleBean);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("注册协议onError-->"+errorCode);
            }
        }));

    }
    @Override
    protected void onViewDestroy() {

    }
}
