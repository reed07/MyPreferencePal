package com.google.android.gms.internal.icing;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzbt {
    public static <T> zzbs<T> zza(zzbs<T> zzbs) {
        if ((zzbs instanceof zzbv) || (zzbs instanceof zzbu)) {
            return zzbs;
        }
        if (zzbs instanceof Serializable) {
            return new zzbu(zzbs);
        }
        return new zzbv(zzbs);
    }

    public static <T> zzbs<T> zzb(@NullableDecl T t) {
        return new zzbw(t);
    }
}
