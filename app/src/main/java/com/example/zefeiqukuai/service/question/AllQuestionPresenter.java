package com.example.zefeiqukuai.service.question;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
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

public class AllQuestionPresenter extends BasePresenter<AllQuestionView> {
    public AllQuestionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    //发送邮件
    public void sendEmail(String title,String content,int category_id) {
       // LogUtil.e("----------------------->" + title);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("content",content);
        jsonObject.put("category_id",category_id);
        String s = JSON.toJSONString(jsonObject);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.SENDEMAIL, requestBody, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data,new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("这个是发送邮件"+result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode()==0)
                {
                    Toast.makeText(mContext,"发送成功",Toast.LENGTH_SHORT).show();
                    ((Activity)mContext).finish();
                    ARouter.getInstance().build("/zefeiqukuai/MainActivity").navigation();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode+"-----------------"+errorMsg);
                Toast.makeText(mContext,errorMsg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
