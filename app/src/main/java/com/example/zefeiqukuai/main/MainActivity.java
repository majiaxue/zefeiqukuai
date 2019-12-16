package com.example.zefeiqukuai.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.base.utils.WebSocketManager;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.ConfigBean;

import java.io.IOException;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.main_group)
    RadioGroup mainGroup;
    private final String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private final int REQUEST_CODE = 0xa123;
    //private String url = "ws://192.168.0.125:8091";
    private String url = "ws://121.40.146.147:8096";

    public static MediaPlayer player;//声明一个MediaPlayer对象

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        WebSocketManager.getInstance().init(url);
        presenter.loadData(getSupportFragmentManager(), R.id.main_frame);
        presenter.getConfig();
        initPermission();
        presenter.getMusic();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    presenter.checkUp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void initClick() {
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });

    }

    private void initPermission() {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, perms, REQUEST_CODE);
            }
        }
    }

    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void openMusic() {
        if (player == null) {
            player = MediaPlayer.create(this, Uri
                    .parse(SPUtil.getStringValue(CommonResource.MUSICURL)));//实例化对象，通过播放本机server上的一首音乐
            player.setLooping(true);//设置不循环播放
            LogUtil.e("------------->" + SPUtil.getStringValue(CommonResource.MUSICURL));
        }
//        startService(new Intent(this.getApplicationContext(), MusicService.class));
        if (SPUtil.isOpenMusic()) {
            start();
        }
    }

    @Override
    public void loadConfig(ConfigBean configBean) {
        int type = configBean.getAdv_info().getType();
        //图片
        if (type ==0){

        }else if (type ==1){//视频

        }
    }

    public void start() {
        if (player != null) {
            player.start();
        }
    }

    //停止播放音乐方法
    public void stop() {
        // 当player对象不为空时
        if (player != null) {
            player.seekTo(0);//设置从头開始
            player.stop();//停止播放
            try {
                player.prepare();//预载入音乐
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
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();//停止播放
            player.release();//释放资源
            player = null;//把player对象设置为null
        }
    }

    @Override
    public void toHome() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
