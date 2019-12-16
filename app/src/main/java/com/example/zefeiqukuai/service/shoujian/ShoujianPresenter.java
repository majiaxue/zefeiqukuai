package com.example.zefeiqukuai.service.shoujian;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.ShouJianBean;
import com.example.zefeiqukuai.service.adapter.ShouJianAdapter;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ShoujianPresenter extends BasePresenter<ShoujianView> {


    private List<ShouJianBean> bean;
    private ShouJianAdapter adapter;

    public ShoujianPresenter(Context context) {
        super(context);
    }


    //收件箱列表
    public void  getData(){
        LogUtil.e("这是token222"+ SPUtil.getStringValue(CommonResource.TOKEN));
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.SHUJIANGXIANG, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("这是收件箱列表"+result);
                try {
                    if (result != null) {
                        bean = JSON.parseArray(result, ShouJianBean.class);
                        adapter = new ShouJianAdapter(mContext, bean, R.layout.inbox__recyitem);
                        if (getView() != null) {
                            getView().loadRv(adapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/zefeiqukuai/XQShouJianAcxtivity")
                                .withString("content",bean.get(position).getContent())
                                .withString("time",bean.get(position).getCreate_time())
                                .navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode+"------------------"+errorMsg);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}
