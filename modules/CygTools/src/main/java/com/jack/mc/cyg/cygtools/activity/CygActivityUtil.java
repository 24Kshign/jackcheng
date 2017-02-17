package com.jack.mc.cyg.cygtools.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 *
 */
public class CygActivityUtil {

    public CygActivityUtil() {
    }

    public static boolean isActive(Activity activity) {
        if (activity == null) {
            return false;
        } else if (activity.isFinishing()) {
            return false;
        }
        return true;
    }

    /**
     * 获取根节点的view
     *
     * @param activity
     * @return
     */
    public static View getRootView(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 把context转成Activity
     *
     * @param context
     * @return
     */
    public static Activity toActivity(Context context) {
        if (context == null) {
            CygLog.error();
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        } else {
            CygLog.error();
            return null;
        }
    }
}