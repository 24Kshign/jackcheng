package com.jack.mc.cyg.cygtools.presenter;

import com.jack.mc.cyg.cygtools.util.CygClass;

/**
 *
 */
public class CygPresenter {

    public static <T> T getPresent(Class<T> cls) {
        T instance = CygClass.newInstance(cls);
        if (instance == null) {
            return null;
        }
        return instance;
    }
}
