package com.example.zefeiqukuai.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyPetBean;

import java.util.List;

public class MyRecordAdapter extends MyRecyclerAdapter<MyPetBean> {
    public MyRecordAdapter(Context mContext, List<MyPetBean> myPetBeans, int content_gong1) {
        super(mContext, myPetBeans, content_gong1);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyPetBean data, int position) {
        holder.setText(R.id.content_gong_value, data.getStart_price() + "-" + data.getEnd_price())
                .setText(R.id.content_gong_time, data.getStart_time() + "-" + data.getEnd_time())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setText(R.id.content_gong_bh,data.getId()+"")
                .setText(R.id.gong_name, data.getName());
        ImageView viewById = holder.itemView.findViewById(R.id.img);
        Glide.with(context).load(CommonResource.BASEURL_8089 + data.getImage()).into(viewById);

    }
}
