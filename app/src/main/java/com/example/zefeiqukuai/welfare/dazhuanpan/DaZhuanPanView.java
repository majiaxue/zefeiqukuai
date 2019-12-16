package com.example.zefeiqukuai.welfare.dazhuanpan;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.WheelRecordAdapter;
import com.example.zefeiqukuai.bean.ChouJiangResultBean;
import com.example.zefeiqukuai.bean.DaZhuanPanBean;
import com.example.zefeiqukuai.bean.UserAcountBean;

public interface DaZhuanPanView extends IView {
    void loadData(DaZhuanPanBean daZhuanPanBean);

    void loadRv(WheelRecordAdapter wheelRecordAdapter);

    void loadResult(ChouJiangResultBean resultBean);

    void loadUserAcount(UserAcountBean userAcountBean);
}
