package com.google.android.gms.internal.icing;

import android.net.Uri;

public final class zzbo {
    private final String zzdm;
    /* access modifiers changed from: private */
    public final Uri zzdn;
    /* access modifiers changed from: private */
    public final String zzdo;
    /* access modifiers changed from: private */
    public final String zzdp;
    private final boolean zzdq;
    private final boolean zzdr;
    private final boolean zzds;

    public zzbo(Uri uri) {
        this(null, uri, "", "", false, false, false);
    }

    private zzbo(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3) {
        this.zzdm = null;
        this.zzdn = uri;
        this.zzdo = str2;
        this.zzdp = str3;
        this.zzdq = false;
        this.zzdr = false;
        this.zzds = false;
    }

    public final zzbl<Boolean> zza(String str, boolean z) {
        return zzbl.zza(this, str, z);
    }
}
