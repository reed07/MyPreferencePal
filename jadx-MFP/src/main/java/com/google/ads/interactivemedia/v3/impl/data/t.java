package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class t extends ah {
    private final float volume;

    private t(float f) {
        this.volume = f;
    }

    public final float volume() {
        return this.volume;
    }

    public final String toString() {
        float f = this.volume;
        StringBuilder sb = new StringBuilder(40);
        sb.append("VolumeUpdateData{volume=");
        sb.append(f);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ah)) {
            return false;
        }
        return Float.floatToIntBits(this.volume) == Float.floatToIntBits(((ah) obj).volume());
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.volume) ^ 1000003;
    }

    /* synthetic */ t(float f, f fVar) {
        this(f);
    }
}
