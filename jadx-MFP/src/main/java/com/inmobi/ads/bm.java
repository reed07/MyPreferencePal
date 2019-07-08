package com.inmobi.ads;

import android.support.annotation.NonNull;

/* compiled from: RawAsset */
public final class bm {
    public final int a;
    @NonNull
    public final String b;

    public bm(int i, @NonNull String str) {
        this.a = i;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bm)) {
            return false;
        }
        bm bmVar = (bm) obj;
        return this.a == bmVar.a && this.b.equals(bmVar.b);
    }

    public final int hashCode() {
        return (this.a * 31) + this.b.hashCode();
    }
}
