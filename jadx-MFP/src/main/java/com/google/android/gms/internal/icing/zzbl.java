package com.google.android.gms.internal.icing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public abstract class zzbl<T> {
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzdc = null;
    private static final Object zzdf = new Object();
    private static boolean zzdg = false;
    private static final AtomicInteger zzdj = new AtomicInteger();
    private final String name;
    private final zzbo zzdh;
    private final T zzdi;
    private volatile int zzdk;
    private volatile T zzdl;

    public static void zzd(Context context) {
        synchronized (zzdf) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzdc != context) {
                synchronized (zzbb.class) {
                    zzbb.zzct.clear();
                }
                synchronized (zzbp.class) {
                    zzbp.zzdt.clear();
                }
                synchronized (zzbi.class) {
                    zzbi.zzdb = null;
                }
                zzdj.incrementAndGet();
                zzdc = context;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract T zza(Object obj);

    static void zzx() {
        zzdj.incrementAndGet();
    }

    private zzbl(zzbo zzbo, String str, T t) {
        this.zzdk = -1;
        if (zzbo.zzdn != null) {
            this.zzdh = zzbo;
            this.name = str;
            this.zzdi = t;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    private final String zzq(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.name);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzy() {
        return zzq(this.zzdh.zzdp);
    }

    public final T get() {
        int i = zzdj.get();
        if (this.zzdk < i) {
            synchronized (this) {
                if (this.zzdk < i) {
                    if (zzdc != null) {
                        zzbo zzbo = this.zzdh;
                        T zzz = zzz();
                        if (zzz == null) {
                            zzz = zzaa();
                            if (zzz == null) {
                                zzz = this.zzdi;
                            }
                        }
                        this.zzdl = zzz;
                        this.zzdk = i;
                    } else {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                }
            }
        }
        return this.zzdl;
    }

    @Nullable
    private final T zzz() {
        zzbf zzbf;
        zzbo zzbo = this.zzdh;
        String str = (String) zzbi.zzc(zzdc).zzn("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
        if (!(str != null && zzay.zzcg.matcher(str).matches())) {
            if (this.zzdh.zzdn != null) {
                zzbf = zzbb.zza(zzdc.getContentResolver(), this.zzdh.zzdn);
            } else {
                Context context = zzdc;
                zzbo zzbo2 = this.zzdh;
                zzbf = zzbp.zza(context, (String) null);
            }
            if (zzbf != null) {
                Object zzn = zzbf.zzn(zzy());
                if (zzn != null) {
                    return zza(zzn);
                }
            }
        } else {
            String str2 = "PhenotypeFlag";
            String str3 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(zzy());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        return null;
    }

    @Nullable
    private final T zzaa() {
        zzbo zzbo = this.zzdh;
        Object zzn = zzbi.zzc(zzdc).zzn(zzq(this.zzdh.zzdo));
        if (zzn != null) {
            return zza(zzn);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static zzbl<Boolean> zza(zzbo zzbo, String str, boolean z) {
        return new zzbn(zzbo, str, Boolean.valueOf(z));
    }

    /* synthetic */ zzbl(zzbo zzbo, String str, Object obj, zzbm zzbm) {
        this(zzbo, str, obj);
    }
}
