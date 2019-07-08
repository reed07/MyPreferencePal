package com.facebook.ads.internal.w.b;

import android.os.Build.VERSION;
import android.view.View;

public enum j {
    INTERNAL_NO_TAG(0),
    INTERNAL_NO_CLICK(1),
    INTERNAL_API_TOO_LOW(2),
    INTERNAL_WRONG_TAG_CLASS(3),
    INTERNAL_NULL_VIEW(4),
    INTERNAL_AD_ICON(5),
    INTERNAL_AD_TITLE(6),
    INTERNAL_AD_COVER_IMAGE(7),
    INTERNAL_AD_SUBTITLE(8),
    INTERNAL_AD_BODY(9),
    INTERNAL_AD_CALL_TO_ACTION(10),
    INTERNAL_AD_SOCIAL_CONTEXT(11),
    INTERNAL_AD_CHOICES_ICON(12),
    INTERNAL_AD_MEDIA(13),
    INTERNAL_AD_OPTIONS_VIEW(12);
    
    public static int p;
    private final int q;

    static {
        p = -1593835521;
    }

    private j(int i) {
        this.q = i;
    }

    public static void a(View view, j jVar) {
        if (view != null && jVar != null && VERSION.SDK_INT > 4) {
            view.setTag(p, jVar);
        }
    }

    public int a() {
        return this.q;
    }
}
