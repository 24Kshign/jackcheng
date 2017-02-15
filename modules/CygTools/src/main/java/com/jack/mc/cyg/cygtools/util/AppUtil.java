package com.jack.mc.cyg.cygtools.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jack.mc.cyg.cygtools.app.CygApplication;


/**
 * app信息的封装
 */
public class AppUtil {

    public AppUtil() {

    }

    //应用程序最大可用内存
    public static int MAX_MEMORY = ((int) Runtime.getRuntime().maxMemory())/1024/1024;
    //应用程序已获得内存
    public static long TOTAL_MEMORY = ((int) Runtime.getRuntime().totalMemory())/1024/1024;
    //应用程序已获得内存中未使用内存
    public static long FREE_MEMORY = ((int) Runtime.getRuntime().freeMemory())/1024/1024;

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = CygApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(CygApplication.getInstance().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}