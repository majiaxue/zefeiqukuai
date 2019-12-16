package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.zhong;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.LingZhongAdapter;

public interface ZhongView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(LingZhongAdapter dealAdapter);

}
