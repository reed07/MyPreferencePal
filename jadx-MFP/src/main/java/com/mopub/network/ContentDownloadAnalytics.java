package com.mopub.network;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.MoPubError;
import java.util.ArrayList;
import java.util.List;

class ContentDownloadAnalytics {
    @NonNull
    private AdResponse mAdResponse;
    @Nullable
    Long mBeforeLoadTime = null;

    enum DownloadResult {
        AD_LOADED("ad_loaded"),
        MISSING_ADAPTER("missing_adapter"),
        TIMEOUT("timeout"),
        INVALID_DATA("invalid_data");
        
        /* access modifiers changed from: private */
        @NonNull
        public final String value;

        private DownloadResult(String str) {
            this.value = str;
        }
    }

    ContentDownloadAnalytics(@NonNull AdResponse adResponse) {
        Preconditions.checkNotNull(adResponse);
        this.mAdResponse = adResponse;
    }

    /* access modifiers changed from: 0000 */
    public void reportBeforeLoad(@Nullable Context context) {
        if (context != null) {
            String beforeLoadUrl = this.mAdResponse.getBeforeLoadUrl();
            if (!TextUtils.isEmpty(beforeLoadUrl)) {
                this.mBeforeLoadTime = Long.valueOf(SystemClock.uptimeMillis());
                TrackingRequest.makeTrackingHttpRequest(beforeLoadUrl, context);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAfterLoad(@Nullable Context context, @Nullable MoPubError moPubError) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadUrls(), errorCodeToDownloadResult(moPubError).value), context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAfterLoadSuccess(@Nullable Context context) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadSuccessUrls(), DownloadResult.AD_LOADED.value), context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAfterLoadFail(@Nullable Context context, @Nullable MoPubError moPubError) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadFailUrls(), errorCodeToDownloadResult(moPubError).value), context);
        }
    }

    @Nullable
    private List<String> generateAfterLoadUrls(@Nullable List<String> list, @NonNull String str) {
        if (list == null || list.isEmpty() || this.mBeforeLoadTime == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String replace : list) {
            arrayList.add(replace.replace("%%LOAD_DURATION_MS%%", String.valueOf(SystemClock.uptimeMillis() - this.mBeforeLoadTime.longValue())).replace("%%LOAD_RESULT%%", Uri.encode(str)));
        }
        return arrayList;
    }

    @NonNull
    private DownloadResult errorCodeToDownloadResult(@Nullable MoPubError moPubError) {
        if (moPubError == null) {
            return DownloadResult.AD_LOADED;
        }
        switch (moPubError.getIntCode()) {
            case 0:
                return DownloadResult.AD_LOADED;
            case 1:
                return DownloadResult.MISSING_ADAPTER;
            case 2:
                return DownloadResult.TIMEOUT;
            default:
                return DownloadResult.INVALID_DATA;
        }
    }
}
