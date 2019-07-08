package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzbl extends zzfz {
    private static final String ID = zza.ENDS_WITH.toString();

    public zzbl() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, String str2, Map<String, zzp> map) {
        return str.endsWith(str2);
    }
}
