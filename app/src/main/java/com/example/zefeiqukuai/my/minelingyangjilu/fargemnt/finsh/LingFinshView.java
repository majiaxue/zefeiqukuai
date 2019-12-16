package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.finsh;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.YiLingYangAdapter;

public interface LingFinshView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(YiLingYangAdapter dealAdapter);

}
