package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioAttributes.Builder;

/* compiled from: IMASDK */
public final class dc {
    public static final dc a = new dd().a();
    public final int b;
    public final int c;
    private final int d;
    private AudioAttributes e;

    private dc(int i, int i2, int i3) {
        this.b = i;
        this.d = i2;
        this.c = i3;
    }

    @TargetApi(21)
    public final AudioAttributes a() {
        if (this.e == null) {
            this.e = new Builder().setContentType(this.b).setFlags(this.d).setUsage(this.c).build();
        }
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        dc dcVar = (dc) obj;
        return this.b == dcVar.b && this.d == dcVar.d && this.c == dcVar.c;
    }

    public final int hashCode() {
        return ((((this.b + 527) * 31) + this.d) * 31) + this.c;
    }

    /* synthetic */ dc(int i, int i2, int i3, byte b2) {
        this(i, i2, i3);
    }
}
