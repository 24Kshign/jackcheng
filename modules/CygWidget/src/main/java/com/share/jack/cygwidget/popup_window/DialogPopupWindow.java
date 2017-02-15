package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;

import com.share.jack.cygwidget.R;


/**
 * Created by 大灯泡 on 2016/1/23.
 * dialogpopup :)
 * 客串一下dialog
 */
public class DialogPopupWindow extends BasePopupWindow {

    public DialogPopupWindow(Activity context) {
        super(context);
    }

    @Override
    protected Animation getAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
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
        return mPopupView;
    }

    @Override
    public View getPopupView() {
        return getPopupViewById(R.layout.pw_dialog_popupwindow);
    }

    @Override
    public View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }
}
