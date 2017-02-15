package com.jack.mc.cyg.cygtools.handler;

import android.os.Looper;

public final class CygMainLooperHandler extends CygHandler {

    private static final class SingletonHolder {
        private static final CygMainLooperHandler INSTANCE = new CygMainLooperHandler();
    }

    public static CygMainLooperHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CygMainLooperHandler() {
        super(Looper.getMainLooper());
    }
}
