package com.example.zefeiqukuai.my.shoukuan.fragment.card;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.BankCardBean;

public interface CardView extends IView {
    void loadZfbData(BankCardBean bankCardBean);
}
