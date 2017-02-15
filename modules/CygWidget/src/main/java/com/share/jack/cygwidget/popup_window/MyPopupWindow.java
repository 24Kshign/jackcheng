package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

/**
 *
 */
public class MyPopupWindow extends BasePopupWindow {

    public MyPopupWindow(Activity activity) {
        super(activity);
    }

    @Override
    protected View getPopupView() {
        return null;
    }

    @Override
    protected View getAnimationView() {
        return null;
    }

    @Override
    protected Animation getAnimation() {
        return null;
    }

    @Override
    protected Animator getAnimator() {
        return null;
    }

    @Override
    protected View getInputView() {
        return null;
    }

    @Override
    protected View getDismissView() {
        return null;
    }
}
