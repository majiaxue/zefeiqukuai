package com.example.zefeiqukuai.my.setting.mymessage;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.UserInfoBean;

public interface MyMessageView extends IView {
    void takePhoto(Intent captureIntent);

    void photoAlbum(Intent intent);

    void cropPhoto(Intent intent);

    void loadUserInfo(UserInfoBean userInfoBean);

    void showHeader(Uri fileUri);
}
