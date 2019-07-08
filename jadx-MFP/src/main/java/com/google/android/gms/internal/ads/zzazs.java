package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzazs {
    private static zzv zzemw;
    private static final Object zzemx = new Object();
    @Deprecated
    private static final zzazy<Void> zzemy = new zzazt();

    public zzazs(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        zzbe(context);
    }

    @Deprecated
    public final <T> zzbcb<T> zza(String str, zzazy<T> zzazy) {
        zzbcl zzbcl = new zzbcl();
        zzemw.zze(new zzbaa(str, zzbcl));
        return zzbbq.zza(zzbbq.zza((zzbcb<A>) zzbcl, (zzbbm<A, B>) new zzazv<A,B>(this, zzazy), (Executor) zzayf.zzeky), Throwable.class, (zzbbl<? super X, ? extends V>) new zzazu<Object,Object>(this, zzazy), zzbcg.zzepp);
    }

    public final zzbcb<String> zza(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        String str2 = str;
        zzazz zzazz = new zzazz(null);
        zzazw zzazw = new zzazw(this, str2, zzazz);
        zzbax zzbax = new zzbax(null);
        zzazx zzazx = new zzazx(this, i, str, zzazz, zzazw, bArr, map, zzbax);
        if (zzbax.isEnabled()) {
            try {
                zzbax.zza(str2, HttpConstants.METHOD_GET, zzazx.getHeaders(), zzazx.zzh());
            } catch (zza e) {
                zzaxz.zzeo(e.getMessage());
            }
        }
        zzemw.zze(zzazx);
        return zzazz;
    }

    public final zzbcb<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }

    @VisibleForTesting
    private static zzv zzbe(Context context) {
        zzv zzv;
        zzv zzv2;
        synchronized (zzemx) {
            if (zzemw == null) {
                zzaan.initialize(context);
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcvy)).booleanValue()) {
                    zzv2 = zzazm.zzbd(context);
                } else {
                    zzv2 = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj((zzai) new zzas()));
                    zzv2.start();
                }
                zzemw = zzv2;
            }
            zzv = zzemw;
        }
        return zzv;
    }
}
