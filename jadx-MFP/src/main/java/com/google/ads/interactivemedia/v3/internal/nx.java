package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: IMASDK */
final class nx implements ll, mv<nl<no>>, nn<no> {
    final int a;
    private final nw b;
    private final tz c;
    private final ti d;
    private final long e;
    private final ts f;
    private final sf g;
    private final mz h;
    private final ny[] i;
    private final lh j;
    private final op k;
    private final IdentityHashMap<nl<no>, os> l = new IdentityHashMap<>();
    private final lr m;
    private lm n;
    private nl<no>[] o = new nl[0];
    private oo[] p = new oo[0];
    private mu q;
    private tc r;
    private int s;
    private List<yu> t;
    private boolean u;

    public nx(int i2, tc tcVar, int i3, nw nwVar, tz tzVar, ti tiVar, lr lrVar, long j2, ts tsVar, sf sfVar, lh lhVar, or orVar) {
        int i4;
        List<rr> list;
        int i5;
        boolean[] zArr;
        int i6;
        boolean z;
        boolean z2;
        ou ouVar;
        tc tcVar2 = tcVar;
        sf sfVar2 = sfVar;
        lh lhVar2 = lhVar;
        this.a = i2;
        this.r = tcVar2;
        this.s = i3;
        this.b = nwVar;
        this.c = tzVar;
        this.d = tiVar;
        this.m = lrVar;
        this.e = j2;
        this.f = tsVar;
        this.g = sfVar2;
        this.j = lhVar2;
        this.k = new op(tcVar2, orVar, sfVar2);
        this.q = lhVar2.a(this.o);
        ov a2 = tcVar.a(i3);
        this.t = a2.d;
        List<rr> list2 = a2.c;
        List<yu> list3 = this.t;
        int size = list2.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i7 = 0; i7 < size; i7++) {
            sparseIntArray.put(((rr) list2.get(i7)).a, i7);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr2 = new boolean[size];
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            if (!zArr2[i9]) {
                zArr2[i9] = true;
                List<ou> list4 = ((rr) list2.get(i9)).e;
                int i10 = 0;
                while (true) {
                    if (i10 >= list4.size()) {
                        ouVar = null;
                        break;
                    }
                    ouVar = (ou) list4.get(i10);
                    if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(ouVar.a)) {
                        break;
                    }
                    i10++;
                }
                if (ouVar == null) {
                    int i11 = i8 + 1;
                    iArr[i8] = new int[]{i9};
                    i8 = i11;
                } else {
                    String[] a3 = vf.a(ouVar.b, ",");
                    int[] iArr2 = new int[(a3.length + 1)];
                    iArr2[0] = i9;
                    int i12 = 1;
                    for (String parseInt : a3) {
                        int i13 = sparseIntArray.get(Integer.parseInt(parseInt), -1);
                        if (i13 != -1) {
                            zArr2[i13] = true;
                            iArr2[i12] = i13;
                            i12++;
                        }
                    }
                    if (i12 < iArr2.length) {
                        iArr2 = Arrays.copyOf(iArr2, i12);
                    }
                    int i14 = i8 + 1;
                    iArr[i8] = iArr2;
                    i8 = i14;
                }
            }
        }
        if (i8 < size) {
            iArr = (int[][]) Arrays.copyOf(iArr, i8);
        }
        int length = iArr.length;
        boolean[] zArr3 = new boolean[length];
        boolean[] zArr4 = new boolean[length];
        int i15 = 0;
        int i16 = 0;
        while (i15 < length) {
            int[] iArr3 = iArr[i15];
            int length2 = iArr3.length;
            int i17 = 0;
            while (true) {
                if (i17 >= length2) {
                    z = false;
                    break;
                }
                List<oy> list5 = ((rr) list2.get(iArr3[i17])).c;
                for (int i18 = 0; i18 < list5.size(); i18++) {
                    if (!((oy) list5.get(i18)).d.isEmpty()) {
                        z = true;
                        break;
                    }
                }
                i17++;
            }
            if (z) {
                zArr3[i15] = true;
                i16++;
            }
            int[] iArr4 = iArr[i15];
            int length3 = iArr4.length;
            int i19 = 0;
            while (true) {
                if (i19 >= length3) {
                    z2 = false;
                    break;
                }
                List<ou> list6 = ((rr) list2.get(iArr4[i19])).d;
                int i20 = 0;
                while (i20 < list6.size()) {
                    if ("urn:scte:dash:cc:cea-608:2015".equals(((ou) list6.get(i20)).a)) {
                        z2 = true;
                        break;
                    } else {
                        i20++;
                        lr lrVar2 = lrVar;
                    }
                }
                i19++;
                lr lrVar3 = lrVar;
            }
            if (z2) {
                zArr4[i15] = true;
                i16++;
            }
            i15++;
            lr lrVar4 = lrVar;
        }
        int size2 = i16 + length + list3.size();
        mx[] mxVarArr = new mx[size2];
        ny[] nyVarArr = new ny[size2];
        int i21 = 0;
        int i22 = 0;
        while (i21 < length) {
            int[] iArr5 = iArr[i21];
            ArrayList arrayList = new ArrayList();
            for (int i23 : iArr5) {
                arrayList.addAll(((rr) list2.get(i23)).c);
            }
            bs[] bsVarArr = new bs[arrayList.size()];
            for (int i24 = 0; i24 < bsVarArr.length; i24++) {
                bsVarArr[i24] = ((oy) arrayList.get(i24)).a;
            }
            rr rrVar = (rr) list2.get(iArr5[0]);
            int i25 = i22 + 1;
            if (zArr3[i21]) {
                i4 = i25 + 1;
            } else {
                i4 = i25;
                i25 = -1;
            }
            if (zArr4[i21]) {
                i5 = i4 + 1;
                list = list2;
            } else {
                list = list2;
                i5 = i4;
                i4 = -1;
            }
            mxVarArr[i22] = new mx(bsVarArr);
            nyVarArr[i22] = ny.a(rrVar.b, iArr5, i22, i25, i4);
            int i26 = -1;
            if (i25 != -1) {
                int i27 = rrVar.a;
                i6 = length;
                StringBuilder sb = new StringBuilder(16);
                sb.append(i27);
                sb.append(":emsg");
                zArr = zArr3;
                mxVarArr[i25] = new mx(bs.a(sb.toString(), MimeTypes.APPLICATION_EMSG, (String) null, -1, (fa) null));
                nyVarArr[i25] = ny.a(iArr5, i22);
                i26 = -1;
            } else {
                i6 = length;
                zArr = zArr3;
            }
            if (i4 != i26) {
                int i28 = rrVar.a;
                StringBuilder sb2 = new StringBuilder(18);
                sb2.append(i28);
                sb2.append(":cea608");
                mxVarArr[i4] = new mx(bs.a(sb2.toString(), MimeTypes.APPLICATION_CEA608, 0, null));
                nyVarArr[i4] = ny.b(iArr5, i22);
            }
            i21++;
            list2 = list;
            length = i6;
            zArr3 = zArr;
            i22 = i5;
        }
        int i29 = 0;
        while (i29 < list3.size()) {
            mxVarArr[i22] = new mx(bs.a(((yu) list3.get(i29)).a(), MimeTypes.APPLICATION_EMSG, (String) null, -1, (fa) null));
            int i30 = i22 + 1;
            nyVarArr[i22] = ny.a(i29);
            i29++;
            i22 = i30;
        }
        Pair create = Pair.create(new mz(mxVarArr), nyVarArr);
        this.h = (mz) create.first;
        this.i = (ny[]) create.second;
        lrVar.a();
    }

    public final void a(tc tcVar, int i2) {
        oo[] ooVarArr;
        this.r = tcVar;
        this.s = i2;
        this.k.a(tcVar);
        nl<no>[] nlVarArr = this.o;
        if (nlVarArr != null) {
            for (nl<no> a2 : nlVarArr) {
                a2.a().a(tcVar, i2);
            }
            this.n.a(this);
        }
        this.t = tcVar.a(i2).d;
        for (oo ooVar : this.p) {
            Iterator it = this.t.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                yu yuVar = (yu) it.next();
                if (yuVar.a().equals(ooVar.a())) {
                    boolean z = true;
                    int a3 = tcVar.a() - 1;
                    if (!tcVar.d || i2 != a3) {
                        z = false;
                    }
                    ooVar.a(yuVar, z);
                }
            }
        }
    }

    public final void f() {
        this.k.b();
        for (nl<no> a2 : this.o) {
            a2.a((nn<T>) this);
        }
        this.n = null;
        this.m.b();
    }

    public final synchronized void a(nl<no> nlVar) {
        os osVar = (os) this.l.remove(nlVar);
        if (osVar != null) {
            osVar.a();
        }
    }

    public final void a(lm lmVar, long j2) {
        this.n = lmVar;
        lmVar.a(this);
    }

    public final void a_() throws IOException {
        this.f.a();
    }

    public final mz b() {
        return this.h;
    }

    public final long a(rt[] rtVarArr, boolean[] zArr, mt[] mtVarArr, boolean[] zArr2, long j2) {
        int[] iArr = new int[rtVarArr.length];
        for (int i2 = 0; i2 < rtVarArr.length; i2++) {
            if (rtVarArr[i2] != null) {
                iArr[i2] = this.h.a(rtVarArr[i2].f());
            } else {
                iArr[i2] = -1;
            }
        }
        for (int i3 = 0; i3 < rtVarArr.length; i3++) {
            if (rtVarArr[i3] == null || !zArr[i3]) {
                if (mtVarArr[i3] instanceof nl) {
                    mtVarArr[i3].a((nn<T>) this);
                } else if (mtVarArr[i3] instanceof nm) {
                    mtVarArr[i3].a();
                }
                mtVarArr[i3] = null;
            }
        }
        for (int i4 = 0; i4 < rtVarArr.length; i4++) {
            if ((mtVarArr[i4] instanceof li) || (mtVarArr[i4] instanceof nm)) {
                int a2 = a(i4, iArr);
                boolean z = a2 == -1 ? mtVarArr[i4] instanceof li : (mtVarArr[i4] instanceof nm) && mtVarArr[i4].a == mtVarArr[a2];
                if (!z) {
                    if (mtVarArr[i4] instanceof nm) {
                        mtVarArr[i4].a();
                    }
                    mtVarArr[i4] = null;
                }
            }
        }
        for (int i5 = 0; i5 < rtVarArr.length; i5++) {
            if (mtVarArr[i5] == null && rtVarArr[i5] != null) {
                zArr2[i5] = true;
                ny nyVar = this.i[iArr[i5]];
                if (nyVar.c == 0) {
                    mtVarArr[i5] = a(nyVar, rtVarArr[i5], j2);
                } else if (nyVar.c == 2) {
                    mtVarArr[i5] = new oo((yu) this.t.get(nyVar.d), rtVarArr[i5].f().a(0), this.r.d);
                }
            }
        }
        for (int i6 = 0; i6 < rtVarArr.length; i6++) {
            if (mtVarArr[i6] == null && rtVarArr[i6] != null) {
                ny nyVar2 = this.i[iArr[i6]];
                if (nyVar2.c == 1) {
                    int a3 = a(i6, iArr);
                    if (a3 == -1) {
                        mtVarArr[i6] = new li();
                    } else {
                        mtVarArr[i6] = mtVarArr[a3].a(j2, nyVar2.b);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (nl nlVar : mtVarArr) {
            if (nlVar instanceof nl) {
                arrayList.add(nlVar);
            } else if (nlVar instanceof oo) {
                arrayList2.add((oo) nlVar);
            }
        }
        this.o = new nl[arrayList.size()];
        arrayList.toArray(this.o);
        this.p = new oo[arrayList2.size()];
        arrayList2.toArray(this.p);
        this.q = this.j.a(this.o);
        return j2;
    }

    public final void a(long j2, boolean z) {
        for (nl<no> a2 : this.o) {
            a2.a(j2, z);
        }
    }

    public final void a(long j2) {
        this.q.a(j2);
    }

    public final boolean c(long j2) {
        return this.q.c(j2);
    }

    public final long e() {
        return this.q.e();
    }

    public final long c() {
        if (!this.u) {
            this.m.c();
            this.u = true;
        }
        return -9223372036854775807L;
    }

    public final long d() {
        return this.q.d();
    }

    public final long b(long j2) {
        for (nl<no> b2 : this.o) {
            b2.b(j2);
        }
        for (oo b3 : this.p) {
            b3.b(j2);
        }
        return j2;
    }

    public final long a(long j2, cm cmVar) {
        nl<no>[] nlVarArr;
        for (nl<no> nlVar : this.o) {
            if (nlVar.a == 2) {
                return nlVar.a(j2, cmVar);
            }
        }
        return j2;
    }

    private final int a(int i2, int[] iArr) {
        int i3 = iArr[i2];
        if (i3 == -1) {
            return -1;
        }
        int i4 = this.i[i3].e;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            int i6 = iArr[i5];
            if (i6 == i4 && this.i[i6].c == 0) {
                return i5;
            }
        }
        return -1;
    }

    private final nl<no> a(ny nyVar, rt rtVar, long j2) {
        int i2;
        bs[] bsVarArr;
        int[] iArr;
        ny nyVar2 = nyVar;
        int[] iArr2 = new int[2];
        bs[] bsVarArr2 = new bs[2];
        boolean z = nyVar2.f != -1;
        if (z) {
            bsVarArr2[0] = this.h.a(nyVar2.f).a(0);
            iArr2[0] = 4;
            i2 = 1;
        } else {
            i2 = 0;
        }
        boolean z2 = nyVar2.g != -1;
        if (z2) {
            bsVarArr2[i2] = this.h.a(nyVar2.g).a(0);
            int i3 = i2 + 1;
            iArr2[i2] = 3;
            i2 = i3;
        }
        if (i2 < 2) {
            bs[] bsVarArr3 = (bs[]) Arrays.copyOf(bsVarArr2, i2);
            int[] copyOf = Arrays.copyOf(iArr2, i2);
            bsVarArr = bsVarArr3;
            iArr = copyOf;
        } else {
            bsVarArr = bsVarArr2;
            iArr = iArr2;
        }
        os a2 = (!this.r.d || !z) ? null : this.k.a();
        os osVar = a2;
        nl nlVar = new nl(nyVar2.b, iArr, bsVarArr, this.b.a(this.f, this.r, this.s, nyVar2.a, rtVar, nyVar2.b, this.e, z, z2, a2, this.c), this, this.g, j2, this.d, this.m);
        synchronized (this) {
            this.l.put(nlVar, osVar);
        }
        return nlVar;
    }

    public final /* synthetic */ void a(mu muVar) {
        this.n.a(this);
    }
}
