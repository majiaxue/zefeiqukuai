package com.example.zefeiqukuai.pet_detail;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.PetDetailBean;

public interface PetDetailView extends IView {
    void loadData(PetDetailBean petDetailBean);

    void takePhoto(Intent captureIntent);

    void photoAlbum(Intent intent);

    void cropPhoto(Intent intent);

    void showHeader(Uri fileUri);

    void paycommit();

}
