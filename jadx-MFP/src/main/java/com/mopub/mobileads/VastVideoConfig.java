package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Strings;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class VastVideoConfig implements Serializable {
    private static final long serialVersionUID = 2;
    @NonNull
    private final ArrayList<VastAbsoluteProgressTracker> mAbsoluteTrackers = new ArrayList<>();
    @NonNull
    private final Set<String> mAvidJavascriptResources = new HashSet();
    @Nullable
    private String mClickThroughUrl;
    @NonNull
    private final ArrayList<VastTracker> mClickTrackers = new ArrayList<>();
    @NonNull
    private final ArrayList<VastTracker> mCloseTrackers = new ArrayList<>();
    @NonNull
    private final ArrayList<VastTracker> mCompleteTrackers = new ArrayList<>();
    @Nullable
    private String mCustomCloseIconUrl;
    @Nullable
    private String mCustomCtaText;
    @NonNull
    private ForceOrientation mCustomForceOrientation = ForceOrientation.FORCE_LANDSCAPE;
    @Nullable
    private String mCustomSkipText;
    @Nullable
    private String mDiskMediaFileUrl;
    /* access modifiers changed from: private */
    public String mDspCreativeId;
    @NonNull
    private final ArrayList<VastTracker> mErrorTrackers = new ArrayList<>();
    @NonNull
    private final Map<String, String> mExternalViewabilityTrackers = new HashMap();
    @NonNull
    private final ArrayList<VastFractionalProgressTracker> mFractionalTrackers = new ArrayList<>();
    @NonNull
    private final ArrayList<VastTracker> mImpressionTrackers = new ArrayList<>();
    private boolean mIsForceOrientationSet;
    private boolean mIsRewardedVideo = false;
    @Nullable
    private VastCompanionAdConfig mLandscapeVastCompanionAdConfig;
    @NonNull
    private final Set<String> mMoatImpressionPixels = new HashSet();
    @Nullable
    private String mNetworkMediaFileUrl;
    @NonNull
    private final ArrayList<VastTracker> mPauseTrackers = new ArrayList<>();
    @Nullable
    private VastCompanionAdConfig mPortraitVastCompanionAdConfig;
    private String mPrivacyInformationIconClickthroughUrl;
    private String mPrivacyInformationIconImageUrl;
    @NonNull
    private final ArrayList<VastTracker> mResumeTrackers = new ArrayList<>();
    @Nullable
    private String mSkipOffset;
    @NonNull
    private final ArrayList<VastTracker> mSkipTrackers = new ArrayList<>();
    @NonNull
    private Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds = new HashMap();
    @Nullable
    private VastIconConfig mVastIconConfig;
    @Nullable
    private VideoViewabilityTracker mVideoViewabilityTracker;

    public void setDspCreativeId(@NonNull String str) {
        this.mDspCreativeId = str;
    }

    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    public void addImpressionTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "impressionTrackers cannot be null");
        this.mImpressionTrackers.addAll(list);
    }

    public void addFractionalTrackers(@NonNull List<VastFractionalProgressTracker> list) {
        Preconditions.checkNotNull(list, "fractionalTrackers cannot be null");
        this.mFractionalTrackers.addAll(list);
        Collections.sort(this.mFractionalTrackers);
    }

    public void addAbsoluteTrackers(@NonNull List<VastAbsoluteProgressTracker> list) {
        Preconditions.checkNotNull(list, "absoluteTrackers cannot be null");
        this.mAbsoluteTrackers.addAll(list);
        Collections.sort(this.mAbsoluteTrackers);
    }

    public void addCompleteTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "completeTrackers cannot be null");
        this.mCompleteTrackers.addAll(list);
    }

    public void addPauseTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "pauseTrackers cannot be null");
        this.mPauseTrackers.addAll(list);
    }

    public void addResumeTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "resumeTrackers cannot be null");
        this.mResumeTrackers.addAll(list);
    }

    public void addCloseTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "closeTrackers cannot be null");
        this.mCloseTrackers.addAll(list);
    }

    public void addSkipTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "skipTrackers cannot be null");
        this.mSkipTrackers.addAll(list);
    }

    public void addClickTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(list);
    }

    public void addErrorTrackers(@NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        this.mErrorTrackers.addAll(list);
    }

    public void addVideoTrackers(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            JSONArray optJSONArray2 = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if (optJSONArray != null && optJSONArray2 != null) {
                for (int i = 0; i < optJSONArray2.length(); i++) {
                    String optString = optJSONArray2.optString(i);
                    List hydrateUrls = hydrateUrls(optString, optJSONArray);
                    VideoTrackingEvent fromString = VideoTrackingEvent.fromString(optString);
                    if (!(optString == null || hydrateUrls == null)) {
                        switch (fromString) {
                            case START:
                                addStartTrackersForUrls(hydrateUrls);
                                break;
                            case FIRST_QUARTILE:
                                addFractionalTrackersForUrls(hydrateUrls, 0.25f);
                                break;
                            case MIDPOINT:
                                addFractionalTrackersForUrls(hydrateUrls, 0.5f);
                                break;
                            case THIRD_QUARTILE:
                                addFractionalTrackersForUrls(hydrateUrls, 0.75f);
                                break;
                            case COMPLETE:
                                addCompleteTrackersForUrls(hydrateUrls);
                                break;
                            case COMPANION_AD_VIEW:
                                addCompanionAdViewTrackersForUrls(hydrateUrls);
                                break;
                            case COMPANION_AD_CLICK:
                                addCompanionAdClickTrackersForUrls(hydrateUrls);
                                break;
                            default:
                                StringBuilder sb = new StringBuilder();
                                sb.append("Encountered unknown video tracking event: ");
                                sb.append(optString);
                                MoPubLog.d(sb.toString());
                                break;
                        }
                    }
                }
            }
        }
    }

    public void addExternalViewabilityTrackers(@Nullable Map<String, String> map) {
        if (map != null) {
            this.mExternalViewabilityTrackers.putAll(map);
        }
    }

    public void addAvidJavascriptResources(@Nullable Set<String> set) {
        if (set != null) {
            this.mAvidJavascriptResources.addAll(set);
        }
    }

    public void addMoatImpressionPixels(@Nullable Set<String> set) {
        if (set != null) {
            this.mMoatImpressionPixels.addAll(set);
        }
    }

    public void setClickThroughUrl(@Nullable String str) {
        this.mClickThroughUrl = str;
    }

    public void setNetworkMediaFileUrl(@Nullable String str) {
        this.mNetworkMediaFileUrl = str;
    }

    public void setDiskMediaFileUrl(@Nullable String str) {
        this.mDiskMediaFileUrl = str;
    }

    public void setVastCompanionAd(@Nullable VastCompanionAdConfig vastCompanionAdConfig, @Nullable VastCompanionAdConfig vastCompanionAdConfig2) {
        this.mLandscapeVastCompanionAdConfig = vastCompanionAdConfig;
        this.mPortraitVastCompanionAdConfig = vastCompanionAdConfig2;
    }

    public void setSocialActionsCompanionAds(@NonNull Map<String, VastCompanionAdConfig> map) {
        this.mSocialActionsCompanionAds = map;
    }

    public void setVastIconConfig(@Nullable VastIconConfig vastIconConfig) {
        this.mVastIconConfig = vastIconConfig;
    }

    public void setCustomCtaText(@Nullable String str) {
        if (str != null) {
            this.mCustomCtaText = str;
        }
    }

    public void setCustomSkipText(@Nullable String str) {
        if (str != null) {
            this.mCustomSkipText = str;
        }
    }

    public void setCustomCloseIconUrl(@Nullable String str) {
        if (str != null) {
            this.mCustomCloseIconUrl = str;
        }
    }

    public void setCustomForceOrientation(@Nullable ForceOrientation forceOrientation) {
        if (forceOrientation != null && forceOrientation != ForceOrientation.UNDEFINED) {
            this.mCustomForceOrientation = forceOrientation;
            this.mIsForceOrientationSet = true;
        }
    }

    public void setSkipOffset(@Nullable String str) {
        if (str != null) {
            this.mSkipOffset = str;
        }
    }

    public void setVideoViewabilityTracker(@Nullable VideoViewabilityTracker videoViewabilityTracker) {
        if (videoViewabilityTracker != null) {
            this.mVideoViewabilityTracker = videoViewabilityTracker;
        }
    }

    public void setIsRewardedVideo(boolean z) {
        this.mIsRewardedVideo = z;
    }

    public void setPrivacyInformationIconImageUrl(@Nullable String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public void setPrivacyInformationIconClickthroughUrl(@Nullable String str) {
        this.mPrivacyInformationIconClickthroughUrl = str;
    }

    @NonNull
    public List<VastTracker> getImpressionTrackers() {
        return this.mImpressionTrackers;
    }

    @NonNull
    public ArrayList<VastAbsoluteProgressTracker> getAbsoluteTrackers() {
        return this.mAbsoluteTrackers;
    }

    @NonNull
    public ArrayList<VastFractionalProgressTracker> getFractionalTrackers() {
        return this.mFractionalTrackers;
    }

    @NonNull
    public List<VastTracker> getPauseTrackers() {
        return this.mPauseTrackers;
    }

    @NonNull
    public List<VastTracker> getResumeTrackers() {
        return this.mResumeTrackers;
    }

    @NonNull
    public List<VastTracker> getCompleteTrackers() {
        return this.mCompleteTrackers;
    }

    @NonNull
    public List<VastTracker> getCloseTrackers() {
        return this.mCloseTrackers;
    }

    @NonNull
    public List<VastTracker> getSkipTrackers() {
        return this.mSkipTrackers;
    }

    @NonNull
    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    @NonNull
    public List<VastTracker> getErrorTrackers() {
        return this.mErrorTrackers;
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    @Nullable
    public String getNetworkMediaFileUrl() {
        return this.mNetworkMediaFileUrl;
    }

    @Nullable
    public String getDiskMediaFileUrl() {
        return this.mDiskMediaFileUrl;
    }

    @Nullable
    public VastCompanionAdConfig getVastCompanionAd(int i) {
        switch (i) {
            case 1:
                return this.mPortraitVastCompanionAdConfig;
            case 2:
                return this.mLandscapeVastCompanionAdConfig;
            default:
                return this.mLandscapeVastCompanionAdConfig;
        }
    }

    @NonNull
    public Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds() {
        return this.mSocialActionsCompanionAds;
    }

    @Nullable
    public VastIconConfig getVastIconConfig() {
        return this.mVastIconConfig;
    }

    @Nullable
    public String getCustomCtaText() {
        return this.mCustomCtaText;
    }

    @Nullable
    public String getCustomSkipText() {
        return this.mCustomSkipText;
    }

    @Nullable
    public String getCustomCloseIconUrl() {
        return this.mCustomCloseIconUrl;
    }

    @Nullable
    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.mVideoViewabilityTracker;
    }

    @NonNull
    public Map<String, String> getExternalViewabilityTrackers() {
        return this.mExternalViewabilityTrackers;
    }

    @NonNull
    public Set<String> getAvidJavascriptResources() {
        return this.mAvidJavascriptResources;
    }

    @NonNull
    public Set<String> getMoatImpressionPixels() {
        return this.mMoatImpressionPixels;
    }

    public boolean isCustomForceOrientationSet() {
        return this.mIsForceOrientationSet;
    }

    public boolean hasCompanionAd() {
        return (this.mLandscapeVastCompanionAdConfig == null || this.mPortraitVastCompanionAdConfig == null) ? false : true;
    }

    @NonNull
    public ForceOrientation getCustomForceOrientation() {
        return this.mCustomForceOrientation;
    }

    @Nullable
    public String getSkipOffsetString() {
        return this.mSkipOffset;
    }

    public boolean isRewardedVideo() {
        return this.mIsRewardedVideo;
    }

    @Nullable
    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    @Nullable
    public String getPrivacyInformationIconClickthroughUrl() {
        return this.mPrivacyInformationIconClickthroughUrl;
    }

    public void handleImpression(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mImpressionTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClickForResult(@NonNull Activity activity, int i, int i2) {
        handleClick(activity, i, Integer.valueOf(i2));
    }

    public void handleClickWithoutResult(@NonNull Context context, int i) {
        handleClick(context.getApplicationContext(), i, null);
    }

    private void handleClick(@NonNull final Context context, int i, @Nullable final Integer num) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mClickTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
        if (!TextUtils.isEmpty(this.mClickThroughUrl)) {
            new Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new ResultActions() {
                public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
                }

                public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, VastVideoConfig.this.mDspCreativeId);
                        Class<MoPubBrowser> cls = MoPubBrowser.class;
                        Intent startActivityIntent = Intents.getStartActivityIntent(context, cls, bundle);
                        try {
                            if (context instanceof Activity) {
                                Preconditions.checkNotNull(num);
                                ((Activity) context).startActivityForResult(startActivityIntent, num.intValue());
                                return;
                            }
                            Intents.startActivity(context, startActivityIntent);
                        } catch (ActivityNotFoundException unused) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Activity ");
                            sb.append(cls.getName());
                            sb.append(" not found. Did you declare it in your AndroidManifest.xml?");
                            MoPubLog.d(sb.toString());
                        } catch (IntentNotResolvableException unused2) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Activity ");
                            sb2.append(cls.getName());
                            sb2.append(" not found. Did you declare it in your AndroidManifest.xml?");
                            MoPubLog.d(sb2.toString());
                        }
                    }
                }
            }).withoutMoPubBrowser().build().handleUrl(context, this.mClickThroughUrl);
        }
    }

    public void handleResume(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mResumeTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handlePause(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mPauseTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClose(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCloseTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
        TrackingRequest.makeVastTrackingHttpRequest(this.mSkipTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleComplete(@NonNull Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCompleteTrackers, null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleError(@NonNull Context context, @Nullable VastErrorCode vastErrorCode, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mErrorTrackers, vastErrorCode, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    @NonNull
    public List<VastTracker> getUntriggeredTrackersBefore(int i, int i2) {
        if (!NoThrow.checkArgument(i2 > 0) || i < 0) {
            return Collections.emptyList();
        }
        float f = ((float) i) / ((float) i2);
        ArrayList arrayList = new ArrayList();
        VastAbsoluteProgressTracker vastAbsoluteProgressTracker = new VastAbsoluteProgressTracker("", i);
        int size = this.mAbsoluteTrackers.size();
        for (int i3 = 0; i3 < size; i3++) {
            VastAbsoluteProgressTracker vastAbsoluteProgressTracker2 = (VastAbsoluteProgressTracker) this.mAbsoluteTrackers.get(i3);
            if (vastAbsoluteProgressTracker2.compareTo(vastAbsoluteProgressTracker) > 0) {
                break;
            }
            if (!vastAbsoluteProgressTracker2.isTracked()) {
                arrayList.add(vastAbsoluteProgressTracker2);
            }
        }
        VastFractionalProgressTracker vastFractionalProgressTracker = new VastFractionalProgressTracker("", f);
        int size2 = this.mFractionalTrackers.size();
        for (int i4 = 0; i4 < size2; i4++) {
            VastFractionalProgressTracker vastFractionalProgressTracker2 = (VastFractionalProgressTracker) this.mFractionalTrackers.get(i4);
            if (vastFractionalProgressTracker2.compareTo(vastFractionalProgressTracker) > 0) {
                break;
            }
            if (!vastFractionalProgressTracker2.isTracked()) {
                arrayList.add(vastFractionalProgressTracker2);
            }
        }
        return arrayList;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(Integer.MAX_VALUE, Integer.MAX_VALUE).size();
    }

    @Nullable
    public Integer getSkipOffsetMillis(int i) {
        Integer num;
        String str = this.mSkipOffset;
        if (str != null) {
            try {
                if (Strings.isAbsoluteTracker(str)) {
                    num = Strings.parseAbsoluteOffset(this.mSkipOffset);
                } else if (Strings.isPercentageTracker(this.mSkipOffset)) {
                    num = Integer.valueOf(Math.round(((float) i) * (Float.parseFloat(this.mSkipOffset.replace("%", "")) / 100.0f)));
                } else {
                    MoPubLog.d(String.format("Invalid VAST skipoffset format: %s", new Object[]{this.mSkipOffset}));
                    return null;
                }
                if (num != null) {
                    if (num.intValue() < i) {
                        return num;
                    }
                    return Integer.valueOf(i);
                }
            } catch (NumberFormatException unused) {
                MoPubLog.d(String.format("Failed to parse skipoffset %s", new Object[]{this.mSkipOffset}));
            }
        }
        return null;
    }

    @Nullable
    private List<String> hydrateUrls(@Nullable String str, @NonNull JSONArray jSONArray) {
        Preconditions.checkNotNull(jSONArray);
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                arrayList.add(optString.replace(Constants.VIDEO_TRACKING_URL_MACRO, str));
            }
        }
        return arrayList;
    }

    private List<VastTracker> createVastTrackersForUrls(@NonNull List<String> list) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : list) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    private void addCompleteTrackersForUrls(@NonNull List<String> list) {
        Preconditions.checkNotNull(list);
        addCompleteTrackers(createVastTrackersForUrls(list));
    }

    private void addStartTrackersForUrls(@NonNull List<String> list) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastAbsoluteProgressTracker : list) {
            arrayList.add(new VastAbsoluteProgressTracker(vastAbsoluteProgressTracker, 0));
        }
        addAbsoluteTrackers(arrayList);
    }

    private void addFractionalTrackersForUrls(@NonNull List<String> list, float f) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastFractionalProgressTracker : list) {
            arrayList.add(new VastFractionalProgressTracker(vastFractionalProgressTracker, f));
        }
        addFractionalTrackers(arrayList);
    }

    private void addCompanionAdViewTrackersForUrls(@NonNull List<String> list) {
        Preconditions.checkNotNull(list);
        if (hasCompanionAd()) {
            List createVastTrackersForUrls = createVastTrackersForUrls(list);
            this.mLandscapeVastCompanionAdConfig.addCreativeViewTrackers(createVastTrackersForUrls);
            this.mPortraitVastCompanionAdConfig.addCreativeViewTrackers(createVastTrackersForUrls);
        }
    }

    private void addCompanionAdClickTrackersForUrls(@NonNull List<String> list) {
        Preconditions.checkNotNull(list);
        if (hasCompanionAd()) {
            List createVastTrackersForUrls = createVastTrackersForUrls(list);
            this.mLandscapeVastCompanionAdConfig.addClickTrackers(createVastTrackersForUrls);
            this.mPortraitVastCompanionAdConfig.addClickTrackers(createVastTrackersForUrls);
        }
    }
}
