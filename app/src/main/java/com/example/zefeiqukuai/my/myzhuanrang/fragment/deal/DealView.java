package com.example.zefeiqukuai.my.myzhuanrang.fragment.deal;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.DealAdapter;

public interface DealView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(DealAdapter dealAdapter);
}
