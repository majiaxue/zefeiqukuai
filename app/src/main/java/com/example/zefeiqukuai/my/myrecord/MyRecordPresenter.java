package com.example.zefeiqukuai.my.myrecord;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.MyAdapter;
import com.example.zefeiqukuai.adapter.MyRecordAdapter;
import com.example.zefeiqukuai.bean.MyPetBean;
import com.example.zefeiqukuai.bean.PetDetailBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class MyRecordPresenter extends BasePresenter<MyRecordView> {
    private List<MyPetBean> myPetBeans;
    private MyRecordAdapter myRecordAdapter;
    public MyRecordPresenter(Context context) {
        super(context);
    }
    public void loadData( ) {
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.CONVERRECORD,SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("我的兑换宠物列表：" + result);
                myPetBeans = JSON.parseArray(result, MyPetBean.class);
                if (myRecordAdapter == null) {
                    myRecordAdapter = new MyRecordAdapter(mContext, myPetBeans, R.layout.content_gong);
                    if (getView()!=null){
                        getView().loadData(myRecordAdapter);
                    }
                } else {
                    myRecordAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----我的兑换宠物列表------" + errorMsg);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
