package com.example.zefeiqukuai.my.vip;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.VipBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.PUT;

public class MyVipPresenter extends BasePresenter<MyVipView> {
    public MyVipPresenter(Context context) {
        super(context);
    }
    public void getRankList(){
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.RANKLIST, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("会员等级"+result);
                List<VipBean> vipBeans = JSON.parseArray(result, VipBean.class);
                if (getView()!=null)
                {
                    getView().loadRank(vipBeans);
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
