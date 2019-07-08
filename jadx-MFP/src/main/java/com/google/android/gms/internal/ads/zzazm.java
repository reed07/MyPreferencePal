package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

@zzark
public final class zzazm extends zzaj {
    private final Context mContext;

    public static zzv zzbd(Context context) {
        zzv zzv = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley"), 20971520), new zzazm(context, new zzas()));
        zzv.start();
        return zzv;
    }

    private zzazm(Context context, zzar zzar) {
        super(zzar);
        this.mContext = context;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        if (zzr.zzi() && zzr.getMethod() == 0) {
            if (Pattern.matches((String) zzwu.zzpz().zzd(zzaan.zzcvz), zzr.getUrl())) {
                zzwu.zzpv();
                if (zzbat.zzc(this.mContext, 13400000)) {
                    zzp zzc = new zzaft(this.mContext).zzc(zzr);
                    if (zzc != null) {
                        String str = "Got gmscore asset response: ";
                        String valueOf = String.valueOf(zzr.getUrl());
                        zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return zzc;
                    }
                    String str2 = "Failed to get gmscore asset response: ";
                    String valueOf2 = String.valueOf(zzr.getUrl());
                    zzaxz.v(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                }
            }
        }
        return super.zzc(zzr);
    }
}
