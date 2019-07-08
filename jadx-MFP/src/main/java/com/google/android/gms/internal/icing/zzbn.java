package com.google.android.gms.internal.icing;

import android.util.Log;

final class zzbn extends zzbl<Boolean> {
    zzbn(zzbo zzbo, String str, Boolean bool) {
        super(zzbo, str, bool, null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzay.zzcg.matcher(str).matches()) {
                return Boolean.valueOf(true);
            }
            if (zzay.zzch.matcher(str).matches()) {
                return Boolean.valueOf(false);
            }
        }
        String zzy = super.zzy();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzy).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zzy);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
