package com.jack.mc.cyg.cygtools.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.activity.CygActivityUtil;
import com.jack.mc.cyg.cygtools.eventbus.CygEventBusUtil;
import com.jack.mc.cyg.cygtools.inputmethod.CygView;
import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 * 对Fragment的封装
 */

public abstract class CygFragment extends Fragment {
    protected abstract int layoutRes();

    /**
     * onViewReallyCreated才是真正创建了View
     */
    protected void onViewReallyCreated(View view) {
    }

    protected boolean isSaveViewStatus() {
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * root view 与 getView() 的区别：
     * isSaveViewStatus()为true的情况下，root view 创建后就一直存在，保存着view的状态
     * getView() 在 Fragment 里控制，通过 FragmentTransaction 进行各种操作的时候会丢失view
     */
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CygEventBusUtil.registerSubscriber(this);  //注册EventBus
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CygEventBusUtil.unregisterSubscriber(this);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = rootView();
        if (!isSaveViewStatus() || rootView == null) {
            CygLog.debug("inflate root view");
            rootView = inflater.inflate(layoutRes(), null);
            mRootView = rootView;
            onViewReallyCreated(rootView);
        } else {
            CygLog.debug("root view is already created");
            // 缓存的mRootView需要判断是否已经被加过parent
            // 如果有parent需要从parent删除，要不然会发生这个mRootView已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                CygLog.debug("parent != null");
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    /**
     * final了，不能Override了
     */
    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected final boolean isAlive() {
        return CygActivityUtil.isActive(getActivity());
    }

    protected final View inflateLayout(@LayoutRes int layoutRes) {
        return CygView.inflateLayout(getActivity(), layoutRes);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // delegate RzViewController

    public View rootView() {
        return mRootView;
    }

    public <VIEW extends View> VIEW findView(@IdRes int id) {
        return CygView.findView(mRootView, id);
    }

}
