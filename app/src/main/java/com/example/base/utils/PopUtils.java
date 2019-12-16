package com.example.base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.net_change_util.OnGetListener;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.EventBusBean;
import com.example.zefeiqukuai.bean.GetCodeBean;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PopUtils {
    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void parmsPop(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_getmoney, null);
        ImageView close = view.findViewById(R.id.pop_close);
        final PopupWindow popupWindow = new PopupWindow(view, (int) context.getResources().getDimension(R.dimen.dp_259), (int) context.getResources().getDimension(R.dimen.dp_142), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    //转盘抽奖
    public static void zhuanPop(final Context context, int day, double cost) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tan03, null);
        ImageView close = view.findViewById(R.id.zhuanpan_close);
        TextView conttent1 = view.findViewById(R.id.pop_zhuanpan_content2);
        TextView conttent2 = view.findViewById(R.id.pop_zhuanpan_content3);
        conttent1.setText("二:每抽折扣一次需扣除" + ArithUtil.exact(cost, 2) + "的动态收益");
        conttent2.setText("三:每天可抽" + day + "次");
        final PopupWindow popupWindow = new PopupWindow(view, (int) context.getResources().getDimension(R.dimen.dp_350), (int) context.getResources().getDimension(R.dimen.dp_200), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    //获取验证码弹框
    public static void yanPop(final Context context, OnGetCodeListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tankuang4, null);
        TextView conttent1 = view.findViewById(R.id.tan_psw);
        EditText conttent2 = view.findViewById(R.id.tan_que_psw);
        ImageView close = view.findViewById(R.id.close);
        TextView getCode = view.findViewById(R.id.register_get_code3);
        TextView ok = view.findViewById(R.id.tv_ok);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        conttent1.setText(SPUtil.getStringValue(CommonResource.MENBER));
        final PopupWindow popupWindow = new PopupWindow(view, (int) context.getResources().getDimension(R.dimen.dp_320), (int) context.getResources().getDimension(R.dimen.dp_220), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PhoneNumUtil.isMobileNO(conttent1.getText().toString())) {
//            Map map = MapUtil.getInstance().addParms("phone", phone).build();
                    Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getDataWithout(CommonResource.SENDSMS + "?phone=" + conttent1.getText().toString());
                    RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("获取验证码：" + result);
                            GetCodeBean getCodeBean = JSON.parseObject(result, GetCodeBean.class);
                            if (getCodeBean.getErrcode() == 0) {

                                getCode.setEnabled(false);
                                getCode.setBackgroundResource(R.drawable.bg_get_code);
                                CountDownTimerUtil.startTimer(context, getCode);
                                Toast.makeText(context, "短信验证码已发送到您手机", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            Toast.makeText(context, "" + errorMsg, Toast.LENGTH_SHORT).show();
                            LogUtil.e(errorCode + "-----------" + errorMsg + errorCode);
                        }
                    }));
                } else {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        listener.onClick(popupWindow, conttent2, ok);
    }

    //支付方式弹框
    public static void payPop(final Context context, OnGetPayListener listener) {
        int[] type = {1};
        View view = LayoutInflater.from(context).inflate(R.layout.pay_type, null);
        LinearLayout paymentBank = view.findViewById(R.id.payment_bank);
        LinearLayout paymentZfb = view.findViewById(R.id.payment_zfb);
        LinearLayout paymentWeiXin = view.findViewById(R.id.payment_weixin);
        ImageView paymentWeiXinImg = view.findViewById(R.id.payment_weixin_img);
        ImageView paymentZfbImg = view.findViewById(R.id.payment_zfb_img);
        ImageView paymentBankImg = view.findViewById(R.id.payment_bank_img);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_233), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        paymentBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                paymentBankImg.setImageResource( R.drawable.icon_xuanzhong );
//                paymentZfbImg.setImageResource(R.drawable.icon_weixuanzhong );
//                paymentWeiXinImg.setImageResource(R.drawable.icon_weixuanzhong );
                listener.onClick(3);
                popupWindow.dismiss();

            }
        });
        paymentZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                paymentBankImg.setImageResource( R.drawable.icon_weixuanzhong );
//                paymentZfbImg.setImageResource(R.drawable.icon_xuanzhong );
//                paymentWeiXinImg.setImageResource(R.drawable.icon_weixuanzhong );
                listener.onClick(1);
                popupWindow.dismiss();
            }
        });
        paymentWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                paymentBankImg.setImageResource( R.drawable.icon_weixuanzhong );
//                paymentZfbImg.setImageResource(R.drawable.icon_weixuanzhong );
//                paymentWeiXinImg.setImageResource(R.drawable.icon_xuanzhong );
                listener.onClick(2);
                popupWindow.dismiss();
            }
        });
    }

    public static void getLangure(final Context context, OnGetListener listener) {

        final int[] type = {1};
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tan07, null);
        LinearLayout popLinZh = view.findViewById(R.id.pop_zh_lin);
        LinearLayout popEn = view.findViewById(R.id.pop_en);
        TextView ok = view.findViewById(R.id.duihuan_ok);
        TextView cancel = view.findViewById(R.id.duihuan_cancel);
        ImageView ChImg = view.findViewById(R.id.pop_ch_img);
        ImageView EnImg = view.findViewById(R.id.pop_en_img);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_150), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(type[0]);
                popupWindow.dismiss();
            }
        });

        popLinZh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type[0] = 1;
                ChImg.setImageResource(R.drawable.icon_xuanzhong11);
                EnImg.setImageResource(R.drawable.icon_weixuanzhong11);
            }
        });
        popEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type[0] = 2;
                ChImg.setImageResource(R.drawable.icon_weixuanzhong11);
                EnImg.setImageResource(R.drawable.icon_xuanzhong11);
            }
        });
    }

    public static void getQiang(final Context context, int id) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_qiang, null);
        ImageView popQiang = view.findViewById(R.id.pop_qiang);
        TextView qiangTime = view.findViewById(R.id.qiang_time);
        ImageView close = view.findViewById(R.id.zhuanpan_close);
        Glide.with(context).load(R.drawable.qiang).into(popQiang);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
        new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long l) {
                qiangTime.setText(l / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                popupWindow.dismiss();
                EventBus.getDefault().post(new EventBusBean(CommonResource.QIANGGOU, id + ""));
            }
        }.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }


    public static void createPop(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    public static void createPopQiang(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tan05, null);
        ImageView close = view.findViewById(R.id.pop_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public static void createPopResult(final Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tan06, null);
        TextView viewById = view.findViewById(R.id.zhongjiang_content);
        viewById.setText("恭喜您获得" + text + "收益");
        ImageView close = view.findViewById(R.id.pop_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public static void createPopCenter(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void viewPopBottom(View text, final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setAnimationStyle(R.style.animScale);
//        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(text, 0, 0, Gravity.BOTTOM);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }


    public static void changeHeader(final Context context, OnChangeHeaderListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_change_header, null);
        TextView takePhoto = view.findViewById(R.id.pop_change_header_camera);
        TextView photoAlbum = view.findViewById(R.id.pop_change_header_xiangce);
        TextView cancel = view.findViewById(R.id.pop_change_header_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        listener.setOnChangeHeader(popupWindow, takePhoto, photoAlbum);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void seeBigImg(final Context context, String url) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_full_image, null);
        ImageView img = inflate.findViewById(R.id.pop_full_img);
        Glide.with(context).load(url).into(img);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void update(final Context context, String versions, String message, boolean isHas, OnClearCacheListener listener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_geng_xin, null);
        TextView versionsText = inflate.findViewById(R.id.pop_gen_xin_versions);
        TextView messageText = inflate.findViewById(R.id.pop_gen_xin_text_message);
        TextView updateBottom = inflate.findViewById(R.id.pop_gen_xin_bottom);
        ImageView img = inflate.findViewById(R.id.pop_gen_xin_cancel);
        versionsText.setText("V" + versions);
        messageText.setText(message);
        if (isHas) {
            updateBottom.setText("立即安装");
        }
        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
//                AppManager.getInstance().AppExit();
            }
        });

        listener.setOnClearCache(popupWindow, updateBottom);
    }

}
