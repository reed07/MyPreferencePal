package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final /* synthetic */ class zzbay implements zzbbc {
    private final String zzbpk;
    private final Map zzdim;
    private final String zzeoq;
    private final byte[] zzeor;

    zzbay(String str, String str2, Map map, byte[] bArr) {
        this.zzeoq = str;
        this.zzbpk = str2;
        this.zzdim = map;
        this.zzeor = bArr;
    }

    public final void zza(JsonWriter jsonWriter) {
        zzbax.zza(this.zzeoq, this.zzbpk, this.zzdim, this.zzeor, jsonWriter);
    }
}
