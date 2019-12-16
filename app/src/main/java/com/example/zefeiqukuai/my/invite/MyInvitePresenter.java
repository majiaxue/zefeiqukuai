package com.example.zefeiqukuai.my.invite;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.QRCode;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.InviteBean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MyInvitePresenter extends BasePresenter<MyInviteView> {
    public MyInvitePresenter(Context context) {
        super(context);
    }

    public void setClipboard(String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
    }
    //分享注册邀请地址
    public void getInvite(){
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.RETURNREGISTER, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("分享注册邀请地址"+result);
                InviteBean inviteBean = JSON.parseObject(result, InviteBean.class);
                if (getView()!=null){
                    getView().loadCode(inviteBean.getUrl());
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
