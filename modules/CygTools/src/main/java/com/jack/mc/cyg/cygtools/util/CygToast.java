package com.jack.mc.cyg.cygtools.util;

import android.widget.Toast;

import com.jack.mc.cyg.cygtools.app.CygApplication;


/**
 * toast封装
 */
public final class CygToast {

    private CygToast() {

    }

    private static Toast mToast;

    //弹出提示框
    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(CygApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    //取消提示框
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    //弹出提示框
    public static void showToast(int resId) {
        String text = CygApplication.getInstance().getString(resId);
        if (mToast == null) {
            mToast = Toast.makeText(CygApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
