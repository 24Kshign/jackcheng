package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.jack.mc.cyg.cygtools.util.CygToast;
import com.share.jack.cygwidget.R;


/**
 * Created by 大灯泡 on 2016/1/15.
 * 普通的popup
 */
public class ScalePopupWindow extends BasePopupWindow implements View.OnClickListener {

    private View popupView;

    public ScalePopupWindow(Activity context) {
        super(context);
        bindEvent();
    }


    @Override
    protected Animation getAnimation() {
        return getDefaultScaleAnimation();
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
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View getPopupView() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.pw_scale_popupwindow, null);
        return popupView;
    }

    @Override
    public View getAnimationView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tx_1) {
            CygToast.showToast("click tx_1");
        } else if (i == R.id.tx_2) {
            CygToast.showToast("click tx_2");
        } else if (i == R.id.tx_3) {
            CygToast.showToast("click tx_3");
        }
    }
}
