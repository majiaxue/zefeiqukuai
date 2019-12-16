package com.example.zefeiqukuai.my.mypet;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.ProcessDialogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.MyAdapter;
import com.example.zefeiqukuai.bean.InviteBean;
import com.example.zefeiqukuai.bean.MyPetBean;
import com.example.zefeiqukuai.bean.PetPriceBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MyPetPresenter extends BasePresenter<MyPetView> {

    private MyAdapter myAdapter;
    private List<MyPetBean> myPetBeans;

    public MyPetPresenter(Context context) {
        super(context);
    }

    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public void getPet() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.CONVERLIST, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("兑换宠物列表" + result);
                myPetBeans = JSON.parseArray(result, MyPetBean.class);
                myAdapter = new MyAdapter(mContext, myPetBeans, R.layout.content_gong1);
                if (getView() != null) {
                    getView().loadData(myAdapter);
                }
                myAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getPrice(myPetBeans.get(index).getId());

                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("兑换宠物列表onError" + errorCode + errorMsg);
            }
        }));
    }

    //抢购宠物所需要金额
    public void getPrice(int pet_id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pet_id", pet_id);
        String pddGoodsSearchVoStr = JSON.toJSONString(jsonObject);
        LogUtil.e("------------" + pddGoodsSearchVoStr);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.GETPRICE, body, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("兑换宠物金额" + result);
                final int[] type = {5};
                PetPriceBean petPriceBean = JSON.parseObject(result, PetPriceBean.class);
                if (petPriceBean != null) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.pop_tan04, null);
                    LinearLayout chengyijin = view.findViewById(R.id.chengyijin_lin_pop);
                    LinearLayout dongtaishouyi = view.findViewById(R.id.dongtaishouyi_lin);
                    LinearLayout zhuancunshouyi = view.findViewById(R.id.zhuancunshouyi_lin);
                    TextView cancel = view.findViewById(R.id.duihuan_cancel);
                    TextView sureOk = view.findViewById(R.id.duihuan_ok);
                    TextView cyjContent = view.findViewById(R.id.chengyijin_content);
                    TextView dtsyContent = view.findViewById(R.id.dongtaishouyi_content);
                    TextView zcsy = view.findViewById(R.id.zhuancunshouyi_content);
                    ImageView cyjImg = view.findViewById(R.id.chengyijin_img);
                    ImageView zcsyImg = view.findViewById(R.id.zhuancunshouyi_img);
                    ImageView dtsyImg = view.findViewById(R.id.dongtaishouyi_img);
                    ImageView clise = view.findViewById(R.id.clise);
                    cyjContent.setText("诚意金(将扣除" + petPriceBean.getData().getSincerity() + "诚意金)");
                    dtsyContent.setText("动态收益(将扣除" + petPriceBean.getData().getTrends() + "动态收益)");
                    zcsy.setText("转存收益(将扣除" + petPriceBean.getData().getTurn() + "转存收益)");
                    final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) mContext.getResources().getDimension(R.dimen.dp_244), true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
                    popupWindow.showAtLocation(new View(mContext), Gravity.CENTER, 0, 0);
                    setTransparency(mContext, 0.3f);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setTransparency(mContext, 1f);
                        }
                    });
                    clise.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                    // 2动态收益，5诚意金，6转存收益
                    //诚意金
                    chengyijin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type[0] = 5;
                            cyjImg.setImageResource(R.drawable.icon_xuanzhong11);
                            dtsyImg.setImageResource(R.drawable.icon_weixuanzhong11);
                            zcsyImg.setImageResource(R.drawable.icon_weixuanzhong11);
                        }
                    });
                    dongtaishouyi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type[0] = 2;
                            cyjImg.setImageResource(R.drawable.icon_weixuanzhong11);
                            dtsyImg.setImageResource(R.drawable.icon_xuanzhong11);
                            zcsyImg.setImageResource(R.drawable.icon_weixuanzhong11);
                        }
                    });
                    zhuancunshouyi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type[0] = 6;
                            cyjImg.setImageResource(R.drawable.icon_weixuanzhong11);
                            dtsyImg.setImageResource(R.drawable.icon_weixuanzhong11);
                            zcsyImg.setImageResource(R.drawable.icon_xuanzhong11);
                        }
                    });
                    sureOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            ProcessDialogUtil.showProcessDialog(mContext);
                            getConvert(pet_id, type[0]);
                        }
                    });
                }else {
                    Toast.makeText(mContext, "数据为空" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }

    //兑换宠物
    public void getConvert(int pet_id, int currency_id) {
        ProcessDialogUtil.showProcessDialog(mContext);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pet_id", pet_id);
        jsonObject.put("currency_id", currency_id);
        String pddGoodsSearchVoStr = JSON.toJSONString(jsonObject);
        LogUtil.e("------------" + pddGoodsSearchVoStr);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.CONVER, body, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("兑换宠物" + result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode() == 0) {
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "兑换成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("兑换宠物onError" + errorCode + errorMsg);
            }
        }));
    }


    @Override
    protected void onViewDestroy() {

    }
}
