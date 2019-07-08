package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;

/* compiled from: IMASDK */
public class qe implements ll, mv<qa>, qx {
    private final pt a;
    private final qv b;
    private final ps c;
    private final tz d;
    private final ti e;
    private final lr f;
    private final sf g;
    private final IdentityHashMap<mt, Integer> h = new IdentityHashMap<>();
    private final qg i = new qg();
    private final lh j;
    private final boolean k;
    private lm l;
    private int m;
    private mz n;
    private qa[] o = new qa[0];
    private qa[] p = new qa[0];
    private mu q;
    private boolean r;

    public long a(long j2, cm cmVar) {
        return j2;
    }

    public void f() {
        int i2 = this.m - 1;
        this.m = i2;
        if (i2 <= 0) {
            int i3 = 0;
            for (qa f2 : this.o) {
                i3 += f2.f().b;
            }
            mx[] mxVarArr = new mx[i3];
            qa[] qaVarArr = this.o;
            int length = qaVarArr.length;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                qa qaVar = qaVarArr[i4];
                int i6 = qaVar.f().b;
                int i7 = i5;
                int i8 = 0;
                while (i8 < i6) {
                    int i9 = i7 + 1;
                    mxVarArr[i7] = qaVar.f().a(i8);
                    i8++;
                    i7 = i9;
                }
                i4++;
                i5 = i7;
            }
            this.n = new mz(mxVarArr);
            this.l.a(this);
        }
    }

    public void a(qo qoVar) {
        this.b.c(qoVar);
    }

    public qe(pt ptVar, qv qvVar, ps psVar, tz tzVar, ti tiVar, lr lrVar, sf sfVar, lh lhVar, boolean z) {
        this.a = ptVar;
        this.b = qvVar;
        this.c = psVar;
        this.d = tzVar;
        this.e = tiVar;
        this.f = lrVar;
        this.g = sfVar;
        this.j = lhVar;
        this.k = z;
        this.q = lhVar.a(new mu[0]);
        lrVar.a();
    }

    public void g() {
        this.b.b((qx) this);
        for (qa h2 : this.o) {
            h2.h();
        }
        this.l = null;
        this.f.b();
    }

    public void a(lm lmVar, long j2) {
        this.l = lmVar;
        this.b.a((qx) this);
        d(j2);
    }

    public void a_() throws IOException {
        for (qa c2 : this.o) {
            c2.c();
        }
    }

    public mz b() {
        return this.n;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ea, code lost:
        if (r5 != r8[0]) goto L_0x00ee;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(com.google.ads.interactivemedia.v3.internal.rt[] r21, boolean[] r22, com.google.ads.interactivemedia.v3.internal.mt[] r23, boolean[] r24, long r25) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            int r3 = r1.length
            int[] r3 = new int[r3]
            int r4 = r1.length
            int[] r4 = new int[r4]
            r6 = 0
        L_0x000d:
            int r7 = r1.length
            if (r6 >= r7) goto L_0x004e
            r7 = r2[r6]
            r8 = -1
            if (r7 != 0) goto L_0x0017
            r7 = -1
            goto L_0x0025
        L_0x0017:
            java.util.IdentityHashMap<com.google.ads.interactivemedia.v3.internal.mt, java.lang.Integer> r7 = r0.h
            r9 = r2[r6]
            java.lang.Object r7 = r7.get(r9)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
        L_0x0025:
            r3[r6] = r7
            r4[r6] = r8
            r7 = r1[r6]
            if (r7 == 0) goto L_0x004b
            r7 = r1[r6]
            com.google.ads.interactivemedia.v3.internal.mx r7 = r7.f()
            r9 = 0
        L_0x0034:
            com.google.ads.interactivemedia.v3.internal.qa[] r10 = r0.o
            int r11 = r10.length
            if (r9 >= r11) goto L_0x004b
            r10 = r10[r9]
            com.google.ads.interactivemedia.v3.internal.mz r10 = r10.f()
            int r10 = r10.a(r7)
            if (r10 == r8) goto L_0x0048
            r4[r6] = r9
            goto L_0x004b
        L_0x0048:
            int r9 = r9 + 1
            goto L_0x0034
        L_0x004b:
            int r6 = r6 + 1
            goto L_0x000d
        L_0x004e:
            java.util.IdentityHashMap<com.google.ads.interactivemedia.v3.internal.mt, java.lang.Integer> r6 = r0.h
            r6.clear()
            int r6 = r1.length
            com.google.ads.interactivemedia.v3.internal.mt[] r6 = new com.google.ads.interactivemedia.v3.internal.mt[r6]
            int r7 = r1.length
            com.google.ads.interactivemedia.v3.internal.mt[] r7 = new com.google.ads.interactivemedia.v3.internal.mt[r7]
            int r8 = r1.length
            com.google.ads.interactivemedia.v3.internal.rt[] r15 = new com.google.ads.interactivemedia.v3.internal.rt[r8]
            com.google.ads.interactivemedia.v3.internal.qa[] r8 = r0.o
            int r8 = r8.length
            com.google.ads.interactivemedia.v3.internal.qa[] r13 = new com.google.ads.interactivemedia.v3.internal.qa[r8]
            r12 = 0
            r14 = 0
            r16 = 0
        L_0x0065:
            com.google.ads.interactivemedia.v3.internal.qa[] r8 = r0.o
            int r8 = r8.length
            if (r14 >= r8) goto L_0x010a
            r8 = 0
        L_0x006b:
            int r9 = r1.length
            if (r8 >= r9) goto L_0x0084
            r9 = r3[r8]
            r10 = 0
            if (r9 != r14) goto L_0x0076
            r9 = r2[r8]
            goto L_0x0077
        L_0x0076:
            r9 = r10
        L_0x0077:
            r7[r8] = r9
            r9 = r4[r8]
            if (r9 != r14) goto L_0x007f
            r10 = r1[r8]
        L_0x007f:
            r15[r8] = r10
            int r8 = r8 + 1
            goto L_0x006b
        L_0x0084:
            com.google.ads.interactivemedia.v3.internal.qa[] r8 = r0.o
            r11 = r8[r14]
            r8 = r11
            r9 = r15
            r10 = r22
            r5 = r11
            r11 = r7
            r2 = r12
            r12 = r24
            r17 = r2
            r18 = r13
            r2 = r14
            r13 = r25
            r19 = r15
            r15 = r16
            boolean r8 = r8.a(r9, r10, r11, r12, r13, r15)
            r9 = 0
            r10 = 0
        L_0x00a2:
            int r11 = r1.length
            r12 = 1
            if (r9 >= r11) goto L_0x00d5
            r11 = r4[r9]
            if (r11 != r2) goto L_0x00c5
            r10 = r7[r9]
            if (r10 == 0) goto L_0x00b0
            r10 = 1
            goto L_0x00b1
        L_0x00b0:
            r10 = 0
        L_0x00b1:
            com.google.ads.interactivemedia.v3.internal.qi.c(r10)
            r10 = r7[r9]
            r6[r9] = r10
            java.util.IdentityHashMap<com.google.ads.interactivemedia.v3.internal.mt, java.lang.Integer> r10 = r0.h
            r11 = r7[r9]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r2)
            r10.put(r11, r13)
            r10 = 1
            goto L_0x00d2
        L_0x00c5:
            r11 = r3[r9]
            if (r11 != r2) goto L_0x00d2
            r11 = r7[r9]
            if (r11 != 0) goto L_0x00ce
            goto L_0x00cf
        L_0x00ce:
            r12 = 0
        L_0x00cf:
            com.google.ads.interactivemedia.v3.internal.qi.c(r12)
        L_0x00d2:
            int r9 = r9 + 1
            goto L_0x00a2
        L_0x00d5:
            if (r10 == 0) goto L_0x00fd
            r18[r17] = r5
            int r9 = r17 + 1
            if (r17 != 0) goto L_0x00f7
            r5.a(r12)
            if (r8 != 0) goto L_0x00ed
            com.google.ads.interactivemedia.v3.internal.qa[] r8 = r0.p
            int r10 = r8.length
            if (r10 == 0) goto L_0x00ed
            r10 = 0
            r8 = r8[r10]
            if (r5 == r8) goto L_0x00fb
            goto L_0x00ee
        L_0x00ed:
            r10 = 0
        L_0x00ee:
            com.google.ads.interactivemedia.v3.internal.qg r5 = r0.i
            r5.a()
            r12 = r9
            r16 = 1
            goto L_0x0100
        L_0x00f7:
            r10 = 0
            r5.a(r10)
        L_0x00fb:
            r12 = r9
            goto L_0x0100
        L_0x00fd:
            r10 = 0
            r12 = r17
        L_0x0100:
            int r14 = r2 + 1
            r13 = r18
            r15 = r19
            r2 = r23
            goto L_0x0065
        L_0x010a:
            r17 = r12
            r18 = r13
            r10 = 0
            int r1 = r6.length
            r2 = r23
            java.lang.System.arraycopy(r6, r10, r2, r10, r1)
            r1 = r18
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r12)
            com.google.ads.interactivemedia.v3.internal.qa[] r1 = (com.google.ads.interactivemedia.v3.internal.qa[]) r1
            r0.p = r1
            com.google.ads.interactivemedia.v3.internal.lh r1 = r0.j
            com.google.ads.interactivemedia.v3.internal.qa[] r2 = r0.p
            com.google.ads.interactivemedia.v3.internal.mu r1 = r1.a(r2)
            r0.q = r1
            return r25
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qe.a(com.google.ads.interactivemedia.v3.internal.rt[], boolean[], com.google.ads.interactivemedia.v3.internal.mt[], boolean[], long):long");
    }

    public void a(long j2, boolean z) {
        for (qa a2 : this.p) {
            a2.a(j2, z);
        }
    }

    public void a(long j2) {
        this.q.a(j2);
    }

    public boolean c(long j2) {
        if (this.n != null) {
            return this.q.c(j2);
        }
        for (qa b2 : this.o) {
            b2.b();
        }
        return false;
    }

    public long e() {
        return this.q.e();
    }

    public long c() {
        if (!this.r) {
            this.f.c();
            this.r = true;
        }
        return -9223372036854775807L;
    }

    public long d() {
        return this.q.d();
    }

    public long b(long j2) {
        qa[] qaVarArr = this.p;
        if (qaVarArr.length > 0) {
            boolean b2 = qaVarArr[0].b(j2, false);
            int i2 = 1;
            while (true) {
                qa[] qaVarArr2 = this.p;
                if (i2 >= qaVarArr2.length) {
                    break;
                }
                qaVarArr2[i2].b(j2, b2);
                i2++;
            }
            if (b2) {
                this.i.a();
            }
        }
        return j2;
    }

    public void h() {
        this.l.a(this);
    }

    public void i() {
        this.l.a(this);
    }

    public boolean a(qo qoVar, long j2) {
        boolean z = true;
        for (qa a2 : this.o) {
            z &= a2.a(qoVar, j2);
        }
        this.l.a(this);
        return z;
    }

    private void d(long j2) {
        qn b2 = this.b.b();
        boolean z = !b2.b.isEmpty();
        List<qo> list = b2.c;
        List<qo> list2 = b2.d;
        this.m = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            a(b2, j2, (List<qa>) arrayList, (List<int[]>) arrayList2);
        }
        a(j2, list, (List<qa>) arrayList, (List<int[]>) arrayList2);
        for (int i2 = 0; i2 < list2.size(); i2++) {
            qo qoVar = (qo) list2.get(i2);
            qa a2 = a(3, new qo[]{qoVar}, (bs) null, Collections.emptyList(), j2);
            arrayList2.add(new int[]{i2});
            arrayList.add(a2);
            a2.a(new mz(new mx(qoVar.b)), 0, mz.a);
        }
        this.o = (qa[]) arrayList.toArray(new qa[0]);
        arrayList2.toArray(new int[0][]);
        qa[] qaVarArr = this.o;
        this.m = qaVarArr.length;
        qaVarArr[0].a(true);
        for (qa b3 : this.o) {
            b3.b();
        }
        this.p = this.o;
    }

    private void a(qn qnVar, long j2, List<qa> list, List<int[]> list2) {
        boolean z;
        boolean z2;
        qn qnVar2 = qnVar;
        int[] iArr = new int[qnVar2.b.size()];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < qnVar2.b.size(); i4++) {
            bs bsVar = ((qo) qnVar2.b.get(i4)).b;
            if (bsVar.n > 0 || vf.a(bsVar.e, 2) != null) {
                iArr[i4] = 2;
                i2++;
            } else if (vf.a(bsVar.e, 1) != null) {
                iArr[i4] = 1;
                i3++;
            } else {
                iArr[i4] = -1;
            }
        }
        int length = iArr.length;
        if (i2 > 0) {
            z2 = true;
            z = false;
        } else if (i3 < length) {
            i2 = length - i3;
            z2 = false;
            z = true;
        } else {
            i2 = length;
            z2 = false;
            z = false;
        }
        qo[] qoVarArr = new qo[i2];
        int[] iArr2 = new int[i2];
        int i5 = 0;
        for (int i6 = 0; i6 < qnVar2.b.size(); i6++) {
            if ((!z2 || iArr[i6] == 2) && (!z || iArr[i6] != 1)) {
                qoVarArr[i5] = (qo) qnVar2.b.get(i6);
                int i7 = i5 + 1;
                iArr2[i5] = i6;
                i5 = i7;
            }
        }
        String str = qoVarArr[0].b.e;
        int[] iArr3 = iArr2;
        qa a2 = a(0, qoVarArr, qnVar2.e, qnVar2.f, j2);
        list.add(a2);
        list2.add(iArr3);
        if (this.k && str != null) {
            boolean z3 = vf.a(str, 2) != null;
            boolean z4 = vf.a(str, 1) != null;
            ArrayList arrayList = new ArrayList();
            if (z3) {
                bs[] bsVarArr = new bs[i2];
                for (int i8 = 0; i8 < bsVarArr.length; i8++) {
                    bsVarArr[i8] = a(qoVarArr[i8].b);
                }
                arrayList.add(new mx(bsVarArr));
                if (z4 && (qnVar2.e != null || qnVar2.c.isEmpty())) {
                    arrayList.add(new mx(a(qoVarArr[0].b, qnVar2.e, false)));
                }
                List<bs> list3 = qnVar2.f;
                if (list3 != null) {
                    for (int i9 = 0; i9 < list3.size(); i9++) {
                        arrayList.add(new mx((bs) list3.get(i9)));
                    }
                }
            } else if (z4) {
                bs[] bsVarArr2 = new bs[i2];
                for (int i10 = 0; i10 < bsVarArr2.length; i10++) {
                    bsVarArr2[i10] = a(qoVarArr[i10].b, qnVar2.e, true);
                }
                arrayList.add(new mx(bsVarArr2));
            } else {
                String str2 = "Unexpected codecs attribute: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            mx mxVar = new mx(bs.a("ID3", MimeTypes.APPLICATION_ID3, (String) null, -1, (fa) null));
            arrayList.add(mxVar);
            a2.a(new mz((mx[]) arrayList.toArray(new mx[0])), 0, new mz(mxVar));
        }
    }

    private void a(long j2, List<qo> list, List<qa> list2, List<int[]> list3) {
        List<qo> list4 = list;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = ((qo) list4.get(i2)).c;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                boolean z = true;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (vf.a((Object) str, (Object) ((qo) list4.get(i3)).c)) {
                        qo qoVar = (qo) list4.get(i3);
                        arrayList2.add(Integer.valueOf(i3));
                        arrayList.add(qoVar);
                        z &= qoVar.b.e != null;
                    }
                }
                qa a2 = a(1, (qo[]) arrayList.toArray(new qo[0]), (bs) null, Collections.emptyList(), j2);
                list3.add(vf.a((List<Integer>) arrayList2));
                list2.add(a2);
                if (this.k && z) {
                    bs[] bsVarArr = new bs[arrayList.size()];
                    for (int i4 = 0; i4 < bsVarArr.length; i4++) {
                        bsVarArr[i4] = ((qo) arrayList.get(i4)).b;
                    }
                    a2.a(new mz(new mx(bsVarArr)), 0, mz.a);
                }
            } else {
                List<qa> list5 = list2;
                List<int[]> list6 = list3;
            }
        }
    }

    private qa a(int i2, qo[] qoVarArr, bs bsVar, List<bs> list, long j2) {
        pm pmVar = new pm(this.a, this.b, qoVarArr, this.c, this.d, this.i, list);
        qa qaVar = new qa(i2, this, pmVar, this.g, j2, bsVar, this.e, this.f);
        return qaVar;
    }

    private static bs a(bs bsVar) {
        String a2 = vf.a(bsVar.e, 2);
        return bs.a(bsVar.a, bsVar.b, bsVar.g, un.f(a2), a2, bsVar.d, bsVar.m, bsVar.n, bsVar.o, null, bsVar.c, 0);
    }

    private static bs a(bs bsVar, bs bsVar2, boolean z) {
        String str;
        int i2;
        int i3;
        String str2;
        String str3;
        bs bsVar3 = bsVar;
        bs bsVar4 = bsVar2;
        if (bsVar4 != null) {
            String str4 = bsVar4.e;
            int i4 = bsVar4.s;
            int i5 = bsVar4.c;
            String str5 = bsVar4.x;
            str3 = bsVar4.b;
            str2 = str4;
            i3 = i4;
            i2 = i5;
            str = str5;
        } else {
            String a2 = vf.a(bsVar3.e, 1);
            if (z) {
                int i6 = bsVar3.s;
                int i7 = bsVar3.c;
                str2 = a2;
                i3 = i6;
                str = bsVar3.x;
                i2 = i7;
                str3 = bsVar3.b;
            } else {
                str2 = a2;
                str3 = null;
                str = null;
                i3 = -1;
                i2 = 0;
            }
        }
        return bs.a(bsVar3.a, str3, bsVar3.g, un.f(str2), str2, z ? bsVar3.d : -1, i3, -1, null, i2, 0, str);
    }

    public /* synthetic */ void a(mu muVar) {
        h();
    }
}
