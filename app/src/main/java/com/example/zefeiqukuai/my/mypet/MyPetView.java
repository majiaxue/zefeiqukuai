package com.example.zefeiqukuai.my.mypet;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.MyAdapter;

public interface MyPetView extends IView {

    void loadData(MyAdapter myAdapter);
}
