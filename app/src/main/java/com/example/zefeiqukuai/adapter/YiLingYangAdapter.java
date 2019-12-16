package com.example.zefeiqukuai.adapter;

import android.content.Context;

import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.StaySaleBean;
import com.example.zefeiqukuai.bean.YiLingYangBean;

import java.util.ArrayList;

public class YiLingYangAdapter extends MyRecyclerAdapter<YiLingYangBean.DataBean> {

    public YiLingYangAdapter(Context mContext, ArrayList<YiLingYangBean.DataBean> dataBeans, int content_gong) {
        super(mContext, dataBeans, content_gong);
    }

    @Override
    public void convert(RecyclerViewHolder holder, YiLingYangBean.DataBean data, int position) {
        holder.setText(R.id.content_gong1_name, data.getName())
                .setText(R.id.content_gong_bh, data.getId() + "")
                .setText(R.id.content_gong_value, data.getPrice())
                .setText(R.id.content_gong_time, data.getAdopt_time())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setImageUrl(R.id.content_gong1_img, CommonResource.BASEURL_8089 + data.getImage());
        holder.setText(R.id.content_gong1_btn, "提前出售")
                .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_zhong);
        if(viewOnClickListener!=null){
            viewOnClickListener.ViewOnClick(holder.getView(R.id.content_gong1_btn),position);
        }


    }
}
