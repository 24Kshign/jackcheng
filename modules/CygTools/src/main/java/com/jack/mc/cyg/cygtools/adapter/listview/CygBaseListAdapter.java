package com.jack.mc.cyg.cygtools.adapter.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 对BaseAdapter的封装
 */
public abstract class CygBaseListAdapter<T> extends BaseAdapter {

//    protected abstract View findView(int position, View convertView, ViewGroup parent, T data);

    protected abstract CygBaseListViewHolder<T> getHolder(ViewGroup parent);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public CygBaseListAdapter(Context context, boolean isDataListReferenceMode) {
        mIsDataListReferenceMode = isDataListReferenceMode;
        this.context = context;
    }

    public CygBaseListAdapter(Context context) {
        this(context, true);
    }

    private final boolean mIsDataListReferenceMode;  //是否是引用模式
    private Context context;
    private List<T> mDataList;

    public List<T> getDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        if (mIsDataListReferenceMode) {
            mDataList = dataList;
        } else {
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            } else {
                mDataList.clear();
            }
            if (dataList != null) {
                mDataList.addAll(dataList);
            }
        }
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return mDataList.get(position);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        if (mDataList == null) {
            return null;
        }
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CygBaseListViewHolder baseListViewHolder;
        if (convertView == null) {
            baseListViewHolder = getHolder(parent);
        } else {
            baseListViewHolder = (CygBaseListViewHolder) convertView.getTag();
        }
        baseListViewHolder.setData(getItem(position));
        return baseListViewHolder.getItemView();
    }

    //    @Override
//    public final View findView(int position, View convertView, ViewGroup parent) {
//        try {
//            T data = null;
//            if (mDataList != null && mDataList.size() > position) {
//                data = mDataList.get(position);
//            }
//            return findView(position, convertView, parent, data);
//        } catch (Exception e) {
//            CygLog.error(e);
//        }
//        return null;
//    }
}
