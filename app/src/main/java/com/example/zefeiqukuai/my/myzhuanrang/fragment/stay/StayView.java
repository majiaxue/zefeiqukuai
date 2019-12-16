package com.example.zefeiqukuai.my.myzhuanrang.fragment.stay;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.StaySaleAdapter;

public interface StayView extends IView {
    void refreshSuccesss();

    void loadStaySaleAdapter(StaySaleAdapter staySaleAdapter);

}
