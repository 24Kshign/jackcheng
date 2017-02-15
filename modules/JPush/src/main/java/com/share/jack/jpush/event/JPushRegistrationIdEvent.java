package com.share.jack.jpush.event;

/**
 *
 */
public class JPushRegistrationIdEvent {

    private final String mRegistrationId;

    public JPushRegistrationIdEvent(String registrationId) {
        mRegistrationId = registrationId;
    }

    public String getRegistrationId() {
        return mRegistrationId;
    }
}
