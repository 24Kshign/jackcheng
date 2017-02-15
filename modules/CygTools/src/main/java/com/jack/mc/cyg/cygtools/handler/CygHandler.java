package com.jack.mc.cyg.cygtools.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.jack.mc.cyg.cygtools.util.CygLog;

public class CygHandler extends Handler {

    public CygHandler() {
        super();
    }

    public CygHandler(Callback callback) {
        super(callback);
    }

    public CygHandler(Looper looper) {
        super(looper);
    }

    public CygHandler(Looper looper, Callback callback) {
        super(looper, callback);
    }

    public final boolean postDelayed(Runnable runnable, Object token, long delayMillis){
        CygLog.verbose("postDelayed");
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        try {
            return postAtTime(runnable, token, SystemClock.uptimeMillis() + delayMillis);
        } catch (Exception e) {
            CygLog.error("postAtTime error", e);
            return false;
        }
    }

    public final boolean post(Runnable runnable, Object token){
        return postDelayed(runnable, token, 0);
    }
}
