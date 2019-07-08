package com.google.android.gms.internal.icing;

import java.util.NoSuchElementException;

final class zzcf extends zzch {
    private final int limit = this.zzgb.size();
    private int position = 0;
    private final /* synthetic */ zzce zzgb;

    zzcf(zzce zzce) {
        this.zzgb = zzce;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzgb.zzl(i);
        }
        throw new NoSuchElementException();
    }
}
