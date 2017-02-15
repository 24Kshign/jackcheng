package com.jack.mc.cyg.cygtools.util;

import android.support.annotation.Nullable;

/**
 * 对象封装
 */
public final class CygObject {

    private CygObject() {
    }

    public static int hashCode(@Nullable Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static String toString(@Nullable Object o) {
        return o != null ? o.toString() : null;
    }

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2) && o2.equals(o1);
    }

    public static boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
