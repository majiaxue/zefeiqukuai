package com.example.zefeiqukuai.music_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;

import java.io.IOException;

public class MusicService extends Service {
    public MusicBinder musicBinder;
    public static MusicService musicService;
    private MediaPlayer player;//声明一个MediaPlayer对象

    public static MusicService getInstance() {
        return musicService == null ? new MusicService() : musicService;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    //创建服务
    @Override
    public void onCreate() {
        // 当player对象为空时
//        if (player == null) {
//            player = MediaPlayer.create(MusicService.this, Uri
//                    .parse(SPUtil.getStringValue(CommonResource.MUSICURL)));//实例化对象，通过播放本机server上的一首音乐
//            player.setLooping(true);//设置不循环播放
//        }
        musicBinder = new MusicBinder();
        if (player == null) {
            player = MediaPlayer.create(MusicService.this, Uri
                    .parse(SPUtil.getStringValue(CommonResource.MUSICURL)));//实例化对象，通过播放本机server上的一首音乐
            player.setLooping(true);//设置不循环播放
            LogUtil.e("------------->" + SPUtil.getStringValue(CommonResource.MUSICURL));
        }
        super.onCreate();
    }

    //销毁服务
    @Override
    public void onDestroy() {
        //当对象不为空时
        if (player != null) {
            player.stop();//停止播放
            player.release();//释放资源
            player = null;//把player对象设置为null
        }
        super.onDestroy();
    }

    //開始服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO 自己主动生成的方法存根
        Bundle b = intent.getExtras();//获取到从MainActivity类中传递过来的Bundle对象
        int op = b.getInt("msg");//再获取到MainActivity类中op的值
        switch (op) {
            case 1://当op为1时。即点击播放button时
                play();//调用play()方法
                break;
            case 2://当op为2时，即点击暂停button时
                pause();//调用pause()方法
                break;
            case 3://当op为3时。即点击停止button时
                stop();//调用stop()方法
                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
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

    //暂停播放音乐方法
    public void pause() {
        // 当player对象正在播放时而且player对象不为空时
        if (player.isPlaying() && player != null) {
            player.pause();//暂停播放音乐
        }
    }

    //播放音乐方法
    public void play() {
        // 当player对象不为空而且player不是正在播放时
        if (player != null) {
            player.start();//開始播放音乐
        }
    }

    class MusicBinder extends Binder {
        void start() {
            play();
        }
    }
}
