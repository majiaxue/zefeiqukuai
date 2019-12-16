package com.example.zefeiqukuai.my.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.CacheUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.NewVersionBean;
import com.example.zefeiqukuai.main.MainActivity;
import com.example.zefeiqukuai.my.setting.mymessage.MyMessageActivity;

import java.io.IOException;

import butterknife.BindView;

public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.mine_setting)
    ImageView mineSetting;
    @BindView(R.id.setting_msg)
    RelativeLayout settingMsg;
    @BindView(R.id.setting_music)
    ImageView settingMusic;
    @BindView(R.id.setting_version)
    RelativeLayout settingVersion;
    @BindView(R.id.setting_huancun_text)
    TextView settingHuancunText;
    @BindView(R.id.setting_qingchu)
    RelativeLayout settingQingchu;
    @BindView(R.id.setting_btn_logout)
    TextView settingBtnLogout;
    @BindView(R.id.setting_huancun_version)
    TextView settingHuancunVersion;

    NewVersionBean newVersionBean = new NewVersionBean();
    private String clientVersion;
    private String totalCacheSize;
    private MainActivity mainActivity;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mysetting;
    }

    @Override
    public void initData() {
        includeTitle.setText("设置");
        presenter.getVersion();
        getVersionInfo();
        mainActivity = new MainActivity();
        totalCacheSize = CacheUtil.getTotalCacheSize(this);
        settingHuancunText.setText(totalCacheSize);
        isOpen();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //个人信息
        settingMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyMessageActivity.class);
                startActivity(intent);
            }
        });
        settingVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientVersion.equals(newVersionBean.getVersion())) {
                    Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "有新版本", Toast.LENGTH_SHORT).show();
                }
            }
        });
        settingQingchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clearCache(totalCacheSize);
            }
        });
        settingBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logout();
            }
        });

        settingMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.addParm(CommonResource.OPENMUSIC, !SPUtil.isOpenMusic());
                isOpen();
                open_close(SPUtil.isOpenMusic());
            }
        });
    }

    private void isOpen() {
        if (SPUtil.isOpenMusic()) {
            settingMusic.setImageResource(R.drawable.shezhi_kaiqi);
        } else {
            settingMusic.setImageResource(R.drawable.shezhi_guanbi);
        }
    }

    private void open_close(boolean isOpen) {
        if (isOpen) {
//            MusicService.getInstance().play();
//            mainActivity.start();
            MainActivity.player.start();
        } else {
//            mainActivity.stop();
            MainActivity.player.stop();
            MainActivity.player.stop();//停止播放
            try {
                MainActivity.player.prepare();//预载入音乐
            } catch (IllegalStateException e) {
                // TODO 自己主动生成的 catch 块
                e.printStackTrace();
            } catch (IOException e) {
                // TODO 自己主动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    @Override
    public SettingView createView() {
        return this;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    public void getVersionInfo() {

        PackageManager pm = getPackageManager();
        try {
            // 0代表拿所有的信息 packageInfo 是一个bean对象 是对整个清单文件的封装
            // ApplicationInfo是PackageInfo的子集
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            clientVersion = packageInfo.versionName;
            settingHuancunVersion.setText(clientVersion);
            LogUtil.e("当前版本：" + clientVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadVersion(NewVersionBean newVersionBean) {
        this.newVersionBean = newVersionBean;

    }

    @Override
    public void clearSuccess() {
        totalCacheSize = CacheUtil.getTotalCacheSize(this);
        settingHuancunText.setText(totalCacheSize);
    }


}
