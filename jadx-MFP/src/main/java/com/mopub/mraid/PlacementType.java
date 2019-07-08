package com.mopub.mraid;

import java.util.Locale;

public enum PlacementType {
    INLINE,
    INTERSTITIAL;

    /* access modifiers changed from: 0000 */
    public String toJavascriptString() {
        return toString().toLowerCase(Locale.US);
    }
}
