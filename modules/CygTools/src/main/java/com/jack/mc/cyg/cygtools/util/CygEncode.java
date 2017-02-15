package com.jack.mc.cyg.cygtools.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码解码的封装
 */
public class CygEncode {

    /**
     * URL解码
     *
     * @param encode
     * @return
     */
    public static String getDecodeStr(String encode) {
        if (CygString.isEmpty(encode)) {
            return "";
        }
        try {
            return URLDecoder.decode(encode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            CygLog.error();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * URL转码
     *
     * @param encode 需要转码的字符串
     * @param code   需要转码的编码
     * @return
     */
    public static String getURLEncoderString(String encode, String code) {
        if (CygString.isEmpty(encode)) {
            return "";
        }
        try {
            return URLEncoder.encode(encode, code);
        } catch (UnsupportedEncodingException e) {
            CygLog.error();
            e.printStackTrace();
        }
        return null;
    }

}