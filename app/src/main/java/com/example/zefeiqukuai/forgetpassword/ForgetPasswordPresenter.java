package com.example.zefeiqukuai.forgetpassword;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.AppManager;
import com.example.base.utils.LogUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.ForgetPasswordBean;
import com.example.zefeiqukuai.bean.RegisterBean;
import com.example.zefeiqukuai.elkmarket.ElkMarketFragment;
import com.example.zefeiqukuai.login.LoginActivity;
import com.example.zefeiqukuai.mine.MineFragment;
import com.example.zefeiqukuai.service.ServiceFragment;
import com.example.zefeiqukuai.welfare.WelfareFragment;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordView> {


    public ForgetPasswordPresenter(Context context) {
        super(context);
    }
    //忘记密码
    public void loadData(String phone,String password,String code)
    {
        ForgetPasswordBean forgetPasswordBean = new ForgetPasswordBean();
        forgetPasswordBean.setCode(code);
        forgetPasswordBean.setPassword(password);
        forgetPasswordBean.setPhone(phone);
        String pddGoodsSearchVoStr = JSON.toJSONString(forgetPasswordBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postDataWithBody(CommonResource.FORGETPASSWORD, body);
        RetrofitUtil.getInstance().toSubscribe(observable,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("忘记密码---->"+result);
                LogUtil.e("忘记密码---->"+phone);
                LogUtil.e("忘记密码---->"+password);
                ARouter.getInstance().build("/zefeiqukuai/login/LoginActivity").withString("phone",phone).withString("password",password).navigation();
//                Intent intent = new Intent(mContext, LoginActivity.class);
//                intent.putExtra("phone",forgetPasswordBean.getPhone());
//                intent.putExtra("pwd",forgetPasswordBean.getCode());
//                intent.putExtra("type",2);
//                mContext.startActivity(intent);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("忘记密码error-->"+errorMsg);
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
                    getView().getCodeSuccess();
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
