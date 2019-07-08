package com.google.android.gms.internal.icing;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbv<T> implements zzbs<T> {
    @NullableDecl
    private T value;
    private volatile zzbs<T> zzdx;
    private volatile boolean zzdy;

    zzbv(zzbs<T> zzbs) {
        this.zzdx = (zzbs) zzbr.checkNotNull(zzbs);
    }

    public final T get() {
        if (!this.zzdy) {
            synchronized (this) {
                if (!this.zzdy) {
                    T t = this.zzdx.get();
                    this.value = t;
                    this.zzdy = true;
                    this.zzdx = null;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj = this.zzdx;
        if (obj == null) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
