package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzbk extends zzbq {
    private static final String ID = zza.ENCODE.toString();
    private static final String zzbcf = zzb.ARG0.toString();
    private static final String zzbcg = zzb.NO_PADDING.toString();
    private static final String zzbch = zzb.INPUT_FORMAT.toString();
    private static final String zzbci = zzb.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzbcf);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String str;
        String str2;
        byte[] bArr;
        String str3;
        zzp zzp = (zzp) map.get(zzbcf);
        if (zzp == null || zzp == zzgj.zzqq()) {
            return zzgj.zzqq();
        }
        String zzc = zzgj.zzc(zzp);
        zzp zzp2 = (zzp) map.get(zzbch);
        if (zzp2 == null) {
            str = "text";
        } else {
            str = zzgj.zzc(zzp2);
        }
        zzp zzp3 = (zzp) map.get(zzbci);
        if (zzp3 == null) {
            str2 = "base16";
        } else {
            str2 = zzgj.zzc(zzp3);
        }
        int i = 2;
        zzp zzp4 = (zzp) map.get(zzbcg);
        if (zzp4 != null && zzgj.zzg(zzp4).booleanValue()) {
            i = 3;
        }
        try {
            if ("text".equals(str)) {
                bArr = zzc.getBytes();
            } else if ("base16".equals(str)) {
                bArr = zzo.decode(zzc);
            } else if ("base64".equals(str)) {
                bArr = Base64.decode(zzc, i);
            } else if ("base64url".equals(str)) {
                bArr = Base64.decode(zzc, i | 8);
            } else {
                String str4 = "Encode: unknown input format: ";
                String valueOf = String.valueOf(str);
                zzdi.e(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                return zzgj.zzqq();
            }
            if ("base16".equals(str2)) {
                str3 = zzo.encode(bArr);
            } else if ("base64".equals(str2)) {
                str3 = Base64.encodeToString(bArr, i);
            } else if ("base64url".equals(str2)) {
                str3 = Base64.encodeToString(bArr, i | 8);
            } else {
                String str5 = "Encode: unknown output format: ";
                String valueOf2 = String.valueOf(str2);
                zzdi.e(valueOf2.length() != 0 ? str5.concat(valueOf2) : new String(str5));
                return zzgj.zzqq();
            }
            return zzgj.zzj(str3);
        } catch (IllegalArgumentException unused) {
            zzdi.e("Encode: invalid input:");
            return zzgj.zzqq();
        }
    }
}
