package com.example.zefeiqukuai.my.yuyue;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.YuYueAdapter;
import com.example.zefeiqukuai.bean.YuYueBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MineYuYueRecordPresenter extends BasePresenter<MineYuYueRecordView> {

    private YuYueAdapter yuYueAdapter;

    public MineYuYueRecordPresenter(Context context) {
        super(context);
    }
    ArrayList<YuYueBean.DataBean> dataBeans =  new ArrayList<>();

    public void  getData(){
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.APPOINTLIST, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("我的预约记录"+result);
                YuYueBean yuYueBean = JSON.parseObject(result, YuYueBean.class);
                if(yuYueBean!=null)
                {
                    dataBeans.addAll(yuYueBean.getData());

                    if (yuYueAdapter==null){
                        yuYueAdapter = new YuYueAdapter(mContext, dataBeans, R.layout.content_gong);
                        if (getView()!=null)
                        {
                            getView().loadAdapter(yuYueAdapter);
                        }
                    }else {
                        yuYueAdapter.notifyDataSetChanged();
                    }
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
