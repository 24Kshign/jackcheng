package com.jack.mc.cyg.cygtools.http.callback;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.jack.mc.cyg.cygtools.http.ApiException;
import com.jack.mc.cyg.cygtools.util.CygString;
import com.jack.mc.cyg.cygtools.util.CygToast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 对请求失败的分类操作
 */
public abstract class CygSubscriberApi<T> extends BaseSubscriber<T> {

    private static final String TAG = "CygSubscriberApi";

    private boolean isNeedProgress;
    private String titleMsg;

    public CygSubscriberApi(Activity activity) {
        this(activity, null);
    }

    public CygSubscriberApi(Activity activity, String titleMsg) {
        super(activity);
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
        StringBuffer sb = new StringBuffer();
        sb.append("请求失败：");
        if (t instanceof NetworkErrorException || t instanceof UnknownHostException) {
            sb.append("网络异常");
        } else if (t instanceof SocketTimeoutException || t instanceof ConnectException
                || t instanceof IOException) {
            sb.append("请求超时");
        } else if (t instanceof JsonSyntaxException) {
            sb.append("请求不合法");
        } else if (t instanceof ApiException) {
            ApiException exception = (ApiException) t;
            if (exception.isTokenExpried()) {
                sb.append("token异常");
            } else {
                sb.append(CygString.valueOf(t.getMessage()));
            }
        }
        Log.e(TAG, sb.toString());
        CygToast.showToast(sb.toString());
    }
}