package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import com.nineoldandroids.view.ViewHelper;
import com.share.jack.cygwidget.R;


/**
 * Created by 大灯泡
 */
public class InputPopupWindow extends BasePopupWindow implements View.OnClickListener{
    private Button mCancelButton;
    private Button mCompeleteButton;
    private EditText mInputEdittext;

    public InputPopupWindow(Activity context) {
        super(context);
        mCancelButton= (Button) mPopupView.findViewById(R.id.btn_cancel);
        mCompeleteButton= (Button) mPopupView.findViewById(R.id.btn_Compelete);
        mInputEdittext= (EditText) mPopupView.findViewById(R.id.ed_input);

        setAutoShowInputMethod(true);
        bindEvent();
    }

    @Override
    protected Animation getAnimation() {
        return null;
    }

    private void bindEvent() {
        mCancelButton.setOnClickListener(this);
        mCompeleteButton.setOnClickListener(this);
    }

    //=============================================================super methods


    @Override
    protected Animator getAnimator() {
        ViewHelper.setPivotX(getAnimationView(), getAnimationView().getMeasuredWidth() / 2);
        ViewHelper.setPivotY(getAnimationView(), getAnimationView().getMeasuredHeight()/2);
        return getDefaultSlideFromBottomAnimationSet();
    }

    @Override
    protected View getInputView() {
        return mInputEdittext;
    }

    @Override
    protected View getDismissView() {
        return mPopupView;
    }

    @Override
    public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.pw_input_popupwindow,null);
    }

    @Override
    public View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }

    @Override
    public Animator getExitAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getAnimationView() != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(getAnimationView(), "translationY", 0, 250).setDuration(400),
                        ObjectAnimator.ofFloat(getAnimationView(), "alpha", 1, 0.4f).setDuration(250 * 3 / 2));
            }
        }
        return set;
    }

    //=============================================================click event
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_cancel:
//                dismiss();
//                break;
//            case R.id.btn_Compelete:
//                ToastUtils.ToastMessage(mContext,mInputEdittext.getText().toString());
//                dismiss();
//                break;
//            default:
//                break;
//        }

    }
}
