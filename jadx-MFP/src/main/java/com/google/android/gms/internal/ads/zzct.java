package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class zzct extends zzcs {
    public static zzct zza(String str, Context context, boolean z) {
        zza(context, z);
        return new zzct(context, str, z);
    }

    private zzct(Context context, String str, boolean z) {
        super(context, str, z);
    }

    /* access modifiers changed from: protected */
    public final List<Callable<Void>> zza(zzdl zzdl, Context context, zzbl zzbl, zzbi zzbi) {
        if (zzdl.zzac() == null || !this.zzrp) {
            return super.zza(zzdl, context, zzbl, zzbi);
        }
        int zzy = zzdl.zzy();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zza(zzdl, context, zzbl, zzbi));
        zzee zzee = new zzee(zzdl, "DPtJycwRqj/e0TdTHULzeUhZhWJ1IU3iwhH90sUbn4eYKEdB5HI7UC0PtJgg2RSN", "Ye7G7hL63+8nOBoyCAHdjfK62rvCOKz3+aC1KA/K9CI=", zzbl, zzy, 24);
        arrayList.add(zzee);
        return arrayList;
    }
}
