package com.example.zefeiqukuai.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.base.mvp.BaseFragment;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.UserAcountBean;
import com.example.zefeiqukuai.bean.UserInfoBean;
import com.example.zefeiqukuai.login.LoginActivity;
import com.example.zefeiqukuai.my.chengyi.ChengYiActivity;
import com.example.zefeiqukuai.my.dongtaishouyi.MyDongTaiShouYiActivity;
import com.example.zefeiqukuai.my.introduce.IntroduceActivity;
import com.example.zefeiqukuai.my.invite.MyInviteActivity;
import com.example.zefeiqukuai.my.jieshao.MyJieShaoActivity;
import com.example.zefeiqukuai.my.luer.MyLuErActivity;
import com.example.zefeiqukuai.my.minelingyangjilu.MineLingYangRecordActivity;
import com.example.zefeiqukuai.my.mypet.MyPetActivity;
import com.example.zefeiqukuai.my.mysafe.MineSafeActivity;
import com.example.zefeiqukuai.my.mysave.MySaveYiActivity;
import com.example.zefeiqukuai.my.mysure.MineSureActivity;
import com.example.zefeiqukuai.my.myteam.MyTeamActivity;
import com.example.zefeiqukuai.my.myzhuanrang.MineZhuanRangRecordActivity;
import com.example.zefeiqukuai.my.setting.SettingActivity;
import com.example.zefeiqukuai.my.shoukuan.ShouKuanActivity;
import com.example.zefeiqukuai.my.vip.MyVipActivity;
import com.example.zefeiqukuai.my.yuyue.MineYuYueRecordActivity;

import butterknife.BindView;


public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @BindView(R.id.mine_header)
    ImageView mineHeader;
    @BindView(R.id.mine_level)
    TextView mineLevel;
    @BindView(R.id.mine_nick_name)
    TextView mineNickName;
    @BindView(R.id.mine_setting)
    ImageView mineSetting;
    @BindView(R.id.mine_wiashouji)
    TextView mineWiashouji;
    @BindView(R.id.mine_wiashouji_lin)
    LinearLayout mineWiashoujiLin;
    @BindView(R.id.mine_luer)
    TextView mineLuer;
    @BindView(R.id.mine_luer_lin)
    LinearLayout mineLuerLin;
    @BindView(R.id.mine_dongtaishouyi)
    TextView mineDongtaishouyi;
    @BindView(R.id.mine_dongtaishouyi_lin)
    LinearLayout mineDongtaishouyiLin;
    @BindView(R.id.mine_leijishouyi)
    TextView mineLeijishouyi;
    @BindView(R.id.mine_zongzijin)
    TextView mineZongzijin;
    @BindView(R.id.mine_zongzijin_lin)
    LinearLayout mineZongzijinLin;
    @BindView(R.id.mine_chengyijin)
    TextView mineChengyijin;
    @BindView(R.id.mine_chengyijin_lin)
    LinearLayout mineChengyijinLin;
    @BindView(R.id.mine_zhuancunshouyi)
    TextView mineZhuancunshouyi;
    @BindView(R.id.mine_zhuancunshouyi_lin)
    LinearLayout mineZhuancunshouyiLin;
    @BindView(R.id.mine_leijishouyi_lin)
    LinearLayout mineLeijishouyiLin;
    @BindView(R.id.mine_lingyangjilu)
    LinearLayout mineLingyangjilu;
    @BindView(R.id.mine_zhuanrangjilu)
    LinearLayout mineZhuanrangjilu;
    @BindView(R.id.mine_yuyuejilu)
    LinearLayout mineYuyuejilu;
    @BindView(R.id.mine_anquanzhongxin)
    LinearLayout mineAnquanzhongxin;
    @BindView(R.id.mine_shimingrenzheng)
    LinearLayout mineShimingrenzheng;
    @BindView(R.id.mine_shoukuan)
    LinearLayout mineShoukuan;
    @BindView(R.id.my_team)
    LinearLayout myTeam;
    @BindView(R.id.my_invite)
    LinearLayout myInvite;
    @BindView(R.id.my_jieshao)
    LinearLayout myJieshao;
    @BindView(R.id.mine_phone)
    TextView minePhone;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.rel)
    RelativeLayout rel;
    UserInfoBean userInfoBean;
    @BindView(R.id.my_pet)
    LinearLayout myPet;

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //领养记录
        mineLingyangjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MineLingYangRecordActivity.class);
                startActivity(intent);
            }
        });
        //转让记录
        mineZhuanrangjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MineZhuanRangRecordActivity.class);
                startActivity(intent);
            }
        });
        //预约记录
        mineYuyuejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MineYuYueRecordActivity.class);
                startActivity(intent);
            }
        });
        //安全中心
        mineAnquanzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MineSafeActivity.class);
                startActivity(intent);
            }
        });
        //实名认证
        mineShimingrenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(SPUtil.getStringValue(CommonResource.CARD))) {
                    Intent intent = new Intent(getActivity(), MineSureActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "您已经实名认证", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //设置
        mineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        //收款
        mineShoukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShouKuanActivity.class);
                startActivity(intent);
            }
        });
        //团队
        myTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyTeamActivity.class);
                startActivity(intent);
            }
        });
        //邀请好友
        myInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyInviteActivity.class);
                startActivity(intent);
            }
        });
        //玩法介绍
        myJieshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyJieShaoActivity.class);
                startActivity(intent);
            }
        });
        //动态收益
        mineDongtaishouyiLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyDongTaiShouYiActivity.class);
                startActivity(intent);
            }
        });
        //转存收益
        mineZhuancunshouyiLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MySaveYiActivity.class);
                startActivity(intent);
            }
        });
        //微分 鹿耳
        mineLuerLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyLuErActivity.class);
                startActivity(intent);
            }
        });
        //会员中心
        mineHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyVipActivity.class);
                startActivity(intent);
            }
        });
        //是否退出登录  退出后点击进入登录页面
        mineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //兑换宠物
        myPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyPetActivity.class);
                startActivity(intent);
            }
        });
        //玩法介绍
        myJieshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), IntroduceActivity.class);
                startActivity(intent);
            }
        });
        mineChengyijinLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChengYiActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }

    @Override
    public void loadUserInfo(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
        Glide.with(getContext()).load(CommonResource.BASEURL_8089 + userInfoBean.getHead_image()).placeholder(R.drawable.icon_wode1).apply(RequestOptions.circleCropTransform()).into(mineHeader);
        mineNickName.setText(userInfoBean.getNick_name());
        minePhone.setText(userInfoBean.getMember());
        mineLevel.setText(userInfoBean.getRank_name());
    }

    @Override
    public void loadUserAcount(UserAcountBean userAcountBean) {
        LogUtil.e("00000000000000000----------" + userAcountBean.getPet());
        mineWiashouji.setText(ArithUtil.exact(userAcountBean.getWia(), 2) + "");
        mineLuer.setText(ArithUtil.exact(userAcountBean.getPet(), 2) + "");
        mineDongtaishouyi.setText(ArithUtil.exact(userAcountBean.getIncome(), 2) + "");
        mineLeijishouyi.setText(ArithUtil.exact(userAcountBean.getAdd_income(), 2) + "");
        mineZongzijin.setText(ArithUtil.exact(userAcountBean.getProperty(), 2) + "");
        mineChengyijin.setText(ArithUtil.exact(userAcountBean.getSincerity_money(), 2) + "");
        mineZhuancunshouyi.setText(ArithUtil.exact(userAcountBean.getTurn(), 2) + "");

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && TextUtils.isEmpty(SPUtil.getStringValue(CommonResource.TOKEN))) {
            //如果该页面可见但是未登录
            mineWiashouji.setText("0");
            mineLuer.setText("0");
            mineDongtaishouyi.setText("0");
            mineLeijishouyi.setText("0");
            mineZongzijin.setText("0");
            mineChengyijin.setText("0");
            mineZhuancunshouyi.setText("0");
        } else if (!hidden && !TextUtils.isEmpty(SPUtil.getStringValue(CommonResource.TOKEN))) {
            //如果该页面可见并且已登录
        }
//
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserAcount();
        presenter.getUserInfo();
        if (TextUtils.isEmpty(SPUtil.getStringValue(CommonResource.TOKEN))) {
            Glide.with(getContext()).load(CommonResource.BASEURL_8089 + SPUtil.getStringValue(CommonResource.HEADIMAGE)).placeholder(R.drawable.icon_wode1).apply(RequestOptions.circleCropTransform()).into(mineHeader);
            mineName.setVisibility(View.VISIBLE);
            rel.setVisibility(View.GONE);

        }
    }
}
