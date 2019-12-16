package com.example.zefeiqukuai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyPetBean;

import java.util.List;

public class MyAdapter extends MyRecyclerAdapter<MyPetBean> {
    public MyAdapter(Context mContext, List<MyPetBean> myPetBeans, int content_gong1) {
        super(mContext, myPetBeans, content_gong1);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyPetBean data, int position) {
        holder.setText(R.id.content_gong_value, data.getStart_price() + "-" + data.getEnd_price())
                .setText(R.id.content_gong_time, data.getStart_time() + "-" + data.getEnd_time())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setText(R.id.number,"可兑换数量:")
                .setText(R.id.content_gong_bh, data.getConvert_num() + "")
                .setText(R.id.content_gong1_name, data.getName());
        holder.setText(R.id.content_gong1_btn, "兑换")
                .setBackgroundResource(R.id.content_gong1_btn, R.drawable.shape_lingyang);
        ImageView viewById = holder.itemView.findViewById(R.id.content_gong1_img);
        Glide.with(context).load(CommonResource.BASEURL_8089 + data.getImage()).into(viewById);
        viewOnClickListener.ViewOnClick(holder.getView(R.id.content_gong1_btn), position);


    }
}
