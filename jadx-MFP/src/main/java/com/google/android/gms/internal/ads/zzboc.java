package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class zzboc implements zzbjs {
    private static final byte[] zzfdo = new byte[0];
    private final String zzfiv;
    private final byte[] zzfiw;
    private final zzboj zzfix;
    private final zzboa zzfiy;
    private final zzboe zzfiz;

    public zzboc(ECPublicKey eCPublicKey, byte[] bArr, String str, zzboj zzboj, zzboa zzboa) throws GeneralSecurityException {
        zzbog.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzfiz = new zzboe(eCPublicKey);
        this.zzfiw = bArr;
        this.zzfiv = str;
        this.zzfix = zzboj;
        this.zzfiy = zzboa;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzbof zza = this.zzfiz.zza(this.zzfiv, this.zzfiw, bArr2, this.zzfiy.zzafw(), this.zzfix);
        byte[] zzc = this.zzfiy.zzl(zza.zzaka()).zzc(bArr, zzfdo);
        byte[] zzajz = zza.zzajz();
        return ByteBuffer.allocate(zzajz.length + zzc.length).put(zzajz).put(zzc).array();
    }
}
