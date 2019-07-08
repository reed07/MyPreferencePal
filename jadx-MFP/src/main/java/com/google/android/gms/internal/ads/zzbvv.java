package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class zzbvv implements zzbc {
    private static zzbwg zzco = zzbwg.zzk(zzbvv.class);
    private String type;
    private long zzapb;
    private zzbd zzgcf;
    boolean zzgcg;
    private boolean zzgch;
    private ByteBuffer zzgci;
    private long zzgcj;
    private long zzgck = -1;
    private zzbwa zzgcl;
    private ByteBuffer zzgcm = null;

    private final synchronized void zzaqf() {
        if (!this.zzgch) {
            try {
                zzbwg zzbwg = zzco;
                String str = "mem mapping ";
                String valueOf = String.valueOf(this.type);
                zzbwg.zzge(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                this.zzgci = this.zzgcl.zzk(this.zzgcj, this.zzgck);
                this.zzgch = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzg(ByteBuffer byteBuffer);

    protected zzbvv(String str) {
        this.type = str;
        this.zzgch = true;
        this.zzgcg = true;
    }

    public final void zza(zzbwa zzbwa, ByteBuffer byteBuffer, long j, zzaz zzaz) throws IOException {
        this.zzgcj = zzbwa.position();
        this.zzapb = this.zzgcj - ((long) byteBuffer.remaining());
        this.zzgck = j;
        this.zzgcl = zzbwa;
        zzbwa.zzaw(zzbwa.position() + j);
        this.zzgch = false;
        this.zzgcg = false;
        zzaqg();
    }

    public final synchronized void zzaqg() {
        zzaqf();
        zzbwg zzbwg = zzco;
        String str = "parsing details of ";
        String valueOf = String.valueOf(this.type);
        zzbwg.zzge(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (this.zzgci != null) {
            ByteBuffer byteBuffer = this.zzgci;
            this.zzgcg = true;
            byteBuffer.rewind();
            zzg(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.zzgcm = byteBuffer.slice();
            }
            this.zzgci = null;
        }
    }

    public final String getType() {
        return this.type;
    }

    public final void zza(zzbd zzbd) {
        this.zzgcf = zzbd;
    }
}
