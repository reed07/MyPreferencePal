package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;

final class zzazv implements zzbbm<zzp, T> {
    private final /* synthetic */ zzazy zzemz;

    zzazv(zzazs zzazs, zzazy zzazy) {
        this.zzemz = zzazy;
    }

    public final /* synthetic */ Object apply(Object obj) {
        return this.zzemz.zze(new ByteArrayInputStream(((zzp) obj).data));
    }
}
