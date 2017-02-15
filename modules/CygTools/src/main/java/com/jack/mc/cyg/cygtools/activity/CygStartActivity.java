package com.jack.mc.cyg.cygtools.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 启动Activity的封装
 */
public final class CygStartActivity {

    private CygStartActivity() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int DEFAULT_FLAGS = 0;

    private static Intent newIntent(Context context, Class<? extends Activity> cls, Bundle bundle, int flags) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(flags);
        return intent;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // startActivity

    public static void start(Context context, Class<? extends Activity> cls, Bundle bundle, int flags) {
        Intent intent = newIntent(context, cls, bundle, flags);
        context.startActivity(intent);
    }

    public static void start(Context context, Class<? extends Activity> cls, Bundle bundle) {
        start(context, cls, bundle, DEFAULT_FLAGS);
    }

    public static void start(Context context, Class<? extends Activity> cls, int flags) {
        start(context, cls, null, flags);
    }

    public static void start(Context context, Class<? extends Activity> cls) {
        start(context, cls, null, DEFAULT_FLAGS);
    }

    ////////////////////////////////////////
    // startActivityForResult

    public static void startForResult(Activity activity, Class<? extends Activity> cls, Bundle bundle, int flags, int requestCode) {
        Intent intent = newIntent(activity, cls, bundle, flags);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startForResult(Activity activity, Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        startForResult(activity, cls, bundle, DEFAULT_FLAGS, requestCode);
    }

    public static void startForResult(Activity activity, Class<? extends Activity> cls, int flags, int requestCode) {
        startForResult(activity, cls, null, flags, requestCode);
    }

    public static void startForResult(Activity activity, Class<? extends Activity> cls, int requestCode) {
        startForResult(activity, cls, null, DEFAULT_FLAGS, requestCode);
    }

    ////////////////////////////////////////
    // Fragment startActivity

    public static void start(Fragment fragment, Class<? extends Activity> cls, Bundle bundle, int flags) {
        start(fragment.getActivity(), cls, bundle, flags);
    }

    public static void start(Fragment fragment, Class<? extends Activity> cls, Bundle bundle) {
        start(fragment.getActivity(), cls, bundle, DEFAULT_FLAGS);
    }

    public static void start(Fragment fragment, Class<? extends Activity> cls, int flags) {
        start(fragment.getActivity(), cls, null, flags);
    }

    public static void start(Fragment fragment, Class<? extends Activity> cls) {
        start(fragment.getActivity(), cls, null, DEFAULT_FLAGS);
    }

    ////////////////////////////////////////
    // Fragment startActivityForResult

    public static void startForResult(Fragment fragment, Class<? extends Activity> cls, Bundle bundle, int flags, int requestCode) {
        startForResult(fragment.getActivity(), cls, bundle, flags, requestCode);
    }

    public static void startForResult(Fragment fragment, Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        startForResult(fragment.getActivity(), cls, bundle, DEFAULT_FLAGS, requestCode);
    }

    public static void startForResult(Fragment fragment, Class<? extends Activity> cls, int flags, int requestCode) {
        startForResult(fragment.getActivity(), cls, null, flags, requestCode);
    }

    public static void startForResult(Fragment fragment, Class<? extends Activity> cls, int requestCode) {
        startForResult(fragment.getActivity(), cls, null, DEFAULT_FLAGS, requestCode);
    }
}
