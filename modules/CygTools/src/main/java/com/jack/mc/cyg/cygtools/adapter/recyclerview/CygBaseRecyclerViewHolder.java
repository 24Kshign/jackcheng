package com.jack.mc.cyg.cygtools.adapter.recyclerview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * 对RecyclerView的ViewHolder的封装
 */
public abstract class CygBaseRecyclerViewHolder<DATA> extends RecyclerView.ViewHolder {

    public View itemView;
    private SparseArray<View> mViews;

    public CygBaseRecyclerViewHolder(View view) {
        super(view);
        this.itemView = view;
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

    /**
     * 获取Context实例对象
     *
     * @return
     */
    protected Context getContext() {
        return itemView.getContext();
    }

    public abstract void setData(DATA data);
}
