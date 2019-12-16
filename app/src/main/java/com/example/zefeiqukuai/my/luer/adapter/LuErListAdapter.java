package com.example.zefeiqukuai.my.luer.adapter;

import android.content.Context;


import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.JiaoYiBean;


import java.util.List;

public class LuErListAdapter extends MyRecyclerAdapter<JiaoYiBean.DataBean> {
    public LuErListAdapter(Context context, List<JiaoYiBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JiaoYiBean.DataBean data, int position) {
        holder.setText(R.id.item_gou_xiao, data.getRemark())
                .setText(R.id.item_gou_time, data.getCreate_time());
        int income = data.getIncome();
        if (income == 0) {
            holder.setText(R.id.item_gou_kouqian, "-" + ArithUtil.exact(data.getAccount(), 2));
            holder.setText(R.id.item_gou_ok, "扣费成功");
        } else {
            holder.setText(R.id.item_gou_kouqian, "+" + ArithUtil.exact(data.getAccount(), 2));
            holder.setText(R.id.item_gou_ok, "充值成功");
        }

    }
}
