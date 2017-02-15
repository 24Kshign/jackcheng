package com.jack.mc.cyg.cygtools.listener;

import android.view.MotionEvent;
import android.view.View;

import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 *
 */
public abstract class CygOnTouchActionUpListener implements View.OnTouchListener {

    protected abstract void onTouchActionUp(View v, MotionEvent event);

    @Override
    public final boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= 0) {
                try {
                    onTouchActionUp(v, event);
                } catch (Exception e) {
                    CygLog.error(e);
                }
                return true;
            }
        }
        return false;
    }
}
