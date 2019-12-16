package com.example.zefeiqukuai.my.myzhuanrang.fragment.finsh;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.DealAdapter;

public interface FinshView extends IView {
    void refreshSuccesss();

    void loadDealAdapter(DealAdapter dealAdapter);

}
