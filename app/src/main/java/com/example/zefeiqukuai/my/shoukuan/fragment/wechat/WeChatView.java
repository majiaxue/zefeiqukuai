package com.example.zefeiqukuai.my.shoukuan.fragment.wechat;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.WeChatBean;

public interface WeChatView extends IView {
    void takePhoto(Intent captureIntent);

    void photoAlbum(Intent intent);

    void showHeader(Uri fileUri);

    void cropPhoto(Intent intent);

    void loadZfbData(WeChatBean weChatBean);
}
