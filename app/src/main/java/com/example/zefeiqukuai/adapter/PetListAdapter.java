package com.example.zefeiqukuai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.base.adapter.MyRecyclerAdapter;
import com.example.base.utils.ArithUtil;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.PetListBean;

import java.util.List;

public class PetListAdapter extends MyRecyclerAdapter<PetListBean.DataBean> {
    public PetListAdapter(Context mContext, List<PetListBean.DataBean> data, int content_gong1) {
        super(mContext, data, content_gong1);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PetListBean.DataBean data, int position) {
        int state = data.getState();
        //holder.setText(R.id.rv_collection_preferential_price, "￥" + split[0]);
        //holder.setImageResource(R.id.rv_collection_check, R.drawable.icon_xuanzhong);
        holder.setText(R.id.content_gong_value, data.getStart_price() + "-" + data.getEnd_price())
                .setText(R.id.content_gong_time, data.getStart_time() + "-" + data.getEnd_time())
                .setText(R.id.content_gong_qiang, data.getAppoint() + "/" + data.getPurchase())
                .setText(R.id.content_gong_shouyi, data.getDay() + "/" + ArithUtil.exact(data.getBonus_rate() * 100, 2) + "%")
                .setText(R.id.content_gong_hkt, ArithUtil.exact(data.getCoin_hkt(), 2) + "枚")
                .setText(R.id.content_gong1_name, data.getName());
//                .setImageUrl(R.id.content_gong1_img, CommonResource.BASEURL_8090+data.getImage());
        LinearLayout viewById1 = holder.itemView.findViewById(R.id.bh);
        viewById1.setVisibility(View.GONE);
        ImageView viewById = holder.itemView.findViewById(R.id.content_gong1_img);
        Glide.with(context).load(CommonResource.BASEURL_8089 + data.getImage()).into(viewById);
        if (state == 1) {
            holder.setText(R.id.content_gong1_btn, "预约")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_yuyue);
        } else if (state == 2) {
            holder.setText(R.id.content_gong1_btn, "繁殖中")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_gong_zhong);
        } else if (state == 3) {
            holder.setText(R.id.content_gong1_btn, "抢购")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.bg_btn_login);
        } else if (state == 5) {
            holder.setText(R.id.content_gong1_btn, "待领养")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.shape_lingyang);
        }
        if (data.getRemaining_time() > 0 && data.getRemaining_time() <= 90) {
            holder.setText(R.id.content_gong1_btn, "离抢购还有" + data.getRemaining_time() + "秒")
                    .setBackgroundResource(R.id.content_gong1_btn, R.drawable.shape_lingyang);
        }

        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.content_gong1_btn), position);
        }


    }
}
