package com.example.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zefeiqukuai.R;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class UIHelper {
    private static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void clearCache(final Context context, String totalCache, OnClearCacheListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_clear_cache, null);
        TextView cancel = view.findViewById(R.id.pop_cache_cancel);
        TextView confirm = view.findViewById(R.id.pop_cache_confirm);
        TextView content = view.findViewById(R.id.pop_cache_content);
        content.setText("确定要清除(" + totalCache + ")缓存吗？");
        final PopupWindow popupWindow = new PopupWindow(view, (int) context.getResources().getDimension(R.dimen.dp_259), (int) context.getResources().getDimension(R.dimen.dp_177), true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, confirm);
    }

}