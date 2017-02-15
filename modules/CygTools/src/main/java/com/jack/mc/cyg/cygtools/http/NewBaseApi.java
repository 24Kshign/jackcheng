package com.jack.mc.cyg.cygtools.http;


import com.jack.mc.cyg.cygtools.app.HttpServletAddress;
import com.jack.mc.cyg.cygtools.http.convert.CustomGsonConverterFactory;
import com.jack.mc.cyg.cygtools.util.CygClass;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * BaseApi
 */
public abstract class NewBaseApi {

    protected Retrofit mRetrofit;
    private static final int DEFAULT_TIME = 6;

    public NewBaseApi() {
        super();
        //创建okHttpClient
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //设置拦截器
//        builder.addInterceptor(new MarvelSigningInterceptor("TAG"));
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient okHttpClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpServletAddress.getInstance().getOnlineAddress())
                .client(okHttpClient)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())   // 指定subscribe()发生在IO线程
                .unsubscribeOn(Schedulers.io())  // 指定Subscriber的回调发生在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public static <T> T getPresent(Class<T> cls) {
        T instance = CygClass.newInstance(cls);
        if (instance == null) {
            return null;
        }
        return instance;
    }
}