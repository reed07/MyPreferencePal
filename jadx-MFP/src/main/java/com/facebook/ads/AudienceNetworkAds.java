package com.facebook.ads;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.w.b.l;

public final class AudienceNetworkAds {
    public static final String TAG = "FBAudienceNetwork";

    private AudienceNetworkAds() {
    }

    public static void initialize(Context context) {
        l.a(context, "Context can not be null.");
        a.a(context);
    }

    public static boolean isInAdsProcess(Context context) {
        l.a(context, "Context can not be null.");
        com.facebook.ads.internal.w.f.a.a = true;
        String a = com.facebook.ads.internal.w.f.a.a(context);
        return !TextUtils.isEmpty(a) && a.endsWith(":adnw");
    }
}
