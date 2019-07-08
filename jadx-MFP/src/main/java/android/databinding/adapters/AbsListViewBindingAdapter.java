package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

@BindingMethods({@BindingMethod(attribute = "android:listSelector", method = "setSelector", type = AbsListView.class), @BindingMethod(attribute = "android:scrollingCache", method = "setScrollingCacheEnabled", type = AbsListView.class), @BindingMethod(attribute = "android:smoothScrollbar", method = "setSmoothScrollbarEnabled", type = AbsListView.class), @BindingMethod(attribute = "android:onMovedToScrapHeap", method = "setRecyclerListener", type = AbsListView.class)})
@RestrictTo
public class AbsListViewBindingAdapter {

    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }

    @BindingAdapter(requireAll = false, value = {"android:onScroll", "android:onScrollStateChanged"})
    public static void setOnScroll(AbsListView absListView, final OnScroll onScroll, final OnScrollStateChanged onScrollStateChanged) {
        absListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
                OnScrollStateChanged onScrollStateChanged = onScrollStateChanged;
                if (onScrollStateChanged != null) {
                    onScrollStateChanged.onScrollStateChanged(absListView, i);
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                OnScroll onScroll = onScroll;
                if (onScroll != null) {
                    onScroll.onScroll(absListView, i, i2, i3);
                }
            }
        });
    }
}
