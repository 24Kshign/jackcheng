package com.jack.mc.cyg.cygtools.app;

/**
 *
 */
public class HttpServletAddress {

    private String onlineAddress;

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
}