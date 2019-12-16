package com.example.zefeiqukuai.my.setting.mymessage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.example.base.mvp.BasePresenter;
import com.example.base.net.OnDataListener;
import com.example.base.net.OnMyCallBack;
import com.example.base.net.OnTripartiteCallBack;
import com.example.base.net.RetrofitUtil;
import com.example.base.utils.ImageUtil;
import com.example.base.utils.LogUtil;
import com.example.base.utils.MapUtil;
import com.example.base.utils.OnChangeHeaderListener;
import com.example.base.utils.PopUtils;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.bean.BaseBean;
import com.example.zefeiqukuai.bean.UpdateMessageBean;
import com.example.zefeiqukuai.bean.UpdatePasswordBean;
import com.example.zefeiqukuai.bean.UserInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MyMessagePresenter extends BasePresenter<MyMessageView> {

    private Uri fileUri;
    private Uri cropUri;
    private UserInfoBean userInfoBean;
    private String path;

    public MyMessagePresenter(Context context) {
        super(context);
    }

    //修改信息
    public void updateMeaasge(String name) {

        LogUtil.e("-->" + name);
        userInfoBean.setHead_image(path);
        userInfoBean.setNick_name(name);
        //Map build = MapUtil.getInstance().addParms("head_image", userInfoBean.getHead_image()).addParms("nick_name", name).build();
        String pddGoodsSearchVoStr = JSON.toJSONString(userInfoBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postHeadWithBody(CommonResource.USERINFOCOMMIT, body, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("修改个人信息" + result);
                UpdateMessageBean updateMessageBean = JSON.parseObject(result, UpdateMessageBean.class);
                if (updateMessageBean.getErrcode() == 0) {
                    Toast.makeText(mContext, updateMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
                    (  ( Activity)mContext).finish();
                } else {
                    (  ( Activity)mContext).finish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("修改个人信息onError-->" + errorCode);
            }
        }));

    }

    public void updateHeader() {
        PopUtils.changeHeader(mContext, new OnChangeHeaderListener() {
            @Override
            public void setOnChangeHeader(final PopupWindow pop, TextView camera, TextView album) {
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCamera();
                        pop.dismiss();
                    }
                });

                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPhotoAlbum();
                        pop.dismiss();
                    }
                });
            }
        });
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), "myHeader.jpg");
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(mContext.getApplicationContext(), mContext.getPackageName(), file);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            fileUri = Uri.fromFile(file);
        }
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        getView().takePhoto(captureIntent);
    }

    private void openPhotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        getView().photoAlbum(intent);
    }

    public void cropImage() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(fileUri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故只保存图片Uri，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //裁剪后的图片Uri路径，uritempFile为Uri类变量

        cropUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "myHeader_crop.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        getView().cropPhoto(intent);
    }

    public void parseUri(Intent intent) {
        fileUri = intent.getData();
        String type = intent.getType();
        if (fileUri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = fileUri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = mContext.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        fileUri = uri_temp;
                    }
                }
            }
        }
      uploadPhoto();
    }

    public void uploadPhoto() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            base64 = base64.replaceAll("[\\s*\t\n\r]", "");
            Map image = MapUtil.getInstance().addParms("image", base64).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).postData(CommonResource.BASEUPLOAD, image);
            RetrofitUtil.getInstance().toSubscribe(observable,new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("base64上传-->"+result);
                    BaseBean baseBean = JSON.parseObject(result, BaseBean.class);
                    path = baseBean.getPath();
                    getView().showHeader(fileUri);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("base64上传-->"+errorCode);
                }
            }));

        } catch (Exception e) {
        }
    }

    public void getUserInfo() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_8089).getHeadWithout(CommonResource.USERINFO, SPUtil.getStringValue(CommonResource.TOKEN));
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("个人信息" + result);
                userInfoBean = JSON.parseObject(result, UserInfoBean.class);
                path =userInfoBean.getHead_image();
                SPUtil.addParm(CommonResource.NICKNAME,userInfoBean.getNick_name());
                if (getView() != null) {
                    getView().loadUserInfo(userInfoBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("个人信息onError-->" + errorCode);
            }
        }));

    }

    @Override
    protected void onViewDestroy() {

    }
}
