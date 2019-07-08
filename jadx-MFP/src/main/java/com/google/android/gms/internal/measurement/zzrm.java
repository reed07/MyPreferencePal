package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzrm {
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

    private zzrm(List<zzri> list, List<zzri> list2, List<zzri> list3, List<zzri> list4, List<zzri> list5, List<zzri> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
        this.zzbpb = Collections.unmodifiableList(list);
        this.zzbpc = Collections.unmodifiableList(list2);
        this.zzbpd = Collections.unmodifiableList(list3);
        this.zzbpe = Collections.unmodifiableList(list4);
        this.zzbqh = Collections.unmodifiableList(list5);
        this.zzbqi = Collections.unmodifiableList(list6);
        this.zzbqj = Collections.unmodifiableList(list7);
        this.zzbqk = Collections.unmodifiableList(list8);
        this.zzbql = Collections.unmodifiableList(list9);
        this.zzbqm = Collections.unmodifiableList(list10);
    }

    public final List<zzri> zzsk() {
        return this.zzbpb;
    }

    public final List<zzri> zzsl() {
        return this.zzbpc;
    }

    public final List<zzri> zzsm() {
        return this.zzbpd;
    }

    public final List<zzri> zzsn() {
        return this.zzbpe;
    }

    public final List<zzri> zzte() {
        return this.zzbqh;
    }

    public final List<zzri> zztf() {
        return this.zzbqi;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbpb);
        String valueOf2 = String.valueOf(this.zzbpc);
        String valueOf3 = String.valueOf(this.zzbpd);
        String valueOf4 = String.valueOf(this.zzbpe);
        String valueOf5 = String.valueOf(this.zzbqh);
        String valueOf6 = String.valueOf(this.zzbqi);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 102 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
        sb.append("Positive predicates: ");
        sb.append(valueOf);
        sb.append("  Negative predicates: ");
        sb.append(valueOf2);
        sb.append("  Add tags: ");
        sb.append(valueOf3);
        sb.append("  Remove tags: ");
        sb.append(valueOf4);
        sb.append("  Add macros: ");
        sb.append(valueOf5);
        sb.append("  Remove macros: ");
        sb.append(valueOf6);
        return sb.toString();
    }
}
