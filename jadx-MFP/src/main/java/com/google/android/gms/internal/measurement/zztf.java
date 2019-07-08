package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zztf extends zzth {
    private final int limit = this.zzbtv.size();
    private int position = 0;
    private final /* synthetic */ zzte zzbtv;

    zztf(zzte zzte) {
        this.zzbtv = zzte;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzbtv.zzan(i);
        }
        throw new NoSuchElementException();
    }
}
