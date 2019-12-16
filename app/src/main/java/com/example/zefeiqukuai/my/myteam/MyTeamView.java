package com.example.zefeiqukuai.my.myteam;

import com.example.base.mvp.IView;
import com.example.zefeiqukuai.bean.MyTeamBean;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter2;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter3;

public interface MyTeamView extends IView {
    void loadUI(MyTeamAdapter adapter);
    void loadUI2(MyTeamAdapter2 adapter);
    void loadData(MyTeamBean myTeamBean);
    void loadUI3(MyTeamAdapter3 adapter);
}
