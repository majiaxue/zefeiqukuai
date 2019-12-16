package com.example.zefeiqukuai.my.shoukuan.fragment.zhifubao;

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
import com.example.zefeiqukuai.bean.ZFBBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;


public class ZhiFuBaoFragment extends BaseFragment<ZhiFuBaoView, ZhiFuBaoPresenter> implements ZhiFuBaoView {
    @BindView(R.id.zhifubao_zh)
    EditText zhifubaoZh;
    @BindView(R.id.chonghzi_type_lin)
    LinearLayout chonghziTypeLin;
    @BindView(R.id.chongzhi_type_name)
    TextView chongzhiTypeName;
    @BindView(R.id.zhifubao_name)
    EditText zhifubaoName;
    @BindView(R.id.zhifubao_shoukuanma_img)
    ImageView zhifubaoShoukuanmaImg;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    private String zhifubaozh;
    private String zhifubaoname;
    ZFBBean zfbBean;

    @Override
    public int getLayoutId() {
        return R.layout.zhifubao_fragment;
    }

    @Override
    public void initData() {
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        presenter.getGathing(1);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShou(EventBusBean shoukuan) {
        if (CommonResource.SHOUKUAN.equals(shoukuan.getType())) {
            zhifubaozh = zhifubaoZh.getText().toString();
            zhifubaoname = zhifubaoName.getText().toString();

            if (TextUtils.isEmpty(zhifubaozh) || TextUtils.isEmpty(zhifubaoname)) {
                Toast.makeText(getContext(), "支付宝账号或者昵称为空请检查", Toast.LENGTH_SHORT).show();
            } else {
                SPUtil.addParm("zhifubaozh", zhifubaozh);
                SPUtil.addParm("zhifubaoname", zhifubaoname);
                EventBus.getDefault().post(new EventBusBean(CommonResource.SHOUKUANFANGSHI, "zfb"));
            }
        }
    }

    @Override
    public void initClick() {
        zhifubaoShoukuanmaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateHeader();
            }
        });

    }

    @Override
    public ZhiFuBaoView createView() {
        return this;
    }

    @Override
    public ZhiFuBaoPresenter createPresenter() {
        return new ZhiFuBaoPresenter(getContext());
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
    public void loadZfbData(ZFBBean zfbBean) {
       this. zfbBean =zfbBean;
       if (TextUtils.isEmpty(zfbBean.getAliname())){
           zhifubaoZh.setHint("请输入支付宝账号");
           zhifubaoName.setHint("请输入支付宝昵称");
           Glide.with(getContext()).load(R.drawable.shoukuan_shangchuan).into(zhifubaoShoukuanmaImg);
       }else{
           zhifubaoZh.setText(zfbBean.getAliname());
           zhifubaoName.setText(zfbBean.getAlino());
           Glide.with(getContext()).load(CommonResource.BASEURL_8089+zfbBean.getAliqrcode()).into(zhifubaoShoukuanmaImg);
       }

    }

    @Override
    public void showHeader(Uri fileUri) {
        LogUtil.e("~~~~~~~~~~~~~~~~~~");
        Glide.with(this).load(fileUri).into(zhifubaoShoukuanmaImg);

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
}
