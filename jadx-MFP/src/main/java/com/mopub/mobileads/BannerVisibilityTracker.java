package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Views;
import java.lang.ref.WeakReference;

class BannerVisibilityTracker {
    /* access modifiers changed from: private */
    @Nullable
    public BannerVisibilityTrackerListener mBannerVisibilityTrackerListener;
    /* access modifiers changed from: private */
    public boolean mIsImpTrackerFired;
    /* access modifiers changed from: private */
    public boolean mIsVisibilityScheduled;
    @NonNull
    @VisibleForTesting
    final OnPreDrawListener mOnPreDrawListener = new OnPreDrawListener() {
        public boolean onPreDraw() {
            BannerVisibilityTracker.this.scheduleVisibilityCheck();
            return true;
        }
    };
    /* access modifiers changed from: private */
    @NonNull
    public final View mRootView;
    /* access modifiers changed from: private */
    @NonNull
    public final View mTrackedView;
    /* access modifiers changed from: private */
    @NonNull
    public final BannerVisibilityChecker mVisibilityChecker;
    @NonNull
    private final Handler mVisibilityHandler = new Handler();
    @NonNull
    private final BannerVisibilityRunnable mVisibilityRunnable = new BannerVisibilityRunnable();
    @NonNull
    @VisibleForTesting
    WeakReference<ViewTreeObserver> mWeakViewTreeObserver = new WeakReference<>(null);

    static class BannerVisibilityChecker {
        private final Rect mClipRect = new Rect();
        private int mMinVisibleDips;
        private int mMinVisibleMillis;
        private long mStartTimeMillis = Long.MIN_VALUE;

        BannerVisibilityChecker(int i, int i2) {
            this.mMinVisibleDips = i;
            this.mMinVisibleMillis = i2;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasBeenVisibleYet() {
            return this.mStartTimeMillis != Long.MIN_VALUE;
        }

        /* access modifiers changed from: 0000 */
        public void setStartTimeMillis() {
            this.mStartTimeMillis = SystemClock.uptimeMillis();
        }

        /* access modifiers changed from: 0000 */
        public boolean hasRequiredTimeElapsed() {
            boolean z = false;
            if (!hasBeenVisibleYet()) {
                return false;
            }
            if (SystemClock.uptimeMillis() - this.mStartTimeMillis >= ((long) this.mMinVisibleMillis)) {
                z = true;
            }
            return z;
        }

        /* access modifiers changed from: 0000 */
        public boolean isVisible(@Nullable View view, @Nullable View view2) {
            boolean z = false;
            if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || view2.getWidth() <= 0 || view2.getHeight() <= 0 || !view2.getGlobalVisibleRect(this.mClipRect)) {
                return false;
            }
            if (((long) (Dips.pixelsToIntDips((float) this.mClipRect.width(), view2.getContext()) * Dips.pixelsToIntDips((float) this.mClipRect.height(), view2.getContext()))) >= ((long) this.mMinVisibleDips)) {
                z = true;
            }
            return z;
        }
    }

    class BannerVisibilityRunnable implements Runnable {
        BannerVisibilityRunnable() {
        }

        public void run() {
            if (!BannerVisibilityTracker.this.mIsImpTrackerFired) {
                BannerVisibilityTracker.this.mIsVisibilityScheduled = false;
                if (BannerVisibilityTracker.this.mVisibilityChecker.isVisible(BannerVisibilityTracker.this.mRootView, BannerVisibilityTracker.this.mTrackedView)) {
                    if (!BannerVisibilityTracker.this.mVisibilityChecker.hasBeenVisibleYet()) {
                        BannerVisibilityTracker.this.mVisibilityChecker.setStartTimeMillis();
                    }
                    if (BannerVisibilityTracker.this.mVisibilityChecker.hasRequiredTimeElapsed() && BannerVisibilityTracker.this.mBannerVisibilityTrackerListener != null) {
                        BannerVisibilityTracker.this.mBannerVisibilityTrackerListener.onVisibilityChanged();
                        BannerVisibilityTracker.this.mIsImpTrackerFired = true;
                    }
                }
                if (!BannerVisibilityTracker.this.mIsImpTrackerFired) {
                    BannerVisibilityTracker.this.scheduleVisibilityCheck();
                }
            }
        }
    }

    interface BannerVisibilityTrackerListener {
        void onVisibilityChanged();
    }

    @VisibleForTesting
    public BannerVisibilityTracker(@NonNull Context context, @NonNull View view, @NonNull View view2, int i, int i2) {
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view2);
        this.mRootView = view;
        this.mTrackedView = view2;
        this.mVisibilityChecker = new BannerVisibilityChecker(i, i2);
        setViewTreeObserver(context, this.mTrackedView);
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

    /* access modifiers changed from: 0000 */
    public void setBannerVisibilityTrackerListener(@Nullable BannerVisibilityTrackerListener bannerVisibilityTrackerListener) {
        this.mBannerVisibilityTrackerListener = bannerVisibilityTrackerListener;
    }

    /* access modifiers changed from: 0000 */
    public void destroy() {
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mWeakViewTreeObserver.clear();
        this.mBannerVisibilityTrackerListener = null;
    }

    /* access modifiers changed from: 0000 */
    public void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }
}
