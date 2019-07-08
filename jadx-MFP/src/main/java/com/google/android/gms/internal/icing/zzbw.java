package com.google.android.gms.internal.icing;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbw<T> implements zzbs<T>, Serializable {
    @NullableDecl
    private final T zzdz;

    zzbw(@NullableDecl T t) {
        this.zzdz = t;
    }

    public final T get() {
        return this.zzdz;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof zzbw)) {
            return false;
        }
        zzbw zzbw = (zzbw) obj;
        T t = this.zzdz;
        T t2 = zzbw.zzdz;
        if (t == t2 || (t != null && t.equals(t2))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdz});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzdz);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
