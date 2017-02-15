package com.jack.mc.cyg.cygtools.adapter.listview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * 对ListView的ViewHolder的封装
 */

public abstract class CygBaseListViewHolder<DATA> {

    public abstract void setData(DATA data);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final View itemView;
    private SparseArray<View> mViews;

    public CygBaseListViewHolder(View itemView) {
        this.itemView = itemView;
        itemView.setTag(this);
        mViews = new SparseArray<>();
    }

    /**
     * 根据id来获取布局中的view
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(@IdRes int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public View getItemView() {
        return itemView;
    }

    protected Context getContext() {
        return itemView.getContext();
    }
}