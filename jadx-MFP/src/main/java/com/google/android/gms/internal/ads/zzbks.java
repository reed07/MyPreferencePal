package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public final class zzbks {
    private static final Logger logger = Logger.getLogger(zzbks.class.getName());

    public static zzbjs zza(zzbju zzbju) throws GeneralSecurityException {
        zzbjz zza = zzbkb.zza(zzbju, null);
        for (Collection it : zza.zzafr()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!(((zzbka) it2.next()).zzafs() instanceof zzbjs)) {
                        throw new GeneralSecurityException("invalid HybridEncrypt key material");
                    }
                }
            }
        }
        return new zzbkt(zza);
    }
}
