package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.shen;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.LingZhongAdapter;

public interface LingShenView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(LingZhongAdapter dealAdapter);

}
