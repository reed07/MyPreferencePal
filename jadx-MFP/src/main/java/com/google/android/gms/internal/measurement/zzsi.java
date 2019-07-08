package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public abstract class zzsi<T> {
    private static final Object zzbro = new Object();
    private static boolean zzbrp = false;
    private static final AtomicInteger zzbrs = new AtomicInteger();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzri = null;
    private final String name;
    private volatile T zzall;
    private final zzso zzbrq;
    private final T zzbrr;
    private volatile int zzbrt;

    public static void zzae(Context context) {
        synchronized (zzbro) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzri != context) {
                synchronized (zzrx.class) {
                    zzrx.zzbrd.clear();
                }
                synchronized (zzsp.class) {
                    zzsp.zzbsb.clear();
                }
                synchronized (zzse.class) {
                    zzse.zzbrl = null;
                }
                zzbrs.incrementAndGet();
                zzri = context;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract T zzs(Object obj);

    static void zztq() {
        zzbrs.incrementAndGet();
    }

    private zzsi(zzso zzso, String str, T t) {
        this.zzbrt = -1;
        if (zzso.zzbrv != null) {
            this.zzbrq = zzso;
            this.name = str;
            this.zzbrr = t;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    private final String zzfr(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.name);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zztr() {
        return zzfr(this.zzbrq.zzbrx);
    }

    public final T getDefaultValue() {
        return this.zzbrr;
    }

    public final T get() {
        int i = zzbrs.get();
        if (this.zzbrt < i) {
            synchronized (this) {
                if (this.zzbrt < i) {
                    if (zzri != null) {
                        zzso zzso = this.zzbrq;
                        T zzts = zzts();
                        if (zzts == null) {
                            zzts = zztt();
                            if (zzts == null) {
                                zzts = this.zzbrr;
                            }
                        }
                        this.zzall = zzts;
                        this.zzbrt = i;
                    } else {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                }
            }
        }
        return this.zzall;
    }

    @Nullable
    private final T zzts() {
        zzsb zzsb;
        zzso zzso = this.zzbrq;
        String str = (String) zzse.zzad(zzri).zzfn("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
        if (!(str != null && zzru.zzbqq.matcher(str).matches())) {
            if (this.zzbrq.zzbrv != null) {
                zzsb = zzrx.zza(zzri.getContentResolver(), this.zzbrq.zzbrv);
            } else {
                Context context = zzri;
                zzso zzso2 = this.zzbrq;
                zzsb = zzsp.zzi(context, null);
            }
            if (zzsb != null) {
                Object zzfn = zzsb.zzfn(zztr());
                if (zzfn != null) {
                    return zzs(zzfn);
                }
            }
        } else {
            String str2 = "PhenotypeFlag";
            String str3 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(zztr());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        return null;
    }

    @Nullable
    private final T zztt() {
        zzso zzso = this.zzbrq;
        Object zzfn = zzse.zzad(zzri).zzfn(zzfr(this.zzbrq.zzbrw));
        if (zzfn != null) {
            return zzs(zzfn);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static zzsi<Long> zza(zzso zzso, String str, long j) {
        return new zzsj(zzso, str, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static zzsi<Integer> zza(zzso zzso, String str, int i) {
        return new zzsk(zzso, str, Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public static zzsi<Boolean> zza(zzso zzso, String str, boolean z) {
        return new zzsl(zzso, str, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public static zzsi<Double> zza(zzso zzso, String str, double d) {
        return new zzsm(zzso, str, Double.valueOf(d));
    }

    /* access modifiers changed from: private */
    public static zzsi<String> zza(zzso zzso, String str, String str2) {
        return new zzsn(zzso, str, str2);
    }

    /* synthetic */ zzsi(zzso zzso, String str, Object obj, zzsj zzsj) {
        this(zzso, str, obj);
    }
}
