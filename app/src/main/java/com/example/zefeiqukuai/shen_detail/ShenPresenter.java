package com.example.zefeiqukuai.shen_detail;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.UpdateMessageBean;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShenPresenter extends BasePresenter<ShenView> {
    public ShenPresenter(Context context) {
        super(context);
    }
    public void getAppeal(int id,int type,String appealcause){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("type",type);
        jsonObject.put("appealcause",appealcause);
        String pddGoodsSearchVoStr = JSON.toJSONString(jsonObject);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.APPEALSELL, body, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("卖家申诉"+result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode() ==0){
                    ((Activity)mContext).finish();
                    Toast.makeText(mContext, "申诉成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("卖家申诉onError"+errorCode+errorMsg);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
