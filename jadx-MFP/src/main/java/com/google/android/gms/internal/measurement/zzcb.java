package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

@VisibleForTesting
public final class zzcb extends zzau {
    @VisibleForTesting
    zzcb(zzaw zzaw) {
        super(zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
    }

    public final zzac zzek() {
        zzcl();
        return zzca().zzae();
    }

    public final String zzel() {
        zzcl();
        zzac zzek = zzek();
        int i = zzek.zzuh;
        int i2 = zzek.zzui;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append(AvidJSONUtil.KEY_X);
        sb.append(i2);
        return sb.toString();
    }
}
