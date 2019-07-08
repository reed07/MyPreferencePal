package com.google.android.gms.internal.fitness;

import android.support.annotation.Nullable;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class zzf {
    public static <T> int zza(@Nullable T t, List<T> list) {
        if (t == null) {
            return -1;
        }
        int indexOf = list.indexOf(t);
        if (indexOf >= 0) {
            return indexOf;
        }
        list.add(t);
        return list.size() - 1;
    }
}
