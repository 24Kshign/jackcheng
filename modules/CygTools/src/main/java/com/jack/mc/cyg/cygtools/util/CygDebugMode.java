package com.jack.mc.cyg.cygtools.util;

/**
 * 调试的封装
 */
public final class CygDebugMode {

    private CygDebugMode() {
    }

    private static boolean sIsDebugMode = false;

    public static boolean isDebugMode() {
        return sIsDebugMode;
    }

    public static void setDebugMode(boolean isDebugMode) {
        sIsDebugMode = isDebugMode;
        if (sIsDebugMode) {
            // 避免发布的时候是Debug模式，做个提示
            CygToast.showToast("********************************\n" +
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "            当前是Debug模式！\n" +
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "********************************");
        }
    }
}
