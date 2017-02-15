package com.jack.mc.cyg.cygtools.eventbus;

/**
 *
 */
public class CygEventBusUtil {

    private CygEventBusUtil() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 这个接口没有任何方法
     * 以下类都具有生命周期管理，它们的子类 implements Subscriber 会自动注册和反注册EventBus
     */
    public interface Subscriber {
    }

    public static void registerSubscriber(Object subscriber) {
        if (subscriber instanceof Subscriber) {
            CygEventBus.register(subscriber);
        }
    }

    public static void unregisterSubscriber(Object subscriber) {
        if (subscriber instanceof Subscriber) {
            CygEventBus.unregister(subscriber);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static abstract class AutoRegisterSubscriber {

        public AutoRegisterSubscriber() {
            CygEventBus.register(this);
        }
    }
}
