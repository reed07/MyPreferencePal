package com.mopub.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public enum CreativeOrientation {
    PORTRAIT,
    LANDSCAPE,
    UNDEFINED;

    @NonNull
    public static CreativeOrientation fromHeader(@Nullable String str) {
        if ("l".equalsIgnoreCase(str)) {
            return LANDSCAPE;
        }
        if ("p".equalsIgnoreCase(str)) {
            return PORTRAIT;
        }
        return UNDEFINED;
    }
}
