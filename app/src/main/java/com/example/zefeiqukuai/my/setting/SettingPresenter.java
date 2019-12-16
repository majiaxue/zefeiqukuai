package com.example.zefeiqukuai.my.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.CacheUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.OnClearCacheListener;
import com.example.base.utils.SPUtil;
import com.example.base.utils.UIHelper;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.NewVersionBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;
import com.example.zefeiqukuai.login.LoginActivity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class SettingPresenter extends BasePresenter<SettingView> {

    private NewVersionBean newVersionBean;

    public SettingPresenter(Context context) {
        super(context);
    }
    //获取最新版本
    public void getVersion(){
        Map type = MapUtil.getInstance().addParms("type", "0").build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.NEWVERSION,type, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("新版本-->"+result);
                newVersionBean =  JSON.parseObject(result, NewVersionBean.class);
                if (getView()!=null){
                    getView().loadVersion(newVersionBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("新版本onError-->"+errorCode);
            }
        }));
    }
    //清除缓存
    public void clearCache(String totalCache) {

        UIHelper.clearCache(mContext, totalCache, new OnClearCacheListener() {
            @Override
            public void setOnClearCache(final PopupWindow pop, View confirm) {
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CacheUtil.clearAllCache(mContext);
                        pop.dismiss();
                        getView().clearSuccess();
                    }
                });
            }
        });
    }
    //退出登录
    public void logout() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).deleteDataWithout(CommonResource.LOGOUT, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("退出：" + result);
               // JpushUtil.deleteAlias();
                SPUtil.loginOut();
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode() ==0){
                    Toast.makeText(mContext, "退出登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
