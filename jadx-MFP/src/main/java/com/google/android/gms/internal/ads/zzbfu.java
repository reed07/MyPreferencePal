package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@zzark
final class zzbfu {
    private final ArrayList<zzpb> zzexd = new ArrayList<>();
    private long zzexe;

    zzbfu() {
    }

    /* access modifiers changed from: 0000 */
    public final long zzadb() {
        Iterator it = this.zzexd.iterator();
        while (it.hasNext()) {
            Map responseHeaders = ((zzpb) it.next()).getResponseHeaders();
            if (responseHeaders != null) {
                for (Entry entry : responseHeaders.entrySet()) {
                    try {
                        if ("content-length".equalsIgnoreCase((String) entry.getKey())) {
                            this.zzexe = Math.max(this.zzexe, Long.parseLong((String) ((List) entry.getValue()).get(0)));
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                it.remove();
            }
        }
        return this.zzexe;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzpb zzpb) {
        this.zzexd.add(zzpb);
    }
}
