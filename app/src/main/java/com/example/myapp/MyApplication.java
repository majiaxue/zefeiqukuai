package com.example.myapp;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.utils.SPUtil;
import com.example.zefeiqukuai.music_service.MusicService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化fresco
        initFresco();
        SPUtil.getInstance(this);
        ARouter.init(MyApplication.this);
    }
    private void initFresco() {

//        //磁盘内存配置
//        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(getApplicationContext())
//                .setBaseDirectoryName(FRESCO_CACHE_DIR)
//                .setBaseDirectoryPath(getCacheDir())
//                .setMaxCacheSize(MAX_DISK_SIZE)
//                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_SIZE_ON_LOW_DISK_SPACE)
//                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_SIZE_ON_VERY_LOW_DISK_SPACE)
//                .build();
        //对ImagePipelineConfig进行一些配置
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
                .setDownsampleEnabled(true)              // 对图片进行自动缩放
                .setResizeAndRotateEnabledForNetwork(true) // 对网络图片进行resize处理，减少内存消耗
                .setBitmapsConfig(Bitmap.Config.RGB_565) //图片设置RGB_565，减小内存开销 fresco默认情况下是RGB_8888
//                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, config);
        Fresco.initialize(this);
    }
}
