package com.example.zefeiqukuai.adapter;

import android.content.Context;

import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.DealBean;
import com.example.zefeiqukuai.bean.LingZhongBean;

import java.util.ArrayList;

public class LingZhongAdapter extends MyRecyclerAdapter<LingZhongBean.DataBean> {

    public LingZhongAdapter(Context mContext, ArrayList<LingZhongBean.DataBean> dataBeans, int content_gong1) {
        super(mContext, dataBeans, content_gong1);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LingZhongBean.DataBean data, int position) {
        holder.setText(R.id.content_gong_value, data.getPrice() + "")
                .setText(R.id.content_gong_time, data.getAddtime())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setText(R.id.content_gong1_name, data.getName())
                .setText(R.id.content_gong_bh,data.getId()+"")
                .setImageUrl(R.id.content_gong1_img, CommonResource.BASEURL_8089 + data.getImage());
        int status = data.getStatus();
        if (status == 0) {
            holder.setText(R.id.content_gong1_btn, "交易中")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_zhong);
        } else if (status == 1) {
            holder.setText(R.id.content_gong1_btn, "已付款")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_zhong);
        } else if (status == 2) {
            holder.setText(R.id.content_gong1_btn, "已完成")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_zhong);
        } else if (status == 3) {
            holder.setText(R.id.content_gong1_btn, "申诉中")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_zhong);
        } else if (status == 4) {
            holder.setText(R.id.content_gong1_btn, "申诉成功")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_success);
        } else if (status == 5) {
            holder.setText(R.id.content_gong1_btn, "申诉失败")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_shen_fail);
        }
    }
}
