package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import org.json.JSONObject;

@zzark
public final class zzaxg {
    public final int errorCode;
    @Nullable
    public final zzwf zzbst;
    public final zzasi zzeag;
    @Nullable
    public final JSONObject zzehh;
    public final zzakr zzehj;
    public final long zzehn;
    public final long zzeho;
    public final zzum zzehw;
    public final boolean zzehx;
    public final zzasm zzehy;

    public zzaxg(zzasi zzasi, zzasm zzasm, zzakr zzakr, zzwf zzwf, int i, long j, long j2, JSONObject jSONObject, zzur zzur) {
        this.zzeag = zzasi;
        this.zzehy = zzasm;
        this.zzehj = null;
        this.zzbst = null;
        this.errorCode = i;
        this.zzehn = j;
        this.zzeho = j2;
        this.zzehh = null;
        this.zzehw = new zzum(zzur);
        this.zzehx = false;
    }

    public zzaxg(zzasi zzasi, zzasm zzasm, zzakr zzakr, zzwf zzwf, int i, long j, long j2, JSONObject jSONObject, zzum zzum, @Nullable Boolean bool) {
        this.zzeag = zzasi;
        this.zzehy = zzasm;
        this.zzehj = zzakr;
        this.zzbst = zzwf;
        this.errorCode = i;
        this.zzehn = j;
        this.zzeho = j2;
        this.zzehh = jSONObject;
        this.zzehw = zzum;
        if (bool != null) {
            this.zzehx = bool.booleanValue();
        } else {
            this.zzehx = zzbal.zzf(zzasi.zzdwg.zzcjl);
        }
    }
}
