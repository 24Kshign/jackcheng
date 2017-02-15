package com.jack.mc.cyg.cygtools.util;

import android.os.Environment;

import com.jack.mc.cyg.cygtools.app.CygApplication;

import java.io.File;

/**
 * 存储的封装
 */
public final class CygEnvironment {

    public static final String separator = "/";

    public CygEnvironment() {

    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    //获取sd卡根目录
    public static File getExternalStorageDir() {
        if (!hasExternalStorage()) {
            CygLog.error("没有SDK卡！");
            return null;
        }
        return Environment.getExternalStorageDirectory();
    }

    //获取sd卡根目录路径
    public static String getExternalStoragePath() {
        File file = getExternalStorageDir();
        if (file == null) {
            CygLog.error();
            return CygString.EMPTY_STRING;
        }
        return file.getAbsolutePath();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    // 应用files目录
    public static File getExternalFilesDir() {
        if (!hasExternalStorage()) {
            CygLog.error("没有SDK卡！");
            return null;
        }
        return CygApplication.getInstance().getExternalFilesDir(null);
    }

    //获取应用files目录路径
    public static String getExternalFilesPath() {
        File file = getExternalFilesDir();
        if (file == null) {
            CygLog.error();
            return CygString.EMPTY_STRING;
        }
        return file.getAbsolutePath();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    // 应用cache目录
    public static File getExternalCacheDir() {
        if (hasExternalStorage()) {
            CygLog.error("没有SDK卡！");
            return null;
        }
        return CygApplication.getInstance().getExternalCacheDir();
    }

    //获取应用cache目录路径
    public static String getExternalCachePath() {
        File file = getExternalCacheDir();
        if (file == null) {
            CygLog.error();
            return CygString.EMPTY_STRING;
        }
        return file.getAbsolutePath();
    }
}