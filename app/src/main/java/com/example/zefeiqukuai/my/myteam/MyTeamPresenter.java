package com.example.zefeiqukuai.my.myteam;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyTeamBean;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter2;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter3;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MyTeamPresenter extends BasePresenter<MyTeamView> {

    private String[] titleArr = {"第一代", "第二代", "第三代"};
    private MyTeamBean myTeamBean;

    public MyTeamPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
    public void initTabLayout(final TabLayout tabLayout){

        for (String title : titleArr) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        initIndicator(tabLayout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ProcessDialogUtil.showProcessDialog(mContext);
                if (tab.getPosition() == 0) {
                }  else if (tab.getPosition() == 1) {
                }else if (tab.getPosition()==2){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //我的团队列表
    public void loadData(TabLayout tabLayout){
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.MYTEAMLIST, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("我的团队列表-------------"+result);
                myTeamBean = JSON.parseObject(result, MyTeamBean.class);
                MyTeamAdapter adapter=new MyTeamAdapter(mContext,myTeamBean.getGener1(), R.layout.item_myteam_list);
                if (result!=null){
                    getView().loadUI(adapter);
                    getView().loadData(myTeamBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
        for (String title : titleArr) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        initIndicator(tabLayout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    MyTeamAdapter adapter=new MyTeamAdapter(mContext,myTeamBean.getGener1(), R.layout.item_myteam_list);
                    getView().loadUI(adapter);
                    getView().loadData(myTeamBean);
                }  else if (tab.getPosition() == 1) {
                    MyTeamAdapter2 adapter2=new MyTeamAdapter2(mContext,myTeamBean.getGener2(), R.layout.item_myteam_list);
                    getView().loadUI2(adapter2);
                    getView().loadData(myTeamBean);
                }else if (tab.getPosition()==2){
                    MyTeamAdapter3 adapter3=new MyTeamAdapter3(mContext,myTeamBean.getGener3(), R.layout.item_myteam_list);
                    getView().loadUI3(adapter3);
                    getView().loadData(myTeamBean);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initIndicator(final TabLayout managerOrderDetailsTab) {
        managerOrderDetailsTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) managerOrderDetailsTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
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
}
