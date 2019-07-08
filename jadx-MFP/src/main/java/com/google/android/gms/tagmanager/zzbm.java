package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

@ShowFirstParty
@VisibleForTesting
public final class zzbm extends zzfz {
    private static final String ID = zza.EQUALS.toString();

    public zzbm() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, String str2, Map<String, zzp> map) {
        return str.equals(str2);
    }
}
