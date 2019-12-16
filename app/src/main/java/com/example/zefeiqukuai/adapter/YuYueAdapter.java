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
import com.example.zefeiqukuai.bean.YuYueBean;

import java.util.ArrayList;

public class YuYueAdapter extends MyRecyclerAdapter<YuYueBean.DataBean> {
    public YuYueAdapter(Context mContext, ArrayList<YuYueBean.DataBean> dataBeans, int content_gong1) {
        super(mContext,dataBeans,content_gong1);
    }

    @Override
    public void convert(RecyclerViewHolder holder, YuYueBean.DataBean data, int position) {
        holder.setText(R.id.content_gong_value,data.getStart_price()+"-"+data.getEnd_price())
                .setText(R.id.content_gong_time,data.getCreate_time())
                .setText(R.id.content_gong_bh,data.getId()+"")
                .setText(R.id.content_gong_qiang,data.getAppoint()+"/"+data.getPurchase())
                .setText(R.id.content_gong_shouyi,data.getDay()+"/"+ ArithUtil.exact(data.getBonus_rate()*100,2)+"%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(),2)+"枚")
                .setText(R.id.gong_name,data.getName());
//                .setImageUrl(R.id.content_gong1_img, CommonResource.BASEURL_8090+data.getImage());
        ImageView viewById = holder.itemView.findViewById(R.id.img);
        Glide.with(context).load(CommonResource.BASEURL_8089+data.getImage()).into(viewById);

    }
}
