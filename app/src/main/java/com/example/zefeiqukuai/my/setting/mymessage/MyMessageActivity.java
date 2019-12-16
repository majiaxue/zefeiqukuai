package com.example.zefeiqukuai.my.setting.mymessage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.base.mvp.BaseActivity;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMessageActivity extends BaseActivity<MyMessageView, MyMessagePresenter> implements MyMessageView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.mymsg_head)
    ImageView mymsgHead;
    @BindView(R.id.mine_setting)
    ImageView mineSetting;
    @BindView(R.id.setting_msg)
    RelativeLayout settingMsg;
    @BindView(R.id.mymsg_name)
    TextView mymsgName;
    @BindView(R.id.mymsg_name_lin)
    RelativeLayout mymsgNameLin;
    @BindView(R.id.mymsg_zh)
    TextView mymsgZh;
    @BindView(R.id.mymsg_zh_lin)
    RelativeLayout mymsgZhLin;
    @BindView(R.id.mymsg_tuijian_person)
    TextView mymsgTuijianPerson;
    @BindView(R.id.mymsg_tuijian_name)
    TextView mymsgTuijianName;
    @BindView(R.id.mymsg_tuijian_zh)
    TextView mymsgTuijianZh;
    private boolean isLoad = true;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    UserInfoBean userInfoBean = new UserInfoBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    @Override
    public void initData() {
        includeTitle.setText("个人信息");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("保存");



    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mymsgName.getText().toString()))
                {
                    Toast.makeText(MyMessageActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.updateMeaasge(mymsgName.getText().toString());
                }

            }
        });
        //上传头像
        mymsgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoad = false;
                presenter.updateHeader();
            }
        });
    }

    @Override
    public MyMessageView createView() {
        return this;
    }

    @Override
    public MyMessagePresenter createPresenter() {
        return new MyMessagePresenter(this);
    }

    @Override
    public void takePhoto(Intent captureIntent) {
        startActivityForResult(captureIntent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void cropPhoto(Intent intent) {
        startActivityForResult(intent, CROP_CODE);
    }

    @Override
    public void loadUserInfo(UserInfoBean userInfoBean) {
        mymsgName.setText(userInfoBean.getNick_name());
        mymsgZh.setText(userInfoBean.getMember());
        mymsgTuijianPerson.setText(userInfoBean.getParent_name());
        mymsgTuijianName.setText(userInfoBean.getParent_nick_name());
        mymsgTuijianZh.setText(userInfoBean.getParent_member());
        Glide.with(this).load(CommonResource.BASEURL_8089+userInfoBean.getHead_image()).placeholder(R.drawable.icon_wode1).apply(RequestOptions.circleCropTransform()).into(mymsgHead);
    }

    @Override
    public void showHeader(Uri fileUri) {
        Glide.with(this).load(fileUri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mymsgHead);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.uploadPhoto();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                presenter.uploadPhoto();
                break;
        }
    }
}
