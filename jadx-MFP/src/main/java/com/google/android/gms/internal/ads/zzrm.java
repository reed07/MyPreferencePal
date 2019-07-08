package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzrm implements zzsq {
    private final WeakReference<View> zzbvp;
    private final WeakReference<zzaxf> zzbvq;

    public zzrm(View view, zzaxf zzaxf) {
        this.zzbvp = new WeakReference<>(view);
        this.zzbvq = new WeakReference<>(zzaxf);
    }

    public final View zznc() {
        return (View) this.zzbvp.get();
    }

    public final boolean zznd() {
        return this.zzbvp.get() == null || this.zzbvq.get() == null;
    }

    public final zzsq zzne() {
        return new zzrl((View) this.zzbvp.get(), (zzaxf) this.zzbvq.get());
    }
}
