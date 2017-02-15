package com.share.jack.cygwidget.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


/**
 *
 */
public final class CygDialog {

    private CygDialog() {
    }

    /**
     * 进度条框
     */
    public static MaterialDialog progress(Activity activity, @NonNull CharSequence content) {
        return new MaterialDialog.Builder(activity)
                .content(content)
                .progress(true, 0)
                .show();
    }

    /**
     * 进度条框
     */
    public static MaterialDialog progress(Activity activity, @NonNull CharSequence title, @NonNull CharSequence content) {
        return new MaterialDialog.Builder(activity)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
    }

    public interface OnConfirmListener {
        void onConfirm(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction);
    }

    /**
     * 弹出框
     */
    public static MaterialDialog confirm(Activity activity,
                                         @NonNull CharSequence title, @NonNull CharSequence content,
                                         @NonNull CharSequence positiveText, @NonNull CharSequence negativeText,
                                         final OnConfirmListener onConfirmListener) {
        return new MaterialDialog.Builder(activity)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        if (onConfirmListener != null) {
                            onConfirmListener.onConfirm(materialDialog, dialogAction);
                        }
                    }
                })
                .show();
    }

    /**
     * 确认框
     */
    public static MaterialDialog confirm(Activity activity,
                                         @NonNull CharSequence title, @NonNull CharSequence content,
                                         OnConfirmListener onConfirmListener) {
        return confirm(activity, title, content, "确定", "取消", onConfirmListener);
    }
}
