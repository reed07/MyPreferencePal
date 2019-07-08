package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsm extends zzsi<Double> {
    zzsm(zzso zzso, String str, Double d) {
        super(zzso, str, d, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzv */
    public final Double zzs(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zztr = super.zztr();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zztr).length() + 27 + String.valueOf(valueOf).length());
        sb.append("Invalid double value for ");
        sb.append(zztr);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
