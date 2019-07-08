package com.mopub.common;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class VisibilityTracker {
    private long mAccessCounter;
    /* access modifiers changed from: private */
    public boolean mIsVisibilityScheduled;
    @NonNull
    @VisibleForTesting
    final OnPreDrawListener mOnPreDrawListener;
    /* access modifiers changed from: private */
    @NonNull
    public final Map<View, TrackingInfo> mTrackedViews;
    @NonNull
    private final ArrayList<View> mTrimmedViews;
    /* access modifiers changed from: private */
    @NonNull
    public final VisibilityChecker mVisibilityChecker;
    @NonNull
    private final Handler mVisibilityHandler;
    @NonNull
    private final VisibilityRunnable mVisibilityRunnable;
    /* access modifiers changed from: private */
    @Nullable
    public VisibilityTrackerListener mVisibilityTrackerListener;
    @NonNull
    @VisibleForTesting
    WeakReference<ViewTreeObserver> mWeakViewTreeObserver;

    static class TrackingInfo {
        long mAccessOrder;
        int mMaxInvisiblePercent;
        int mMinViewablePercent;
        @Nullable
        Integer mMinVisiblePx;
        View mRootView;

        TrackingInfo() {
        }
    }

    public static class VisibilityChecker {
        private final Rect mClipRect = new Rect();

        public boolean hasRequiredTimeElapsed(long j, int i) {
            return SystemClock.uptimeMillis() - j >= ((long) i);
        }

        public boolean isVisible(@Nullable View view, @Nullable View view2, int i, @Nullable Integer num) {
            if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || !view2.getGlobalVisibleRect(this.mClipRect)) {
                return false;
            }
            long height = ((long) this.mClipRect.height()) * ((long) this.mClipRect.width());
            long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
            if (height2 <= 0) {
                return false;
            }
            boolean z = true;
            if (num == null || num.intValue() <= 0) {
                if (height * 100 < ((long) i) * height2) {
                    z = false;
                }
                return z;
            }
            if (height < ((long) num.intValue())) {
                z = false;
            }
            return z;
        }
    }

    class VisibilityRunnable implements Runnable {
        @NonNull
        private final ArrayList<View> mInvisibleViews = new ArrayList<>();
        @NonNull
        private final ArrayList<View> mVisibleViews = new ArrayList<>();

        VisibilityRunnable() {
        }

        public void run() {
            VisibilityTracker.this.mIsVisibilityScheduled = false;
            for (Entry entry : VisibilityTracker.this.mTrackedViews.entrySet()) {
                View view = (View) entry.getKey();
                int i = ((TrackingInfo) entry.getValue()).mMinViewablePercent;
                int i2 = ((TrackingInfo) entry.getValue()).mMaxInvisiblePercent;
                Integer num = ((TrackingInfo) entry.getValue()).mMinVisiblePx;
                View view2 = ((TrackingInfo) entry.getValue()).mRootView;
                if (VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i, num)) {
                    this.mVisibleViews.add(view);
                } else if (!VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i2, null)) {
                    this.mInvisibleViews.add(view);
                }
            }
            if (VisibilityTracker.this.mVisibilityTrackerListener != null) {
                VisibilityTracker.this.mVisibilityTrackerListener.onVisibilityChanged(this.mVisibleViews, this.mInvisibleViews);
            }
            this.mVisibleViews.clear();
            this.mInvisibleViews.clear();
        }
    }

    public interface VisibilityTrackerListener {
        void onVisibilityChanged(List<View> list, List<View> list2);
    }

    public VisibilityTracker(@NonNull Context context) {
        this(context, new WeakHashMap(10), new VisibilityChecker(), new Handler());
    }

    @VisibleForTesting
    VisibilityTracker(@NonNull Context context, @NonNull Map<View, TrackingInfo> map, @NonNull VisibilityChecker visibilityChecker, @NonNull Handler handler) {
        this.mAccessCounter = 0;
        this.mTrackedViews = map;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityHandler = handler;
        this.mVisibilityRunnable = new VisibilityRunnable();
        this.mTrimmedViews = new ArrayList<>(50);
        this.mOnPreDrawListener = new OnPreDrawListener() {
            public boolean onPreDraw() {
                VisibilityTracker.this.scheduleVisibilityCheck();
                return true;
            }
        };
        this.mWeakViewTreeObserver = new WeakReference<>(null);
        setViewTreeObserver(context, null);
    }

    private void setViewTreeObserver(@Nullable Context context, @Nullable View view) {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            View topmostView = Views.getTopmostView(context, view);
            if (topmostView == null) {
                MoPubLog.d("Unable to set Visibility Tracker due to no available root view.");
                return;
            }
            ViewTreeObserver viewTreeObserver2 = topmostView.getViewTreeObserver();
            if (!viewTreeObserver2.isAlive()) {
                MoPubLog.w("Visibility Tracker was unable to track views because the root view tree observer was not alive");
                return;
            }
            this.mWeakViewTreeObserver = new WeakReference<>(viewTreeObserver2);
            viewTreeObserver2.addOnPreDrawListener(this.mOnPreDrawListener);
        }
    }

    public void setVisibilityTrackerListener(@Nullable VisibilityTrackerListener visibilityTrackerListener) {
        this.mVisibilityTrackerListener = visibilityTrackerListener;
    }

    public void addView(@NonNull View view, int i, @Nullable Integer num) {
        addView(view, view, i, num);
    }

    public void addView(@NonNull View view, @NonNull View view2, int i, @Nullable Integer num) {
        addView(view, view2, i, i, num);
    }

    public void addView(@NonNull View view, @NonNull View view2, int i, int i2, @Nullable Integer num) {
        setViewTreeObserver(view2.getContext(), view2);
        TrackingInfo trackingInfo = (TrackingInfo) this.mTrackedViews.get(view2);
        if (trackingInfo == null) {
            trackingInfo = new TrackingInfo();
            this.mTrackedViews.put(view2, trackingInfo);
            scheduleVisibilityCheck();
        }
        int min = Math.min(i2, i);
        trackingInfo.mRootView = view;
        trackingInfo.mMinViewablePercent = i;
        trackingInfo.mMaxInvisiblePercent = min;
        long j = this.mAccessCounter;
        trackingInfo.mAccessOrder = j;
        trackingInfo.mMinVisiblePx = num;
        this.mAccessCounter = j + 1;
        long j2 = this.mAccessCounter;
        if (j2 % 50 == 0) {
            trimTrackedViews(j2 - 50);
        }
    }

    private void trimTrackedViews(long j) {
        for (Entry entry : this.mTrackedViews.entrySet()) {
            if (((TrackingInfo) entry.getValue()).mAccessOrder < j) {
                this.mTrimmedViews.add(entry.getKey());
            }
        }
        Iterator it = this.mTrimmedViews.iterator();
        while (it.hasNext()) {
            removeView((View) it.next());
        }
        this.mTrimmedViews.clear();
    }

    public void removeView(@NonNull View view) {
        this.mTrackedViews.remove(view);
    }

    public void clear() {
        this.mTrackedViews.clear();
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
    }

    public void destroy() {
        clear();
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mWeakViewTreeObserver.clear();
        this.mVisibilityTrackerListener = null;
    }

    public void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }
}
