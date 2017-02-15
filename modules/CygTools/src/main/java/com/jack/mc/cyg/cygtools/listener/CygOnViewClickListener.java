package com.jack.mc.cyg.cygtools.listener;

import android.view.View;

import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 *
 */
public abstract class CygOnViewClickListener implements View.OnClickListener {

    protected abstract void onClick();

    @Override
    public final void onClick(View v) {
        try {
            onClick();
        } catch (Exception e) {
            CygLog.error(e);
        }
    }
}
