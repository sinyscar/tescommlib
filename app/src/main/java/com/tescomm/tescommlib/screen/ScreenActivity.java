package com.tescomm.tescommlib.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.tescomm.tescommlib.R;
import com.tescomm.tescommlib.base.BaseActivity;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/27
 *     desc  : demo about ScreenUtils
 * </pre>
 */
public class ScreenActivity extends BaseActivity {

    ImageView ivScreenshot;
    TextView tvAboutScreen;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScreenActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_screen;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        ivScreenshot = findViewById(R.id.iv_screenshot);
        tvAboutScreen = findViewById(R.id.tv_about_screen);
        findViewById(R.id.btn_set_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_set_non_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_toggle_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_set_landscape).setOnClickListener(this);
        findViewById(R.id.btn_set_portrait).setOnClickListener(this);
        findViewById(R.id.btn_screenshot).setOnClickListener(this);
        findViewById(R.id.btn_set_sleep_duration).setOnClickListener(this);
        findViewById(R.id.btn_test_adapt_screen).setOnClickListener(this);

        updateAboutScreen();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_fullscreen:
                ScreenUtils.setFullScreen(this);
                break;
            case R.id.btn_set_non_fullscreen:
                ScreenUtils.setNonFullScreen(this);
                break;
            case R.id.btn_toggle_fullscreen:
                ScreenUtils.toggleFullScreen(this);
                break;
            case R.id.btn_set_landscape:
                ScreenUtils.setLandscape(this);
                break;
            case R.id.btn_set_portrait:
                ScreenUtils.setPortrait(this);
                break;
            case R.id.btn_screenshot:
                ivScreenshot.setImageBitmap(ScreenUtils.screenShot(this));
                break;
            case R.id.btn_set_sleep_duration:
//                ScreenUtils.setSleepDuration(100000);
                break;
            case R.id.btn_test_adapt_screen:
                ScreenAdaptActivity.start(this);
                break;
        }
        updateAboutScreen();
    }

    private void updateAboutScreen() {
        tvAboutScreen.setText(new SpanUtils()
                .appendLine("getScreenWidth: " + ScreenUtils.getScreenWidth())
                .appendLine("getScreenHeight: " + ScreenUtils.getScreenHeight())
                .appendLine("isLandscape: " + ScreenUtils.isLandscape())
                .appendLine("isPortrait: " + ScreenUtils.isPortrait())
                .appendLine("getScreenRotation: " + ScreenUtils.getScreenRotation(this))
                .appendLine("isScreenLock: " + ScreenUtils.isScreenLock())
                .appendLine("getSleepDuration: " + ScreenUtils.getSleepDuration())
                .append("isTablet: " + ScreenUtils.isTablet())
                .create()
        );
    }
}