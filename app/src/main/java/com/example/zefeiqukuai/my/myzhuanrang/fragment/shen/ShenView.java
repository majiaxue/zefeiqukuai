package com.example.zefeiqukuai.my.myzhuanrang.fragment.shen;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.DealAdapter;

public interface ShenView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(DealAdapter dealAdapter);
}
