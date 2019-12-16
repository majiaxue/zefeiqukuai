package com.example.zefeiqukuai.main;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.ConfigBean;

public interface MainView extends IView {

    void toHome();

    void openMusic();

    void loadConfig(ConfigBean configBean);
}
