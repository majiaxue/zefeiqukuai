package com.example.zefeiqukuai.my.shoukuan.fragment.zhifubao;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.ZFBBean;

public interface ZhiFuBaoView extends IView {
    void takePhoto(Intent captureIntent);

    void photoAlbum(Intent intent);

    void showHeader(Uri fileUri);

    void cropPhoto(Intent intent);

    void loadZfbData(ZFBBean zfbBean);
}
