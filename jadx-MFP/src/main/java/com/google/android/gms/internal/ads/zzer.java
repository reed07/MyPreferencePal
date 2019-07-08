package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzer extends zzeu {
    private final View zzuh;

    public zzer(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2, View view) {
        super(zzdl, str, str2, zzbl, i, 57);
        this.zzuh = view;
    }

    /* access modifiers changed from: protected */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        if (this.zzuh != null) {
            DisplayMetrics displayMetrics = this.zzqo.getContext().getResources().getDisplayMetrics();
            zzdt zzdt = new zzdt((String) this.zzuw.invoke(null, new Object[]{this.zzuh, displayMetrics}));
            zzbn zzbn = new zzbn();
            zzbn.zzhe = zzdt.zztt;
            zzbn.zzhf = zzdt.zzhf;
            zzbn.zzhg = zzdt.zzhg;
            this.zzun.zzfp = zzbn;
        }
    }
}
