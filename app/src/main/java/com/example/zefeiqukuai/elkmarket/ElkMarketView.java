package com.example.zefeiqukuai.elkmarket;

import android.view.View;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.adapter.PetListAdapter;

import java.util.List;

public interface ElkMarketView extends IView
{

    void lodeMarquee(List<View> views);

    void loadUI(PetListAdapter petListAdapter);

    void refresh();
}
