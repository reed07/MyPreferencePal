package com.google.android.gms.internal.ads;

import java.io.InputStream;

final class zzuf extends zzbcl<InputStream> {
    private final /* synthetic */ zzue zzcad;

    zzuf(zzue zzue) {
        this.zzcad = zzue;
    }

    public final boolean cancel(boolean z) {
        this.zzcad.disconnect();
        return super.cancel(z);
    }
}
