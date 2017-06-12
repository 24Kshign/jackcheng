package com.jack.mc.cyg.cygtools.http.callback;

import com.jack.mc.cyg.cygtools.http.progress.ProgressDialogHandler;
import com.jack.mc.cyg.cygtools.util.CygLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 *
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected abstract void onBaseError(Throwable t);

    protected abstract void onBaseNext(T data);

    protected abstract boolean isNeedProgressDialog();

    protected abstract String getTitleMsg();

    private ProgressDialogHandler mProgressDialogHandler;
    private BaseImpl mBaseImpl;

    public BaseObserver(BaseImpl baseImpl) {
        mBaseImpl = baseImpl;
        if (null != mBaseImpl) {
            if (null == mProgressDialogHandler) {
                mProgressDialogHandler = new ProgressDialogHandler(baseImpl.getActivity(), true);
            }
        }
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
    public void onSubscribe(Disposable d) {
        //显示进度条
        if (isNeedProgressDialog()) {
            showProgressDialog();
        }
        if (null != mBaseImpl) {
            mBaseImpl.addDisposable(d);
        }
    }

    @Override
    public void onNext(T value) {
        //成功
        CygLog.debug("http is onNext");
        if (null!=value) {
            onBaseNext(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        //关闭进度条
        CygLog.error("http is onError");
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
        onBaseError(e);
    }

    @Override
    public void onComplete() {
        //关闭进度条
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
    }
}