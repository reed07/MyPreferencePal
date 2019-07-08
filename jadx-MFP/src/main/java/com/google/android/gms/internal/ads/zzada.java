package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzada extends AdChoicesInfo {
    private final List<Image> zzdab = new ArrayList();
    private final zzacx zzddp;
    private String zzddq;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[Catch:{ RemoteException -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0027 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzada(com.google.android.gms.internal.ads.zzacx r4) {
        /*
            r3 = this;
            r3.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3.zzdab = r0
            r3.zzddp = r4
            com.google.android.gms.internal.ads.zzacx r0 = r3.zzddp     // Catch:{ RemoteException -> 0x0015 }
            java.lang.String r0 = r0.getText()     // Catch:{ RemoteException -> 0x0015 }
            r3.zzddq = r0     // Catch:{ RemoteException -> 0x0015 }
            goto L_0x001f
        L_0x0015:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0)
            java.lang.String r0 = ""
            r3.zzddq = r0
        L_0x001f:
            java.util.List r4 = r4.zzro()     // Catch:{ RemoteException -> 0x005b }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ RemoteException -> 0x005b }
        L_0x0027:
            boolean r0 = r4.hasNext()     // Catch:{ RemoteException -> 0x005b }
            if (r0 == 0) goto L_0x005a
            java.lang.Object r0 = r4.next()     // Catch:{ RemoteException -> 0x005b }
            boolean r1 = r0 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x005b }
            if (r1 == 0) goto L_0x004c
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch:{ RemoteException -> 0x005b }
            if (r0 == 0) goto L_0x004c
            java.lang.String r1 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x005b }
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzadb     // Catch:{ RemoteException -> 0x005b }
            if (r2 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzadb r1 = (com.google.android.gms.internal.ads.zzadb) r1     // Catch:{ RemoteException -> 0x005b }
            goto L_0x004d
        L_0x0046:
            com.google.android.gms.internal.ads.zzadd r1 = new com.google.android.gms.internal.ads.zzadd     // Catch:{ RemoteException -> 0x005b }
            r1.<init>(r0)     // Catch:{ RemoteException -> 0x005b }
            goto L_0x004d
        L_0x004c:
            r1 = 0
        L_0x004d:
            if (r1 == 0) goto L_0x0027
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r0 = r3.zzdab     // Catch:{ RemoteException -> 0x005b }
            com.google.android.gms.internal.ads.zzade r2 = new com.google.android.gms.internal.ads.zzade     // Catch:{ RemoteException -> 0x005b }
            r2.<init>(r1)     // Catch:{ RemoteException -> 0x005b }
            r0.add(r2)     // Catch:{ RemoteException -> 0x005b }
            goto L_0x0027
        L_0x005a:
            return
        L_0x005b:
            r4 = move-exception
            java.lang.String r0 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzada.<init>(com.google.android.gms.internal.ads.zzacx):void");
    }

    public final CharSequence getText() {
        return this.zzddq;
    }

    public final List<Image> getImages() {
        return this.zzdab;
    }
}
