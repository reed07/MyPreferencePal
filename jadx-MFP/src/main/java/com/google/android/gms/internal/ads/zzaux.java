package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzaux extends zzex implements zzauw {
    public zzaux() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzauw zzab(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if (queryLocalInterface instanceof zzauw) {
            return (zzauw) queryLocalInterface;
        }
        return new zzauy(iBinder);
    }

    /* JADX WARNING: type inference failed for: r4v2 */
    /* JADX WARNING: type inference failed for: r4v3, types: [com.google.android.gms.internal.ads.zzavb] */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.google.android.gms.internal.ads.zzavd] */
    /* JADX WARNING: type inference failed for: r4v7, types: [com.google.android.gms.internal.ads.zzavb] */
    /* JADX WARNING: type inference failed for: r4v8, types: [com.google.android.gms.internal.ads.zzauu] */
    /* JADX WARNING: type inference failed for: r4v10, types: [com.google.android.gms.internal.ads.zzauv] */
    /* JADX WARNING: type inference failed for: r4v12, types: [com.google.android.gms.internal.ads.zzauu] */
    /* JADX WARNING: type inference failed for: r4v13 */
    /* JADX WARNING: type inference failed for: r4v14 */
    /* JADX WARNING: type inference failed for: r4v15 */
    /* JADX WARNING: type inference failed for: r4v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v2
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.internal.ads.zzauv, com.google.android.gms.internal.ads.zzavd, com.google.android.gms.internal.ads.zzavb, com.google.android.gms.internal.ads.zzauu]
  uses: [com.google.android.gms.internal.ads.zzavb, com.google.android.gms.internal.ads.zzauu]
  mth insns count: 86
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            r4 = 34
            if (r1 == r4) goto L_0x0111
            r4 = 0
            switch(r1) {
                case 1: goto L_0x0102;
                case 2: goto L_0x00fb;
                case 3: goto L_0x00da;
                default: goto L_0x0008;
            }
        L_0x0008:
            switch(r1) {
                case 5: goto L_0x00cf;
                case 6: goto L_0x00c8;
                case 7: goto L_0x00c1;
                case 8: goto L_0x00ba;
                case 9: goto L_0x00ab;
                case 10: goto L_0x009b;
                case 11: goto L_0x008b;
                case 12: goto L_0x007f;
                case 13: goto L_0x0073;
                case 14: goto L_0x0063;
                case 15: goto L_0x0057;
                case 16: goto L_0x0035;
                case 17: goto L_0x0029;
                case 18: goto L_0x0019;
                case 19: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r1 = 0
            return r1
        L_0x000d:
            java.lang.String r1 = r2.readString()
            r0.setCustomData(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x0019:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzd(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x0029:
            java.lang.String r1 = r2.readString()
            r0.setAppPackageName(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x0035:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x003c
            goto L_0x004f
        L_0x003c:
            java.lang.String r2 = "com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzauu
            if (r4 == 0) goto L_0x004a
            r4 = r2
            com.google.android.gms.internal.ads.zzauu r4 = (com.google.android.gms.internal.ads.zzauu) r4
            goto L_0x004f
        L_0x004a:
            com.google.android.gms.internal.ads.zzauv r4 = new com.google.android.gms.internal.ads.zzauv
            r4.<init>(r1)
        L_0x004f:
            r0.zza(r4)
            r3.writeNoException()
            goto L_0x011b
        L_0x0057:
            android.os.Bundle r1 = r0.getAdMetadata()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzey.zzb(r3, r1)
            goto L_0x011b
        L_0x0063:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzxq r1 = com.google.android.gms.internal.ads.zzxr.zzc(r1)
            r0.zza(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x0073:
            java.lang.String r1 = r2.readString()
            r0.setUserId(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x007f:
            java.lang.String r1 = r0.getMediationAdapterClassName()
            r3.writeNoException()
            r3.writeString(r1)
            goto L_0x011b
        L_0x008b:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzg(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x009b:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzf(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x00ab:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zze(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x00ba:
            r0.destroy()
            r3.writeNoException()
            goto L_0x011b
        L_0x00c1:
            r0.resume()
            r3.writeNoException()
            goto L_0x011b
        L_0x00c8:
            r0.pause()
            r3.writeNoException()
            goto L_0x011b
        L_0x00cf:
            boolean r1 = r0.isLoaded()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzey.writeBoolean(r3, r1)
            goto L_0x011b
        L_0x00da:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x00e1
            goto L_0x00f4
        L_0x00e1:
            java.lang.String r2 = "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzavb
            if (r4 == 0) goto L_0x00ef
            r4 = r2
            com.google.android.gms.internal.ads.zzavb r4 = (com.google.android.gms.internal.ads.zzavb) r4
            goto L_0x00f4
        L_0x00ef:
            com.google.android.gms.internal.ads.zzavd r4 = new com.google.android.gms.internal.ads.zzavd
            r4.<init>(r1)
        L_0x00f4:
            r0.zza(r4)
            r3.writeNoException()
            goto L_0x011b
        L_0x00fb:
            r0.show()
            r3.writeNoException()
            goto L_0x011b
        L_0x0102:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzavh> r1 = com.google.android.gms.internal.ads.zzavh.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzey.zza(r2, r1)
            com.google.android.gms.internal.ads.zzavh r1 = (com.google.android.gms.internal.ads.zzavh) r1
            r0.zza(r1)
            r3.writeNoException()
            goto L_0x011b
        L_0x0111:
            boolean r1 = com.google.android.gms.internal.ads.zzey.zza(r2)
            r0.setImmersiveMode(r1)
            r3.writeNoException()
        L_0x011b:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaux.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
