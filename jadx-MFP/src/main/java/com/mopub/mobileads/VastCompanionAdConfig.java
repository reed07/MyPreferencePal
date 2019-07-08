package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;

public class VastCompanionAdConfig implements Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final String mClickThroughUrl;
    @NonNull
    private final List<VastTracker> mClickTrackers;
    @NonNull
    private final List<VastTracker> mCreativeViewTrackers;
    private final int mHeight;
    @NonNull
    private final VastResource mVastResource;
    private final int mWidth;

    public VastCompanionAdConfig(int i, int i2, @NonNull VastResource vastResource, @Nullable String str, @NonNull List<VastTracker> list, @NonNull List<VastTracker> list2) {
        Preconditions.checkNotNull(vastResource);
        Preconditions.checkNotNull(list, "clickTrackers cannot be null");
        Preconditions.checkNotNull(list2, "creativeViewTrackers cannot be null");
        this.mWidth = i;
        this.mHeight = i2;
        this.mVastResource = vastResource;
        this.mClickThroughUrl = str;
        this.mClickTrackers = list;
        this.mCreativeViewTrackers = list2;
    }

    public void addClickTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(list);
    }

    public void addCreativeViewTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "creativeViewTrackers cannot be null");
        this.mCreativeViewTrackers.addAll(list);
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @NonNull
    public VastResource getVastResource() {
        return this.mVastResource;
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    @NonNull
    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    @NonNull
    public List<VastTracker> getCreativeViewTrackers() {
        return this.mCreativeViewTrackers;
    }

    /* access modifiers changed from: 0000 */
    public void handleImpression(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context);
        TrackingRequest.makeVastTrackingHttpRequest(this.mCreativeViewTrackers, null, Integer.valueOf(i), null, context);
    }

    /* access modifiers changed from: 0000 */
    public void handleClick(@NonNull final Context context, final int i, @Nullable String str, @Nullable final String str2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkArgument(context instanceof Activity, "context must be an activity");
        String correctClickThroughUrl = this.mVastResource.getCorrectClickThroughUrl(this.mClickThroughUrl, str);
        if (!TextUtils.isEmpty(correctClickThroughUrl)) {
            new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new ResultActions() {
                public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
                }

                public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        if (!TextUtils.isEmpty(str2)) {
                            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str2);
                        }
                        Class<MoPubBrowser> cls = MoPubBrowser.class;
                        try {
                            ((Activity) context).startActivityForResult(Intents.getStartActivityIntent(context, cls, bundle), i);
                        } catch (ActivityNotFoundException unused) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Activity ");
                            sb.append(cls.getName());
                            sb.append(" not found. Did you declare it in your AndroidManifest.xml?");
                            MoPubLog.d(sb.toString());
                        }
                    }
                }
            }).withDspCreativeId(str2).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
        }
    }
}
