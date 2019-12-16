package com.example.zefeiqukuai.transfer_detail;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.ImageUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.OnChangeHeaderListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.BaseBean;
import com.example.zefeiqukuai.bean.CheckPasswordBean;
import com.example.zefeiqukuai.bean.PetDetailBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class TransferDetailPreenter extends BasePresenter<TransferDetailView> {
    private Uri fileUri;
    private Uri cropUri;

    public TransferDetailPreenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(int id) {
        Map map = MapUtil.getInstance().addParms("id", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.ORDER_DETAIL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("详情：" + result);
                PetDetailBean petDetailBean = JSON.parseObject(result, PetDetailBean.class);
                if (petDetailBean != null) {
                    getView().loadData(petDetailBean);

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----详情------" + errorMsg);
            }
        }));
    }

    //确认订单
    public void getData(int id) {
        Map map = MapUtil.getInstance().addParms("id", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.CONFIRM, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("卖家确认订单：" + result);
                UpdateMessageBean petDetailBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (petDetailBean.getErrcode()==0) {
                    ((Activity)mContext).finish();
                    Toast.makeText(mContext, petDetailBean.getMsg(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, petDetailBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----卖家确认订单------" + errorMsg);
            }
        }));
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
                LogUtil.e("卖家取消申诉"+result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode() ==0){
                    ((Activity)mContext).finish();
                    Toast.makeText(mContext, "取消申诉成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("卖家取消申诉onError"+errorCode+errorMsg);
            }
        }));
    }
}
