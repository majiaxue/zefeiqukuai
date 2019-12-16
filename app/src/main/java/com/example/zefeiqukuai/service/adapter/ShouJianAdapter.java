package com.example.zefeiqukuai.service.adapter;

import android.content.Context;

import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.ShouJianBean;

import java.util.List;

public class ShouJianAdapter extends MyRecyclerAdapter<ShouJianBean> {
    public ShouJianAdapter(Context context, List<ShouJianBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ShouJianBean data, int position) {
        holder.setText(R.id.in_title,data.getTitle())
                .setText(R.id.in_content,data.getContent())
                .setText(R.id.in_time,data.getCreate_time());
    }
}
