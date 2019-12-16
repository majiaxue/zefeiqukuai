package com.example.zefeiqukuai.my.updatepaypwd;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.GetCodeBean;
import com.example.zefeiqukuai.bean.UpdatePasswordBean;
import com.example.zefeiqukuai.bean.UppwdBean;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UpdatePasswordPayPresenter extends BasePresenter<UpdatePasswordPayView> {
    public UpdatePasswordPayPresenter(Context context) {
        super(context);
    }
    // 修改密码
    public void updatePassword(String code,String password)
    {
        UppwdBean uppwdBean = new UppwdBean();
        uppwdBean.setCode(code);
        uppwdBean.setPassword(password);
        String pddGoodsSearchVoStr = JSON.toJSONString(uppwdBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.UPPAYPWD, body, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("修改支付密码"+result);
                UpdatePasswordBean updatePasswordBean = JSON.parseObject(result, UpdatePasswordBean.class);
                if (updatePasswordBean.getErrcode()==0)
                {
                    ((Activity)mContext).finish();
                }else{
                    Toast.makeText(mContext, updatePasswordBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("修改密码onError-->"+errorCode);
            }
        }));


    }
    //获取验证码
    public void getCodeNum(String phone) {
        if (PhoneNumUtil.isMobileNO(phone)) {
//            Map map = MapUtil.getInstance().addParms("phone", phone).build();
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getDataWithout(CommonResource.SENDSMS +"?phone="+phone );
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("获取验证码：" + result);
                    GetCodeBean getCodeBean = JSON.parseObject(result, GetCodeBean.class);
                    if (getCodeBean.getErrcode()==0)
                    {
                        getView().getCodeSuccess();
                        Toast.makeText(mContext, "短信验证码已发送到您手机", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                    LogUtil.e(errorCode + "-----------" + errorMsg+errorCode);
                }
            }));
        } else {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onViewDestroy() {

    }
}
