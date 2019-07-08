package com.mopub.mobileads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;

class VastIconConfig implements Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final String mClickThroughUri;
    @NonNull
    private final List<VastTracker> mClickTrackingUris;
    @Nullable
    private final Integer mDurationMS;
    private final int mHeight;
    private final int mOffsetMS;
    @NonNull
    private final VastResource mVastResource;
    @NonNull
    private final List<VastTracker> mViewTrackingUris;
    private final int mWidth;

    VastIconConfig(int i, int i2, @Nullable Integer num, @Nullable Integer num2, @NonNull VastResource vastResource, @NonNull List<VastTracker> list, @Nullable String str, @NonNull List<VastTracker> list2) {
        int i3;
        Preconditions.checkNotNull(vastResource);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.mWidth = i;
        this.mHeight = i2;
        if (num == null) {
            i3 = 0;
        } else {
            i3 = num.intValue();
        }
        this.mOffsetMS = i3;
        this.mDurationMS = num2;
        this.mVastResource = vastResource;
        this.mClickTrackingUris = list;
        this.mClickThroughUri = str;
        this.mViewTrackingUris = list2;
    }

    /* access modifiers changed from: 0000 */
    public int getWidth() {
        return this.mWidth;
    }

    /* access modifiers changed from: 0000 */
    public int getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: 0000 */
    public int getOffsetMS() {
        return this.mOffsetMS;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getDurationMS() {
        return this.mDurationMS;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public VastResource getVastResource() {
        return this.mVastResource;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getClickTrackingUris() {
        return this.mClickTrackingUris;
    }

    /* access modifiers changed from: 0000 */
    public void handleImpression(@NonNull Context context, int i, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        TrackingRequest.makeVastTrackingHttpRequest(this.mViewTrackingUris, null, Integer.valueOf(i), str, context);
    }

    /* access modifiers changed from: 0000 */
    public void handleClick(@NonNull final Context context, @Nullable String str, @Nullable final String str2) {
        Preconditions.checkNotNull(context);
        String correctClickThroughUrl = this.mVastResource.getCorrectClickThroughUrl(this.mClickThroughUri, str);
        if (!TextUtils.isEmpty(correctClickThroughUrl)) {
            new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER).withResultActions(new ResultActions() {
                public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
                }

                public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        if (!TextUtils.isEmpty(str2)) {
                            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str2);
                        }
                        try {
                            Intents.startActivity(context, Intents.getStartActivityIntent(context, MoPubBrowser.class, bundle));
                        } catch (IntentNotResolvableException e) {
                            MoPubLog.d(e.getMessage());
                        }
                    }
                }
            }).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
        }
    }
}
