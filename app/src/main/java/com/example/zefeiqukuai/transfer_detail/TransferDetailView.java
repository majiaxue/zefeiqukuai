package com.example.zefeiqukuai.transfer_detail;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.PetDetailBean;

public interface TransferDetailView extends IView {


    void loadData(PetDetailBean petDetailBean);
}
