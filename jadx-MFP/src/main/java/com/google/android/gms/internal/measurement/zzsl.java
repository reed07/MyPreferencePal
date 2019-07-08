package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsl extends zzsi<Boolean> {
    zzsl(zzso zzso, String str, Boolean bool) {
        super(zzso, str, bool, null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzs(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzru.zzbqq.matcher(str).matches()) {
                return Boolean.valueOf(true);
            }
            if (zzru.zzbqr.matcher(str).matches()) {
                return Boolean.valueOf(false);
            }
        }
        String zztr = super.zztr();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zztr).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zztr);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
