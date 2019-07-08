package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class SwipeRefreshListView extends ListView {
    /* access modifiers changed from: private */
    public OnScrollListener extraOnScrollListener;
    private OnScrollListener onSwipeRefreshScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (SwipeRefreshListView.this.extraOnScrollListener != null) {
                SwipeRefreshListView.this.extraOnScrollListener.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (SwipeRefreshListView.this.refreshLayout != null) {
                boolean z = false;
                int top = SwipeRefreshListView.this.getChildCount() == 0 ? 0 : SwipeRefreshListView.this.getChildAt(0).getTop();
                SwipeRefreshLayout access$100 = SwipeRefreshListView.this.refreshLayout;
                if (top >= 0) {
                    z = true;
                }
                access$100.setEnabled(z);
            }
            if (SwipeRefreshListView.this.extraOnScrollListener != null) {
                SwipeRefreshListView.this.extraOnScrollListener.onScroll(absListView, i, i2, i3);
            }
        }
    };
    /* access modifiers changed from: private */
    public SwipeRefreshLayout refreshLayout;

    public SwipeRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SwipeRefreshListView(Context context) {
        super(context);
        init();
    }

    public SwipeRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        super.setOnScrollListener(this.onSwipeRefreshScrollListener);
    }

    public void setRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.refreshLayout = swipeRefreshLayout;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.extraOnScrollListener = onScrollListener;
        super.setOnScrollListener(this.onSwipeRefreshScrollListener);
    }
}
