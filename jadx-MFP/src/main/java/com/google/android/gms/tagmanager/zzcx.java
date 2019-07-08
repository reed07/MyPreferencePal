package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import java.util.Set;

final class zzcx extends zzbq {
    private static final String ID = zza.JOINER.toString();
    private static final String zzbcf = zzb.ARG0.toString();
    private static final String zzbcy = zzb.ITEM_SEPARATOR.toString();
    private static final String zzbcz = zzb.KEY_VALUE_SEPARATOR.toString();
    private static final String zzbda = zzb.ESCAPE.toString();

    public zzcx() {
        super(ID, zzbcf);
    }

    public final boolean zznk() {
        return true;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzp zzc(java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzp> r10) {
        /*
            r9 = this;
            java.lang.String r0 = zzbcf
            java.lang.Object r0 = r10.get(r0)
            com.google.android.gms.internal.measurement.zzp r0 = (com.google.android.gms.internal.measurement.zzp) r0
            if (r0 != 0) goto L_0x000f
            com.google.android.gms.internal.measurement.zzp r10 = com.google.android.gms.tagmanager.zzgj.zzqq()
            return r10
        L_0x000f:
            java.lang.String r1 = zzbcy
            java.lang.Object r1 = r10.get(r1)
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = com.google.android.gms.tagmanager.zzgj.zzc(r1)
            goto L_0x0020
        L_0x001e:
            java.lang.String r1 = ""
        L_0x0020:
            java.lang.String r2 = zzbcz
            java.lang.Object r2 = r10.get(r2)
            com.google.android.gms.internal.measurement.zzp r2 = (com.google.android.gms.internal.measurement.zzp) r2
            if (r2 == 0) goto L_0x002f
            java.lang.String r2 = com.google.android.gms.tagmanager.zzgj.zzc(r2)
            goto L_0x0031
        L_0x002f:
            java.lang.String r2 = "="
        L_0x0031:
            int r3 = com.google.android.gms.tagmanager.zzcz.zzbdc
            java.lang.String r4 = zzbda
            java.lang.Object r10 = r10.get(r4)
            com.google.android.gms.internal.measurement.zzp r10 = (com.google.android.gms.internal.measurement.zzp) r10
            r4 = 0
            if (r10 == 0) goto L_0x008a
            java.lang.String r10 = com.google.android.gms.tagmanager.zzgj.zzc(r10)
            java.lang.String r3 = "url"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x004d
            int r3 = com.google.android.gms.tagmanager.zzcz.zzbdd
            goto L_0x008a
        L_0x004d:
            java.lang.String r3 = "backslash"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x006c
            int r3 = com.google.android.gms.tagmanager.zzcz.zzbde
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            zza(r4, r1)
            zza(r4, r2)
            r10 = 92
            java.lang.Character r10 = java.lang.Character.valueOf(r10)
            r4.remove(r10)
            goto L_0x008a
        L_0x006c:
            java.lang.String r0 = "Joiner: unsupported escape type: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r1 = r10.length()
            if (r1 == 0) goto L_0x007d
            java.lang.String r10 = r0.concat(r10)
            goto L_0x0082
        L_0x007d:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0)
        L_0x0082:
            com.google.android.gms.tagmanager.zzdi.e(r10)
            com.google.android.gms.internal.measurement.zzp r10 = com.google.android.gms.tagmanager.zzgj.zzqq()
            return r10
        L_0x008a:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            int r5 = r0.type
            r6 = 0
            switch(r5) {
                case 2: goto L_0x00c3;
                case 3: goto L_0x009d;
                default: goto L_0x0095;
            }
        L_0x0095:
            java.lang.String r0 = com.google.android.gms.tagmanager.zzgj.zzc(r0)
            zza(r10, r0, r3, r4)
            goto L_0x00dd
        L_0x009d:
            com.google.android.gms.internal.measurement.zzp[] r5 = r0.zzqk
            int r5 = r5.length
            if (r6 >= r5) goto L_0x00dd
            if (r6 <= 0) goto L_0x00a7
            r10.append(r1)
        L_0x00a7:
            com.google.android.gms.internal.measurement.zzp[] r5 = r0.zzqk
            r5 = r5[r6]
            java.lang.String r5 = com.google.android.gms.tagmanager.zzgj.zzc(r5)
            com.google.android.gms.internal.measurement.zzp[] r7 = r0.zzql
            r7 = r7[r6]
            java.lang.String r7 = com.google.android.gms.tagmanager.zzgj.zzc(r7)
            zza(r10, r5, r3, r4)
            r10.append(r2)
            zza(r10, r7, r3, r4)
            int r6 = r6 + 1
            goto L_0x009d
        L_0x00c3:
            com.google.android.gms.internal.measurement.zzp[] r0 = r0.zzqj
            int r2 = r0.length
            r5 = 1
            r5 = 0
            r7 = 1
        L_0x00c9:
            if (r5 >= r2) goto L_0x00dd
            r8 = r0[r5]
            if (r7 != 0) goto L_0x00d2
            r10.append(r1)
        L_0x00d2:
            java.lang.String r7 = com.google.android.gms.tagmanager.zzgj.zzc(r8)
            zza(r10, r7, r3, r4)
            int r5 = r5 + 1
            r7 = 0
            goto L_0x00c9
        L_0x00dd:
            java.lang.String r10 = r10.toString()
            com.google.android.gms.internal.measurement.zzp r10 = com.google.android.gms.tagmanager.zzgj.zzj(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcx.zzc(java.util.Map):com.google.android.gms.internal.measurement.zzp");
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Integer, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Integer, code=null, for r2v0, types: [java.lang.Integer, int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(java.lang.StringBuilder r0, java.lang.String r1, java.lang.Integer r2, java.util.Set<java.lang.Character> r3) {
        /*
            java.lang.String r1 = zza(r1, r2, r3)
            r0.append(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcx.zza(java.lang.StringBuilder, java.lang.String, int, java.util.Set):void");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Integer, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Integer, code=null, for r4v0, types: [java.lang.Integer, int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zza(java.lang.String r3, java.lang.Integer r4, java.util.Set<java.lang.Character> r5) {
        /*
            int[] r0 = com.google.android.gms.tagmanager.zzcy.zzbdb
            int r4 = r4 + -1
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0043;
                case 2: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            return r3
        L_0x000a:
            java.lang.String r4 = "\\"
            java.lang.String r0 = "\\\\"
            java.lang.String r3 = r3.replace(r4, r0)
            java.util.Iterator r4 = r5.iterator()
        L_0x0016:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0042
            java.lang.Object r5 = r4.next()
            java.lang.Character r5 = (java.lang.Character) r5
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "\\"
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0037
            java.lang.String r0 = r0.concat(r1)
            goto L_0x003d
        L_0x0037:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x003d:
            java.lang.String r3 = r3.replace(r5, r0)
            goto L_0x0016
        L_0x0042:
            return r3
        L_0x0043:
            java.lang.String r3 = com.google.android.gms.tagmanager.zzgn.zzei(r3)     // Catch:{ UnsupportedEncodingException -> 0x0048 }
            return r3
        L_0x0048:
            r4 = move-exception
            java.lang.String r5 = "Joiner: unsupported encoding"
            com.google.android.gms.tagmanager.zzdi.zza(r5, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcx.zza(java.lang.String, int, java.util.Set):java.lang.String");
    }
}
