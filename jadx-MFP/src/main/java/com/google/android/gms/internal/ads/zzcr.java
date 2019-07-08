package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;

public abstract class zzcr implements zzcq {
    protected static volatile zzdl zzqo;
    protected MotionEvent zzqu;
    protected LinkedList<MotionEvent> zzqv = new LinkedList<>();
    protected long zzqw = 0;
    protected long zzqx = 0;
    protected long zzqy = 0;
    protected long zzqz = 0;
    protected long zzra = 0;
    protected long zzrb = 0;
    protected long zzrc = 0;
    protected double zzrd;
    private double zzre;
    private double zzrf;
    protected float zzrg;
    protected float zzrh;
    protected float zzri;
    protected float zzrj;
    private boolean zzrk = false;
    protected boolean zzrl = false;
    protected DisplayMetrics zzrm;

    protected zzcr(Context context) {
        try {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctq)).booleanValue()) {
                zzbw.zzw();
            } else {
                zzdq.zzb(zzqo);
            }
            this.zzrm = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzdi;

    /* access modifiers changed from: protected */
    public abstract zzbl zza(Context context, View view, Activity activity);

    /* access modifiers changed from: protected */
    public abstract zzbl zza(Context context, zzbi zzbi);

    /* access modifiers changed from: protected */
    public abstract zzdr zzb(MotionEvent motionEvent) throws zzdi;

    public void zzb(View view) {
    }

    public final String zza(Context context) {
        if (zzds.isMainThread()) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcts)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, null, null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, null);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.view.MotionEvent r13) {
        /*
            r12 = this;
            boolean r0 = r12.zzrk
            r1 = 0
            if (r0 == 0) goto L_0x0035
            r2 = 0
            r12.zzqz = r2
            r12.zzqy = r2
            r12.zzqx = r2
            r12.zzqw = r2
            r12.zzra = r2
            r12.zzrc = r2
            r12.zzrb = r2
            java.util.LinkedList<android.view.MotionEvent> r0 = r12.zzqv
            java.util.Iterator r0 = r0.iterator()
        L_0x001b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002b
            java.lang.Object r2 = r0.next()
            android.view.MotionEvent r2 = (android.view.MotionEvent) r2
            r2.recycle()
            goto L_0x001b
        L_0x002b:
            java.util.LinkedList<android.view.MotionEvent> r0 = r12.zzqv
            r0.clear()
            r0 = 0
            r12.zzqu = r0
            r12.zzrk = r1
        L_0x0035:
            int r0 = r13.getAction()
            switch(r0) {
                case 0: goto L_0x0062;
                case 1: goto L_0x003d;
                case 2: goto L_0x003d;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x0074
        L_0x003d:
            float r0 = r13.getRawX()
            double r2 = (double) r0
            float r0 = r13.getRawY()
            double r4 = (double) r0
            double r6 = r12.zzre
            double r6 = r2 - r6
            double r8 = r12.zzrf
            double r8 = r4 - r8
            double r10 = r12.zzrd
            double r6 = r6 * r6
            double r8 = r8 * r8
            double r6 = r6 + r8
            double r6 = java.lang.Math.sqrt(r6)
            double r10 = r10 + r6
            r12.zzrd = r10
            r12.zzre = r2
            r12.zzrf = r4
            goto L_0x0074
        L_0x0062:
            r2 = 0
            r12.zzrd = r2
            float r0 = r13.getRawX()
            double r2 = (double) r0
            r12.zzre = r2
            float r0 = r13.getRawY()
            double r2 = (double) r0
            r12.zzrf = r2
        L_0x0074:
            int r0 = r13.getAction()
            r2 = 1
            r4 = 1
            switch(r0) {
                case 0: goto L_0x0111;
                case 1: goto L_0x00db;
                case 2: goto L_0x0087;
                case 3: goto L_0x0080;
                default: goto L_0x007e;
            }
        L_0x007e:
            goto L_0x012e
        L_0x0080:
            long r0 = r12.zzqz
            long r0 = r0 + r2
            r12.zzqz = r0
            goto L_0x012e
        L_0x0087:
            long r2 = r12.zzqx
            int r0 = r13.getHistorySize()
            int r0 = r0 + r4
            long r5 = (long) r0
            long r2 = r2 + r5
            r12.zzqx = r2
            com.google.android.gms.internal.ads.zzdr r13 = r12.zzb(r13)     // Catch:{ zzdi -> 0x012e }
            if (r13 == 0) goto L_0x00a2
            java.lang.Long r0 = r13.zzgn     // Catch:{ zzdi -> 0x012e }
            if (r0 == 0) goto L_0x00a2
            java.lang.Long r0 = r13.zztp     // Catch:{ zzdi -> 0x012e }
            if (r0 == 0) goto L_0x00a2
            r0 = 1
            goto L_0x00a3
        L_0x00a2:
            r0 = 0
        L_0x00a3:
            if (r0 == 0) goto L_0x00b7
            long r2 = r12.zzrb     // Catch:{ zzdi -> 0x012e }
            java.lang.Long r0 = r13.zzgn     // Catch:{ zzdi -> 0x012e }
            long r5 = r0.longValue()     // Catch:{ zzdi -> 0x012e }
            java.lang.Long r0 = r13.zztp     // Catch:{ zzdi -> 0x012e }
            long r7 = r0.longValue()     // Catch:{ zzdi -> 0x012e }
            long r5 = r5 + r7
            long r2 = r2 + r5
            r12.zzrb = r2     // Catch:{ zzdi -> 0x012e }
        L_0x00b7:
            android.util.DisplayMetrics r0 = r12.zzrm     // Catch:{ zzdi -> 0x012e }
            if (r0 == 0) goto L_0x00c6
            if (r13 == 0) goto L_0x00c6
            java.lang.Long r0 = r13.zzgl     // Catch:{ zzdi -> 0x012e }
            if (r0 == 0) goto L_0x00c6
            java.lang.Long r0 = r13.zztq     // Catch:{ zzdi -> 0x012e }
            if (r0 == 0) goto L_0x00c6
            r1 = 1
        L_0x00c6:
            if (r1 == 0) goto L_0x012e
            long r0 = r12.zzrc     // Catch:{ zzdi -> 0x012e }
            java.lang.Long r2 = r13.zzgl     // Catch:{ zzdi -> 0x012e }
            long r2 = r2.longValue()     // Catch:{ zzdi -> 0x012e }
            java.lang.Long r13 = r13.zztq     // Catch:{ zzdi -> 0x012e }
            long r5 = r13.longValue()     // Catch:{ zzdi -> 0x012e }
            long r2 = r2 + r5
            long r0 = r0 + r2
            r12.zzrc = r0     // Catch:{ zzdi -> 0x012e }
            goto L_0x012e
        L_0x00db:
            android.view.MotionEvent r13 = android.view.MotionEvent.obtain(r13)
            r12.zzqu = r13
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.zzqv
            android.view.MotionEvent r0 = r12.zzqu
            r13.add(r0)
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.zzqv
            int r13 = r13.size()
            r0 = 6
            if (r13 <= r0) goto L_0x00fc
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.zzqv
            java.lang.Object r13 = r13.remove()
            android.view.MotionEvent r13 = (android.view.MotionEvent) r13
            r13.recycle()
        L_0x00fc:
            long r0 = r12.zzqy
            long r0 = r0 + r2
            r12.zzqy = r0
            java.lang.Throwable r13 = new java.lang.Throwable     // Catch:{ zzdi -> 0x012e }
            r13.<init>()     // Catch:{ zzdi -> 0x012e }
            java.lang.StackTraceElement[] r13 = r13.getStackTrace()     // Catch:{ zzdi -> 0x012e }
            long r0 = r12.zza(r13)     // Catch:{ zzdi -> 0x012e }
            r12.zzra = r0     // Catch:{ zzdi -> 0x012e }
            goto L_0x012e
        L_0x0111:
            float r0 = r13.getX()
            r12.zzrg = r0
            float r0 = r13.getY()
            r12.zzrh = r0
            float r0 = r13.getRawX()
            r12.zzri = r0
            float r13 = r13.getRawY()
            r12.zzrj = r13
            long r0 = r12.zzqw
            long r0 = r0 + r2
            r12.zzqw = r0
        L_0x012e:
            r12.zzrl = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcr.zza(android.view.MotionEvent):void");
    }

    public final void zza(int i, int i2, int i3) {
        MotionEvent motionEvent = this.zzqu;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        DisplayMetrics displayMetrics = this.zzrm;
        if (displayMetrics != null) {
            this.zzqu = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * displayMetrics.density, this.zzrm.density * ((float) i2), BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0, 0);
        } else {
            this.zzqu = null;
        }
        this.zzrl = false;
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        zzbl zzbl;
        if (z) {
            try {
                zzbl = zza(context, view, activity);
                this.zzrk = true;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                return Integer.toString(7);
            } catch (Throwable unused2) {
                return Integer.toString(3);
            }
        } else {
            zzbl = zza(context, null);
        }
        if (zzbl != null) {
            if (zzbl.zzamj() != 0) {
                return zzbw.zza(zzbl, str);
            }
        }
        return Integer.toString(5);
    }
}
