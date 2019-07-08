package com.facebook.ads.internal.protocol;

import com.brightcove.player.network.DownloadStatus;
import com.facebook.ads.AdError;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthExerciseType.SamsungExercise;

public enum AdErrorType {
    UNKNOWN_ERROR(-1, "unknown error", false),
    NETWORK_ERROR(1000, "Network Error", true),
    NO_FILL(1001, "No Fill", true),
    LOAD_TOO_FREQUENTLY(1002, "Ad was re-loaded too frequently", true),
    DISABLED_APP(DownloadStatus.ERROR_TOO_MANY_REDIRECTS, "App is disabled from making ad requests", true),
    SERVER_ERROR(2000, "Server Error", true),
    INTERNAL_ERROR(2001, "Internal Error", true),
    CACHE_FAILURE_ERROR(2002, "Pre Caching failure", true),
    START_BEFORE_INIT(2005, "initAd must be called before startAd", true),
    REMOTE_ADS_SERVICE_ERROR(AdError.REMOTE_ADS_SERVICE_ERROR, "Ads Service process error", true),
    INTERSTITIAL_AD_TIMEOUT(AdError.INTERSTITIAL_AD_TIMEOUT, "Timeout loading Interstitial Ad", true),
    BROKEN_MEDIA_ERROR(AdError.BROKEN_MEDIA_ERROR_CODE, "Failed to load Media for Native Ad", true),
    AD_REQUEST_FAILED(1111, "Facebook Ads SDK request for ads failed", false),
    AD_REQUEST_TIMEOUT(1112, "Facebook Ads SDK request for ads timed out", false),
    PARSER_FAILURE(1201, "Failed to parse Facebook Ads SDK delivery response", false),
    UNKNOWN_RESPONSE(1202, "Unknown Facebook Ads SDK delivery response type", false),
    ERROR_MESSAGE(1203, "Facebook Ads SDK delivery response Error message", true),
    NO_AD_PLACEMENT(1302, "Facebook Ads SDK returned no ad placements", false),
    MEDIATION_ERROR(3001, "Mediation Error", true),
    BID_IMPRESSION_MISMATCH(SamsungExercise.HOCKEY, "Bid payload does not match placement", true),
    BID_PAYLOAD_ERROR(SamsungExercise.RUGBY, "Invalid bid payload", false),
    NO_ADAPTER_ON_LOAD(5001, "Adapter is null onLoad Ad", false),
    NO_ADAPTER_ON_START(5002, "Adapter is null onStart Ad", false),
    INTERSTITIAL_CONTROLLER_IS_NULL(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, "Interstitial Controller is null show Ad", false),
    WEB_VIEW_FAILED_TO_LOAD(FitnessStatusCodes.APP_MISMATCH, "WebView failed to load", false),
    NO_MEDIAVIEW_IN_NATIVEAD(6001, "MediaView is missing in NativeAd", true),
    NO_ICONVIEW_IN_NATIVEBANNERAD(6002, "IconView is missing in NativeBannerAd", true),
    UNSUPPORTED_AD_ASSET_NATIVEAD(6003, "unsupported type of ad assets", true),
    AD_ALREADY_STARTED(7001, "Ad already started", true),
    LOAD_CALLED_WHILE_SHOWING_AD(7002, "Ad cannot be loaded while being displayed", true),
    CLEAR_TEXT_SUPPORT_NOT_ALLOWED(7003, "In order to use cache in Facebook Audience Network SDK you should whitelist 127.0.0.1 in your Network Security Configuration:\n<domain-config cleartextTrafficPermitted=\"true\">\n    <domain includeSubdomains=\"true\">127.0.0.1</domain>\n</domain-config>\nSee more: https://developers.facebook.com/docs/audience-network/android-network-security-config", true),
    INCORRECT_STATE_ERROR(AdError.INCORRECT_STATE_ERROR, "You can't call %s for ad in state %s", true),
    MISSING_DEPENDENCIES_ERROR(AdError.MISSING_DEPENDENCIES_ERROR, "Facebook Audience Network SDK doesn't have all required classes. Please, check LogCat output for tag %s. See more: https://developers.facebook.com/docs/audience-network/android/ ", true),
    IMAGE_CACHE_ERROR(SamsungExercise.BALLET, "Failed to cache image", false);
    
    private final int a;
    private final String b;
    private final boolean c;

    private AdErrorType(int i, String str, boolean z) {
        this.a = i;
        this.b = str;
        this.c = z;
    }

    public static AdErrorType adErrorTypeFromCode(int i) {
        return adErrorTypeFromCode(i, UNKNOWN_ERROR);
    }

    public static AdErrorType adErrorTypeFromCode(int i, AdErrorType adErrorType) {
        AdErrorType[] values;
        for (AdErrorType adErrorType2 : values()) {
            if (adErrorType2.getErrorCode() == i) {
                return adErrorType2;
            }
        }
        return adErrorType;
    }

    public String getDefaultErrorMessage() {
        return this.b;
    }

    public int getErrorCode() {
        return this.a;
    }

    public boolean isPublicError() {
        return this.c;
    }
}
