package com.example.zefeiqukuai.my.shoukuan;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.adapter.BaseVPAdapter;
import com.example.base.mvp.BaseFragmentActivity;
import com.example.base.utils.LogUtil;
import com.example.base.utils.OnGetCodeListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.EventBusBean;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShouKuanActivity extends BaseFragmentActivity<ShouKuanView, ShouKuanPresenter> implements ShouKuanView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.shoukuan_tab)
    TabLayout teamTab;
    @BindView(R.id.shoukuan_viewpager)
    ViewPager teamViewpager;
    public int isSend = 0;
    private String zhifubaozh;
    private String zhifubaoname;
    private String cardbank;
    private String cardzhihang;
    private String cardbankname;
    private String cardBankkh;
    private String wechatzh;
    private String wechatname;
    private String zhifubaocode;
    private String wechatcode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shoukuan;
    }

    @Override
    public void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        includeTitle.setText("收款管理");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("修改");
        presenter.initTabLayout(teamTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        teamViewpager.setOffscreenPageLimit(2);
        //tablayout联动viewpager
        teamTab.setupWithViewPager(teamViewpager);
//        teamViewpager.setCurrentItem(type);
//        teamTab.getTabAt(type).select();

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventBusBean(CommonResource.SHOUKUAN, "shoukuan"));
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSave(EventBusBean fasongshoukuan) {
        if (CommonResource.SHOUKUANFANGSHI.equals(fasongshoukuan.getType())) {
            isSend++;
            if (isSend >= 3) {
                PopUtils.yanPop(ShouKuanActivity.this, new OnGetCodeListener() {
                    @Override
                    public void onClick(PopupWindow pop, EditText edit, View view) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (TextUtils.isEmpty(edit.getText().toString())) {
                                    Toast.makeText(ShouKuanActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                                } else {
                                    pop.dismiss();
                                    isSend = 0;
                                    //支付宝
                                    zhifubaozh = SPUtil.getStringValue("zhifubaozh");
                                    zhifubaoname = SPUtil.getStringValue("zhifubaoname");
                                    //银行卡
                                    cardbank = SPUtil.getStringValue("cardbank");
                                    cardzhihang = SPUtil.getStringValue("cardzhihang");
                                    cardbankname = SPUtil.getStringValue("cardbankname");
                                    cardBankkh = SPUtil.getStringValue("cardBankkh");
                                    //微信
                                    wechatzh = SPUtil.getStringValue("wechatzh");
                                    wechatname = SPUtil.getStringValue("wechatname");
                                    //支付宝付款码
                                    zhifubaocode = SPUtil.getStringValue("zhifubaocode");
                                    //微信付款码
                                    wechatcode = SPUtil.getStringValue("wechatcode");
                                    JSONObject jsonObject = new JSONObject();
                                    //支付宝姓名
                                    jsonObject.put("aliname", zhifubaoname);
                                    //支付宝账号
                                    jsonObject.put("alino", zhifubaozh);
                                    //支付宝收款码
                                    jsonObject.put("aliqrcode", zhifubaocode);
                                    //微信
                                    jsonObject.put("wxname", wechatname);
                                    jsonObject.put("wxno", wechatzh);
                                    jsonObject.put("wxqrcode", wechatcode);
                                    //银行卡
                                    jsonObject.put("bankname", cardbank);
                                    jsonObject.put("branch", cardzhihang);
                                    jsonObject.put("cardname", cardbankname);
                                    jsonObject.put("cardno", cardBankkh);
                                    jsonObject.put("code", edit.getText().toString());
                                    String pddGoodsSearchVoStr = JSON.toJSONString(jsonObject);
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
                                    presenter.commit(body);
                                }
                            }
                        });
                    }
                });
            }
        }

    }

    @Override
    public ShouKuanView createView() {
        return this;
    }

    @Override
    public ShouKuanPresenter createPresenter() {
        return new ShouKuanPresenter(this);
    }

    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        teamViewpager.setAdapter(baseVPAdapter);
        teamTab.setTabsFromPagerAdapter(baseVPAdapter);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtil.e("22222222222222222222222");
//    }
}
