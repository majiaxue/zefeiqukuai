package com.example.zefeiqukuai.adapter;

import android.content.Context;

import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.StaySaleBean;

import java.util.ArrayList;

public class StaySaleAdapter extends MyRecyclerAdapter<StaySaleBean.DataBean> {
    public StaySaleAdapter(Context mContext, ArrayList<StaySaleBean.DataBean> dataBeans, int content_gong) {
        super(mContext, dataBeans, content_gong);
    }

    @Override
    public void convert(RecyclerViewHolder holder, StaySaleBean.DataBean data, int position) {

        holder.setText(R.id.gong_name, data.getName())
                .setText(R.id.content_gong_value, data.getPrice())
                .setText(R.id.content_gong_time, data.getIntime())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setImageUrl(R.id.img, CommonResource.BASEURL_8089 + data.getImage());


    }
}
