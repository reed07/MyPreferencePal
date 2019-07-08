package com.facebook.ads.internal.a;

import android.support.annotation.Nullable;

public enum a {
    CANNOT_OPEN,
    CANNOT_TRACK;

    public static boolean a(@Nullable a aVar) {
        return CANNOT_OPEN.equals(aVar) || CANNOT_TRACK.equals(aVar);
    }
}
