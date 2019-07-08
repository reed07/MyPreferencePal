package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzaas {
    public static void zza(zzaaq zzaaq, @Nullable zzaap zzaap) {
        if (zzaap.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(zzaap.zzmr())) {
            zzaaq.zza(zzaap.getContext(), zzaap.zzmr(), zzaap.zzra(), zzaap.zzrb());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
