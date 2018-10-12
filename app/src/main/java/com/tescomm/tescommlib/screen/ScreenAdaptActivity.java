package com.tescomm.tescommlib.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tescomm.tescommlib.R;
import com.tescomm.tescommlib.base.BaseActivity;
import com.tescomm.tescommlib.helper.DialogHelper;

/**
 * Copyright  2017 北京泰合佳通信息技术有限公司. All rights reserved.
 * <p>
 *  新老项目都可以用这套方案，老项目中如果有新的 Activity 加进来，
 *  那么可以对其使用该方案来适配，然后在启动其他老的 Activity 时候 cancelAdaptScreen 即可。
 *  新项目我建议采用我工具类中的使用，可以让你爽到极致，
 *  在 BaseActivity 中 setContentView(xx) 之前调用适配代码即可
 *
 * @author [闫季群]
 * @date [创建日期，2018/10/12]
 * @Version version: 1.0
 */
public class ScreenAdaptActivity extends BaseActivity {

    private TextView tvUp;
    private TextView tvDown;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScreenAdaptActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        if (ScreenUtils.isPortrait()) {
            //传入设计图实际px尺寸：720
            ScreenUtils.adaptScreen4VerticalSlide(this, 720);
        } else {
            ScreenUtils.setFullScreen(this);
            //传入设计图实际px尺寸：720
            ScreenUtils.adaptScreen4HorizontalSlide(this, 720);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_screen_adapt;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        if (ScreenUtils.isPortrait()) {
            findViewById(R.id.btn_show_system_toast).setOnClickListener(this);
            findViewById(R.id.btn_show_util_toast).setOnClickListener(this);
            findViewById(R.id.btn_system_dialog).setOnClickListener(this);
            findViewById(R.id.btn_system_dialog_without_adapt).setOnClickListener(this);
        }
    }

    @Override
    public void doBusiness() {

    }
/*
 * 使用系统ui,需要先取消适配，显示完成后，在回复适配。见toast 和dialog
 */
    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_system_toast:
                Toast.makeText(this, "System Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_show_util_toast:
                ToastUtils.showShort("Util Toast");
                break;
            case R.id.btn_system_dialog:
                DialogHelper.showAdaptScreenDialog();
                break;
            case R.id.btn_system_dialog_without_adapt:
                ScreenUtils.cancelAdaptScreen(this);
                DialogHelper.showAdaptScreenDialog();
                ScreenUtils.adaptScreen4VerticalSlide(this, 720);
                break;
        }
    }

    @Override
    protected void onDestroy() {
//        ScreenUtils.cancelAdaptScreen(this);
        super.onDestroy();
    }
}
