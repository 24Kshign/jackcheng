package com.jack.mc.cyg.cygtools.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 单位换算的封装
 */
public class CygDisplayMetrics {

    public CygDisplayMetrics() {

    }

    /**
     * dp转px
     */
    public static int dp2px(Context context,float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转dp
     */
    public static float px2dp(Context context, int px) {
        return (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将sp值转换为px值
     */
    public static int sp2px(Context context, float sp) {
        return (int) (sp * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static int sScreenWidth = 0;
    private static int sScreenHeight = 0;

    public static int getScreenWidth(Activity activity) {
        if (sScreenWidth == 0) {
            initScreenSize(activity);
        }
        return sScreenWidth;
    }

    public static int getScreenHeight(Activity activity) {
        if (sScreenHeight == 0) {
            initScreenSize(activity);
        }
        return sScreenHeight;
    }

    private static void initScreenSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sScreenWidth = displayMetrics.widthPixels;
        sScreenHeight = displayMetrics.heightPixels;
    }
}
