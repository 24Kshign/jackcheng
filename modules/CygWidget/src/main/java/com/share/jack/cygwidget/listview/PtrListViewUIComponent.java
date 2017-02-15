package com.share.jack.cygwidget.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jack.mc.cyg.cygptr.PtrClassicFrameLayout;
import com.jack.mc.cyg.cygptr.PtrDefaultHandler;
import com.jack.mc.cyg.cygptr.PtrFrameLayout;
import com.jack.mc.cyg.cygptr.PtrHandler;
import com.jack.mc.cyg.cygptr.loadmore.OnLoadMoreListener;
import com.share.jack.cygwidget.R;
import com.share.jack.cygwidget.loadmore.OnScrollToBottomLoadMoreListener;
import com.share.jack.cygwidget.refersh.OnPullToRefreshListener;


/**
 *
 */
public class PtrListViewUIComponent extends PtrClassicFrameLayout {

    public PtrListViewUIComponent(Context context) {
        super(context);
    }

    public PtrListViewUIComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrListViewUIComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnScrollToBottomLoadMoreListener mOnScrollToBottomLoadMoreListener;

    private OnPullToRefreshListener mOnPullToRefreshListener;

    private ListView mListView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mOnPullToRefreshListener.onPullToRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }
        });

        setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mOnScrollToBottomLoadMoreListener.onScrollToBottomLoadMore();
            }
        });

        mListView = (ListView) findViewById(R.id.listview_uicomponent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 下拉刷新
     */
    public void setOnPullToRefreshListener(OnPullToRefreshListener refreshListener) {
        mOnPullToRefreshListener = refreshListener;
    }

    /**
     * 上拉加载
     */
    public void setOnScrollToBottomLoadMoreListener(OnScrollToBottomLoadMoreListener mOnScrollToBottomLoadMoreListener) {
        this.mOnScrollToBottomLoadMoreListener = mOnScrollToBottomLoadMoreListener;
    }

    /**
     * 设置ListView的adapter
     */
    public void setAdapter(ListAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    /**
     * 设置listview分割线的高度
     *
     * @param hight
     */
    public void setListViewDividerHight(int hight) {
        mListView.setDividerHeight(hight);
    }

    public void addHeaderView(View headerView) {
        mListView.addHeaderView(headerView, null, false);
    }

    public void addFooterView(View footerView) {
        mListView.addFooterView(footerView, null, false);
    }

    public void setListViewDivider(Drawable divider) {
        mListView.setDivider(divider);
    }

    public void setBackGround(int resId){
        mListView.setBackgroundResource(resId);
    }

    /**
     * 设置ListView item的点击事件T
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

    public ListView getListView() {
        return mListView;
    }

    public void delayRefresh(long delayMillis) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                autoRefresh();
            }
        }, delayMillis);
    }
}
