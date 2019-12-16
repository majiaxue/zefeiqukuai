package com.example.zefeiqukuai.adapter;

import android.content.Context;
import android.graphics.Color;

import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.WheelRecordBean;

import java.util.ArrayList;

public class WheelRecordAdapter extends MyRecyclerAdapter<WheelRecordBean.DataBean> {
    public WheelRecordAdapter(Context mContext, ArrayList<WheelRecordBean.DataBean> dataBeans, int rv_points_record) {
        super(mContext, dataBeans, rv_points_record);
    }

    @Override
    public void convert(RecyclerViewHolder holder, WheelRecordBean.DataBean data, int position) {
        if (position % 2 == 0) {
            holder.setBackgroundColor(R.id.wheel_lin, Color.parseColor("#c37f4c"));
        } else {
            holder.setBackgroundColor(R.id.wheel_lin, Color.parseColor("#c98c5e"));
        }
        holder.setText(R.id.wheel_message, data.getResult())
                .setText(R.id.wheel_phone, data.getUser().getMember())
                .setText(R.id.wheel_time, data.getTime());

    }
}
