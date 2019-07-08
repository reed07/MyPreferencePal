package com.facebook.ads.internal.protocol;

import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import java.util.Locale;

public enum AdPlacementType {
    BANNER("banner"),
    INSTREAM("instream"),
    INTERSTITIAL("interstitial"),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
    NATIVE_BANNER("native_banner"),
    REWARDED_VIDEO(AdType.REWARDED_VIDEO),
    UNKNOWN("unknown");
    
    private String a;

    private AdPlacementType(String str) {
        this.a = str;
    }

    public static AdPlacementType fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (Exception unused) {
            return UNKNOWN;
        }
    }

    public String toString() {
        return this.a;
    }
}
