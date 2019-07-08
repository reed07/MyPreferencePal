package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: IMASDK */
public final class ec {
    public final int a = 0;
    public final float b = BitmapDescriptorFactory.HUE_RED;

    public ec(int i, float f) {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ec ecVar = (ec) obj;
        return this.a == ecVar.a && Float.compare(ecVar.b, this.b) == 0;
    }

    public final int hashCode() {
        return ((this.a + 527) * 31) + Float.floatToIntBits(this.b);
    }
}
