package com.jack.mc.cyg.cygtools.inputmethod;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jack.mc.cyg.cygtools.systemserver.CygSystemService;
import com.jack.mc.cyg.cygtools.util.CygObject;


/**
 * 显示键盘的工具类
 */
public class CygInputMethodManager {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // show

    public static void showInputMethod(Context context, View view) {
        if (CygObject.isNullObject(view)) {
            return;
        }
        InputMethodManager inputMethodManager = CygSystemService.getInputMethodManager(context);
        if (CygObject.isNullObject(inputMethodManager)) {
            return;
        }
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void showInputMethod(View view) {
        if (CygObject.isNullObject(view)) {
            return;
        }
        showInputMethod(view.getContext(), view);
    }

    public static void showInputMethod(Activity activity) {
        showInputMethod(activity, activity.getCurrentFocus());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // hide

    public static void hideSoftInput(Context context, View view) {
        if (CygObject.isNullObject(view)) {
            return;
        }
        IBinder token = view.getWindowToken();
        if (CygObject.isNullObject(token)) {
            return;
        }
        InputMethodManager inputMethodManager = CygSystemService.getInputMethodManager(context);
        if (CygObject.isNullObject(inputMethodManager)) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideSoftInput(View view) {
        if (CygObject.isNullObject(view)) {
            return;
        }
        hideSoftInput(view.getContext(), view);
    }

    public static void hideSoftInput(Activity activity) {
        hideSoftInput(activity, activity.getCurrentFocus());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // toggle

    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = CygSystemService.getInputMethodManager(context);
        if (CygObject.isNullObject(inputMethodManager)) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void autoHideSoftInput(Activity activity, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return;
        }

        View focusView = activity.getCurrentFocus();
        if (focusView == null) {
            return;
        }
        if (!(focusView instanceof EditText)) {
            return;
        }

        float x = event.getX();
        float y = event.getY();

        int[] location = {0, 0};
        focusView.getLocationInWindow(location);
        int left = location[0];
        int top = location[1];
        int bottom = top + focusView.getHeight();
        int right = left + focusView.getWidth();

        if (left <= x && x < right && top <= y && y < bottom) {
            // 点击事件在EditText的区域里
            return;
        }
        CygInputMethodManager.hideSoftInput(activity, focusView);
    }
}
