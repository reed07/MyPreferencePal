package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.util.ResponseHeader;
import com.mopub.network.HeaderUtils;
import org.json.JSONObject;

public class AdTypeTranslator {
    public static final String BANNER_SUFFIX = "_banner";
    public static final String INTERSTITIAL_SUFFIX = "_interstitial";

    public enum CustomEventType {
        GOOGLE_PLAY_SERVICES_BANNER("admob_native_banner", "com.mopub.mobileads.GooglePlayServicesBanner", false),
        GOOGLE_PLAY_SERVICES_INTERSTITIAL("admob_full_interstitial", "com.mopub.mobileads.GooglePlayServicesInterstitial", false),
        MILLENNIAL_BANNER("millennial_native_banner", "com.mopub.mobileads.MillennialBanner", false),
        MILLENNIAL_INTERSTITIAL("millennial_full_interstitial", "com.mopub.mobileads.MillennialInterstitial", false),
        MRAID_BANNER("mraid_banner", "com.mopub.mraid.MraidBanner", true),
        MRAID_INTERSTITIAL("mraid_interstitial", "com.mopub.mraid.MraidInterstitial", true),
        HTML_BANNER("html_banner", "com.mopub.mobileads.HtmlBanner", true),
        HTML_INTERSTITIAL("html_interstitial", "com.mopub.mobileads.HtmlInterstitial", true),
        VAST_VIDEO_INTERSTITIAL("vast_interstitial", "com.mopub.mobileads.VastVideoInterstitial", true),
        MOPUB_NATIVE("mopub_native", "com.mopub.nativeads.MoPubCustomEventNative", true),
        MOPUB_VIDEO_NATIVE("mopub_video_native", "com.mopub.nativeads.MoPubCustomEventVideoNative", true),
        MOPUB_REWARDED_VIDEO(AdType.REWARDED_VIDEO, "com.mopub.mobileads.MoPubRewardedVideo", true),
        MOPUB_REWARDED_PLAYABLE(AdType.REWARDED_PLAYABLE, "com.mopub.mobileads.MoPubRewardedPlayable", true),
        UNSPECIFIED("", null, false);
        
        @Nullable
        private final String mClassName;
        private final boolean mIsMoPubSpecific;
        @NonNull
        private final String mKey;

        private CustomEventType(String str, String str2, boolean z) {
            this.mKey = str;
            this.mClassName = str2;
            this.mIsMoPubSpecific = z;
        }

        /* access modifiers changed from: private */
        public static CustomEventType fromString(@Nullable String str) {
            CustomEventType[] values;
            for (CustomEventType customEventType : values()) {
                if (customEventType.mKey.equals(str)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        private static CustomEventType fromClassName(@Nullable String str) {
            CustomEventType[] values;
            for (CustomEventType customEventType : values()) {
                String str2 = customEventType.mClassName;
                if (str2 != null && str2.equals(str)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        public String toString() {
            return this.mClassName;
        }

        public static boolean isMoPubSpecific(@Nullable String str) {
            return fromClassName(str).mIsMoPubSpecific;
        }
    }

    public static String getCustomEventName(@NonNull AdFormat adFormat, @NonNull String str, @Nullable String str2, @Nullable JSONObject jSONObject) {
        CustomEventType customEventType;
        if ("custom".equalsIgnoreCase(str)) {
            return HeaderUtils.extractHeader(jSONObject, ResponseHeader.CUSTOM_EVENT_NAME);
        }
        if (AdType.STATIC_NATIVE.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_NATIVE.toString();
        }
        if (AdType.VIDEO_NATIVE.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_VIDEO_NATIVE.toString();
        }
        if (AdType.REWARDED_VIDEO.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_REWARDED_VIDEO.toString();
        }
        if (AdType.REWARDED_PLAYABLE.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_REWARDED_PLAYABLE.toString();
        }
        if (AdType.HTML.equalsIgnoreCase(str) || AdType.MRAID.equalsIgnoreCase(str)) {
            if (AdFormat.INTERSTITIAL.equals(adFormat)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(INTERSTITIAL_SUFFIX);
                customEventType = CustomEventType.fromString(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(BANNER_SUFFIX);
                customEventType = CustomEventType.fromString(sb2.toString());
            }
            return customEventType.toString();
        } else if ("interstitial".equalsIgnoreCase(str)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(INTERSTITIAL_SUFFIX);
            return CustomEventType.fromString(sb3.toString()).toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(BANNER_SUFFIX);
            return CustomEventType.fromString(sb4.toString()).toString();
        }
    }
}
