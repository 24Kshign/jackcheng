package com.jack.mc.cyg.cygtools.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.jack.mc.cyg.cygtools.charset.CygCharset;
import com.jack.mc.cyg.cygtools.util.CygClass;

/**
 * Created by jack on 17/3/1
 */

public abstract class CygWebViewUIComponent {

    private WebView mView;

    protected static <VIEW_COMPONENT extends CygWebViewUIComponent> VIEW_COMPONENT build(Class<VIEW_COMPONENT> cls, WebView view) {
        VIEW_COMPONENT viewComponent = CygClass.newInstance(cls);
        if (viewComponent != null) {
            viewComponent.initialize(view);
        }
        return viewComponent;
    }

    final void initialize(WebView view) {
        mView = view;
        onCreate();
    }

    public WebView getWebView() {
        return mView;
    }

    protected void onCreate() {

    }

    public final void setWebChromeClient(WebChromeClient client) {
        mView.setWebChromeClient(client);
    }

    public final void loadUrl(String url) {
        mView.loadUrl(url);
    }

    public void loadData(String data, String mimeType, String encoding) {
        mView.loadData(data, mimeType, encoding);
    }

    public void loadData(String data) {
        mView.loadData(data, "text/html", CygCharset.DEFAULT_CHARSET);
    }

    public boolean canGoBack() {
        return mView.canGoBack();
    }

    public void goBack() {
        mView.goBack();
    }

    public boolean canGoForward() {
        return mView.canGoForward();
    }

    public void goForward() {
        mView.goForward();
    }
}