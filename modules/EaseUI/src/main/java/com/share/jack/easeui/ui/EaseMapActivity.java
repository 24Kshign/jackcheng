package com.share.jack.easeui.ui;

import android.app.Activity;

/**
 *
 */
public final class EaseMapActivity {

    private EaseMapActivity() {
    }

    private static Class<? extends Activity> mCls;

    public static Class<? extends Activity> getMapActivityClass() {
        return mCls;
    }

    public static void setMapActivityClass(Class<? extends Activity> cls) {
        mCls = cls;
    }
}
