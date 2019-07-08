package com.facebook.ads.internal.protocol;

public enum c {
    UNKNOWN,
    BANNER,
    INTERSTITIAL,
    NATIVE,
    REWARDED_VIDEO;

    public static c a(e eVar) {
        switch (eVar) {
            case NATIVE_UNKNOWN:
                return NATIVE;
            case WEBVIEW_BANNER_50:
            case WEBVIEW_BANNER_90:
            case WEBVIEW_BANNER_LEGACY:
            case WEBVIEW_BANNER_250:
                return BANNER;
            case WEBVIEW_INTERSTITIAL_HORIZONTAL:
            case WEBVIEW_INTERSTITIAL_VERTICAL:
            case WEBVIEW_INTERSTITIAL_TABLET:
            case WEBVIEW_INTERSTITIAL_UNKNOWN:
                return INTERSTITIAL;
            case REWARDED_VIDEO:
                return REWARDED_VIDEO;
            default:
                return UNKNOWN;
        }
    }

    public AdPlacementType a() {
        switch (this) {
            case INTERSTITIAL:
                return AdPlacementType.INTERSTITIAL;
            case BANNER:
                return AdPlacementType.BANNER;
            case NATIVE:
                return AdPlacementType.NATIVE;
            case REWARDED_VIDEO:
                return AdPlacementType.REWARDED_VIDEO;
            default:
                return AdPlacementType.UNKNOWN;
        }
    }
}
