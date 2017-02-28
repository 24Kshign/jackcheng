package com.jack.mc.cyg.cygtools.inputmethod;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 *
 */
public final class CygView {

    private CygView() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static <VIEW extends View> VIEW fromView(View view) {
        try {
            return (VIEW) view;
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW inflateLayout(Context context, @LayoutRes int layoutRes, @Nullable ViewGroup root, boolean attachToRoot) {
        try {
            View view = LayoutInflater.from(context).inflate(layoutRes, root, attachToRoot);
            return fromView(view);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW inflateLayout(Context context, @LayoutRes int layoutRes, @Nullable ViewGroup root) {
        try {
            View view = LayoutInflater.from(context).inflate(layoutRes, root);
            return fromView(view);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW inflateLayout(Context context, @LayoutRes int layoutRes) {
        try {
            View view = LayoutInflater.from(context).inflate(layoutRes, null);
            return fromView(view);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW findView(View view, @IdRes int id) {
        try {
            View child = view.findViewById(id);
            return fromView(child);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }
}
