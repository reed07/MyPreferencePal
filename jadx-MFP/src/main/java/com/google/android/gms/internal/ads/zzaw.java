package com.google.android.gms.internal.ads;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import java.io.UnsupportedEncodingException;

public class zzaw extends zzr<String> {
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy
    private zzz<String> zzcl;

    public zzaw(int i, String str, zzz<String> zzz, @Nullable zzy zzy) {
        super(i, str, zzy);
        this.zzcl = zzz;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzh */
    public void zza(String str) {
        zzz<String> zzz;
        synchronized (this.mLock) {
            zzz = this.zzcl;
        }
        if (zzz != null) {
            zzz.zzb(str);
        }
    }

    /* access modifiers changed from: protected */
    public final zzx<String> zza(zzp zzp) {
        String str;
        try {
            byte[] bArr = zzp.data;
            String str2 = "ISO-8859-1";
            String str3 = (String) zzp.zzab.get("Content-Type");
            if (str3 != null) {
                String[] split = str3.split(";", 0);
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    String[] split2 = split[i].trim().split("=", 0);
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str2 = split2[1];
                        break;
                    }
                    i++;
                }
            }
            str = new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            str = new String(zzp.data);
        }
        return zzx.zza(str, zzap.zzb(zzp));
    }
}
