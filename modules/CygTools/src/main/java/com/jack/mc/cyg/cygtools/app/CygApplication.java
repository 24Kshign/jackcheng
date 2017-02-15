package com.jack.mc.cyg.cygtools.app;

import android.app.Application;

/**
 * 全局唯一的Application实例，只要继承MyApplication就可以了，MyApplication会把this存到单实例引用
 */
public abstract class CygApplication extends Application {

    private static CygApplication sInstance;

    public static CygApplication getInstance() {
        return sInstance;
    }

    protected CygApplication() {
        sInstance = this;
    }
}
