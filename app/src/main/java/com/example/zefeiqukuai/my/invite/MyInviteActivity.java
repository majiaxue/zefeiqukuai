package com.example.zefeiqukuai.my.invite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.base.mvp.BaseActivity;
import com.example.base.utils.LogUtil;
import com.example.base.utils.QRCode;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  MyInviteActivity extends BaseActivity<MyInviteView, MyInvitePresenter> implements MyInviteView {
    @BindView(R.id.include_back)
    ImageView includeBack;
    @BindView(R.id.include_title)
    TextView includeTitle;
    @BindView(R.id.include_right)
    ImageView includeRight;
    @BindView(R.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R.id.invite_erweima)
    ImageView inviteErweima;
    @BindView(R.id.invite_erweima_lin)
    RelativeLayout inviteErweimaLin;
    @BindView(R.id.baocun)
    TextView baocun;
    @BindView(R.id.invite_code_content)
    TextView inviteCode;
    @BindView(R.id.invite_code_copy)
    TextView inviteCodeCopy;
    private Bitmap qrImage1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    public void initData() {
        includeTitle.setText("邀请好友");
        inviteCode.setText(SPUtil.getStringValue(CommonResource.UUID));
        presenter.getInvite();

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //复制邀请码
        inviteCodeCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setClipboard(SPUtil.getStringValue(CommonResource.UUID));
            }
        });
        //保存二维码
        inviteErweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inviteErweima.setEnabled(false);
                Date date = new Date();
                long time = date.getTime();
                File file = new File(Environment.getExternalStorageDirectory() + "/fad/image");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file1 = new File(Environment.getExternalStorageDirectory() + "/fad/image/invite.jpg");
                try {
                    //文件输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
                    qrImage1.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    //写入，这里会卡顿，因为图片较大
                    fileOutputStream.flush();
                    //记得要关闭写入流
                    fileOutputStream.close();
                    //成功的提示，写入成功后，请在对应目录中找保存的图片
                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                    inviteErweima.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        uri = FileProvider.getUriForFile(getApplicationContext(), MyInviteActivity.this.getPackageName(), file1);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    } else {
                        uri = Uri.fromFile(file1);
                    }
                    intent.setData(uri);
                    sendBroadcast(intent);

                    MediaStore.Images.Media.insertImage(getContentResolver(),
                            qrImage1, Environment.getExternalStorageDirectory() + "/fad/image/" + time + ".jpg", null);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file1.getAbsolutePath())));
                } catch (Exception e) {
                    e.printStackTrace();
                    //失败的提示
                    LogUtil.e("====================="+ e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public MyInviteView createView() {
        return this;
    }

    @Override
    public MyInvitePresenter createPresenter() {
        return new MyInvitePresenter(this);
    }


    @Override
    public void loadCode(String url) {
        qrImage1 = QRCode.createQRImage(url, (int) MyInviteActivity.this.getResources().getDimension(R.dimen.dp_193), (int) MyInviteActivity.this.getResources().getDimension(R.dimen.dp_193));
        Glide.with(MyInviteActivity.this).load(qrImage1).into(inviteErweima);
    }
}
