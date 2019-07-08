package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.network.AdLoader;
import com.mopub.network.AdLoader.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.TrackingRequest;
import java.util.Collections;
import java.util.List;

class AdLoaderRewardedVideo extends AdLoader {
    private boolean mClickTrackerFired = false;
    private boolean mImpressionTrackerFired = false;

    AdLoaderRewardedVideo(@NonNull String str, @NonNull AdFormat adFormat, @NonNull String str2, @NonNull Context context, @NonNull Listener listener) {
        super(str, adFormat, str2, context, listener);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<String> getImpressionUrls() {
        if (this.mLastDeliveredResponse != null) {
            return this.mLastDeliveredResponse.getImpressionTrackingUrls();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getClickUrl() {
        if (this.mLastDeliveredResponse != null) {
            return this.mLastDeliveredResponse.getClickTrackingUrl();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public AdResponse getLastDeliveredResponse() {
        return this.mLastDeliveredResponse;
    }

    /* access modifiers changed from: 0000 */
    public void trackImpression(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (this.mLastDeliveredResponse != null && !this.mImpressionTrackerFired) {
            this.mImpressionTrackerFired = true;
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) getImpressionUrls(), context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void trackClick(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (this.mLastDeliveredResponse != null && !this.mClickTrackerFired) {
            this.mClickTrackerFired = true;
            TrackingRequest.makeTrackingHttpRequest(getClickUrl(), context);
        }
    }
}
