package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

public class zzbvw extends zzbvy implements zzbc {
    private String type;
    private long zzapb;
    private zzbd zzgcf;
    private boolean zzgcn;

    public zzbvw(String str) {
        this.type = str;
    }

    public final void zza(zzbd zzbd) {
        this.zzgcf = zzbd;
    }

    public final String getType() {
        return this.type;
    }

    public final void zza(zzbwa zzbwa, ByteBuffer byteBuffer, long j, zzaz zzaz) throws IOException {
        this.zzapb = zzbwa.position() - ((long) byteBuffer.remaining());
        this.zzgcn = byteBuffer.remaining() == 16;
        zza(zzbwa, j, zzaz);
    }

    public final void zza(zzbwa zzbwa, long j, zzaz zzaz) throws IOException {
        this.zzgcl = zzbwa;
        this.zzgcr = zzbwa.position();
        this.zzayz = this.zzgcr - ((long) ((this.zzgcn || 8 + j >= 4294967296L) ? 16 : 8));
        zzbwa.zzaw(zzbwa.position() + j);
        this.zzaop = zzbwa.position();
        this.zzgcp = zzaz;
    }
}
