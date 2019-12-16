package com.example.zefeiqukuai.my.mysure;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.MineSureBean;
import com.example.zefeiqukuai.bean.ShiMingRenZhengBean;
import com.example.zefeiqukuai.bean.UpdatePasswordBean;
import com.example.zefeiqukuai.bean.UppwdBean;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MineSurePresenter extends BasePresenter<MineSureView> {
    public MineSurePresenter(Context context) {
        super(context);
    }

    public void getLoad(String card, String name) {
        MineSureBean uppwdBean = new MineSureBean();
        uppwdBean.setCard(card);
        uppwdBean.setName(name);

        String pddGoodsSearchVoStr = JSON.toJSONString(uppwdBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.MINESURE, body, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("实名认证" + result);
                ShiMingRenZhengBean updatePasswordBean = JSON.parseObject(result, ShiMingRenZhengBean.class);
                if (updatePasswordBean.getErrcode() == 0) {
                    SPUtil.addParm(CommonResource.CARD,card);
                    Toast.makeText(mContext, "实名认证成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                } else {
                    Toast.makeText(mContext, updatePasswordBean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("实名认证onError-->" + errorCode);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
