package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbv extends zzbq {
    private static final String ID = zza.HASH.toString();
    private static final String zzbcf = zzb.ARG0.toString();
    private static final String zzbch = zzb.INPUT_FORMAT.toString();
    private static final String zzbck = zzb.ALGORITHM.toString();

    public zzbv() {
        super(ID, zzbcf);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String str;
        String str2;
        byte[] bArr;
        zzp zzp = (zzp) map.get(zzbcf);
        if (zzp == null || zzp == zzgj.zzqq()) {
            return zzgj.zzqq();
        }
        String zzc = zzgj.zzc(zzp);
        zzp zzp2 = (zzp) map.get(zzbck);
        if (zzp2 == null) {
            str = "MD5";
        } else {
            str = zzgj.zzc(zzp2);
        }
        zzp zzp3 = (zzp) map.get(zzbch);
        if (zzp3 == null) {
            str2 = "text";
        } else {
            str2 = zzgj.zzc(zzp3);
        }
        if ("text".equals(str2)) {
            bArr = zzc.getBytes();
        } else if ("base16".equals(str2)) {
            bArr = zzo.decode(zzc);
        } else {
            String str3 = "Hash: unknown input format: ";
            String valueOf = String.valueOf(str2);
            zzdi.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return zzgj.zzqq();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return zzgj.zzj(zzo.encode(instance.digest()));
        } catch (NoSuchAlgorithmException unused) {
            String str4 = "Hash: unknown algorithm: ";
            String valueOf2 = String.valueOf(str);
            zzdi.e(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
            return zzgj.zzqq();
        }
    }
}
