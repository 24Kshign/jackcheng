package com.share.jack.jpush;

import android.util.Log;

import com.jack.mc.cyg.cygtools.app.CygApplication;

import cn.jpush.android.api.JPushInterface;

/**
 *
 */
public class JPush {

    public JPush() {
    }

    public static void init() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(CygApplication.getInstance());
    }

    public static String getRegistrationID() {
        String registrationId = JPushInterface.getRegistrationID(CygApplication.getInstance());
        if (registrationId.isEmpty()) {
            Log.e("JPush", "获取极光注册Id失败");
        }
        return registrationId;
    }
}