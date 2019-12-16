package com.example.zefeiqukuai.mine;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.UserAcountBean;
import com.example.zefeiqukuai.bean.UserInfoBean;

public interface MineView extends IView {
    void loadUserInfo(UserInfoBean userInfoBean);

    void loadUserAcount(UserAcountBean userAcountBean);
}
