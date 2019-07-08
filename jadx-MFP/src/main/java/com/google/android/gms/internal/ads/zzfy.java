package com.google.android.gms.internal.ads;

public final class zzfy {
    public static final zzfy zzaaf = new zzfy(1.0f, 1.0f);
    public final float zzaag;
    public final float zzaah;
    private final int zzaai;

    public zzfy(float f, float f2) {
        this.zzaag = f;
        this.zzaah = f2;
        this.zzaai = Math.round(f * 1000.0f);
    }

    public final long zzl(long j) {
        return j * ((long) this.zzaai);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        return this.zzaag == zzfy.zzaag && this.zzaah == zzfy.zzaah;
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.zzaag) + 527) * 31) + Float.floatToRawIntBits(this.zzaah);
    }
}
