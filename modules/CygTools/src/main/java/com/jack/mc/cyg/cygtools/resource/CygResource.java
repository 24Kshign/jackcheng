package com.jack.mc.cyg.cygtools.resource;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 *
 */
public final class CygResource {

    private CygResource() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setConfigToDefault(Context context) {
        Configuration config = new Configuration();
        config.setToDefaults();
        updateConfiguration(context, config);
    }

    /**
     * 设置app字体大小不跟随系统字体大小
     */
    public static void setFontScaleToDefault(Context context) {
        Configuration config = new Configuration();
        config.fontScale = 1.0f;
        updateConfiguration(context, config);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static void updateConfiguration(Context context, Configuration config) {
        Resources res = context.getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
