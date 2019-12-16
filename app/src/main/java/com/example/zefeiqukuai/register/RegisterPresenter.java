package com.example.zefeiqukuai.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.GetCodeBean;
import com.example.zefeiqukuai.bean.RegBean;
import com.example.zefeiqukuai.bean.RegisterBean;
import com.example.zefeiqukuai.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public boolean isRead = true;
    public RegisterPresenter(Context context) {
        super(context);
    }

    //判断是否同意协议
    public void check() {
        if (isRead) {
            getView().noRead();
            isRead = false;
        } else {
            getView().readed();
            isRead = true;
        }
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
    //调用注册的接口
    public void getReg(String parent_member,String phone,String password,String pay_password,String nick_name,String code,String urgent_phone)
    {
        RegisterBean registerBean = new RegisterBean();
        registerBean.setParent_member(parent_member);
        registerBean.setCode(code);
        registerBean.setNick_name(nick_name);
        registerBean.setPassword(password);
        registerBean.setPay_password(pay_password);
        registerBean.setUrgent_phone(urgent_phone);
        registerBean.setPhone(phone);
        String pddGoodsSearchVoStr = JSON.toJSONString(registerBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postDataWithBody(CommonResource.USERREG, body);
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("注册"+result);
                RegBean regBean = JSON.parseObject(result, RegBean.class);
                if(regBean.getErrcode()==0)
                {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("pwd",password);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, "注册失败", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("注册error"+errorMsg);
            }
        }));

    }


    @Override
    protected void onViewDestroy() {
       // mContext.unregisterReceiver(receiver);
      //  EventBus.getDefault().unregister(this);
      //  SPUtil.addParm(CommonResource.TAN_CONTENT, "");
    }


}
