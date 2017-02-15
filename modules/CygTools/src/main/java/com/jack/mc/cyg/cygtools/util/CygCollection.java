package com.jack.mc.cyg.cygtools.util;

import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * collection接口方法的一些封装
 */
public class CygCollection {
    private CygCollection() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean isEmpty(@Nullable Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static int size(@Nullable Collection collection) {
        return collection != null ? collection.size() : 0;
    }

    public static void clear(@Nullable Collection collection) {
        if (collection != null) {
            collection.clear();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static String appendAsString(Collection collection, String separator) {
        if (CygCollection.isEmpty(collection)) {
            return null;
        }
        if (CygString.isEmpty(separator)) {
            return null;
        }
        String result = null;
        for (Object object : collection) {
            String string = CygObject.toString(object);
            if (CygString.isEmpty(string)) {
                continue;
            }
            if (CygString.isEmpty(result)) {
                result = string;
            } else {
                result = result + separator + string;
            }
        }
        return result;
    }

    public static String appendAsString(Collection collection) {
        return appendAsString(collection, ",");
    }

    public static LinkedList<String> splitStringToLinkedList(String string, String separator) {
        if (CygString.isEmpty(string)) {
            return null;
        }
        if (CygString.isEmpty(separator)) {
            return null;
        }
        LinkedList<String> list = new LinkedList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string, separator);
        while (stringTokenizer.hasMoreElements()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }
}