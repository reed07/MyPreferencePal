package com.uacf.core.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.Map;

public final class ListViewUtils {

    public interface TopBottomScrollListener {
        void onScrolledToBottom();

        void onScrolledToTopChanged(boolean z);
    }

    public static void jumpToPosition(ListView listView, int i, boolean z) {
        if (z) {
            listView.smoothScrollToPosition(i);
            return;
        }
        listView.setSelection(i);
        View childAt = listView.getChildAt(i);
        if (childAt != null) {
            childAt.requestFocus();
        }
    }

    public static void notifyDataSetChanged(ListView listView) {
        if (listView != null) {
            notifyDataSetChanged((Adapter) listView.getAdapter());
        }
    }

    public static void notifyDataSetChanged(Adapter adapter) {
        if (adapter instanceof HeaderViewListAdapter) {
            adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        }
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).notifyDataSetChanged();
        }
    }

    public static <T extends Adapter> T getAdapter(ListView listView, Class<T> cls) {
        if (listView != null) {
            T adapter = listView.getAdapter();
            if ((adapter instanceof HeaderViewListAdapter) && cls != null) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                if (cls.isAssignableFrom(headerViewListAdapter.getWrappedAdapter().getClass())) {
                    return headerViewListAdapter.getWrappedAdapter();
                }
            }
            if (!(adapter == null || cls == null || !cls.isAssignableFrom(adapter.getClass()))) {
                return adapter;
            }
        }
        return null;
    }

    public static void jumpToLastItem(ListView listView) {
        jumpToPosition(listView, listView.getAdapter().getCount() - 1, false);
    }

    public static void setupListViewForEmbeddingInsideScrollview(ListView listView) {
        listView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        setListViewHeightBasedOnChildren(listView);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(listView.getWidth(), 0);
            View view = null;
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                view = adapter.getView(i2, view, listView);
                if (i2 == 0) {
                    view.setLayoutParams(new LayoutParams(makeMeasureSpec, -2));
                }
                view.measure(makeMeasureSpec, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = i + (listView.getDividerHeight() * (adapter.getCount() - 1));
            listView.setLayoutParams(layoutParams);
            listView.requestLayout();
        }
    }

    public static boolean isEmpty(ListAdapter listAdapter) {
        return listAdapter == null || listAdapter.getCount() == 0;
    }

    public static void addScrollTopBottomListener(ListView listView, final TopBottomScrollListener topBottomScrollListener) {
        listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 0) {
                    ListAdapter listAdapter = (ListAdapter) absListView.getAdapter();
                    if (absListView.getLastVisiblePosition() == (listAdapter != null ? listAdapter.getCount() - 1 : -1)) {
                        topBottomScrollListener.onScrolledToBottom();
                    }
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int firstVisiblePosition = absListView.getFirstVisiblePosition();
                boolean z = false;
                View childAt = absListView.getChildAt(0);
                int top = childAt != null ? childAt.getTop() : 0;
                if (firstVisiblePosition == 0 && top == 0) {
                    z = true;
                }
                topBottomScrollListener.onScrolledToTopChanged(z);
            }
        });
    }

    public static void refill(ArrayAdapter arrayAdapter, List list) {
        arrayAdapter.setNotifyOnChange(false);
        arrayAdapter.clear();
        arrayAdapter.setNotifyOnChange(true);
        arrayAdapter.addAll(list);
    }

    public static int getViewType(List<?> list, Map<Class<?>, Integer> map, int i) {
        Integer num = (Integer) map.get(list.get(i).getClass());
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
