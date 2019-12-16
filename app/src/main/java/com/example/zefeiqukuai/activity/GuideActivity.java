package com.example.zefeiqukuai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.base.utils.StatusBarUtils;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.TopBannerBean;
import com.example.zefeiqukuai.login.LoginActivity;
import com.example.zefeiqukuai.main.MainActivity;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;


import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private XBanner guideBanner;
    private TextView guideSkip;
    private List<TopBannerBean> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        changeStatus();
        initView();
        initData();
    }


    private void changeStatus() {
        // 设置状态栏
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusTheme(this, true, true);
    }

    private void initView() {
        guideBanner = findViewById(R.id.guide_banner);
        guideSkip = findViewById(R.id.guide_skip);
    }

    private void initData() {
        images.add(new TopBannerBean(R.drawable.yindao1_ys));
        images.add(new TopBannerBean(R.drawable.yindao2_ys));
        images.add(new TopBannerBean(R.drawable.yindao3_ys));
        guideBanner.setData(images, null);
        guideBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                Glide.with(GuideActivity.this).load(images.get(position).getXBannerUrl()).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        guideBanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        guideBanner.setPageChangeDuration(1000);
        guideBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                if (position == 2) {
                    guideBanner.stopAutoPlay();
                    startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                    finish();
                }
            }

        });

        guideSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideBanner.stopAutoPlay();
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    /**
     * 为了更好的体验效果建议在下面两个生命周期中调用下面的方法
     **/
    @Override
    protected void onResume() {
        super.onResume();
        guideBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        guideBanner.stopAutoPlay();
    }

}
