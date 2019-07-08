package com.google.android.gms.internal.icing;

import android.net.Uri;

public final class zzgw implements zzgv {
    private static final zzbl<Boolean> zzqs;
    private static final zzbl<Boolean> zzqt;
    private static final zzbl<Boolean> zzqu;

    public final boolean zzef() {
        return ((Boolean) zzqs.get()).booleanValue();
    }

    static {
        String str = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.icing"));
        zzbo zzbo = new zzbo(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
        zzqs = zzbo.zza("enable_client_grant_slice_permission", true);
        zzqt = zzbo.zza("gms_icing_corpus_schema_store_as_ground_truth", false);
        zzqu = zzbo.zza("enable_safe_index_cleanup", false);
    }
}
