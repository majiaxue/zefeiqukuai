package com.example.zefeiqukuai.my.setting;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.NewVersionBean;

public interface SettingView extends IView {
    void loadVersion(NewVersionBean newVersionBean);

    void clearSuccess();

}
