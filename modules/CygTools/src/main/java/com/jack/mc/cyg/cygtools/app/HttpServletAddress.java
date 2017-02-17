package com.jack.mc.cyg.cygtools.app;

import com.jack.mc.cyg.cygtools.util.CygString;

/**
 *
 */
public class HttpServletAddress {

    private String onlineAddress;   //线上地址
    private String offlineAddress;  //线下地址

    private static final class SingletonHolder {
        private static final HttpServletAddress INSTANCE = new HttpServletAddress();
    }

    public static HttpServletAddress getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private HttpServletAddress() {
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
    }

    public String getOfflineAddress() {
        return offlineAddress;
    }

    public void setOfflineAddress(String offlineAddress) {
        this.offlineAddress = offlineAddress;
    }

    public String getServletAddress() {
        return CygString.isEmpty(onlineAddress) ? offlineAddress : onlineAddress;
    }


}