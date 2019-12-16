package com.example.zefeiqukuai.mine;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.UserAcountBean;
import com.example.zefeiqukuai.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MinePresenter extends BasePresenter<MineView> {
    public MinePresenter(Context context) {
        super(context);
    }
    //获取个人信息
    public void getUserInfo()
    {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.USERINFO, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("个人信息"+result);
                UserInfoBean userInfoBean = JSON.parseObject(result, UserInfoBean.class);
                SPUtil.addParm(CommonResource.UUID,userInfoBean.getUuid());
                SPUtil.addParm(CommonResource.HEADIMAGE,userInfoBean.getHead_image());
                SPUtil.addParm(CommonResource.MENBER,userInfoBean.getMember());
                SPUtil.addParm(CommonResource.NICKNAME,userInfoBean.getNick_name());
                SPUtil.addParm(CommonResource.PARENTNICKNAME,userInfoBean.getParent_nick_name());
                SPUtil.addParm(CommonResource.PARENTNAME,userInfoBean.getParent_name());
                SPUtil.addParm(CommonResource.PARENTMENBER,userInfoBean.getParent_member());
                SPUtil.addParm(CommonResource.NAME,userInfoBean.getName());
                SPUtil.addParm(CommonResource.CARD,userInfoBean.getCard());
                if (getView()!=null)
                {
                    getView().loadUserInfo(userInfoBean);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("个人信息onError-->"+errorCode);
            }
        }));

    }
    //获取用户资产
    public void getUserAcount(){
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.USERACCOUNT, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("用户资产"+result);
                UserAcountBean userAcountBean = JSON.parseObject(result, UserAcountBean.class);
                if (getView()!=null){
                    getView().loadUserAcount(userAcountBean);
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
