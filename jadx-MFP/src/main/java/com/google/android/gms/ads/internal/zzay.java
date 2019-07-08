package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzakr;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzalj;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzauo;
import com.google.android.gms.internal.ads.zzavy;
import com.google.android.gms.internal.ads.zzavz;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzazc;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzyd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzark
@ParametersAreNonnullByDefault
public final class zzay extends zzyd {
    private static final Object sLock = new Object();
    @Nullable
    @GuardedBy("sLock")
    private static zzay zzbpr;
    private final Context mContext;
    private final Object mLock = new Object();
    private boolean zzbps;
    private zzbbi zzbpt;

    public static zzay zza(Context context, zzbbi zzbbi) {
        zzay zzay;
        synchronized (sLock) {
            if (zzbpr == null) {
                zzbpr = new zzay(context.getApplicationContext(), zzbbi);
            }
            zzay = zzbpr;
        }
        return zzay;
    }

    public final void zza(zzalg zzalg) throws RemoteException {
    }

    public final void zzau(String str) {
    }

    @VisibleForTesting
    private zzay(Context context, zzbbi zzbbi) {
        this.mContext = context;
        this.zzbpt = zzbbi;
        this.zzbps = false;
    }

    public final void zza() {
        synchronized (sLock) {
            if (this.zzbps) {
                zzaxz.zzeo("Mobile ads is initialized already.");
                return;
            }
            this.zzbps = true;
            zzaan.initialize(this.mContext);
            zzbv.zzlj().zzd(this.mContext, this.zzbpt);
            zzbv.zzll().initialize(this.mContext);
        }
    }

    public final void zzat(String str) {
        zzaan.initialize(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuz)).booleanValue()) {
                zzbv.zzln().zza(this.mContext, this.zzbpt, str, null);
            }
        }
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        if (!TextUtils.isEmpty(str)) {
            zzaan.initialize(this.mContext);
            boolean booleanValue = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcuz)).booleanValue() | ((Boolean) zzwu.zzpz().zzd(zzaan.zzcri)).booleanValue();
            zzaz zzaz = null;
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcri)).booleanValue()) {
                booleanValue = true;
                zzaz = new zzaz(this, (Runnable) ObjectWrapper.unwrap(iObjectWrapper));
            }
            if (booleanValue) {
                zzbv.zzln().zza(this.mContext, this.zzbpt, str, zzaz);
            }
        }
    }

    public final void setAppVolume(float f) {
        zzbv.zzlk().setAppVolume(f);
    }

    public final float zzkj() {
        return zzbv.zzlk().zzkj();
    }

    public final void setAppMuted(boolean z) {
        zzbv.zzlk().setAppMuted(z);
    }

    public final boolean zzkk() {
        return zzbv.zzlk().zzkk();
    }

    public final String zzkl() {
        return this.zzbpt.zzdp;
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzaxz.e("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            zzaxz.e("Context is null. Failed to open debug menu.");
            return;
        }
        zzazc zzazc = new zzazc(context);
        zzazc.setAdUnitId(str);
        zzazc.zzee(this.zzbpt.zzdp);
        zzazc.showDialog();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(Runnable runnable) {
        Context context = this.mContext;
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map zzyg = zzbv.zzlj().zzyq().zzzi().zzyg();
        if (zzyg != null && !zzyg.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzaxz.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzauo zzxg = zzauo.zzxg();
            if (zzxg != null) {
                Collection<zzakr> values = zzyg.values();
                HashMap hashMap = new HashMap();
                IObjectWrapper wrap = ObjectWrapper.wrap(context);
                for (zzakr zzakr : values) {
                    for (zzakq zzakq : zzakr.zzdlp) {
                        String str = zzakq.zzdle;
                        for (String str2 : zzakq.zzdkw) {
                            if (!hashMap.containsKey(str2)) {
                                hashMap.put(str2, new ArrayList());
                            }
                            if (str != null) {
                                ((Collection) hashMap.get(str2)).add(str);
                            }
                        }
                    }
                }
                for (Entry entry : hashMap.entrySet()) {
                    String str3 = (String) entry.getKey();
                    try {
                        zzavy zzdd = zzxg.zzdd(str3);
                        if (zzdd != null) {
                            zzalj zzxn = zzdd.zzxn();
                            if (!zzxn.isInitialized()) {
                                if (zzxn.zzuy()) {
                                    zzxn.zza(wrap, (zzavz) zzdd.zzxo(), (List) entry.getValue());
                                    String str4 = "Initialized rewarded video mediation adapter ";
                                    String valueOf = String.valueOf(str3);
                                    zzaxz.zzdn(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 56);
                        sb.append("Failed to initialize rewarded video mediation adapter \"");
                        sb.append(str3);
                        sb.append("\"");
                        zzaxz.zzc(sb.toString(), th2);
                    }
                }
            }
        }
    }
}
