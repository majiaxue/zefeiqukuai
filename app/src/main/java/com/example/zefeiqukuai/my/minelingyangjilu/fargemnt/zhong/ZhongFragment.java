package com.example.zefeiqukuai.my.minelingyangjilu.fargemnt.zhong;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.mvp.BaseFragment;
import com.example.base.utils.ProcessDialogUtil;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.adapter.LingZhongAdapter;
import com.example.zefeiqukuai.pet_detail.PetDetailActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import butterknife.BindView;

public class ZhongFragment extends BaseFragment<ZhongView, ZhongPresenter> implements ZhongView {
    @BindView(R.id.stay_recy)
    RecyclerView stayRecy;
    @BindView(R.id.my_integral_smart)
    SmartRefreshLayout myIntegralSmart;
    private int type =1;
    private int pageindex =1;
    private int pagesize =20;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stay;
    }

    @Override
    public void initData() {
        ProcessDialogUtil.showProcessDialog(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stayRecy.setLayoutManager(linearLayoutManager);

        presenter.loadMyRecord(myIntegralSmart);
        myIntegralSmart.setRefreshHeader(new MaterialHeader(getContext()));
        myIntegralSmart.setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override
    public void initClick() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getStay(type, pageindex, pagesize);
    }

    @Override
    public ZhongView createView() {
        return this;
    }

    @Override
    public void refreshSuccesss() {
        myIntegralSmart.finishLoadMore();
        myIntegralSmart.finishRefresh();
    }

    @Override
    public void loadDealAdapter(LingZhongAdapter dealAdapter) {
        stayRecy.setAdapter(dealAdapter);
    }

    @Override
    public ZhongPresenter createPresenter() {
        return new ZhongPresenter(getContext());
    }
}
