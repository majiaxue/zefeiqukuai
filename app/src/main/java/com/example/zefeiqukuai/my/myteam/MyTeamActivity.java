package com.example.zefeiqukuai.my.myteam;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseActivity;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyTeamBean;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter2;
import com.example.zefeiqukuai.my.myteam.adapter.MyTeamAdapter3;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MyTeamActivity extends BaseActivity<MyTeamView, MyTeamPresenter> implements MyTeamView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.team_person)
    TextView teamPerson;
    @BindView(R.id.dongtaishouyi_count)
    TextView dongtaishouyiCount;
    @BindView(R.id.team_zhituiperson)
    TextView teamZhituiperson;
    @BindView(R.id.mine_lingyangjilu)
    LinearLayout mineLingyangjilu;
    @BindView(R.id.team_yuetuiperson)
    TextView teamYuetuiperson;
    @BindView(R.id.mine_zhuanrangjilu)
    LinearLayout mineZhuanrangjilu;
    @BindView(R.id.team_newperson)
    TextView teamNewperson;
    @BindView(R.id.mine_yuyuejilu)
    LinearLayout mineYuyuejilu;
    @BindView(R.id.team_tab)
    TabLayout teamTab;
    @BindView(R.id.rec_list)
    RecyclerView recList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public void initData() {
        includeTitle.setText("我的团队");
        presenter.loadData(teamTab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public MyTeamView createView() {
        return this;
    }

    @Override
    public MyTeamPresenter createPresenter() {
        return new MyTeamPresenter(this);
    }

    @Override
    public void loadUI(MyTeamAdapter adapter) {
        recList.setAdapter(adapter);
    }

    @Override
    public void loadUI2(MyTeamAdapter2 adapter) {
        recList.setAdapter(adapter);
    }

    @Override
    public void loadData(MyTeamBean myTeamBean) {
        teamPerson.setText(myTeamBean.getGroup_person_count()+"");//总人数
        teamNewperson.setText(myTeamBean.getToday_count()+"");//今日新增
        teamYuetuiperson.setText((myTeamBean.getGroup_person_count()-myTeamBean.getTotal_direct())+"");//越推
        teamZhituiperson.setText(myTeamBean.getTotal_direct()+"");//直推
    }

    @Override
    public void loadUI3(MyTeamAdapter3 adapter) {
        recList.setAdapter(adapter);
    }
}
