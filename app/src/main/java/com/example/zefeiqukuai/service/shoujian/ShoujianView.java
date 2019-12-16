package com.example.zefeiqukuai.service.shoujian;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.service.adapter.ShouJianAdapter;

public interface ShoujianView extends IView {
    void loadRv(ShouJianAdapter adapter);
}
