package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public abstract class zzbvx extends zzbvv implements zzbc {
    private int flags;
    private int version;

    protected zzbvx(String str) {
        super(str);
    }

    public final int getVersion() {
        if (!this.zzgcg) {
            zzaqg();
        }
        return this.version;
    }

    /* access modifiers changed from: protected */
    public final long zzp(ByteBuffer byteBuffer) {
        this.version = zzbb.zza(byteBuffer.get());
        this.flags = (zzbb.zzb(byteBuffer) << 8) + 0 + zzbb.zza(byteBuffer.get());
        return 4;
    }
}
