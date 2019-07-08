package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public final class zzrn {
    private final List<zzri> zzbpb;
    private final List<zzri> zzbpc;
    private final List<zzri> zzbpd;
    private final List<zzri> zzbpe;
    private final List<zzri> zzbqh;
    private final List<zzri> zzbqi;
    private final List<String> zzbqj;
    private final List<String> zzbqk;
    private final List<String> zzbql;
    private final List<String> zzbqm;

    private zzrn() {
        this.zzbpb = new ArrayList();
        this.zzbpc = new ArrayList();
        this.zzbpd = new ArrayList();
        this.zzbpe = new ArrayList();
        this.zzbqh = new ArrayList();
        this.zzbqi = new ArrayList();
        this.zzbqj = new ArrayList();
        this.zzbqk = new ArrayList();
        this.zzbql = new ArrayList();
        this.zzbqm = new ArrayList();
    }

    public final zzrn zzd(zzri zzri) {
        this.zzbpb.add(zzri);
        return this;
    }

    public final zzrn zze(zzri zzri) {
        this.zzbpc.add(zzri);
        return this;
    }

    public final zzrn zzf(zzri zzri) {
        this.zzbpd.add(zzri);
        return this;
    }

    public final zzrn zzfj(String str) {
        this.zzbql.add(str);
        return this;
    }

    public final zzrn zzg(zzri zzri) {
        this.zzbpe.add(zzri);
        return this;
    }

    public final zzrn zzfk(String str) {
        this.zzbqm.add(str);
        return this;
    }

    public final zzrn zzh(zzri zzri) {
        this.zzbqh.add(zzri);
        return this;
    }

    public final zzrn zzfl(String str) {
        this.zzbqj.add(str);
        return this;
    }

    public final zzrn zzi(zzri zzri) {
        this.zzbqi.add(zzri);
        return this;
    }

    public final zzrn zzfm(String str) {
        this.zzbqk.add(str);
        return this;
    }

    public final zzrm zztg() {
        zzrm zzrm = new zzrm(this.zzbpb, this.zzbpc, this.zzbpd, this.zzbpe, this.zzbqh, this.zzbqi, this.zzbqj, this.zzbqk, this.zzbql, this.zzbqm);
        return zzrm;
    }
}
