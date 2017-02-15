package com.jack.mc.cyg.cygtools.listener;

import android.widget.AbsListView;

import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 * 简化代码，上层可以直接拿到ListView滚到底部的事件
 */
public abstract class CygOnListViewScrollToBottomListener implements AbsListView.OnScrollListener {

    protected abstract void onScrollToBottom(AbsListView view);

    @Override
    public final void onScrollStateChanged(AbsListView view, int scrollState) {
        // 当不滚动时
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            // 判断是否滚动到底部
            if (view.getLastVisiblePosition() == view.getCount() - 1) {
                try {
                    onScrollToBottom(view);
                } catch (Exception e) {
                    CygLog.error(e);
                }
            }
        }
    }

    @Override
    public final void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }
}
