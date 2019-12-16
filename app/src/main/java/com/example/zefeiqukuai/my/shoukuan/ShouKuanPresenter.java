package com.example.zefeiqukuai.my.shoukuan;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.alibaba.fastjson.JSON;
import com.example.base.adapter.BaseVPAdapter;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.ShouKuanWayBean;
import com.example.zefeiqukuai.my.shoukuan.fragment.card.CardFragment;
import com.example.zefeiqukuai.my.shoukuan.fragment.wechat.WeChatFragment;
import com.example.zefeiqukuai.my.shoukuan.fragment.zhifubao.ZhiFuBaoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ShouKuanPresenter extends BasePresenter<ShouKuanView> {
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titleArr = {"支付宝", "微信", "银行卡"};

    public ShouKuanPresenter(Context context) {
        super(context);
    }

    public void initTabLayout(final TabLayout mineOrderTab) {
        for (String title : titleArr) {
            mineOrderTab.addTab(mineOrderTab.newTab().setText(title));
        }

        fragmentList.add(new ZhiFuBaoFragment());
        fragmentList.add(new WeChatFragment());
        fragmentList.add(new CardFragment());

        initTabIndicator(mineOrderTab);
    }

    public void initViewPager(FragmentManager fm) {
        BaseVPAdapter baseVPAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVp(baseVPAdapter);
    }


    private void initTabIndicator(final TabLayout mineOrderTab) {
        mineOrderTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) mineOrderTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void commit(RequestBody body) {
        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.DATHERCOMMIT, body, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收款方式提交" + result);
                ShouKuanWayBean shouKuanWayBean = JSON.parseObject(result, ShouKuanWayBean.class);
                if (shouKuanWayBean.getErrcode() ==0)
                {
                    Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                    ((Activity)mContext).finish();
                }else {
                    Toast.makeText(mContext, shouKuanWayBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------提交------" + errorMsg);
            }
        }));
    }
}
