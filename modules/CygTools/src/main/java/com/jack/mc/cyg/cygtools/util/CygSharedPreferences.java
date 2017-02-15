package com.jack.mc.cyg.cygtools.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.jack.mc.cyg.cygtools.app.CygApplication;


/**
 * sp封装
 */
public final class CygSharedPreferences {

    private CygSharedPreferences() {
    }

    private static SharedPreferences getSharedPreferences(String name) {
        return CygApplication.getInstance().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static String getString(String name, String key, String defValue) {
        return getSharedPreferences(name).getString(key, defValue);
    }

    public static void clearSharePreference(String name) {
        getSharedPreferences(name).edit().clear().commit();
    }

    public static boolean setString(String name, String key, String value) {
        return getSharedPreferences(name).edit().putString(key, value).commit();
    }

    public static int getInt(String name, String key, int defValue) {
        return getSharedPreferences(name).getInt(key, defValue);
    }

    public static boolean setInt(String name, String key, int value) {
        return getSharedPreferences(name).edit().putInt(key, value).commit();
    }

    public static long getLong(String name, String key, long defValue) {
        return getSharedPreferences(name).getLong(key, defValue);
    }

    public static boolean setLong(String name, String key, long value) {
        return getSharedPreferences(name).edit().putLong(key, value).commit();
    }

    public static boolean getBoolean(String name, String key, boolean defValue) {
        return getSharedPreferences(name).getBoolean(key, defValue);
    }

    public static boolean setBoolean(String name, String key, boolean value) {
        return getSharedPreferences(name).edit().putBoolean(key, value).commit();
    }
}
