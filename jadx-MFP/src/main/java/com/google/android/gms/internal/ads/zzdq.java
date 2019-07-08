package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

final class zzdq {
    static zzbjs zztk;

    static boolean zzb(zzdl zzdl) throws IllegalAccessException, InvocationTargetException {
        if (zztk != null) {
            return true;
        }
        String str = (String) zzwu.zzpz().zzd(zzaan.zzctp);
        if (str == null || str.length() == 0) {
            str = null;
            if (zzdl != null) {
                Method zza = zzdl.zza("phpjyfBMe8u7C5auGMsy22WXoT6iMDb5qqttOP4sXOBlc73QdE1wdYLJ++PsHndY", "86B2wkBiSbEIJu45HO/BQ/RpWXZRq9YpFeIER87ao7I=");
                if (zza != null) {
                    str = (String) zza.invoke(null, new Object[0]);
                }
            }
            if (str == null) {
                return false;
            }
        }
        try {
            try {
                zzbju zzk = zzbjy.zzk(zzbu.zza(str, true));
                zzbjo.zza(zzbkp.zzfdl);
                zztk = zzbks.zza(zzk);
                if (zztk != null) {
                    return true;
                }
                return false;
            } catch (GeneralSecurityException unused) {
                return false;
            }
        } catch (IllegalArgumentException unused2) {
            return false;
        }
    }
}
