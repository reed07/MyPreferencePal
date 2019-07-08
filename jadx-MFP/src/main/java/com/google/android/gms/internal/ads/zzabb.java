package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzaf;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabb extends zzabe {
    private final zzaf zzczo;
    @Nullable
    private final String zzczp;
    private final String zzczq;

    public zzabb(zzaf zzaf, @Nullable String str, String str2) {
        this.zzczo = zzaf;
        this.zzczp = str;
        this.zzczq = str2;
    }

    public final String zzrk() {
        return this.zzczp;
    }

    public final String getContent() {
        return this.zzczq;
    }

    public final void zzh(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.zzczo.zzh((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public final void recordClick() {
        this.zzczo.zzjh();
    }

    public final void recordImpression() {
        this.zzczo.zzji();
    }
}
