package com.share.jack.cygwidget.popup_window;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.jack.mc.cyg.cygtools.inputmethod.CygView;
import com.share.jack.cygwidget.R;


/**
 * 从底部滑上来的popup
 */
public class SlideFromBottomBasePopupMenu extends BasePopupWindow {

    public SlideFromBottomBasePopupMenu(Activity context) {
        super(context);
    }

    @Override
    protected final View getInputView() {
        return null;
    }

    @Override
    protected final Animator getAnimator() {
        return null;
    }

    @Override
    protected final Animation getAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    public final View getPopupView() {
        return CygView.inflateLayout(mContext, R.layout.pw_slidefrombottom_popupmenu);
    }

    @Override
    protected final View getDismissView() {
        return mPopupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public final View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }

    public void setMenuItems(View itemsView) {
        FrameLayout itemContainer = (FrameLayout) mPopupView.findViewById(R.id.item_container);
        itemContainer.removeAllViews();
        itemContainer.addView(itemsView);
    }
}
