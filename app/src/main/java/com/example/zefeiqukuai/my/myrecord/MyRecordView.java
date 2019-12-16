package com.example.zefeiqukuai.my.myrecord;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.MyRecordAdapter;

public interface MyRecordView extends IView {
    void loadData(MyRecordAdapter myRecordAdapter);
}
