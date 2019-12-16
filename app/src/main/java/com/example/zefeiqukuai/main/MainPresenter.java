package com.example.zefeiqukuai.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.AppManager;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.OnClearCacheListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.base.utils.SelfDialog;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.CheckUpBean;
import com.example.zefeiqukuai.bean.ConfigBean;
import com.example.zefeiqukuai.bean.MusicBean;
import com.example.zefeiqukuai.elkmarket.ElkMarketFragment;
import com.example.zefeiqukuai.mine.MineFragment;
import com.example.zefeiqukuai.service.ServiceFragment;
import com.example.zefeiqukuai.welfare.WelfareFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MainPresenter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;

    private ElkMarketFragment elkMarketFragment;
    private WelfareFragment welfareFragment;
    private ServiceFragment serviceFragment;
    private MineFragment mineFragment;

    private ProgressBar mProgress;
    private AlertDialog alertDialog;

    private String clientVersion;
    private static final int DOWNLOADING = 1; // 表示正在下载
    private static final int DOWNLOADED = 2; // 下载完毕
    private static final int DOWNLOAD_FAILED = 3; // 下载失败
    private static final String savePath = "/sdcard/zfqk/apk"; // apk保存到SD卡的路径
    private static final String saveFileName = savePath + "/zfqk"; // 完整路径名

    private int progress; // 下载进度
    private boolean cancelFlag = false; // 取消下载标志位
    private CheckUpBean checkUpBean;
    private String newVersion;


    public MainPresenter(Context context) {
        super(context);
    }

    /**
     * 更新UI的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOADED:
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    initInstall();
                    break;
                case DOWNLOAD_FAILED:
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_LONG).show();
                    try {
                        String apkFile = saveFileName + newVersion + ".apk";
                        File file = new File(apkFile);
                        file.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        elkMarketFragment = new ElkMarketFragment();
        welfareFragment = new WelfareFragment();
        serviceFragment = new ServiceFragment();
        mineFragment = new MineFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, elkMarketFragment)
                .add(resId, welfareFragment)
                .add(resId, this.mineFragment)
                .add(resId, serviceFragment);
        transaction.show(elkMarketFragment)
                .hide(welfareFragment)
                .hide(this.mineFragment)
                .hide(serviceFragment)
                .commit();
    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (resId == R.id.main_home) {
            transaction.show(elkMarketFragment)
                    .hide(welfareFragment)
                    .hide(mineFragment)
                    .hide(serviceFragment)
                    .commit();

        } else if (resId == R.id.main_classify) {
            transaction.show(welfareFragment)
                    .hide(elkMarketFragment)
                    .hide(serviceFragment)
                    .hide(mineFragment)
                    .commit();

        } else if (resId == R.id.main_mine) {
            transaction.show(mineFragment)
                    .hide(serviceFragment)
                    .hide(elkMarketFragment)
                    .hide(welfareFragment)
                    .commit();

        } else if (resId == R.id.main_community) {
            transaction.show(serviceFragment)
                    .hide(mineFragment)
                    .hide(welfareFragment)
                    .hide(elkMarketFragment)
                    .commit();
        }
    }

    public void getConfig() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.CONFIG, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                  LogUtil.e("广告"+result);
                ConfigBean configBean = JSON.parseObject(result, ConfigBean.class);
                LogUtil.e("========================"+configBean.toString());
                if (getView()!=null){
                    getView().loadConfig(configBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("广告onError"+errorCode+errorMsg);
            }
        }));
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit();
            System.exit(0);
        }
    }

    @Override
    protected void onViewDestroy() {
        // mContext.unregisterReceiver(receiver);
        EventBus.getDefault().unregister(this);
        SPUtil.addParm(CommonResource.TAN_CONTENT, "");
    }

    private void initInstall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(mContext);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(mContext);
            } else {
                installAPK();
            }
        }
    }

    /**
     * 开启设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        final SelfDialog selfDialog = new SelfDialog(mContext);
        selfDialog.setTitle("提示");
        selfDialog.setMessage("未开启自动安装，无法更新程序");
        selfDialog.setNoOnclickListener("前往设置", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
                Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                ((Activity) mContext).startActivityForResult(intent, 0x111);
            }
        });
        selfDialog.setYesOnclickListener("取消", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
        PopUtils.setTransparency(mContext, 0.3f);
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtils.setTransparency(mContext, 1.0f);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    public void checkUp() {
        getVersionInfo();
        Map map = MapUtil.getInstance().addParms("type", "0").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHead(CommonResource.CHECKVERSION, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("检查更新：" + result);
                checkUpBean = JSON.parseObject(result, CheckUpBean.class);
                String[] split = clientVersion.split("\\.");
                newVersion = checkUpBean.getVersion();

                if (newVersion != null) {
                    String[] split1 = newVersion.split("\\.");
                    File apkFile = new File(saveFileName + newVersion + ".apk");
                    if ((Integer.valueOf(split[0]) < Integer.valueOf(split1[0])) || (Integer.valueOf(split[0]) == Integer.valueOf(split1[0]) && Integer.valueOf(split[1]) < Integer.valueOf(split1[1]))) {
                        if (apkFile.exists()) {
                            PopUtils.update(mContext, newVersion, checkUpBean.getContent(), true, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            installAPK();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        } else {
                            PopUtils.update(mContext, newVersion, checkUpBean.getContent(), false, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            writeToDisk(checkUpBean.getUrl());
                                            showDialog();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        }

                    } else if (Integer.valueOf(split[0]) == Integer.valueOf(split1[0]) && Integer.valueOf(split[1]) == Integer.valueOf(split1[1]) && Integer.valueOf(split[2]) < Integer.valueOf(split1[2])) {
                        if (apkFile.exists()) {
                            PopUtils.update(mContext, newVersion, checkUpBean.getContent(), true, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            installAPK();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        } else {
                            PopUtils.update(mContext, newVersion, checkUpBean.getContent(), false, new OnClearCacheListener() {
                                @Override
                                public void setOnClearCache(final PopupWindow pop, View confirm) {
                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            writeToDisk(checkUpBean.getUrl());
                                            showDialog();
                                            pop.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("更新：" + errorCode + "------------" + errorMsg);
            }
        }));
    }

    private void getVersionInfo() {

        PackageManager pm = mContext.getPackageManager();

        try {
            // 0代表拿所有的信息 packageInfo 是一个bean对象 是对整个清单文件的封装
            // ApplicationInfo是PackageInfo的子集
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            clientVersion = packageInfo.versionName;
            LogUtil.e("当前版本：" + clientVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在更新");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.dialog_update, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void writeToDisk(final String apkUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String apkFile = saveFileName + newVersion + ".apk";
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[64];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); // 点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    LogUtil.e("------>" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 下载完成后自动安装apk
     */
    public void installAPK() {
        File apkFile = new File(saveFileName + newVersion + ".apk");
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getPackageName(), apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            String path = apkFile.getAbsolutePath();
            Uri uri = Uri.parse("file://" + path);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }

    public void getMusic() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getDataWithout(CommonResource.MUSIC);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("音乐----------->" + result);
                MusicBean musicBean = JSON.parseObject(result, MusicBean.class);
                if (musicBean.getErrcode() == 0) {
                    SPUtil.addParm(CommonResource.MUSICURL, CommonResource.BASEURL_8089 + musicBean.getRes().getMusic());
                    if (getView() != null) {
                        getView().openMusic();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----音乐------" + errorMsg);
            }
        }));
    }
}
