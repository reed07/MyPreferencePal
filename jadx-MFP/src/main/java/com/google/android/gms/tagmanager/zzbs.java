package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzbs extends zzdy {
    private static final String ID = zza.GREATER_EQUALS.toString();

    public zzbs() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzgi zzgi, zzgi zzgi2, Map<String, zzp> map) {
        return zzgi.compareTo(zzgi2) >= 0;
    }
}