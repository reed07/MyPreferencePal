package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;

public final class zzrl implements zzsq {
    @Nullable
    private final View mView;
    @Nullable
    private final zzaxf zzbvo;

    public zzrl(View view, zzaxf zzaxf) {
        this.mView = view;
        this.zzbvo = zzaxf;
    }

    public final zzsq zzne() {
        return this;
    }

    public final View zznc() {
        return this.mView;
    }

    public final boolean zznd() {
        return this.zzbvo == null || this.mView == null;
    }
}
