package com.jack.mc.cyg.cygtools.http.callback;

import android.app.Activity;

import com.jack.mc.cyg.cygtools.activity.CygActivityUtil;
import com.jack.mc.cyg.cygtools.http.progress.ProgressDialogHandler;
import com.jack.mc.cyg.cygtools.util.CygLog;

import rx.Subscriber;

/**
 * BaseSubscriber
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    protected abstract void onBaseError(Throwable t);

    protected abstract void onBaseNext(T data);

    protected abstract boolean isNeedProgressDialog();

    protected abstract String getTitleMsg();

    private ProgressDialogHandler mProgressDialogHandler;
    private Activity activity;

    public BaseSubscriber(Activity activity) {
        this.activity = activity;
        mProgressDialogHandler = new ProgressDialogHandler(activity, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG, getTitleMsg()).sendToTarget();
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onError(Throwable t) {
        if (!CygActivityUtil.isActive(activity)) {
            return;
        }
        //关闭进度条
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
        onBaseError(t);
    }

    //订阅开始
    @Override
    public void onStart() {
        if (!CygActivityUtil.isActive(activity)) {
            return;
        }
        CygLog.info("http is start");
        //显示进度条
        if (isNeedProgressDialog()) {
            showProgressDialog();
        }
    }

    //订阅完成
    @Override
    public void onCompleted() {
        if (!CygActivityUtil.isActive(activity)) {
            return;
        }
        CygLog.info("http is Complete");
        //关闭进度条
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
    }

    @Override
    public void onNext(T t) {
        if (!CygActivityUtil.isActive(activity)) {
            return;
        }
        onBaseNext(t);
    }
}
