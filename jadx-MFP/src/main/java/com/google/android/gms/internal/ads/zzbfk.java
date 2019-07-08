package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public abstract class zzbfk implements Releasable {
    protected Context mContext;
    protected String zzeiz;
    protected WeakReference<zzbdz> zzewo;

    public zzbfk(zzbdz zzbdz) {
        this.mContext = zzbdz.getContext();
        this.zzeiz = zzbv.zzlf().zzo(this.mContext, zzbdz.zzabz().zzdp);
        this.zzewo = new WeakReference<>(zzbdz);
    }

    public abstract void abort();

    public void release() {
    }

    /* access modifiers changed from: protected */
    public void zzcz(int i) {
    }

    /* access modifiers changed from: protected */
    public void zzda(int i) {
    }

    /* access modifiers changed from: protected */
    public void zzdb(int i) {
    }

    /* access modifiers changed from: protected */
    public void zzdc(int i) {
    }

    public abstract boolean zzex(String str);

    @VisibleForTesting
    public final void zza(String str, String str2, long j, long j2, boolean z, int i, int i2) {
        Handler handler = zzbat.zztu;
        zzbfm zzbfm = new zzbfm(this, str, str2, j, j2, z, i, i2);
        handler.post(zzbfm);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2, int i) {
        zzbat.zztu.post(new zzbfn(this, str, str2, i));
    }

    @VisibleForTesting
    public final void zzc(String str, String str2, long j) {
        Handler handler = zzbat.zztu;
        zzbfo zzbfo = new zzbfo(this, str, str2, j);
        handler.post(zzbfo);
    }

    @VisibleForTesting
    public final void zza(String str, String str2, String str3, @Nullable String str4) {
        Handler handler = zzbat.zztu;
        zzbfp zzbfp = new zzbfp(this, str, str2, str3, str4);
        handler.post(zzbfp);
    }

    /* access modifiers changed from: protected */
    public String zzey(String str) {
        zzwu.zzpv();
        return zzbat.zzei(str);
    }

    /* access modifiers changed from: private */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zzez(java.lang.String r2) {
        /*
            java.lang.String r0 = "internal"
            int r1 = r2.hashCode()
            switch(r1) {
                case -1947652542: goto L_0x007f;
                case -1396664534: goto L_0x0074;
                case -1347010958: goto L_0x006a;
                case -918817863: goto L_0x005f;
                case -659376217: goto L_0x0055;
                case -642208130: goto L_0x004b;
                case -354048396: goto L_0x0040;
                case -32082395: goto L_0x0035;
                case 3387234: goto L_0x002b;
                case 96784904: goto L_0x0021;
                case 580119100: goto L_0x0016;
                case 725497484: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0089
        L_0x000b:
            java.lang.String r1 = "noCacheDir"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 7
            goto L_0x008a
        L_0x0016:
            java.lang.String r1 = "expireFailed"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 6
            goto L_0x008a
        L_0x0021:
            java.lang.String r1 = "error"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 1
            goto L_0x008a
        L_0x002b:
            java.lang.String r1 = "noop"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 4
            goto L_0x008a
        L_0x0035:
            java.lang.String r1 = "externalAbort"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 10
            goto L_0x008a
        L_0x0040:
            java.lang.String r1 = "sizeExceeded"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 11
            goto L_0x008a
        L_0x004b:
            java.lang.String r1 = "playerFailed"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 5
            goto L_0x008a
        L_0x0055:
            java.lang.String r1 = "contentLengthMissing"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 0
            goto L_0x008a
        L_0x005f:
            java.lang.String r1 = "downloadTimeout"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 9
            goto L_0x008a
        L_0x006a:
            java.lang.String r1 = "inProgress"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 2
            goto L_0x008a
        L_0x0074:
            java.lang.String r1 = "badUrl"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 8
            goto L_0x008a
        L_0x007f:
            java.lang.String r1 = "interrupted"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0089
            r2 = 3
            goto L_0x008a
        L_0x0089:
            r2 = -1
        L_0x008a:
            switch(r2) {
                case 0: goto L_0x0097;
                case 1: goto L_0x0097;
                case 2: goto L_0x0097;
                case 3: goto L_0x0097;
                case 4: goto L_0x0097;
                case 5: goto L_0x0097;
                case 6: goto L_0x0094;
                case 7: goto L_0x0094;
                case 8: goto L_0x0091;
                case 9: goto L_0x0091;
                case 10: goto L_0x008e;
                case 11: goto L_0x008e;
                default: goto L_0x008d;
            }
        L_0x008d:
            goto L_0x0099
        L_0x008e:
            java.lang.String r0 = "policy"
            goto L_0x0099
        L_0x0091:
            java.lang.String r0 = "network"
            goto L_0x0099
        L_0x0094:
            java.lang.String r0 = "io"
            goto L_0x0099
        L_0x0097:
            java.lang.String r0 = "internal"
        L_0x0099:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfk.zzez(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    public final void zza(String str, Map<String, String> map) {
        zzbdz zzbdz = (zzbdz) this.zzewo.get();
        if (zzbdz != null) {
            zzbdz.zza(str, map);
        }
    }
}
