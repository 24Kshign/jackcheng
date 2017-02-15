package com.jack.mc.cyg.cygtools.inputmethod;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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

    public static <VIEW extends View> VIEW inflateLayout(Activity activity, @LayoutRes int layoutRes, @Nullable ViewGroup root, boolean attachToRoot) {
        try {
            View view = activity.getLayoutInflater().inflate(layoutRes, root, attachToRoot);
            return fromView(view);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW inflateLayout(Activity activity, @LayoutRes int layoutRes, @Nullable ViewGroup root) {
        try {
            View view = activity.getLayoutInflater().inflate(layoutRes, root);
            return fromView(view);
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static <VIEW extends View> VIEW inflateLayout(Activity activity, @LayoutRes int layoutRes) {
        try {
            View view = activity.getLayoutInflater().inflate(layoutRes, null);
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
