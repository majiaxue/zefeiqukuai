package com.example.zefeiqukuai.elkmarket;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.utils.TextUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.base.adapter.MyRecyclerAdapter;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.PetListAdapter;
import com.example.zefeiqukuai.bean.BannerBean;
import com.example.zefeiqukuai.bean.GetCodeBean;
import com.example.zefeiqukuai.bean.PetListBean;
import com.example.zefeiqukuai.bean.QiangBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ElkMarketPresenter extends BasePresenter<ElkMarketView> {
    private List<String> data = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private PetListAdapter petListAdapter;
    private List<PetListBean.DataBean> dataBeans = new ArrayList<>();
    private boolean isTrue = true;
    private Thread thread;

    public ElkMarketPresenter(Context context) {
        super(context);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    petListAdapter.notifyItemChanged(msg.arg1, R.id.content_gong1_btn);
                    break;
            }
        }
    };

    //获取公告信息
    public void getNotice(String time) {

        Map lasttime = MapUtil.getInstance().addParms("lasttime", time).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.GETNOTICE, lasttime, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("公告信息--->" + result);
                Date dt = new Date();
                String s = dt.toLocaleString();
                LogUtil.e("公告信息--->" + s);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("公告信息onError--->" + errorCode);
            }
        }));

    }

    //首页轮播图
    public void getBanner(XBanner elkMarketBanner) {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.BANNER, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("轮播图" + result);
                List<BannerBean> bannerBeans = JSON.parseArray(result, BannerBean.class);
                if (bannerBeans != null) {
                    elkMarketBanner.setData(bannerBeans, null);
                    elkMarketBanner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            RequestOptions requestOptions = RequestOptions.centerCropTransform();
                            Glide.with(mContext).load(CommonResource.BASEURL_8089 + bannerBeans.get(position).getImage())
                                    .apply(requestOptions)
                                    .into((ImageView) view);

                        }
                    });
                }
                // 设置XBanner的页面切换特效
                elkMarketBanner.setPageTransformer(Transformer.Default);
                // 设置XBanner页面切换的时间，即动画时长
                elkMarketBanner.setPageChangeDuration(1000);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    //获取列表
    public void getPetList() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.PETLIST, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("市场列表" + result);
                PetListBean petListBean = JSON.parseObject(result, PetListBean.class);
                dataBeans.clear();
                dataBeans.addAll(petListBean.getData());
                getView().refresh();
                if (dataBeans.size() != 0) {
                    if (petListAdapter == null) {
                        petListAdapter = new PetListAdapter(mContext, dataBeans, R.layout.content_gong1);
                        if (getView() != null) {
                            getView().loadUI(petListAdapter);
                        }
                    } else {
                        petListAdapter.notifyDataSetChanged();
                    }
                }

                int temp = 0;
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (dataBeans.get(i).getState() == 5) {
                        if (dataBeans.get(i).getRemaining_time() > 0 && dataBeans.get(i).getRemaining_time() <= 90) {
                            temp++;
                            dataBeans.get(i).setRemaining_time(dataBeans.get(i).getRemaining_time() - 1);
                            Message message = handler.obtainMessage(1);
                            message.arg1 = i;
                            handler.sendMessage(message);
                        }
                    }
                }
                if (temp > 0) {
                    times();
                }

                petListAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String stringValue = SPUtil.getStringValue(CommonResource.CARD);
                                LogUtil.e("=============="+stringValue);
                                if (!TextUtils.isEmpty(stringValue)) {
                                    if (dataBeans.get(index).getState() == 3) {
                                        toBuy(dataBeans.get(index).getId());
                                    } else if (dataBeans.get(index).getState() == 1) {
                                        prepare(dataBeans.get(index).getId());
                                    }
                                } else {
                                    Toast.makeText(mContext, "您还没有实名认证,请完善信息", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });


            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                getView().refresh();
            }
        }));
    }

    private void times() {
        if (thread == null) {
            thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        while (isTrue) {
                            Thread.sleep(1000);
                            int temp = 0;
                            for (int i = 0; i < dataBeans.size(); i++) {
                                if (dataBeans.get(i).getState() == 5) {
                                    if (dataBeans.get(i).getRemaining_time() > 0 && dataBeans.get(i).getRemaining_time() <= 90) {
                                        temp++;
                                        dataBeans.get(i).setRemaining_time(dataBeans.get(i).getRemaining_time() - 1);
                                        Message message = handler.obtainMessage(1);
                                        message.arg1 = i;
                                        handler.sendMessage(message);
                                    }
                                }
                            }
                            isTrue = (temp > 0);
                        }

                        getPetList();
                        thread = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    public void setViewSingleLine(List<String> list) {
        data.addAll(list);
        views.clear();
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_home_marquee_view, null);
            //初始化布局的控件
            TextView marqueeMessage = moreView.findViewById(R.id.marquee_message);
            //进行对控件赋值
            marqueeMessage.setText(data.get(i));
            //添加到循环滚动数组里面去
            views.add(moreView);
            if (getView() != null) {
                getView().lodeMarquee(views);
            }
        }
    }

    private void toBuy(int id) {
        Map type = MapUtil.getInstance().addParms("type", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.ADDORDER, type, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("抢购:" + result);
                GetCodeBean bean = JSON.parseObject(result, GetCodeBean.class);
                if (bean.getErrcode() == 0) {
                    Toast.makeText(mContext, "耐心等待抢购结果", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }

                PopUtils.getQiang(mContext, id);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                LogUtil.e(errorCode + "------抢购------" + errorMsg);
            }
        }));
    }

    public void getResult(String id) {
        Map type = MapUtil.getInstance().addParms("type", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.ROBRESULT, type, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("抢购结果:" + result);
                QiangBean bean = JSON.parseObject(result, QiangBean.class);
                if (bean.getRes().getStat() == 1) {
                    PopUtils.createPopQiang(mContext);
                } else {
                    Toast.makeText(mContext, bean.getRes().getData(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                LogUtil.e(errorCode + "------抢购结果------" + errorMsg);
            }
        }));
    }

    private void prepare(int id) {
        Map type = MapUtil.getInstance().addParms("type", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHead(CommonResource.APPOINT, type, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("预约:" + result);
                UpdateMessageBean bean = JSON.parseObject(result, UpdateMessageBean.class);
                if (bean.getErrcode() == 0) {
                    Toast.makeText(mContext, "预约成功", Toast.LENGTH_SHORT).show();
                    getPetList();
                } else {
                    Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                LogUtil.e(errorCode + "-------预约-------" + errorMsg);
            }
        }));

    }

    @Override
    protected void onViewDestroy() {

    }
}
