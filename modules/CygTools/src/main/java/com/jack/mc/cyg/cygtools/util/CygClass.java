package com.jack.mc.cyg.cygtools.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * class 方法的封装
 */
public final class CygClass {

    private CygClass() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static <T> T newInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static final class ConstructorParam<T> {

        private final Class<T> mCls;
        private final Object mParam;

        public ConstructorParam(Class<T> cls, Object param) {
            mCls = cls;
            mParam = param;
        }

        public Class<T> getCls() {
            return mCls;
        }

        public Object getParam() {
            return mParam;
        }
    }

    public static <T> T newInstanceWithArgs(Class<T> cls, Object[] args) {
        try {
            Class<?>[] argClasses = new Class<?>[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                Object arg = args[i];
                if (arg instanceof ConstructorParam) {
                    ConstructorParam param = (ConstructorParam) arg;
                    argClasses[i] = param.getCls();
                    args[i] = param.getParam();
                } else {
                    argClasses[i] = arg.getClass();
                }
            }
            Constructor<T> constructor = cls.getConstructor(argClasses);
            return constructor.newInstance(args);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }

    public static <T> T newInstance(Class<T> cls, Object... args) {
        Object[] args2 = args.clone();
        return newInstanceWithArgs(cls, args2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Type[] getActualTypeArgumentsOfGenericSuperclass(Class cls) {
        try {
            Type type = cls.getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) (type);
            return parameterizedType.getActualTypeArguments();
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }

    public static Class getActualClassArgumentOfGenericSuperclass(Class cls, int index) {
        try {
            Type[] typeList = getActualTypeArgumentsOfGenericSuperclass(cls);
            if (typeList == null) {
                return null;
            }
            return (Class) typeList[index];
        } catch (Exception e) {
            CygLog.error(e);
            return null;
        }
    }
}
