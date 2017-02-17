package com.jack.mc.cyg.cygtools.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.jack.mc.cyg.cygtools.eventbus.CygEventBusUtil;
import com.jack.mc.cyg.cygtools.inputmethod.CygInputMethodManager;
import com.jack.mc.cyg.cygtools.inputmethod.CygView;

/**
 *
 */
public class CygActivityController {

    public CygActivityController(Activity activity) {
        mActivity = activity;
    }

    private Activity mActivity;
    private boolean mAutoHideSoftInput = false;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        CygEventBusUtil.registerSubscriber(mActivity);
    }

    public void onDestroy() {
        CygEventBusUtil.unregisterSubscriber(mActivity);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mAutoHideSoftInput) {
            CygInputMethodManager.autoHideSoftInput(mActivity, event);
        }
        return false;
    }

    public void setAutoHideSoftInput(boolean autoHide) {
        mAutoHideSoftInput = autoHide;
    }


    public final View layoutInflate(int layoutResource) {
        return CygView.inflateLayout(mActivity, layoutResource);
    }
}