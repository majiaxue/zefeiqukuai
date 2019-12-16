package com.example.zefeiqukuai.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.AppManager;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.PhoneNumUtil;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.LoginBean;
import com.example.zefeiqukuai.bean.RegisterBean;
import com.example.zefeiqukuai.elkmarket.ElkMarketFragment;
import com.example.zefeiqukuai.main.MainActivity;
import com.example.zefeiqukuai.mine.MineFragment;
import com.example.zefeiqukuai.service.ServiceFragment;
import com.example.zefeiqukuai.welfare.WelfareFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context context) {
        super(context);
    }

    public void getLogin(String phone, String pwd) {

        if (!PhoneNumUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if ("".equals(pwd) || pwd == null) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            ProcessDialogUtil.showProcessDialog(mContext);
            Map map = MapUtil.getInstance().addParms("name", phone).addParms("password", pwd).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postData(CommonResource.LOGIN, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("登录" + result);
                    LoginBean loginBean = JSON.parseObject(result, LoginBean.class);
                    SPUtil.addParm(CommonResource.TOKEN, loginBean.getToken());
                    LogUtil.e("--->" + loginBean.getToken());
                    SPUtil.addParm(CommonResource.MERMER, loginBean.getMember());
                    SPUtil.addParm(CommonResource.PHONE, loginBean.getPhone());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("登录error" + errorMsg);
                }
            }));
        }

    }


    @Override
    protected void onViewDestroy() {
        // mContext.unregisterReceiver(receiver);
        EventBus.getDefault().unregister(this);
        //SPUtil.addParm(CommonResource.TAN_CONTENT, "");
    }

}
