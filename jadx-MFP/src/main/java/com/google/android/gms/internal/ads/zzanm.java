package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.mediation.zzb;
import com.google.android.gms.ads.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzanm extends zzanh {
    private final zzbit zzdon;

    public zzanm(zzbit zzbit) {
        this.zzdon = zzbit;
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzamy zzamy, zzalm zzalm, zzwf zzwf) throws RemoteException {
        try {
            zzann zzann = new zzann(this, zzamy, zzalm);
            zzbit zzbit = this.zzdon;
            new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzc.zza(zzwf.width, zzwf.height, zzwf.zzckk);
            zzann.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support banner ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render banner ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzana zzana, zzalm zzalm) throws RemoteException {
        try {
            zzano zzano = new zzano(this, zzana, zzalm);
            zzbit zzbit = this.zzdon;
            new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzano.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support interstitial ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render interstitial ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzane zzane, zzalm zzalm) throws RemoteException {
        try {
            zzanp zzanp = new zzanp(this, zzane, zzalm);
            zzbit zzbit = this.zzdon;
            new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzanp.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support rewarded ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzanc zzanc, zzalm zzalm) throws RemoteException {
        try {
            zzanq zzanq = new zzanq(this, zzanc, zzalm);
            zzbit zzbit = this.zzdon;
            new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzanq.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support native ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    public final void showInterstitial() throws RemoteException {
        zzbiq zzbiq = null;
        try {
            zzbiq.zzxh();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zzvk() throws RemoteException {
        zzbir zzbir = null;
        try {
            zzbir.zzxh();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final zzyp getVideoController() {
        zzbit zzbit = this.zzdon;
        if (!(zzbit instanceof zzb)) {
            return null;
        }
        try {
            return ((zzb) zzbit).getVideoController();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.zzbiv, com.google.android.gms.internal.ads.zzanr] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.ads.zzbiv, com.google.android.gms.internal.ads.zzanr]
  assigns: [com.google.android.gms.internal.ads.zzanr]
  uses: [com.google.android.gms.internal.ads.zzbiv]
  mth insns count: 18
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r4, java.lang.String r5, android.os.Bundle r6, android.os.Bundle r7, com.google.android.gms.internal.ads.zzwf r8, com.google.android.gms.internal.ads.zzanj r9) throws android.os.RemoteException {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzanr r0 = new com.google.android.gms.internal.ads.zzanr     // Catch:{ Throwable -> 0x0029 }
            r0.<init>(r3, r9)     // Catch:{ Throwable -> 0x0029 }
            com.google.android.gms.internal.ads.zzbit r9 = r3.zzdon     // Catch:{ Throwable -> 0x0029 }
            com.google.android.gms.ads.mediation.zza r1 = new com.google.android.gms.ads.mediation.zza     // Catch:{ Throwable -> 0x0029 }
            int r5 = zzcw(r5)     // Catch:{ Throwable -> 0x0029 }
            r1.<init>(r5, r7)     // Catch:{ Throwable -> 0x0029 }
            com.google.android.gms.internal.ads.zzbiu r5 = new com.google.android.gms.internal.ads.zzbiu     // Catch:{ Throwable -> 0x0029 }
            java.lang.Object r4 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r4)     // Catch:{ Throwable -> 0x0029 }
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ Throwable -> 0x0029 }
            int r7 = r8.width     // Catch:{ Throwable -> 0x0029 }
            int r2 = r8.height     // Catch:{ Throwable -> 0x0029 }
            java.lang.String r8 = r8.zzckk     // Catch:{ Throwable -> 0x0029 }
            com.google.android.gms.ads.AdSize r7 = com.google.android.gms.ads.zzc.zza(r7, r2, r8)     // Catch:{ Throwable -> 0x0029 }
            r5.<init>(r4, r1, r6, r7)     // Catch:{ Throwable -> 0x0029 }
            r9.zza(r5, r0)     // Catch:{ Throwable -> 0x0029 }
            return
        L_0x0029:
            r4 = move-exception
            java.lang.String r5 = "Error generating signals for RTB"
            com.google.android.gms.internal.ads.zzbbd.zzb(r5, r4)
            android.os.RemoteException r4 = new android.os.RemoteException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanm.zza(com.google.android.gms.dynamic.IObjectWrapper, java.lang.String, android.os.Bundle, android.os.Bundle, com.google.android.gms.internal.ads.zzwf, com.google.android.gms.internal.ads.zzanj):void");
    }

    public final zzans zzvi() throws RemoteException {
        return zzans.zza(this.zzdon.zzafj());
    }

    public final zzans zzvj() throws RemoteException {
        return zzans.zza(this.zzdon.zzafi());
    }

    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzdon.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < strArr.length) {
            try {
                arrayList.add(new zza(zzcw(strArr[i]), bundleArr[i]));
                i++;
            } catch (IndexOutOfBoundsException unused) {
                throw new RemoteException();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzcw(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = -1396342996(0xffffffffacc57f2c, float:-5.6131957E-12)
            if (r0 == r1) goto L_0x0037
            r1 = -1052618729(0xffffffffc1425017, float:-12.144553)
            if (r0 == r1) goto L_0x002d
            r1 = -239580146(0xfffffffff1b84c0e, float:-1.82519E30)
            if (r0 == r1) goto L_0x0023
            r1 = 604727084(0x240b672c, float:3.022821E-17)
            if (r0 == r1) goto L_0x0019
            goto L_0x0041
        L_0x0019:
            java.lang.String r0 = "interstitial"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0041
            r2 = 1
            goto L_0x0042
        L_0x0023:
            java.lang.String r0 = "rewarded"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0041
            r2 = 2
            goto L_0x0042
        L_0x002d:
            java.lang.String r0 = "native"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0041
            r2 = 3
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "banner"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0041
            r2 = 0
            goto L_0x0042
        L_0x0041:
            r2 = -1
        L_0x0042:
            switch(r2) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0053;
                case 2: goto L_0x0050;
                case 3: goto L_0x004d;
                default: goto L_0x0045;
            }
        L_0x0045:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Internal Error"
            r2.<init>(r0)
            throw r2
        L_0x004d:
            int r2 = com.google.android.gms.ads.zza.zzvl
            return r2
        L_0x0050:
            int r2 = com.google.android.gms.ads.zza.zzvk
            return r2
        L_0x0053:
            int r2 = com.google.android.gms.ads.zza.zzvj
            return r2
        L_0x0056:
            int r2 = com.google.android.gms.ads.zza.zzvi
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanm.zzcw(java.lang.String):int");
    }

    private static Bundle zzcx(String str) throws RemoteException {
        String str2 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzbbd.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                bundle2.putString(str3, jSONObject.getString(str3));
            }
            return bundle2;
        } catch (JSONException e) {
            zzbbd.zzb("", e);
            throw new RemoteException();
        }
    }
}
