package com.jack.mc.cyg.cygtools.http;


import com.jack.mc.cyg.cygtools.util.CygLog;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<BaseResponse<T>, T> {
    @Override
    public T call(BaseResponse<T> httpResult) {
        if (!httpResult.isRequestSuccess()) {
            CygLog.error();
            throw new ApiException(httpResult.getStatus(), httpResult.getMsg());
        }
        return httpResult.getData();
    }
}