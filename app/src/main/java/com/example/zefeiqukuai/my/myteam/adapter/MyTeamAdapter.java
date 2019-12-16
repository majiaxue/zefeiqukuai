package com.example.zefeiqukuai.my.myteam.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.base.adapter.MyRecyclerAdapter;
import com.example.base.utils.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.MyTeamBean;

import java.util.List;

public class MyTeamAdapter extends MyRecyclerAdapter<MyTeamBean.Gener1Bean> {
    public MyTeamAdapter(Context context, List<MyTeamBean.Gener1Bean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyTeamBean.Gener1Bean data, int position) {
        holder.setText(R.id.team_dj, data.getRank_name())
                .setText(R.id.team_time, data.getCreate_time())
               ;
        if ("".equals(data.getHead_image())){
            holder.setBackgroundResource(R.id.team_img,R.drawable.icon_wode1);
        }else{
            holder.setImageUrlCircular(R.id.team_img, CommonResource.BASEURL_8089 + data.getHead_image());
        }
        //s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        TextView phone = holder.itemView.findViewById(R.id.team_name);
        phone.setText("ID:" + data.getMember().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }
}
