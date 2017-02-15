package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;

import com.jack.mc.cyg.cygtools.inputmethod.CygInputMethodManager;
import com.jack.mc.cyg.cygtools.util.CygLog;


/**
 * Created by 大灯泡 on 2016/1/14.
 * 通用的popupWindow
 */
public abstract class BasePopupWindow {

    //元素定义
    protected PopupWindow mPopupWindow;
    //popup视图
    protected View mPopupView;
    protected View mAnimaView;
    protected View mDismissView;
    protected Activity mContext;
    //是否自动弹出输入框(default:false)
    private boolean autoShowInputMethod = false;
    private OnDismissListener mOnDismissListener;
    //animation
    private Animation curExitAnimation;
    private Animator curExitAnimator;
    private Animation curAnimation;
    private Animator curAnimator;

    public BasePopupWindow(Activity activity) {
        initView(activity, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public BasePopupWindow(Activity activity, int w, int h) {
        initView(activity, w, h);
    }

    private void initView(Activity activity, int w, int h) {
        mContext = activity;

        mPopupView = getPopupView();
        mPopupView.setFocusableInTouchMode(true);
        //默认占满全屏
        mPopupWindow = new PopupWindow(mPopupView, w, h);
        //指定透明背景，back键相关
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        //无需动画
        mPopupWindow.setAnimationStyle(0);

        //=======================================================为外层的view添加点击事件，并设置点击消失
        mAnimaView = getAnimationView();
        mDismissView = getDismissView();
        if (mDismissView != null) {
            mDismissView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            if (mAnimaView != null) {
                mAnimaView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        //=============================================================元素获取
        curAnimation = getAnimation();
        curAnimator = getAnimator();
        curExitAnimation = getExitAnimation();
        curExitAnimator = getExitAnimator();
    }

    //------------------------------------------抽象-------------------------------------------------

    protected abstract View getPopupView();

    protected abstract View getAnimationView();

    protected abstract Animation getAnimation();

    protected abstract Animator getAnimator();

    protected abstract View getInputView();

    protected abstract View getDismissView();

    public Animation getExitAnimation() {
        return null;
    }

    public Animator getExitAnimator() {
        return null;
    }

    //------------------------------------------showPopup-------------------------------------------

    public void showPopupWindow() {
        try {
            tryToShowPopup(0, null);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void showPopupWindow(int res) {
        try {
            tryToShowPopup(res, null);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void showPopupWindow(View v) {
        try {
            tryToShowPopup(0, v);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    //------------------------------------------Methods---------------------------------------------

    private void tryToShowPopup(int res, View v) throws Exception {
        //传递了view
        if (res == 0 && v != null) {
            mPopupWindow.showAtLocation(v, Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        //传递了res
        if (res != 0 && v == null) {
            mPopupWindow.showAtLocation(mContext.findViewById(res), Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        //什么都没传递，取顶级view的id
        if (res == 0 && v == null) {
            mPopupWindow.showAtLocation(mContext.findViewById(android.R.id.content),
                    Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0
            );
        }
        if (curAnimation != null && mAnimaView != null) {
            mAnimaView.clearAnimation();
            mAnimaView.startAnimation(curAnimation);
        }
        if (curAnimation == null && curAnimator != null && mAnimaView != null &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            curAnimator.start();
        }
        //自动弹出键盘
        if (autoShowInputMethod && getInputView() != null) {
            getInputView().requestFocus();
            mPopupView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CygInputMethodManager.showInputMethod(getInputView());
                }
            }, 150);
        }
    }

    public void setAdjustInputMethod(boolean needAdjust) {
        if (needAdjust) {
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        } else {
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }

    public void setAutoShowInputMethod(boolean autoShow) {
        this.autoShowInputMethod = autoShow;
        if (autoShow) {
            setAdjustInputMethod(true);
        } else {
            setAdjustInputMethod(false);
        }
    }

    public void setBackPressEnable(boolean backPressEnable) {
        if (backPressEnable) {
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        } else {
            mPopupWindow.setBackgroundDrawable(null);
        }
    }

    public View getPopupViewById(int resId) {
        if (resId != 0) {
            return LayoutInflater.from(mContext).inflate(resId, null);
        } else {
            return null;
        }
    }

    //------------------------------------------Getter/Setter---------------------------------------

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    public OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        if (mOnDismissListener != null) {
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    mOnDismissListener.onDismiss();
                }
            });
        }
    }

    //------------------------------------------状态控制---------------------------------------------

    public void dismiss() {
        try {
            if (curExitAnimation != null) {
                curExitAnimation.setAnimationListener(mAnimationListener);
                mAnimaView.clearAnimation();
                mAnimaView.startAnimation(curExitAnimation);
            } else if (curExitAnimator != null) {
                curExitAnimator.removeListener(mAnimatorListener);
                curExitAnimator.addListener(mAnimatorListener);
                curExitAnimator.start();
            } else {
                mPopupWindow.dismiss();
            }
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    //------------------------------------------Anima-----------------------------------------------

    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    /**
     * 生成TranslateAnimation
     *
     * @param durationMillis 动画显示时间
     * @param start          初始位置
     * @param end            结束位置
     */
    protected Animation getTranslateAnimation(int start, int end, int durationMillis) {
        Animation translateAnimation = new TranslateAnimation(0, 0, start, end);
        translateAnimation.setDuration(durationMillis);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /**
     * 生成ScaleAnimation
     */
    protected Animation getScaleAnimation(float fromX, float toX, float fromY, float toY,
                                          int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        Animation scaleAnimation =
                new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
                        pivotYValue);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        Animation scaleAnimation =
                new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * 生成默认的AlphaAnimation
     */
    protected Animation getDefaultAlphaAnimation() {
        Animation alphaAnimation =
                new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    /**
     * 从下方滑动上来
     */
    protected AnimatorSet getDefaultSlideFromBottomAnimationSet() {
        AnimatorSet set = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            if (mAnimaView != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(mAnimaView, "translationY", 250, 0).setDuration(400),
                        ObjectAnimator.ofFloat(mAnimaView, "alpha", 0.4f, 1).setDuration(250 * 3 / 2));
            }
        }
        return set;
    }

    //------------------------------------------Interface-------------------------------------------

    public interface OnDismissListener {
        void onDismiss();
    }
}
