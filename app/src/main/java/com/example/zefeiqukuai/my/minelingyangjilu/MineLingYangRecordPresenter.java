package com.example.zefeiqukuai.my.minelingyangjilu;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.base.adapter.BaseVPAdapter;
import com.example.base.mvp.BasePresenter;
import com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.finsh.LingFinshFragment;
import com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.shen.LingShenFragment;
import com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.zhong.ZhongFragment;
import com.example.zefeiqukuai.my.myzhuanrang.fragment.deal.DealFragment;
import com.example.zefeiqukuai.my.myzhuanrang.fragment.finsh.FinshFragment;
import com.example.zefeiqukuai.my.myzhuanrang.fragment.stay.StayFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MineLingYangRecordPresenter extends BasePresenter<MineLingYangRecordView> {

    private String[] titleArr = {"领养中", "已领养", "申诉"};
    private List<Fragment> fragmentList = new ArrayList<>();

    public MineLingYangRecordPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {}

    public void initTabLayout(final TabLayout mineOrderTab) {
        for (String title : titleArr) {
            mineOrderTab.addTab(mineOrderTab.newTab().setText(title));
        }

        fragmentList.add(new ZhongFragment());
        fragmentList.add(new LingFinshFragment());
        fragmentList.add(new LingShenFragment());
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
}
