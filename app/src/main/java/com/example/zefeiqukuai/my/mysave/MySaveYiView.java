package com.example.zefeiqukuai.my.mysave;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.JiaoYiBean;
import com.example.zefeiqukuai.my.luer.adapter.LuErListAdapter;


public interface MySaveYiView extends IView {
    void loadUI(LuErListAdapter adapter);

    void loadData(JiaoYiBean bean);

    void refresh();
}
