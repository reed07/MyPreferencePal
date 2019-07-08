package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.BaseNativeAd.NativeEventListener;
import com.mopub.network.TrackingRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NativeAd {
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private final BaseNativeAd mBaseNativeAd;
    @NonNull
    private final Set<String> mClickTrackers;
    @NonNull
    private final Context mContext;
    @NonNull
    private final Set<String> mImpressionTrackers = new HashSet();
    private boolean mIsClicked;
    private boolean mIsDestroyed;
    @NonNull
    private final MoPubAdRenderer mMoPubAdRenderer;
    @Nullable
    private MoPubNativeEventListener mMoPubNativeEventListener;
    private boolean mRecordedImpression;

    public interface MoPubNativeEventListener {
        void onClick(View view);

        void onImpression(View view);
    }

    public NativeAd(@NonNull Context context, @NonNull List<String> list, @NonNull String str, @NonNull String str2, @NonNull BaseNativeAd baseNativeAd, @NonNull MoPubAdRenderer moPubAdRenderer) {
        this.mContext = context.getApplicationContext();
        this.mAdUnitId = str2;
        this.mImpressionTrackers.addAll(list);
        this.mImpressionTrackers.addAll(baseNativeAd.getImpressionTrackers());
        this.mClickTrackers = new HashSet();
        this.mClickTrackers.add(str);
        this.mClickTrackers.addAll(baseNativeAd.getClickTrackers());
        this.mBaseNativeAd = baseNativeAd;
        this.mBaseNativeAd.setNativeEventListener(new NativeEventListener() {
            public void onAdImpressed() {
                NativeAd.this.recordImpression(null);
            }

            public void onAdClicked() {
                NativeAd.this.handleClick(null);
            }
        });
        this.mMoPubAdRenderer = moPubAdRenderer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(InMobiNetworkValues.IMPRESSION_TRACKERS);
        sb.append(":");
        sb.append(this.mImpressionTrackers);
        sb.append("\n");
        sb.append("clickTrackers");
        sb.append(":");
        sb.append(this.mClickTrackers);
        sb.append("\n");
        sb.append("recordedImpression");
        sb.append(":");
        sb.append(this.mRecordedImpression);
        sb.append("\n");
        sb.append("isClicked");
        sb.append(":");
        sb.append(this.mIsClicked);
        sb.append("\n");
        sb.append("isDestroyed");
        sb.append(":");
        sb.append(this.mIsDestroyed);
        sb.append("\n");
        return sb.toString();
    }

    public void setMoPubNativeEventListener(@Nullable MoPubNativeEventListener moPubNativeEventListener) {
        this.mMoPubNativeEventListener = moPubNativeEventListener;
    }

    @NonNull
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    @NonNull
    public BaseNativeAd getBaseNativeAd() {
        return this.mBaseNativeAd;
    }

    @NonNull
    public View createAdView(@NonNull Context context, @Nullable ViewGroup viewGroup) {
        return this.mMoPubAdRenderer.createAdView(context, viewGroup);
    }

    public void renderAdView(View view) {
        this.mMoPubAdRenderer.renderAdView(view, this.mBaseNativeAd);
    }

    @NonNull
    public MoPubAdRenderer getMoPubAdRenderer() {
        return this.mMoPubAdRenderer;
    }

    public void prepare(@NonNull View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.prepare(view);
        }
    }

    public void clear(@NonNull View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.clear(view);
        }
    }

    public void destroy() {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.destroy();
            this.mIsDestroyed = true;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void recordImpression(@Nullable View view) {
        if (!this.mRecordedImpression && !this.mIsDestroyed) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) this.mImpressionTrackers, this.mContext);
            MoPubNativeEventListener moPubNativeEventListener = this.mMoPubNativeEventListener;
            if (moPubNativeEventListener != null) {
                moPubNativeEventListener.onImpression(view);
            }
            this.mRecordedImpression = true;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleClick(@Nullable View view) {
        if (!this.mIsClicked && !this.mIsDestroyed) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) this.mClickTrackers, this.mContext);
            MoPubNativeEventListener moPubNativeEventListener = this.mMoPubNativeEventListener;
            if (moPubNativeEventListener != null) {
                moPubNativeEventListener.onClick(view);
            }
            this.mIsClicked = true;
        }
    }
}
