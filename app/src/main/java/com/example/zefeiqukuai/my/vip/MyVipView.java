package com.example.zefeiqukuai.my.vip;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.VipBean;

import java.util.List;

public interface MyVipView extends IView {
    void loadRank(List<VipBean> vipBeans);

}
