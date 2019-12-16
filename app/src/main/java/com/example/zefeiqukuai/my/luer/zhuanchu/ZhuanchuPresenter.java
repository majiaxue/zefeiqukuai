package com.example.zefeiqukuai.my.luer.zhuanchu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.CheckPasswordBean;
import com.example.zefeiqukuai.bean.GetNickNameBean;
import com.example.zefeiqukuai.bean.TransferCommitBean;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ZhuanchuPresenter extends BasePresenter<ZhuanchuView> {

    public ZhuanchuPresenter(Context context) {
        super(context);
    }

    public void getNickNa(String member) {
        Map map = MapUtil.getInstance().addParms("member", member).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.GETNICKNAME, map, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("获取昵称" + result);
                GetNickNameBean getNickNameBean = JSON.parseObject(result, GetNickNameBean.class);
                if (getNickNameBean != null) {
                    getView().loadNickName(getNickNameBean.getNick_name());
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("获取昵称onError" + errorCode);
            }
        }));
    }
    //验证支付密码
    public void checkPassword(String pay_password){
        Map map = MapUtil.getInstance().addParms("pay_password", pay_password).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.CHECKPASSWORD, map, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("验证支付密码"+result);
                CheckPasswordBean checkPasswordBean = JSON.parseObject(result, CheckPasswordBean.class);
                if (checkPasswordBean.getErrcode() ==0){
                    getView().paycommit();

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("验证支付密码onError"+errorCode+"=========="+errorMsg);
            }
        }));
    }
    //微分转出
    public void transferCommit(int transfer_id, int money, String target_member) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transfer_id", transfer_id);
        jsonObject.put("money", money);
        jsonObject.put("target_member", target_member);
        String pddGoodsSearchVoStr = JSON.toJSONString(jsonObject);
        LogUtil.e("------------" + pddGoodsSearchVoStr);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.TRANSFERCOMMIT, body, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("转账"+result);
                TransferCommitBean transferCommitBean = JSON.parseObject(result, TransferCommitBean.class);
                if (transferCommitBean.getErrcode() ==0)
                {
                    String weifen = SPUtil.getStringValue("weifen");
                    double v = Double.valueOf(weifen) - money;
                    SPUtil.addParm("weifen",String.valueOf(v));
                    Toast.makeText(mContext, transferCommitBean.getMsg(), Toast.LENGTH_SHORT).show();
                    ((Activity)mContext).finish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                LogUtil.e("转账onError"+errorCode);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
