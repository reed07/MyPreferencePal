package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbuz {
    protected volatile int zzfwt = -1;

    public abstract zzbuz zza(zzbuq zzbuq) throws IOException;

    public void zza(zzbur zzbur) throws IOException {
    }

    /* access modifiers changed from: protected */
    public int zzt() {
        return 0;
    }

    public final int zzamj() {
        int zzt = zzt();
        this.zzfwt = zzt;
        return zzt;
    }

    public static final byte[] zzb(zzbuz zzbuz) {
        byte[] bArr = new byte[zzbuz.zzamj()];
        try {
            zzbur zzr = zzbur.zzr(bArr, 0, bArr.length);
            zzbuz.zza(zzr);
            zzr.zzalv();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzbuz> T zza(T t, byte[] bArr) throws zzbuy {
        return zza(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzbuz> T zza(T t, byte[] bArr, int i, int i2) throws zzbuy {
        try {
            zzbuq zzq = zzbuq.zzq(bArr, 0, i2);
            t.zza(zzq);
            zzq.zzeo(0);
            return t;
        } catch (zzbuy e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzbva.zzc(this);
    }

    /* renamed from: zzapm */
    public zzbuz clone() throws CloneNotSupportedException {
        return (zzbuz) super.clone();
    }
}
