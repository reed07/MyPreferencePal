package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzbjw {
    private static final CopyOnWriteArrayList<zzbjv> zzfdb = new CopyOnWriteArrayList<>();

    public static zzbjv zzfh(String str) throws GeneralSecurityException {
        Iterator it = zzfdb.iterator();
        while (it.hasNext()) {
            zzbjv zzbjv = (zzbjv) it.next();
            if (zzbjv.zzff(str)) {
                return zzbjv;
            }
        }
        String str2 = "No KMS client does support: ";
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
