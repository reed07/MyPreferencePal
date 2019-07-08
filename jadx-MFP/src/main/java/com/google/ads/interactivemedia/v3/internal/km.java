package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.internal.js.a;
import com.google.android.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* compiled from: IMASDK */
public final class km {
    public static final int a = vf.h("ID3");
    private final kn b;

    public km() {
        this(null);
    }

    private static String a(int i) {
        switch (i) {
            case 1:
                return C.UTF16_NAME;
            case 2:
                return "UTF-16BE";
            case 3:
                return "UTF-8";
            default:
                return "ISO-8859-1";
        }
    }

    private static int b(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    public km(kn knVar) {
        this.b = knVar;
    }

    public final js a(byte[] bArr, int i) {
        ko koVar;
        ArrayList arrayList = new ArrayList();
        ut utVar = new ut(bArr, i);
        int i2 = 10;
        boolean z = false;
        if (utVar.b() < 10) {
            Log.w("Id3Decoder", "Data too short to be an ID3 tag");
            koVar = null;
        } else {
            int i3 = utVar.i();
            if (i3 != a) {
                StringBuilder sb = new StringBuilder(59);
                sb.append("Unexpected first three bytes of ID3 tag header: ");
                sb.append(i3);
                Log.w("Id3Decoder", sb.toString());
                koVar = null;
            } else {
                int e = utVar.e();
                utVar.d(1);
                int e2 = utVar.e();
                int o = utVar.o();
                if (e == 2) {
                    if ((e2 & 64) != 0) {
                        Log.w("Id3Decoder", "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                        koVar = null;
                    }
                } else if (e == 3) {
                    if ((e2 & 64) != 0) {
                        int l = utVar.l();
                        utVar.d(l);
                        o -= l + 4;
                    }
                } else if (e == 4) {
                    if ((e2 & 64) != 0) {
                        int o2 = utVar.o();
                        utVar.d(o2 - 4);
                        o -= o2;
                    }
                    if ((e2 & 16) != 0) {
                        o -= 10;
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder(57);
                    sb2.append("Skipped ID3 tag with unsupported majorVersion=");
                    sb2.append(e);
                    Log.w("Id3Decoder", sb2.toString());
                    koVar = null;
                }
                koVar = new ko(e, e < 4 && (e2 & 128) != 0, o);
            }
        }
        if (koVar == null) {
            return null;
        }
        int d = utVar.d();
        if (koVar.a == 2) {
            i2 = 6;
        }
        int b2 = koVar.c;
        if (koVar.b) {
            b2 = a(utVar, koVar.c);
        }
        utVar.b(d + b2);
        if (!a(utVar, koVar.a, i2, false)) {
            if (koVar.a != 4 || !a(utVar, 4, i2, true)) {
                int a2 = koVar.a;
                StringBuilder sb3 = new StringBuilder(56);
                sb3.append("Failed to validate ID3 tag with majorVersion=");
                sb3.append(a2);
                Log.w("Id3Decoder", sb3.toString());
                return null;
            }
            z = true;
        }
        while (utVar.b() >= i2) {
            kp a3 = a(koVar.a, utVar, z, i2, this.b);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        return new js((List<? extends a>) arrayList);
    }

    private static boolean a(ut utVar, int i, int i2, boolean z) {
        int i3;
        long j;
        int i4;
        boolean z2;
        boolean z3;
        ut utVar2 = utVar;
        int i5 = i;
        int d = utVar.d();
        while (true) {
            try {
                int i6 = 1;
                if (utVar.b() >= i2) {
                    if (i5 >= 3) {
                        i4 = utVar.l();
                        j = utVar.j();
                        i3 = utVar.f();
                    } else {
                        i4 = utVar.i();
                        j = (long) utVar.i();
                        i3 = 0;
                    }
                    if (i4 == 0 && j == 0 && i3 == 0) {
                        utVar2.c(d);
                        return true;
                    }
                    if (i5 == 4 && !z) {
                        if ((8421504 & j) != 0) {
                            utVar2.c(d);
                            return false;
                        }
                        j = (((j >> 24) & 255) << 21) | (j & 255) | (((j >> 8) & 255) << 7) | (((j >> 16) & 255) << 14);
                    }
                    if (i5 == 4) {
                        z3 = (i3 & 64) != 0;
                        z2 = (i3 & 1) != 0;
                    } else if (i5 == 3) {
                        z3 = (i3 & 32) != 0;
                        z2 = (i3 & 128) != 0;
                    } else {
                        z3 = false;
                        z2 = false;
                    }
                    if (!z3) {
                        i6 = 0;
                    }
                    if (z2) {
                        i6 += 4;
                    }
                    if (j < ((long) i6)) {
                        utVar2.c(d);
                        return false;
                    } else if (((long) utVar.b()) < j) {
                        return false;
                    } else {
                        utVar2.d((int) j);
                    }
                } else {
                    utVar2.c(d);
                    return true;
                }
            } finally {
                utVar2.c(d);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r14v1, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r5v2, types: [com.google.ads.interactivemedia.v3.internal.kp] */
    /* JADX WARNING: type inference failed for: r5v5, types: [com.google.ads.interactivemedia.v3.internal.kc] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.google.ads.interactivemedia.v3.internal.ks] */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r20v0, types: [com.google.ads.interactivemedia.v3.internal.kg] */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: type inference failed for: r20v1, types: [com.google.ads.interactivemedia.v3.internal.ke] */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r2v18, types: [com.google.ads.interactivemedia.v3.internal.ki] */
    /* JADX WARNING: type inference failed for: r5v22 */
    /* JADX WARNING: type inference failed for: r5v23 */
    /* JADX WARNING: type inference failed for: r5v25, types: [com.google.ads.interactivemedia.v3.internal.ka] */
    /* JADX WARNING: type inference failed for: r5v27, types: [com.google.ads.interactivemedia.v3.internal.kk] */
    /* JADX WARNING: type inference failed for: r2v26, types: [com.google.ads.interactivemedia.v3.internal.ku] */
    /* JADX WARNING: type inference failed for: r5v29 */
    /* JADX WARNING: type inference failed for: r2v28, types: [com.google.ads.interactivemedia.v3.internal.ky] */
    /* JADX WARNING: type inference failed for: r5v31 */
    /* JADX WARNING: type inference failed for: r5v32 */
    /* JADX WARNING: type inference failed for: r5v34, types: [com.google.ads.interactivemedia.v3.internal.ky] */
    /* JADX WARNING: type inference failed for: r5v35 */
    /* JADX WARNING: type inference failed for: r5v36 */
    /* JADX WARNING: type inference failed for: r5v38, types: [com.google.ads.interactivemedia.v3.internal.kw] */
    /* JADX WARNING: type inference failed for: r5v39 */
    /* JADX WARNING: type inference failed for: r5v40 */
    /* JADX WARNING: type inference failed for: r5v43, types: [com.google.ads.interactivemedia.v3.internal.kw] */
    /* JADX WARNING: type inference failed for: r5v44 */
    /* JADX WARNING: type inference failed for: r14v10 */
    /* JADX WARNING: type inference failed for: r14v11 */
    /* JADX WARNING: type inference failed for: r5v52 */
    /* JADX WARNING: type inference failed for: r5v53 */
    /* JADX WARNING: type inference failed for: r5v54 */
    /* JADX WARNING: type inference failed for: r5v55 */
    /* JADX WARNING: type inference failed for: r5v56 */
    /* JADX WARNING: type inference failed for: r5v57 */
    /* JADX WARNING: type inference failed for: r5v58 */
    /* JADX WARNING: type inference failed for: r5v59 */
    /* JADX WARNING: type inference failed for: r5v60 */
    /* JADX WARNING: type inference failed for: r5v61 */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x027d, code lost:
        if (r13 == 67) goto L_0x027f;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v32
  assigns: []
  uses: []
  mth insns count: 529
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
    /* JADX WARNING: Unknown variable types count: 17 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.ads.interactivemedia.v3.internal.kp a(int r29, com.google.ads.interactivemedia.v3.internal.ut r30, boolean r31, int r32, com.google.ads.interactivemedia.v3.internal.kn r33) {
        /*
            r0 = r29
            r7 = r30
            int r8 = r30.e()
            int r9 = r30.e()
            int r10 = r30.e()
            r11 = 3
            if (r0 < r11) goto L_0x0019
            int r1 = r30.e()
            r13 = r1
            goto L_0x001a
        L_0x0019:
            r13 = 0
        L_0x001a:
            r14 = 4
            if (r0 != r14) goto L_0x003c
            int r1 = r30.p()
            if (r31 != 0) goto L_0x003a
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >>> 24
            int r1 = r1 << 21
            r1 = r1 | r2
            r15 = r1
            goto L_0x0049
        L_0x003a:
            r15 = r1
            goto L_0x0049
        L_0x003c:
            if (r0 != r11) goto L_0x0044
            int r1 = r30.p()
            r15 = r1
            goto L_0x0049
        L_0x0044:
            int r1 = r30.i()
            r15 = r1
        L_0x0049:
            if (r0 < r11) goto L_0x0051
            int r1 = r30.f()
            r6 = r1
            goto L_0x0052
        L_0x0051:
            r6 = 0
        L_0x0052:
            r5 = 0
            if (r8 != 0) goto L_0x0067
            if (r9 != 0) goto L_0x0067
            if (r10 != 0) goto L_0x0067
            if (r13 != 0) goto L_0x0067
            if (r15 != 0) goto L_0x0067
            if (r6 != 0) goto L_0x0067
            int r0 = r30.c()
            r7.c(r0)
            return r5
        L_0x0067:
            int r1 = r30.d()
            int r4 = r1 + r15
            int r1 = r30.c()
            if (r4 <= r1) goto L_0x0082
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r1 = "Frame size exceeds remaining tag data"
            android.util.Log.w(r0, r1)
            int r0 = r30.c()
            r7.c(r0)
            return r5
        L_0x0082:
            if (r33 == 0) goto L_0x009a
            r1 = r33
            r2 = r29
            r3 = r8
            r12 = r4
            r4 = r9
            r14 = r5
            r5 = r10
            r18 = r6
            r6 = r13
            boolean r1 = r1.a(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x009e
            r7.c(r12)
            return r14
        L_0x009a:
            r12 = r4
            r14 = r5
            r18 = r6
        L_0x009e:
            r1 = 1
            if (r0 != r11) goto L_0x00bc
            r2 = r18
            r3 = r2 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x00a9
            r3 = 1
            goto L_0x00aa
        L_0x00a9:
            r3 = 0
        L_0x00aa:
            r4 = r2 & 64
            if (r4 == 0) goto L_0x00b0
            r4 = 1
            goto L_0x00b1
        L_0x00b0:
            r4 = 0
        L_0x00b1:
            r2 = r2 & 32
            if (r2 == 0) goto L_0x00b7
            r2 = 1
            goto L_0x00b8
        L_0x00b7:
            r2 = 0
        L_0x00b8:
            r5 = r4
            r6 = 0
            r4 = r3
            goto L_0x00eb
        L_0x00bc:
            r2 = r18
            r3 = 4
            if (r0 != r3) goto L_0x00e6
            r3 = r2 & 64
            if (r3 == 0) goto L_0x00c7
            r3 = 1
            goto L_0x00c8
        L_0x00c7:
            r3 = 0
        L_0x00c8:
            r4 = r2 & 8
            if (r4 == 0) goto L_0x00ce
            r4 = 1
            goto L_0x00cf
        L_0x00ce:
            r4 = 0
        L_0x00cf:
            r5 = r2 & 4
            if (r5 == 0) goto L_0x00d5
            r5 = 1
            goto L_0x00d6
        L_0x00d5:
            r5 = 0
        L_0x00d6:
            r6 = r2 & 2
            if (r6 == 0) goto L_0x00dc
            r6 = 1
            goto L_0x00dd
        L_0x00dc:
            r6 = 0
        L_0x00dd:
            r2 = r2 & r1
            if (r2 == 0) goto L_0x00e3
            r2 = r3
            r3 = 1
            goto L_0x00eb
        L_0x00e3:
            r2 = r3
            r3 = 0
            goto L_0x00eb
        L_0x00e6:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x00eb:
            if (r4 != 0) goto L_0x053d
            if (r5 == 0) goto L_0x00f1
            goto L_0x053d
        L_0x00f1:
            if (r2 == 0) goto L_0x00f8
            int r15 = r15 + -1
            r7.d(r1)
        L_0x00f8:
            if (r3 == 0) goto L_0x0100
            int r15 = r15 + -4
            r2 = 4
            r7.d(r2)
        L_0x0100:
            if (r6 == 0) goto L_0x0106
            int r15 = a(r7, r15)
        L_0x0106:
            r2 = 84
            r3 = 88
            r4 = 2
            if (r8 != r2) goto L_0x014a
            if (r9 != r3) goto L_0x014a
            if (r10 != r3) goto L_0x014a
            if (r0 == r4) goto L_0x0115
            if (r13 != r3) goto L_0x014a
        L_0x0115:
            if (r15 > 0) goto L_0x0119
            r5 = r14
            goto L_0x0146
        L_0x0119:
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = a(r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = r15 + -1
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5 = 0
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = a(r4, r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6.<init>(r4, r5, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r5 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = r3 + r5
            int r1 = a(r4, r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r1 = a(r4, r3, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.kw r5 = new com.google.ads.interactivemedia.v3.internal.kw     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = "TXXX"
            r5.<init>(r2, r6, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
        L_0x0146:
            r19 = r12
            goto L_0x04ee
        L_0x014a:
            if (r8 != r2) goto L_0x017d
            java.lang.String r1 = a(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            if (r15 > 0) goto L_0x0154
            r5 = r14
            goto L_0x0172
        L_0x0154:
            int r2 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r3 = a(r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = r15 + -1
            byte[] r5 = new byte[r4]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6 = 0
            r7.a(r5, r6, r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r2 = a(r5, r6, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r4.<init>(r5, r6, r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.kw r5 = new com.google.ads.interactivemedia.v3.internal.kw     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5.<init>(r1, r14, r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
        L_0x0172:
            r19 = r12
            goto L_0x04ee
        L_0x0176:
            r0 = move-exception
            r1 = r12
            goto L_0x0539
        L_0x017a:
            r1 = r12
            goto L_0x052c
        L_0x017d:
            r5 = 87
            if (r8 != r5) goto L_0x01c0
            if (r9 != r3) goto L_0x01c0
            if (r10 != r3) goto L_0x01c0
            if (r0 == r4) goto L_0x0189
            if (r13 != r3) goto L_0x01c0
        L_0x0189:
            if (r15 > 0) goto L_0x018d
            r5 = r14
            goto L_0x01bc
        L_0x018d:
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = a(r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = r15 + -1
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5 = 0
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = a(r4, r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6.<init>(r4, r5, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r1 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = r3 + r1
            int r1 = b(r4, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = "ISO-8859-1"
            java.lang.String r1 = a(r4, r3, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.ky r5 = new com.google.ads.interactivemedia.v3.internal.ky     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = "WXXX"
            r5.<init>(r2, r6, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
        L_0x01bc:
            r19 = r12
            goto L_0x04ee
        L_0x01c0:
            r3 = 87
            if (r8 != r3) goto L_0x01e3
            java.lang.String r1 = a(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = b(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r6 = "ISO-8859-1"
            r5.<init>(r2, r3, r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.ky r2 = new com.google.ads.interactivemedia.v3.internal.ky     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r2.<init>(r1, r14, r5)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5 = r2
            r19 = r12
            goto L_0x04ee
        L_0x01e3:
            r3 = 73
            r5 = 80
            if (r8 != r5) goto L_0x0214
            r6 = 82
            if (r9 != r6) goto L_0x0214
            if (r10 != r3) goto L_0x0214
            r6 = 86
            if (r13 != r6) goto L_0x0214
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = b(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r6 = "ISO-8859-1"
            r5.<init>(r2, r3, r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = r4 + r1
            int r1 = r2.length     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            byte[] r1 = b(r2, r4, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.ku r2 = new com.google.ads.interactivemedia.v3.internal.ku     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r2.<init>(r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5 = r2
            r19 = r12
            goto L_0x04ee
        L_0x0214:
            r6 = 71
            r14 = 79
            if (r8 != r6) goto L_0x026a
            r6 = 69
            if (r9 != r6) goto L_0x026a
            if (r10 != r14) goto L_0x026a
            r6 = 66
            if (r13 == r6) goto L_0x0226
            if (r0 != r4) goto L_0x026a
        L_0x0226:
            int r2 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r3 = a(r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = r15 + -1
            byte[] r5 = new byte[r4]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6 = 0
            r7.a(r5, r6, r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = b(r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r11 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r14 = "ISO-8859-1"
            r11.<init>(r5, r6, r4, r14)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r4 = r4 + r1
            int r1 = a(r5, r4, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r4 = a(r5, r4, r1, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r6 = b(r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r1 = r1 + r6
            int r6 = a(r5, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r1 = a(r5, r1, r6, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r2 = b(r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r6 = r6 + r2
            int r2 = r5.length     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            byte[] r2 = b(r5, r6, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            com.google.ads.interactivemedia.v3.internal.kk r5 = new com.google.ads.interactivemedia.v3.internal.kk     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r5.<init>(r11, r4, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r19 = r12
            goto L_0x04ee
        L_0x026a:
            r6 = 67
            if (r0 != r4) goto L_0x0275
            if (r8 != r5) goto L_0x0316
            if (r9 != r3) goto L_0x0316
            if (r10 != r6) goto L_0x0316
            goto L_0x027f
        L_0x0275:
            r2 = 65
            if (r8 != r2) goto L_0x0316
            if (r9 != r5) goto L_0x0316
            if (r10 != r3) goto L_0x0316
            if (r13 != r6) goto L_0x0316
        L_0x027f:
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r2 = a(r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r3 = r15 + -1
            byte[] r5 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6 = 0
            r7.a(r5, r6, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            if (r0 != r4) goto L_0x02c1
            java.lang.String r3 = "image/"
            java.lang.String r14 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r4 = "ISO-8859-1"
            r14.<init>(r5, r6, r11, r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r4 = com.google.ads.interactivemedia.v3.internal.vf.d(r14)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r6 = r4.length()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            if (r6 == 0) goto L_0x02ad
            java.lang.String r3 = r3.concat(r4)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            goto L_0x02b3
        L_0x02ad:
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r4.<init>(r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r3 = r4
        L_0x02b3:
            java.lang.String r4 = "image/jpg"
            boolean r4 = r4.equals(r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            if (r4 == 0) goto L_0x02bf
            java.lang.String r3 = "image/jpeg"
            r4 = 2
            goto L_0x02f0
        L_0x02bf:
            r4 = 2
            goto L_0x02f0
        L_0x02c1:
            r3 = 0
            int r4 = b(r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r11 = "ISO-8859-1"
            r6.<init>(r5, r3, r4, r11)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r3 = com.google.ads.interactivemedia.v3.internal.vf.d(r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6 = 47
            int r6 = r3.indexOf(r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r11 = -1
            if (r6 != r11) goto L_0x02f0
            java.lang.String r6 = "image/"
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            int r11 = r3.length()     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            if (r11 == 0) goto L_0x02eb
            java.lang.String r3 = r6.concat(r3)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            goto L_0x02f0
        L_0x02eb:
            java.lang.String r3 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r3.<init>(r6)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
        L_0x02f0:
            int r6 = r4 + 1
            byte r6 = r5[r6]     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r11 = 2
            int r4 = r4 + r11
            int r11 = a(r5, r4, r1)     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            java.lang.String r14 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x017a, all -> 0x0176 }
            r19 = r12
            int r12 = r11 - r4
            r14.<init>(r5, r4, r12, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r1 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r11 = r11 + r1
            int r1 = r5.length     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r1 = b(r5, r11, r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.ka r5 = new com.google.ads.interactivemedia.v3.internal.ka     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5.<init>(r3, r14, r6, r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            goto L_0x04ee
        L_0x0316:
            r19 = r12
            r2 = 77
            if (r8 != r6) goto L_0x0363
            if (r9 != r14) goto L_0x0363
            if (r10 != r2) goto L_0x0363
            if (r13 == r2) goto L_0x0325
            r3 = 2
            if (r0 != r3) goto L_0x0363
        L_0x0325:
            r1 = 4
            if (r15 >= r1) goto L_0x032b
            r5 = 0
            goto L_0x04ee
        L_0x032b:
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r2 = a(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r3 = new byte[r11]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r4 = 0
            r7.a(r3, r4, r11)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5.<init>(r3, r4, r11)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r15 + -4
            byte[] r6 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r7.a(r6, r4, r3)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = a(r6, r4, r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r11 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r11.<init>(r6, r4, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r4 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r3 + r4
            int r1 = a(r6, r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r1 = a(r6, r3, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.ki r2 = new com.google.ads.interactivemedia.v3.internal.ki     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r2.<init>(r5, r11, r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = r2
            goto L_0x04ee
        L_0x0363:
            if (r8 != r6) goto L_0x03e5
            r3 = 72
            if (r9 != r3) goto L_0x03e5
            r3 = 65
            if (r10 != r3) goto L_0x03e5
            if (r13 != r5) goto L_0x03e5
            int r2 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r3 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = b(r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r5 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r6 = r3 - r2
            java.lang.String r11 = "ISO-8859-1"
            r4.<init>(r5, r2, r6, r11)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r3 + r1
            r7.c(r3)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r22 = r30.l()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r23 = r30.l()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            long r5 = r30.j()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r11 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r1 != 0) goto L_0x03a2
            r5 = -1
            r24 = r5
            goto L_0x03a4
        L_0x03a2:
            r24 = r5
        L_0x03a4:
            long r5 = r30.j()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r11 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r1 != 0) goto L_0x03b6
            r5 = -1
            r26 = r5
            goto L_0x03b8
        L_0x03b6:
            r26 = r5
        L_0x03b8:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r1.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r2 = r2 + r15
        L_0x03be:
            int r3 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            if (r3 >= r2) goto L_0x03ce
            com.google.ads.interactivemedia.v3.internal.kp r3 = a(r29, r30, r31, r32, r33)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            if (r3 == 0) goto L_0x03be
            r1.add(r3)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            goto L_0x03be
        L_0x03ce:
            int r2 = r1.size()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.kp[] r2 = new com.google.ads.interactivemedia.v3.internal.kp[r2]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r1.toArray(r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.ke r1 = new com.google.ads.interactivemedia.v3.internal.ke     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r20 = r1
            r21 = r4
            r28 = r2
            r20.<init>(r21, r22, r23, r24, r26, r28)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = r1
            goto L_0x04ee
        L_0x03e5:
            if (r8 != r6) goto L_0x047c
            r3 = 84
            if (r9 != r3) goto L_0x047c
            if (r10 != r14) goto L_0x047c
            if (r13 != r6) goto L_0x047c
            int r2 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r3 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = b(r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r5 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r6 = r3 - r2
            java.lang.String r11 = "ISO-8859-1"
            r4.<init>(r5, r2, r6, r11)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r3 + r1
            r7.c(r3)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = r3 & 2
            if (r5 == 0) goto L_0x0413
            r22 = 1
            goto L_0x0415
        L_0x0413:
            r22 = 0
        L_0x0415:
            r3 = r3 & r1
            if (r3 == 0) goto L_0x041b
            r23 = 1
            goto L_0x041d
        L_0x041b:
            r23 = 0
        L_0x041d:
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = 0
        L_0x0424:
            if (r5 >= r1) goto L_0x044b
            int r6 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r11 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r11 = b(r11, r6)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r12 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r14 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r17 = r1
            int r1 = r11 - r6
            java.lang.String r0 = "ISO-8859-1"
            r12.<init>(r14, r6, r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r3[r5] = r12     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r11 = r11 + 1
            r7.c(r11)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r5 = r5 + 1
            r1 = r17
            r0 = r29
            goto L_0x0424
        L_0x044b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r0.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r2 = r2 + r15
        L_0x0451:
            int r1 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            if (r1 >= r2) goto L_0x0461
            com.google.ads.interactivemedia.v3.internal.kp r1 = a(r29, r30, r31, r32, r33)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            if (r1 == 0) goto L_0x0451
            r0.add(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            goto L_0x0451
        L_0x0461:
            int r1 = r0.size()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.kp[] r1 = new com.google.ads.interactivemedia.v3.internal.kp[r1]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r0.toArray(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.kg r0 = new com.google.ads.interactivemedia.v3.internal.kg     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r20 = r0
            r21 = r4
            r24 = r3
            r25 = r1
            r20.<init>(r21, r22, r23, r24, r25)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = r0
            r0 = r29
            goto L_0x04ee
        L_0x047c:
            if (r8 != r2) goto L_0x04dd
            r0 = 76
            if (r9 != r0) goto L_0x04dd
            r0 = 76
            if (r10 != r0) goto L_0x04dd
            r0 = 84
            if (r13 != r0) goto L_0x04dd
            int r2 = r30.f()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r3 = r30.i()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r4 = r30.i()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r0 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r1 = r30.e()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.us r5 = new com.google.ads.interactivemedia.v3.internal.us     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r6 = r7.a     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r12 = r30.c()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5.a(r6, r12)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r6 = r30.d()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r6 = r6 << r11
            r5.a(r6)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r6 = r15 + -10
            int r6 = r6 * 8
            int r11 = r0 + r1
            int r6 = r6 / r11
            int[] r11 = new int[r6]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int[] r12 = new int[r6]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r14 = 0
        L_0x04c0:
            if (r14 >= r6) goto L_0x04d1
            int r16 = r5.c(r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r17 = r5.c(r1)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r11[r14] = r16     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r12[r14] = r17     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r14 = r14 + 1
            goto L_0x04c0
        L_0x04d1:
            com.google.ads.interactivemedia.v3.internal.ks r0 = new com.google.ads.interactivemedia.v3.internal.ks     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r1 = r0
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5 = r0
            r0 = r29
            goto L_0x04ee
        L_0x04dd:
            r0 = r29
            java.lang.String r1 = a(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            com.google.ads.interactivemedia.v3.internal.kc r5 = new com.google.ads.interactivemedia.v3.internal.kc     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r5.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
        L_0x04ee:
            if (r5 != 0) goto L_0x0526
            java.lang.String r1 = "Id3Decoder"
            java.lang.String r0 = a(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r2 = r2.length()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            int r2 = r2 + 50
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r3.<init>(r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r2 = "Failed to decode frame: id="
            r3.append(r2)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r3.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r0 = ", frameSize="
            r3.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r3.append(r15)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            java.lang.String r0 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            android.util.Log.w(r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x0523, all -> 0x051f }
            r1 = r19
            goto L_0x0528
        L_0x051f:
            r0 = move-exception
            r1 = r19
            goto L_0x0539
        L_0x0523:
            r1 = r19
            goto L_0x052c
        L_0x0526:
            r1 = r19
        L_0x0528:
            r7.c(r1)
            return r5
        L_0x052c:
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r2 = "Unsupported character encoding"
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x0538 }
            r7.c(r1)
            r0 = 0
            return r0
        L_0x0538:
            r0 = move-exception
        L_0x0539:
            r7.c(r1)
            throw r0
        L_0x053d:
            r1 = r12
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r2 = "Skipping unsupported compressed or encrypted frame"
            android.util.Log.w(r0, r2)
            r7.c(r1)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.km.a(int, com.google.ads.interactivemedia.v3.internal.ut, boolean, int, com.google.ads.interactivemedia.v3.internal.kn):com.google.ads.interactivemedia.v3.internal.kp");
    }

    private static int a(ut utVar, int i) {
        byte[] bArr = utVar.a;
        int d = utVar.d();
        while (true) {
            int i2 = d + 1;
            if (i2 >= i) {
                return i;
            }
            if ((bArr[d] & 255) == 255 && bArr[i2] == 0) {
                System.arraycopy(bArr, d + 2, bArr, i2, (i - d) - 2);
                i--;
            }
            d = i2;
        }
    }

    private static String a(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static int a(byte[] bArr, int i, int i2) {
        int b2 = b(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return b2;
        }
        while (b2 < bArr.length - 1) {
            if (b2 % 2 == 0 && bArr[b2 + 1] == 0) {
                return b2;
            }
            b2 = b(bArr, b2 + 1);
        }
        return bArr.length;
    }

    private static int b(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static byte[] b(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return vf.f;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static String a(byte[] bArr, int i, int i2, String str) throws UnsupportedEncodingException {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, str);
    }
}
