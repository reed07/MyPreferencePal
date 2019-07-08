package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzas extends zzcs {
    /* access modifiers changed from: private */
    public long zzade = -1;
    /* access modifiers changed from: private */
    public char zzalu = 0;
    @GuardedBy
    private String zzalv;
    private final zzau zzalw = new zzau(this, 6, false, false);
    private final zzau zzalx = new zzau(this, 6, true, false);
    private final zzau zzaly = new zzau(this, 6, false, true);
    private final zzau zzalz = new zzau(this, 5, false, false);
    private final zzau zzama = new zzau(this, 5, true, false);
    private final zzau zzamb = new zzau(this, 5, false, true);
    private final zzau zzamc = new zzau(this, 4, false, false);
    private final zzau zzamd = new zzau(this, 3, false, false);
    private final zzau zzame = new zzau(this, 2, false, false);

    zzas(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    public final zzau zzjg() {
        return this.zzalw;
    }

    public final zzau zzjh() {
        return this.zzalx;
    }

    public final zzau zzji() {
        return this.zzaly;
    }

    public final zzau zzjj() {
        return this.zzalz;
    }

    public final zzau zzjk() {
        return this.zzama;
    }

    public final zzau zzjl() {
        return this.zzamb;
    }

    public final zzau zzjm() {
        return this.zzamc;
    }

    public final zzau zzjn() {
        return this.zzamd;
    }

    public final zzau zzjo() {
        return this.zzame;
    }

    protected static Object zzbw(String str) {
        if (str == null) {
            return null;
        }
        return new zzav(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && isLoggable(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzbr zzkl = this.zzada.zzkl();
            if (zzkl == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzkl.isInitialized()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i < 0) {
                    i = 0;
                }
                zzat zzat = new zzat(this, i >= 9 ? 8 : i, str, obj, obj2, obj3);
                zzkl.zzc((Runnable) zzat);
            }
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final boolean isLoggable(int i) {
        return Log.isLoggable(zzjp(), i);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void zza(int i, String str) {
        Log.println(i, zzjp(), str);
    }

    @VisibleForTesting
    private final String zzjp() {
        String str;
        synchronized (this) {
            if (this.zzalv == null) {
                if (this.zzada.zzkq() != null) {
                    this.zzalv = this.zzada.zzkq();
                } else {
                    this.zzalv = zzq.zzhy();
                }
            }
            str = this.zzalv;
        }
        return str;
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zza = zza(z, obj);
        String zza2 = zza(z, obj2);
        String zza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length());
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzbx = zzbx(AppMeasurement.class.getCanonicalName());
                String zzbx2 = zzbx(zzbw.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            String zzbx3 = zzbx(className);
                            if (zzbx3.equals(zzbx) || zzbx3.equals(zzbx2)) {
                                sb2.append(": ");
                                sb2.append(stackTraceElement);
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                return sb2.toString();
            } else if (obj instanceof zzav) {
                return ((zzav) obj).zzamn;
            } else {
                if (z) {
                    return "-";
                }
                return String.valueOf(obj);
            }
        }
    }

    private static String zzbx(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public final String zzjq() {
        Pair<String, Long> zzfm = zzgu().zzanb.zzfm();
        if (zzfm == null || zzfm == zzbd.zzana) {
            return null;
        }
        String valueOf = String.valueOf(zzfm.second);
        String str = (String) zzfm.first;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
    }

    public final /* bridge */ /* synthetic */ void zzgh() {
        super.zzgh();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zzaa zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzaq zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzfx zzgr() {
        return super.zzgr();
    }

    public final /* bridge */ /* synthetic */ zzbr zzgs() {
        return super.zzgs();
    }

    public final /* bridge */ /* synthetic */ zzas zzgt() {
        return super.zzgt();
    }

    public final /* bridge */ /* synthetic */ zzbd zzgu() {
        return super.zzgu();
    }

    public final /* bridge */ /* synthetic */ zzq zzgv() {
        return super.zzgv();
    }

    public final /* bridge */ /* synthetic */ zzn zzgw() {
        return super.zzgw();
    }
}
