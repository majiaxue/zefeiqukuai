package com.example.zefeiqukuai.my.luer.chongzhi;

import android.content.Intent;
import android.net.Uri;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.BankCardBean;
import com.example.zefeiqukuai.bean.RealMoneyBean;
import com.example.zefeiqukuai.bean.WeChatBean;
import com.example.zefeiqukuai.bean.ZFBBean;

public interface ChongZhiView extends IView {
    void takePhoto(Intent captureIntent);

    void photoAlbum(Intent intent);

    void showHeader(Uri fileUri);

    void cropPhoto(Intent intent);
    void loadZfbData(ZFBBean zfbBean);

    void loadWxData(WeChatBean weChatBean);

    void loadBank(BankCardBean bankCardBean);

    void loadMoney(RealMoneyBean realMoneyBean);

    void paycommit();

}
