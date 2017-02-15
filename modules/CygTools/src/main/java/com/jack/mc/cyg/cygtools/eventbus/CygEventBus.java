package com.jack.mc.cyg.cygtools.eventbus;


import com.jack.mc.cyg.cygtools.util.CygLog;

import de.greenrobot.event.EventBus;

/**
 * Delegate了EventBus
 * 调试的时候可以在RzEventBus打断点，调试方便点
 */
public final class CygEventBus {

    private CygEventBus() {
    }

    private static final EventBus mEventBus = EventBus.getDefault();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(Object subscriber) {
        try {
            mEventBus.register(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public static void register(Object subscriber, int priority) {
        try {
            mEventBus.register(subscriber, priority);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public static void registerSticky(Object subscriber) {
        try {
            mEventBus.registerSticky(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public static void registerSticky(Object subscriber, int priority) {
        try {
            mEventBus.registerSticky(subscriber, priority);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public static void unregister(Object subscriber) {
        try {
            mEventBus.unregister(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public static boolean isRegistered(Object subscriber) {
        return mEventBus.isRegistered(subscriber);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void post(Object event) {
        mEventBus.post(event);
    }

    public static void postSticky(Object event) {
        mEventBus.postSticky(event);
    }

    public static <T> T getStickyEvent(Class<T> eventType) {
        return mEventBus.getStickyEvent(eventType);
    }

    public static <T> T removeStickyEvent(Class<T> eventType) {
        return mEventBus.removeStickyEvent(eventType);
    }

    public static boolean removeStickyEvent(Object event) {
        return mEventBus.removeStickyEvent(event);
    }

    public static void removeAllStickyEvents() {
        mEventBus.removeAllStickyEvents();
    }

    public static boolean hasSubscriberForEvent(Class<?> eventClass) {
        return mEventBus.hasSubscriberForEvent(eventClass);
    }

    public static void cancelEventDelivery(Object event) {
        mEventBus.cancelEventDelivery(event);
    }
}
