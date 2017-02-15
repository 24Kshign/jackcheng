package com.jack.mc.cyg.cygtools.http.callback;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.jack.mc.cyg.cygtools.http.ApiException;
import com.jack.mc.cyg.cygtools.util.CygString;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *
 */
public abstract class CygSubscriberApi<T> extends BaseSubscriber<T> {

    private static final String TAG = "CygSubscriberApi";

    private boolean isNeedProgress;
    private Activity activity;
    private String titleMsg;

    public CygSubscriberApi(Activity activity) {
        this(activity, null);
    }

    public CygSubscriberApi(Activity activity, String titleMsg) {
        super(activity);
        this.activity = activity;
        this.titleMsg = titleMsg;
        if (CygString.isEmpty(titleMsg)) {
            this.isNeedProgress = false;
        } else {
            this.isNeedProgress = true;
        }
    }

    public CygSubscriberApi(Fragment fragment, String titleMsg) {
        this(fragment.getActivity(), titleMsg);
    }

    public CygSubscriberApi(Fragment fragment) {
        this(fragment.getActivity(), null);
    }

    @Override
    protected boolean isNeedProgressDialog() {
        return isNeedProgress;
    }

    @Override
    protected String getTitleMsg() {
        return titleMsg;
    }

    @Override
    protected void onBaseError(Throwable t) {
        String alert = "";
        if (t instanceof NetworkErrorException || t instanceof UnknownHostException) {
            alert = "网络异常";
        } else if (t instanceof SocketTimeoutException || t instanceof ConnectException) {
            alert = "请求超时";
        } else if (t instanceof IOException) {
            alert = "请求超时";
        } else if (t instanceof ApiException) {
            ApiException exception = (ApiException) t;
            if (exception.isTokenExpried()) {
                alert = "token异常";
            } else {
                alert = t.getMessage();
            }
        }

        Log.e(TAG, "api failure,throw=" + t.getMessage());
        Toast.makeText(activity, "请求失败：" + alert, Toast.LENGTH_SHORT).show();
    }
}
