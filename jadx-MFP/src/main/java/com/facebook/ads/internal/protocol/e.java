package com.facebook.ads.internal.protocol;

import com.google.logging.type.LogSeverity;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;

public enum e {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(101),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(103),
    NATIVE_UNKNOWN(200),
    NATIVE_BANNER(500),
    NATIVE_250(RequestCodes.EDIT_RECIPE_INGREDIENT),
    REWARDED_VIDEO(LogSeverity.WARNING_VALUE),
    INSTREAM_VIDEO(LogSeverity.NOTICE_VALUE);
    
    private final int o;

    private e(int i) {
        this.o = i;
    }

    public int a() {
        return this.o;
    }
}
