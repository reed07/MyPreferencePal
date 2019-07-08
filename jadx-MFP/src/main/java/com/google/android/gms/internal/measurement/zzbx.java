package com.google.android.gms.internal.measurement;

import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

public final class zzbx {
    private final zzaw zzqx;
    private volatile Boolean zzyk;
    private String zzyl;
    private Set<Integer> zzym;

    protected zzbx(zzaw zzaw) {
        Preconditions.checkNotNull(zzaw);
        this.zzqx = zzaw;
    }

    public final boolean zzdw() {
        if (this.zzyk == null) {
            synchronized (this) {
                if (this.zzyk == null) {
                    ApplicationInfo applicationInfo = this.zzqx.getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzyk = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if ((this.zzyk == null || !this.zzyk.booleanValue()) && "com.google.android.gms.analytics".equals(myProcessName)) {
                        this.zzyk = Boolean.TRUE;
                    }
                    if (this.zzyk == null) {
                        this.zzyk = Boolean.TRUE;
                        this.zzqx.zzby().zzu("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzyk.booleanValue();
    }

    public static boolean zzdx() {
        return ((Boolean) zzcf.zzyw.get()).booleanValue();
    }

    public static int zzdy() {
        return ((Integer) zzcf.zzzt.get()).intValue();
    }

    public static long zzdz() {
        return ((Long) zzcf.zzze.get()).longValue();
    }

    public static long zzea() {
        return ((Long) zzcf.zzzh.get()).longValue();
    }

    public static int zzeb() {
        return ((Integer) zzcf.zzzj.get()).intValue();
    }

    public static int zzec() {
        return ((Integer) zzcf.zzzk.get()).intValue();
    }

    @VisibleForTesting
    public static String zzed() {
        return (String) zzcf.zzzm.get();
    }

    @VisibleForTesting
    public static String zzee() {
        return (String) zzcf.zzzl.get();
    }

    public static String zzef() {
        return (String) zzcf.zzzn.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (r1.equals(r0) != false) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Set<java.lang.Integer> zzeg() {
        /*
            r6 = this;
            com.google.android.gms.internal.measurement.zzcg<java.lang.String> r0 = com.google.android.gms.internal.measurement.zzcf.zzzw
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            java.util.Set<java.lang.Integer> r1 = r6.zzym
            if (r1 == 0) goto L_0x0016
            java.lang.String r1 = r6.zzyl
            if (r1 == 0) goto L_0x0016
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0039
        L_0x0016:
            java.lang.String r1 = ","
            java.lang.String[] r1 = android.text.TextUtils.split(r0, r1)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            int r3 = r1.length
            r4 = 0
        L_0x0023:
            if (r4 >= r3) goto L_0x0035
            r5 = r1[r4]
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x0032 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0032 }
            r2.add(r5)     // Catch:{ NumberFormatException -> 0x0032 }
        L_0x0032:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x0035:
            r6.zzyl = r0
            r6.zzym = r2
        L_0x0039:
            java.util.Set<java.lang.Integer> r0 = r6.zzym
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbx.zzeg():java.util.Set");
    }

    public static long zzeh() {
        return ((Long) zzcf.zzaab.get()).longValue();
    }
}
