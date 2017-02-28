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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * BaseApi
 */
public abstract class BaseRetrofit {

    protected Retrofit mRetrofit;
    private static final int DEFAULT_TIME = 6;
    private static final int RETRY_TIME = 1;
    protected CompositeSubscription mCompositeSubscription;

    public BaseRetrofit() {
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
                .baseUrl(HttpServletAddress.getInstance().getServletAddress())
                .client(okHttpClient)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        addSubscription(o.subscribeOn(Schedulers.io())    // 指定subscribe()发生在IO线程
                .unsubscribeOn(Schedulers.io())    // 指定取消subscribe()发生在IO线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定Subscriber的回调发生在io线程
                .timeout(DEFAULT_TIME, TimeUnit.SECONDS)    //重连间隔时间
                .retry(RETRY_TIME)    //接收到onError()事件后触发重订阅
                .subscribe(s));
    }

    private void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected static <T> T getPresent(Class<T> cls) {
        T instance = CygClass.newInstance(cls);
        if (instance == null) {
            return null;
        }
        return instance;
    }
}