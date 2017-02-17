package com.jack.mc.cyg.cygtools.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jack.mc.cyg.cygtools.controller.CygActivityController;

/**
 * Activity基类的封装
 */
public class CygBaseActivity extends Activity implements CygActivityInterface {

    private final CygActivityController mActivityController = new CygActivityController(this);

    public View mStatusBar;  //状态栏
    public String immersionType = TYPE_LAYOUT;   //当前沉浸模式，默认为布局沉浸式
    public static final String TYPE_LAYOUT = "type_layout";  //仅仅改变状态栏颜色的沉浸模式
    public static final String TYPE_BACKGROUND = "type_background";  //将原布局背景扩散至状态栏的沉浸模式

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        if (mActivityController.dispatchTouchEvent(event)) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityController.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityController.onDestroy();
    }

    /**
     * 设置点击edittext外部隐藏键盘
     *
     * @param autoHide
     */
    @Override
    public void setAutoHideSoftInput(boolean autoHide) {
        mActivityController.setAutoHideSoftInput(autoHide);
    }

    @Override
    public View layoutInflate(int layoutResource) {
        return mActivityController.layoutInflate(layoutResource);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 将状态栏添加至布局中
     *
     * @param viewGroup
     */
    private void addStatusBar(ViewGroup viewGroup) {
        mStatusBar = new View(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight());
        mStatusBar.setLayoutParams(lp);
        viewGroup.addView(mStatusBar);
    }

    /**
     * 对4.4以下设备适配沉浸式状态栏
     */
    public void setView(View contentView, int statusColor) {
        //去掉状态栏布局
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //当API>=21时，状态栏会自动增加一块半透明色块，这段代码将其设为透明色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusColor);
            }
            if (immersionType.equals(TYPE_LAYOUT)) {
                LinearLayout ll_content = new LinearLayout(this);
                ll_content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                ll_content.setOrientation(LinearLayout.VERTICAL);
                addStatusBar(ll_content);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                ll_content.addView(contentView, lp);
                setContentView(ll_content);
            } else if (immersionType.equals(TYPE_BACKGROUND)) {
                contentView.setPadding(0, getStatusBarHeight(), 0, 0);
                setContentView(contentView);
            }

        } else {
            setContentView(contentView);
        }
    }

    /**
     * 子类设置布局时应调用该方法
     *
     * @param resId       布局id
     * @param statusColor 状态栏颜色
     */
    public void setView(int resId, int statusColor) {
        View contentView = View.inflate(this, resId, null);
        setView(contentView, statusColor);
    }
}