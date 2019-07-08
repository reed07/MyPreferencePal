package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class zzri implements zzsq {
    private WeakReference<zzacd> zzbvm;

    public zzri(zzacd zzacd) {
        this.zzbvm = new WeakReference<>(zzacd);
    }

    @Nullable
    public final View zznc() {
        zzacd zzacd = (zzacd) this.zzbvm.get();
        if (zzacd != null) {
            return zzacd.zzss();
        }
        return null;
    }

    public final boolean zznd() {
        return this.zzbvm.get() == null;
    }

    public final zzsq zzne() {
        return new zzrk((zzacd) this.zzbvm.get());
    }
}
