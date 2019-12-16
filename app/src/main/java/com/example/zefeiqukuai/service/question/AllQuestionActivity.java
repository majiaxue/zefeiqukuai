package com.example.zefeiqukuai.service.question;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseActivity;
import com.example.base.utils.LogUtil;
import com.example.zefeiqukuai.R;

import butterknife.BindView;

public class AllQuestionActivity extends BaseActivity<AllQuestionView, AllQuestionPresenter> implements AllQuestionView {


    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.out_title)
    EditText outTitle;
    @BindView(R.id.out_content)
    EditText outContent;
    @BindView(R.id.out_send)
    TextView outSend;
    private String content;
    private String title;
    private int category_id;

    @Override
    public int getLayoutId() {
        return R.layout.out_box_layout;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        category_id = intent.getIntExtra("category_id", 0);
        LogUtil.e("传过来的" + category_id);
        if (category_id == 1) {
            includeTitle.setText("领养问题");
        }
        if (category_id == 2) {
            includeTitle.setText("充值问题");
        }
        if (category_id == 3) {
            includeTitle.setText("实名问题");
        }
        if (category_id == 4) {
            includeTitle.setText("冻结问题");
        }
        if (category_id == 5) {
            includeTitle.setText("其他问题");
        }

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        outSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = outTitle.getText().toString();
                content = outContent.getText().toString();
                if (title.equals("")) {
                    Toast.makeText(AllQuestionActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                } else if (content.equals("")) {
                    Toast.makeText(AllQuestionActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    LogUtil.e("发送邮件content" + title + "------" + content);
                    presenter.sendEmail(title, content, category_id);
                }

            }
        });

    }

    @Override
    public AllQuestionView createView() {
        return this;
    }

    @Override
    public AllQuestionPresenter createPresenter() {
        return new AllQuestionPresenter(this);
    }
}
