package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzbkl implements zzbjm {
    private static final byte[] zzfdo = new byte[0];
    private final zzbna zzfdp;
    private final zzbjm zzfdq;

    public zzbkl(zzbna zzbna, zzbjm zzbjm) {
        this.zzfdp = zzbna;
        this.zzfdq = zzbjm;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = zzbkb.zzb(this.zzfdp).toByteArray();
        byte[] zzc = this.zzfdq.zzc(byteArray, zzfdo);
        byte[] zzc2 = ((zzbjm) zzbkb.zza(this.zzfdp.zzaig(), byteArray)).zzc(bArr, bArr2);
        return ByteBuffer.allocate(zzc.length + 4 + zzc2.length).putInt(zzc.length).put(zzc).put(zzc2).array();
    }
}
