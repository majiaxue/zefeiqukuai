package com.example.zefeiqukuai.service;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.FaJianXiangBean;

import java.util.List;

public interface ServiceView extends IView {
    void loadSuccess(List<FaJianXiangBean> faJianXiangBeans);
}
