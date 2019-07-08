package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.ads.interactivemedia.v3.internal.ri.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IMASDK */
public final class rl extends rx {
    public static final Creator<rl> CREATOR = new rm();
    public static final rl a = new rl();
    private final SparseBooleanArray A;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int i;
    public final int j;
    public final boolean k;
    public final int l;
    public final int m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final int t;
    private final SparseArray<Map<mz, a>> z;

    private rl() {
        String str = rx.u.v;
        String str2 = rx.u.w;
        boolean z2 = rx.u.x;
        int i2 = rx.u.y;
        SparseArray sparseArray = r1;
        SparseArray sparseArray2 = new SparseArray();
        SparseBooleanArray sparseBooleanArray = r1;
        SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray();
        this(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true, str, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false, false, str2, z2, i2, false, false, true, 0, sparseArray, sparseBooleanArray);
    }

    public final int describeContents() {
        return 0;
    }

    private rl(int i2, int i3, int i4, int i5, boolean z2, boolean z3, boolean z4, int i6, int i7, boolean z5, String str, int i8, int i9, boolean z6, boolean z7, boolean z8, String str2, boolean z9, int i10, boolean z10, boolean z11, boolean z12, int i11, SparseArray<Map<mz, a>> sparseArray, SparseBooleanArray sparseBooleanArray) {
        super(str, str2, z9, i10);
        this.b = Integer.MAX_VALUE;
        this.c = Integer.MAX_VALUE;
        this.d = Integer.MAX_VALUE;
        this.e = Integer.MAX_VALUE;
        this.f = true;
        this.g = false;
        this.h = true;
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
        this.k = true;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = true;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = true;
        this.t = 0;
        this.z = sparseArray;
        this.A = sparseBooleanArray;
    }

    rl(Parcel parcel) {
        super(parcel);
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = vf.a(parcel);
        this.g = vf.a(parcel);
        this.h = vf.a(parcel);
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = vf.a(parcel);
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = vf.a(parcel);
        this.o = vf.a(parcel);
        this.p = vf.a(parcel);
        this.q = vf.a(parcel);
        this.r = vf.a(parcel);
        this.s = vf.a(parcel);
        this.t = parcel.readInt();
        int readInt = parcel.readInt();
        SparseArray<Map<mz, a>> sparseArray = new SparseArray<>(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            HashMap hashMap = new HashMap(readInt3);
            for (int i3 = 0; i3 < readInt3; i3++) {
                hashMap.put((mz) parcel.readParcelable(mz.class.getClassLoader()), (a) parcel.readParcelable(a.class.getClassLoader()));
            }
            sparseArray.put(readInt2, hashMap);
        }
        this.z = sparseArray;
        this.A = (SparseBooleanArray) vf.a(parcel.readSparseBooleanArray());
    }

    public final boolean a(int i2) {
        return this.A.get(i2);
    }

    public final boolean a(int i2, mz mzVar) {
        Map map = (Map) this.z.get(i2);
        return map != null && map.containsKey(mzVar);
    }

    public final a b(int i2, mz mzVar) {
        Map map = (Map) this.z.get(i2);
        if (map != null) {
            return (a) map.get(mzVar);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        boolean z2;
        boolean z3;
        boolean z4;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        rl rlVar = (rl) obj;
        if (super.equals(obj) && this.b == rlVar.b && this.c == rlVar.c && this.d == rlVar.d && this.e == rlVar.e && this.f == rlVar.f && this.g == rlVar.g && this.h == rlVar.h && this.k == rlVar.k && this.i == rlVar.i && this.j == rlVar.j && this.l == rlVar.l && this.m == rlVar.m && this.n == rlVar.n && this.o == rlVar.o && this.p == rlVar.p && this.q == rlVar.q && this.r == rlVar.r && this.s == rlVar.s && this.t == rlVar.t) {
            SparseBooleanArray sparseBooleanArray = this.A;
            SparseBooleanArray sparseBooleanArray2 = rlVar.A;
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() == size) {
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        z2 = true;
                        break;
                    } else if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i2)) < 0) {
                        z2 = false;
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                z2 = false;
            }
            if (z2) {
                SparseArray<Map<mz, a>> sparseArray = this.z;
                SparseArray<Map<mz, a>> sparseArray2 = rlVar.z;
                int size2 = sparseArray.size();
                if (sparseArray2.size() != size2) {
                    z3 = false;
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size2) {
                            z3 = true;
                            break;
                        }
                        int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i3));
                        if (indexOfKey < 0) {
                            break;
                        }
                        Map map = (Map) sparseArray.valueAt(i3);
                        Map map2 = (Map) sparseArray2.valueAt(indexOfKey);
                        if (map2.size() != map.size()) {
                            z4 = false;
                        } else {
                            Iterator it = map.entrySet().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z4 = true;
                                    break;
                                }
                                Entry entry = (Entry) it.next();
                                mz mzVar = (mz) entry.getKey();
                                if (map2.containsKey(mzVar)) {
                                    if (!vf.a(entry.getValue(), map2.get(mzVar))) {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            z4 = false;
                        }
                        if (!z4) {
                            break;
                        }
                        i3++;
                    }
                    z3 = false;
                }
                if (z3) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((((((((((((((((((((((((((((((((((((super.hashCode() * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + (this.f ? 1 : 0)) * 31) + (this.g ? 1 : 0)) * 31) + (this.h ? 1 : 0)) * 31) + (this.k ? 1 : 0)) * 31) + this.i) * 31) + this.j) * 31) + this.l) * 31) + this.m) * 31) + (this.n ? 1 : 0)) * 31) + (this.o ? 1 : 0)) * 31) + (this.p ? 1 : 0)) * 31) + (this.q ? 1 : 0)) * 31) + (this.r ? 1 : 0)) * 31) + (this.s ? 1 : 0)) * 31) + this.t;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        vf.a(parcel, this.f);
        vf.a(parcel, this.g);
        vf.a(parcel, this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        vf.a(parcel, this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        vf.a(parcel, this.n);
        vf.a(parcel, this.o);
        vf.a(parcel, this.p);
        vf.a(parcel, this.q);
        vf.a(parcel, this.r);
        vf.a(parcel, this.s);
        parcel.writeInt(this.t);
        SparseArray<Map<mz, a>> sparseArray = this.z;
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            int keyAt = sparseArray.keyAt(i3);
            Map map = (Map) sparseArray.valueAt(i3);
            int size2 = map.size();
            parcel.writeInt(keyAt);
            parcel.writeInt(size2);
            for (Entry entry : map.entrySet()) {
                parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                parcel.writeParcelable((Parcelable) entry.getValue(), 0);
            }
        }
        parcel.writeSparseBooleanArray(this.A);
    }
}
