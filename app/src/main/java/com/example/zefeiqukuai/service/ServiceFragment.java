package com.example.zefeiqukuai.service;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.mvp.BaseFragment;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.FaJianXiangBean;
import com.example.zefeiqukuai.service.question.AllQuestionActivity;
import com.example.zefeiqukuai.service.shoujian.ShoujianActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ServiceFragment extends BaseFragment<ServiceView, ServicePresenter> implements ServiceView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.service_shoujianxiang)
    LinearLayout serviceShoujianxiang;
    @BindView(R.id.service_lingyang)
    LinearLayout serviceLingyang;
    @BindView(R.id.service_chongzhi)
    LinearLayout serviceChongzhi;
    @BindView(R.id.service_shiming)
    LinearLayout serviceShiming;
    @BindView(R.id.service_dongjie)
    LinearLayout serviceDongjie;
    @BindView(R.id.service_qita)
    LinearLayout serviceQita;
   private List<FaJianXiangBean> faJianXiangBeans;
    @Override
    public int getLayoutId() {
        return R.layout.service_fragment;
    }

    @Override
    public void initData() {
        includeBack.setVisibility(View.GONE);
        includeTitle.setText("服务中心");
        presenter.getData();

    }

    @Override
    public void initClick() {
      serviceShoujianxiang.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getContext(), ShoujianActivity.class);
              startActivity(intent);
          }
      });
        serviceLingyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (faJianXiangBeans !=null){
                    Intent intent = new Intent(getContext(), AllQuestionActivity.class);
                    intent.putExtra("category_id",faJianXiangBeans.get(0).getId());
                    startActivity(intent);
                }

            }
        });
        serviceChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (faJianXiangBeans !=null){
                    Intent intent = new Intent(getContext(), AllQuestionActivity.class);
                    intent.putExtra("category_id",faJianXiangBeans.get(1).getId());
                    startActivity(intent);
                }
            }
        });
        serviceShiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (faJianXiangBeans !=null){
                    Intent intent = new Intent(getContext(), AllQuestionActivity.class);
                    intent.putExtra("category_id",faJianXiangBeans.get(2).getId());
                    startActivity(intent);
                }
            }
        });
        serviceDongjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (faJianXiangBeans !=null){
                    Intent intent = new Intent(getContext(), AllQuestionActivity.class);
                    intent.putExtra("category_id",faJianXiangBeans.get(3).getId());
                    startActivity(intent);
                }
            }
        });
        serviceQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (faJianXiangBeans !=null){
                    Intent intent = new Intent(getContext(), AllQuestionActivity.class);
                    intent.putExtra("category_id",faJianXiangBeans.get(4).getId());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public ServiceView createView() {
        return this;
    }

    @Override
    public ServicePresenter createPresenter() {
        return new ServicePresenter(getContext());
    }

    @Override
    public void loadSuccess(List<FaJianXiangBean> faJianXiangBeans) {
        this.faJianXiangBeans = faJianXiangBeans;
    }
}
