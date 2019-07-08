package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzho extends zzhj {
    public final zzhk zzagn = new zzhk();
    public long zzago;
    private final int zzagp = 0;
    public ByteBuffer zzdd;

    public zzho(int i) {
    }

    public final void zzs(int i) throws IllegalStateException {
        ByteBuffer byteBuffer = this.zzdd;
        if (byteBuffer == null) {
            this.zzdd = zzt(i);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = this.zzdd.position();
        int i2 = i + position;
        if (capacity < i2) {
            ByteBuffer zzt = zzt(i2);
            if (position > 0) {
                this.zzdd.position(0);
                this.zzdd.limit(position);
                zzt.put(this.zzdd);
            }
            this.zzdd = zzt;
        }
    }

    public final boolean zzdt() {
        return zzr(1073741824);
    }

    public final void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.zzdd;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private final ByteBuffer zzt(int i) {
        ByteBuffer byteBuffer = this.zzdd;
        int capacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        StringBuilder sb = new StringBuilder(44);
        sb.append("Buffer too small (");
        sb.append(capacity);
        sb.append(" < ");
        sb.append(i);
        sb.append(")");
        throw new IllegalStateException(sb.toString());
    }
}
