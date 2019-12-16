package com.example.zefeiqukuai.my.myteam.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.base.adapter.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyTeamBean;

import java.util.List;

public class MyTeamAdapter3 extends MyRecyclerAdapter<MyTeamBean.Gener3Bean> {
    public MyTeamAdapter3(Context context, List<MyTeamBean.Gener3Bean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyTeamBean.Gener3Bean data, int position) {
        holder.setText(R.id.team_dj, data.getRank_name())
                .setText(R.id.team_time, data.getCreate_time());
        TextView phone = holder.itemView.findViewById(R.id.team_name);
        if ("".equals(data.getHead_image())){
            holder.setBackgroundResource(R.id.team_img,R.drawable.icon_wode1);
        }else{
            holder.setImageUrlCircular(R.id.team_img, CommonResource.BASEURL_8089 + data.getHead_image());
        }
        phone.setText("ID:" + data.getMember().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }
}
