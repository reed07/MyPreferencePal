package com.google.android.gms.internal.icing;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbu<T> implements zzbs<T>, Serializable {
    @NullableDecl
    private transient T value;
    private final zzbs<T> zzdx;
    private volatile transient boolean zzdy;

    zzbu(zzbs<T> zzbs) {
        this.zzdx = (zzbs) zzbr.checkNotNull(zzbs);
    }

    public final T get() {
        if (!this.zzdy) {
            synchronized (this) {
                if (!this.zzdy) {
                    T t = this.zzdx.get();
                    this.value = t;
                    this.zzdy = true;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj;
        if (this.zzdy) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        } else {
            obj = this.zzdx;
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
