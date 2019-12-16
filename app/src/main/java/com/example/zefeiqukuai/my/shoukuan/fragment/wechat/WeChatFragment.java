package com.example.zefeiqukuai.my.shoukuan.fragment.wechat;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.mvp.BaseFragment;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.EventBusBean;
import com.example.zefeiqukuai.bean.WeChatBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class WeChatFragment extends BaseFragment<WeChatView, WeChatPresenter> implements WeChatView {
    @BindView(R.id.wechat_zh)
    EditText wechatZh;
    @BindView(R.id.chonghzi_type_lin)
    LinearLayout chonghziTypeLin;
    @BindView(R.id.chongzhi_type_name)
    TextView chongzhiTypeName;
    @BindView(R.id.wechat_name)
    EditText wechatName;
    @BindView(R.id.wechat_shoukuanma_img)
    ImageView wechatShoukuanmaImg;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    private String wechatzh;
    private String wechatname;
    WeChatBean weChatBean;

    @Override
    public int getLayoutId() {
        return R.layout.wechat_fragment;
    }

    @Override
    public void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        presenter.getGathing(2);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShou(EventBusBean shoukuan) {
        if (CommonResource.SHOUKUAN.equals(shoukuan.getType())) {
            wechatzh = wechatZh.getText().toString();
            wechatname = wechatName.getText().toString();

            if (TextUtils.isEmpty(wechatzh) || TextUtils.isEmpty(wechatname)) {
                Toast.makeText(getContext(), "微信账号或者昵称为空请检查", Toast.LENGTH_SHORT).show();
            } else {
                SPUtil.addParm("wechatzh", wechatzh);
                SPUtil.addParm("wechatname", wechatname);
                EventBus.getDefault().post(new EventBusBean(CommonResource.SHOUKUANFANGSHI, "weChat"));
            }
        }
    }

    @Override
    public void initClick() {
        wechatShoukuanmaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateHeader();

            }
        });
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
    public void loadZfbData(WeChatBean weChatBean) {
        this.weChatBean = weChatBean;
        if (TextUtils.isEmpty(weChatBean.getWxname())) {
            wechatName.setHint("请输入微信昵称");
            wechatZh.setHint("请输入微信账号");
            Glide.with(getContext()).load(R.drawable.shoukuan_shangchuan).into(wechatShoukuanmaImg);
        } else {
            wechatName.setText(weChatBean.getWxname());
            wechatZh.setText(weChatBean.getWxno());
            Glide.with(getContext()).load(  CommonResource.BASEURL_8089+weChatBean.getWxqrcode()).into(wechatShoukuanmaImg);
        }
    }

    @Override
    public void showHeader(Uri fileUri) {
        Glide.with(this).load(fileUri).into(wechatShoukuanmaImg);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.cropImage();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                presenter.uploadPhoto();
                break;
        }
    }

    @Override
    public WeChatView createView() {
        return this;
    }

    @Override
    public WeChatPresenter createPresenter() {
        return new WeChatPresenter(getContext());
    }
}
