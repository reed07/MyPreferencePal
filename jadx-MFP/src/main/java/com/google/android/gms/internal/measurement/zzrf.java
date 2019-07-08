package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

public final class zzrf {
    private String zzbay;
    @VisibleForTesting
    private Map<String, Object> zzbnw;
    private final Map<String, Object> zzbnx;
    private final zzrp zzbqg;
    private final Context zzri;
    private final Clock zzrz;

    public zzrf(Context context) {
        this(context, new HashMap(), new zzrp(context), DefaultClock.getInstance());
    }

    @VisibleForTesting
    private zzrf(Context context, Map<String, Object> map, zzrp zzrp, Clock clock) {
        this.zzbay = null;
        this.zzbnw = new HashMap();
        this.zzri = context;
        this.zzrz = clock;
        this.zzbqg = zzrp;
        this.zzbnx = map;
    }

    public final void zzfh(String str) {
        this.zzbay = str;
    }
}
