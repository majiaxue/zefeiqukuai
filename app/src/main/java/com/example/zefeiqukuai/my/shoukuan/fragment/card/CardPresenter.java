package com.example.zefeiqukuai.my.shoukuan.fragment.card;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.BankCardBean;
import com.example.zefeiqukuai.bean.ZFBBean;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;

public class CardPresenter extends BasePresenter<CardView> {
    public CardPresenter(Context context) {
        super(context);
    }
    //获取支付宝账号信息
    public void getGathing(int type){
        Map map = MapUtil.getInstance().addParms("type", type).build();
        Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.GATHERING, map, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(head,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("银行卡收款信息"+result);
                BankCardBean bankCardBean = JSON.parseObject(result, BankCardBean.class);
                if (getView()!=null){
                    getView().loadZfbData(bankCardBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("银行卡收款信息onError--->"+errorCode+"2222"+errorMsg);

            }
        }));
    }
    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
    }
}
