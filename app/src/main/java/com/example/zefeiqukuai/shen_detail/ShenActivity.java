package com.example.zefeiqukuai.shen_detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.mvp.BaseActivity;
import com.example.base.utils.ArithUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.DealBean;
import com.example.zefeiqukuai.bean.PetDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShenActivity extends BaseActivity<ShenView, ShenPresenter> implements ShenView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.gong_name)
    TextView gongName;
    @BindView(R.id.content_gong_bh)
    TextView contentGongBh;
    @BindView(R.id.content_gong_value)
    TextView contentGongValue;
    @BindView(R.id.content_gong_time)
    TextView contentGongTime;
    @BindView(R.id.content_gong_qiang)
    TextView contentGongQiang;
    @BindView(R.id.content_gong_shouyi)
    TextView contentGongShouyi;
    @BindView(R.id.content_gong_hkt)
    TextView contentGongHkt;
    @BindView(R.id.shen_orderid)
    TextView shenOrderid;
    @BindView(R.id.shen_money)
    TextView shenMoney;
    @BindView(R.id.shen_sale)
    TextView shenSale;
    @BindView(R.id.shen_btn)
    TextView shenBtn;
    @BindView(R.id.shen_content)
    EditText shenContent;
    private PetDetailBean petDetailBean;
    DealBean.DataBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.complaint_details_layout;
    }

    @Override
    public void initData() {
        includeTitle.setText("交易申诉");
        Intent intent = getIntent();
        bean = (DealBean.DataBean) intent.getSerializableExtra("beans");
        petDetailBean = (PetDetailBean) intent.getSerializableExtra("petDetailBean");
        contentGongValue.setText(bean.getPrice() + "");
        contentGongBh.setText(bean.getId() + "");
        contentGongTime.setText(bean.getStart_time());
        contentGongQiang.setText(bean.getAppoint() + "/" + bean.getPurchase());
        contentGongShouyi.setText(bean.getDay() + "/" + ArithUtil.exact(bean.getBonus_rate() * 100, 2) + "%");
        contentGongHkt.setText(ArithUtil.exact(bean.getCoin_hkt(), 2) + "枚");
        gongName.setText(bean.getName());
        Glide.with(ShenActivity.this).load(CommonResource.BASEURL_8089 + bean.getImage()).into(img);
        shenOrderid.setText(petDetailBean.getOrdernum() + "");
        shenMoney.setText(bean.getPrice() + "");
        shenSale.setText(petDetailBean.getSeller_member() + "");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bean.getId() + "")) {
                    Toast.makeText(ShenActivity.this, "订单id不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(shenContent.getText().toString()))
                {
                    Toast.makeText(ShenActivity.this, "请输入申诉原因", Toast.LENGTH_SHORT).show();
                }
                else {
                    presenter.getAppeal(bean.getId(), 1,shenContent.getText().toString() );
                }

            }
        });

    }

    @Override
    public ShenView createView() {
        return this;
    }

    @Override
    public ShenPresenter createPresenter() {
        return new ShenPresenter(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
