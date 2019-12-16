package com.example.zefeiqukuai.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.base.utils.StatusBarUtils;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.login.LoginActivity;
import com.example.zefeiqukuai.main.MainActivity;

public class StartActivity extends AppCompatActivity {
    private boolean isFirstIn = false;
    private static final int TIME = 1500;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    stringValue = SPUtil.getStringValue(CommonResource.TOKEN);
                    LogUtil.e("登录token-->"+stringValue);
                    if (TextUtils.isEmpty(stringValue)) {
                        goLogin();
                    } else {
                        goHome();
                    }
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };
    private String stringValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences preferences = getSharedPreferences("first_pref", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", false);
        init();
        changeStatus();
    }

    private void changeStatus() {
        // 设置状态栏
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusTheme(this, true, true);
    }

    private void init() {

        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            SharedPreferences preferences = getSharedPreferences("first_pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", true);
            editor.commit();
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        }
    }

    private void goLogin() {
        Intent i = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void goHome() {
        Intent i = new Intent(StartActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(StartActivity.this, GuideActivity.class);
        startActivity(i);
        finish();
    }
}
