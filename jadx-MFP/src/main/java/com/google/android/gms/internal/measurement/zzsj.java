package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsj extends zzsi<Long> {
    zzsj(zzso zzso, String str, Long l) {
        super(zzso, str, l, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzt */
    public final Long zzs(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zztr = super.zztr();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zztr).length() + 25 + String.valueOf(valueOf).length());
        sb.append("Invalid long value for ");
        sb.append(zztr);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
