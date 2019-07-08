package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzark
public final class zzbfj implements Iterable<zzbfh> {
    private final List<zzbfh> zzewn = new ArrayList();

    public static boolean zzc(zzbdz zzbdz) {
        zzbfh zzd = zzd(zzbdz);
        if (zzd == null) {
            return false;
        }
        zzd.zzewk.abort();
        return true;
    }

    static zzbfh zzd(zzbdz zzbdz) {
        Iterator it = zzbv.zzmd().iterator();
        while (it.hasNext()) {
            zzbfh zzbfh = (zzbfh) it.next();
            if (zzbfh.zzerw == zzbdz) {
                return zzbfh;
            }
        }
        return null;
    }

    public final void zza(zzbfh zzbfh) {
        this.zzewn.add(zzbfh);
    }

    public final void zzb(zzbfh zzbfh) {
        this.zzewn.remove(zzbfh);
    }

    public final Iterator<zzbfh> iterator() {
        return this.zzewn.iterator();
    }

    public final int zzada() {
        return this.zzewn.size();
    }
}
