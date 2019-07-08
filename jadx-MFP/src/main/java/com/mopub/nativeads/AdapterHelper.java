package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

public final class AdapterHelper {
    @NonNull
    private final Context mApplicationContext;
    @NonNull
    private final WeakReference<Context> mContext;
    private final int mInterval;
    private final int mStart;

    public AdapterHelper(@NonNull Context context, int i, int i2) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        boolean z = true;
        Preconditions.checkArgument(i >= 0, "start position must be non-negative");
        if (i2 < 2) {
            z = false;
        }
        Preconditions.checkArgument(z, "interval must be at least 2");
        this.mContext = new WeakReference<>(context);
        this.mApplicationContext = context.getApplicationContext();
        this.mStart = i;
        this.mInterval = i2;
    }

    @NonNull
    public View getAdView(@Nullable View view, @Nullable ViewGroup viewGroup, @Nullable NativeAd nativeAd, @Nullable ViewBinder viewBinder) {
        Context context = (Context) this.mContext.get();
        if (context != null) {
            return NativeAdViewHelper.getAdView(view, viewGroup, context, nativeAd);
        }
        MoPubLog.w("Weak reference to Context in AdapterHelper became null. Returning empty view.");
        return new View(this.mApplicationContext);
    }

    @NonNull
    public View getAdView(@Nullable View view, @Nullable ViewGroup viewGroup, @Nullable NativeAd nativeAd) {
        return getAdView(view, viewGroup, nativeAd, null);
    }

    public int shiftedCount(int i) {
        return i + numberOfAdsThatCouldFitWithContent(i);
    }

    public int shiftedPosition(int i) {
        return i - numberOfAdsSeenUpToPosition(i);
    }

    public boolean isAdPosition(int i) {
        int i2 = this.mStart;
        boolean z = false;
        if (i < i2) {
            return false;
        }
        if ((i - i2) % this.mInterval == 0) {
            z = true;
        }
        return z;
    }

    private int numberOfAdsSeenUpToPosition(int i) {
        int i2 = this.mStart;
        if (i <= i2) {
            return 0;
        }
        return ((int) Math.floor(((double) (i - i2)) / ((double) this.mInterval))) + 1;
    }

    private int numberOfAdsThatCouldFitWithContent(int i) {
        int i2 = this.mStart;
        if (i <= i2) {
            return 0;
        }
        int i3 = this.mInterval - 1;
        if ((i - i2) % i3 == 0) {
            return (i - i2) / i3;
        }
        return ((int) Math.floor(((double) (i - i2)) / ((double) i3))) + 1;
    }
}
