package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: IMASDK */
public final class cc {
    public static final cc a = new cc(1.0f);
    public final float b;
    public final float c;
    public final boolean d;
    private final int e;

    private cc(float f) {
        this(1.0f, 1.0f, false);
    }

    public cc(float f, float f2, boolean z) {
        boolean z2 = true;
        qi.b(f > BitmapDescriptorFactory.HUE_RED);
        if (f2 <= BitmapDescriptorFactory.HUE_RED) {
            z2 = false;
        }
        qi.b(z2);
        this.b = f;
        this.c = f2;
        this.d = z;
        this.e = Math.round(f * 1000.0f);
    }

    public final long a(long j) {
        return j * ((long) this.e);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        cc ccVar = (cc) obj;
        return this.b == ccVar.b && this.c == ccVar.c && this.d == ccVar.d;
    }

    public final int hashCode() {
        return ((((Float.floatToRawIntBits(this.b) + 527) * 31) + Float.floatToRawIntBits(this.c)) * 31) + (this.d ? 1 : 0);
    }
}
