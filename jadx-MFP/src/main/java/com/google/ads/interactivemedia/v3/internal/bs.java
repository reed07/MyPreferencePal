package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class bs implements Parcelable {
    public static final Creator<bs> CREATOR = new bt();
    private final int A;
    private final byte[] B;
    private int C;
    public final String a;
    public final String b;
    public final int c;
    public final int d;
    public final String e;
    public final js f;
    public final String g;
    public final String h;
    public final int i;
    public final List<byte[]> j;
    public final fa k;
    public final long l;
    public final int m;
    public final int n;
    public final float o;
    public final int p;
    public final float q;
    public final vi r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final String x;
    public final int y;
    private final int z;

    public static bs a(String str, String str2, String str3, String str4, String str5, int i2, int i3, int i4, float f2, List<byte[]> list, int i5, int i6) {
        int i7 = i2;
        int i8 = i5;
        int i9 = i6;
        bs bsVar = new bs(str, str2, i8, i9, i7, str5, null, str3, str4, -1, null, null, Long.MAX_VALUE, i3, i4, f2, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, null, -1);
        return bsVar;
    }

    public final int describeContents() {
        return 0;
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, int i4, int i5, float f2, List<byte[]> list, int i6, float f3, fa faVar) {
        return a(str, str2, str3, -1, -1, i4, i5, -1.0f, list, -1, f3, (byte[]) null, -1, (vi) null, (fa) null);
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, int i4, int i5, float f2, List<byte[]> list, int i6, float f3, byte[] bArr, int i7, vi viVar, fa faVar) {
        int i8 = i2;
        bs bsVar = new bs(str, null, 0, 0, i8, str3, null, null, str2, i3, list, faVar, Long.MAX_VALUE, i4, i5, f2, i6, f3, bArr, i7, viVar, -1, -1, -1, -1, -1, null, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, String str4, String str5, int i2, int i3, int i4, List<byte[]> list, int i5, int i6, String str6) {
        int i7 = i2;
        int i8 = i5;
        int i9 = i6;
        bs bsVar = new bs(str, str2, i8, i9, i7, str5, null, str3, str4, -1, null, null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i3, i4, -1, -1, -1, str6, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, int i4, int i5, List<byte[]> list, fa faVar, int i6, String str4) {
        return a(str, str2, (String) null, i2, i3, i4, i5, -1, list, faVar, 0, str4);
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, int i4, int i5, int i6, List<byte[]> list, fa faVar, int i7, String str4) {
        return a(str, str2, str3, i2, i3, i4, i5, i6, -1, -1, list, faVar, i7, str4, (js) null);
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, int i4, int i5, int i6, int i7, int i8, List<byte[]> list, fa faVar, int i9, String str4, js jsVar) {
        int i10 = i2;
        int i11 = i9;
        js jsVar2 = jsVar;
        bs bsVar = new bs(str, null, i11, 0, i10, str3, jsVar2, null, str2, i3, list, faVar, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i4, i5, i6, i7, i8, str4, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6) {
        return a(str, str2, str3, str4, (String) null, -1, i3, 0, str6, -1);
    }

    public static bs a(String str, String str2, String str3, String str4, String str5, int i2, int i3, int i4, String str6, int i5) {
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        bs bsVar = new bs(str, str2, i7, i8, i6, str5, null, str3, str4, -1, null, null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, str6, i5);
        return bsVar;
    }

    public static bs a(String str, String str2, int i2, String str3) {
        return a(str, str2, 0, (String) null, (fa) null);
    }

    public static bs a(String str, String str2, int i2, String str3, fa faVar) {
        return a(str, str2, (String) null, -1, i2, str3, -1, faVar, Long.MAX_VALUE, Collections.emptyList());
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, String str4, fa faVar, long j2) {
        return a((String) null, str2, (String) null, -1, 0, str4, -1, (fa) null, j2, Collections.emptyList());
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, String str4, int i4, fa faVar, long j2, List<byte[]> list) {
        int i5 = i2;
        int i6 = i3;
        List<byte[]> list2 = list;
        bs bsVar = new bs(str, null, i6, 0, i5, str3, null, null, str2, -1, list2, faVar, j2, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, str4, i4);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, int i2, int i3, List<byte[]> list, String str4, fa faVar) {
        bs bsVar = new bs(str, null, i3, 0, -1, null, null, null, str2, -1, list, faVar, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, str4, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, String str4, String str5, int i2, int i3, int i4, String str6) {
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        bs bsVar = new bs(str, str2, i6, i7, i5, str5, null, str3, str4, -1, null, null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, str6, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, long j2) {
        bs bsVar = new bs(str, null, 0, 0, -1, null, null, null, str2, -1, null, null, j2, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, null, -1);
        return bsVar;
    }

    public static bs a(String str, String str2, String str3, int i2, fa faVar) {
        bs bsVar = new bs(str, null, 0, 0, -1, null, null, null, str2, -1, null, null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, null, -1);
        return bsVar;
    }

    private bs(String str, String str2, int i2, int i3, int i4, String str3, js jsVar, String str4, String str5, int i5, List<byte[]> list, fa faVar, long j2, int i6, int i7, float f2, int i8, float f3, byte[] bArr, int i9, vi viVar, int i10, int i11, int i12, int i13, int i14, String str6, int i15) {
        this.a = str;
        this.b = str2;
        this.c = i2;
        this.z = i3;
        this.d = i4;
        this.e = str3;
        this.f = jsVar;
        this.g = str4;
        this.h = str5;
        this.i = i5;
        this.j = list == null ? Collections.emptyList() : list;
        this.k = faVar;
        this.l = j2;
        this.m = i6;
        this.n = i7;
        this.o = f2;
        int i16 = i8;
        if (i16 == -1) {
            i16 = 0;
        }
        this.p = i16;
        this.q = f3 == -1.0f ? 1.0f : f3;
        this.B = bArr;
        this.A = i9;
        this.r = viVar;
        this.s = i10;
        this.t = i11;
        this.u = i12;
        int i17 = i13;
        if (i17 == -1) {
            i17 = 0;
        }
        this.v = i17;
        int i18 = i14;
        if (i18 == -1) {
            i18 = 0;
        }
        this.w = i18;
        this.x = vf.b(str6);
        this.y = i15;
    }

    bs(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.z = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = (js) parcel.readParcelable(js.class.getClassLoader());
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readInt();
        int readInt = parcel.readInt();
        this.j = new ArrayList(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            this.j.add(parcel.createByteArray());
        }
        this.k = (fa) parcel.readParcelable(fa.class.getClassLoader());
        this.l = parcel.readLong();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readFloat();
        this.p = parcel.readInt();
        this.q = parcel.readFloat();
        this.B = vf.a(parcel) ? parcel.createByteArray() : null;
        this.A = parcel.readInt();
        this.r = (vi) parcel.readParcelable(vi.class.getClassLoader());
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readString();
        this.y = parcel.readInt();
    }

    public final bs a(int i2) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, this.f, this.g, this.h, i2, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final bs a(long j2) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, j2, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final bs a(String str, String str2, String str3, String str4, int i2, int i3, int i4, int i5, int i6, String str5) {
        String str6 = str4;
        int i7 = i2;
        bs bsVar = new bs(str, str2, i6, this.z, i7, str6, this.f, this.g, str3, this.i, this.j, this.k, this.l, i3, i4, this.o, this.p, this.q, this.B, this.A, this.r, i5, this.t, this.u, this.v, this.w, str5, this.y);
        return bsVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.bs a(com.google.ads.interactivemedia.v3.internal.bs r34) {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0007
            return r0
        L_0x0007:
            java.lang.String r2 = r0.h
            int r2 = com.google.ads.interactivemedia.v3.internal.un.g(r2)
            java.lang.String r4 = r1.a
            java.lang.String r3 = r1.b
            if (r3 == 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            java.lang.String r3 = r0.b
        L_0x0016:
            r5 = r3
            java.lang.String r3 = r0.x
            r6 = 3
            r7 = 1
            if (r2 == r6) goto L_0x001f
            if (r2 != r7) goto L_0x0026
        L_0x001f:
            java.lang.String r6 = r1.x
            if (r6 == 0) goto L_0x0026
            r31 = r6
            goto L_0x0028
        L_0x0026:
            r31 = r3
        L_0x0028:
            int r3 = r0.d
            r6 = -1
            if (r3 != r6) goto L_0x002f
            int r3 = r1.d
        L_0x002f:
            r8 = r3
            java.lang.String r3 = r0.e
            if (r3 != 0) goto L_0x0043
            java.lang.String r6 = r1.e
            java.lang.String r6 = com.google.ads.interactivemedia.v3.internal.vf.a(r6, r2)
            java.lang.String[] r9 = com.google.ads.interactivemedia.v3.internal.vf.j(r6)
            int r9 = r9.length
            if (r9 != r7) goto L_0x0043
            r9 = r6
            goto L_0x0044
        L_0x0043:
            r9 = r3
        L_0x0044:
            float r3 = r0.o
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r6 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x0054
            r6 = 2
            if (r2 != r6) goto L_0x0054
            float r2 = r1.o
            r20 = r2
            goto L_0x0056
        L_0x0054:
            r20 = r3
        L_0x0056:
            int r2 = r0.c
            int r3 = r1.c
            r6 = r2 | r3
            int r2 = r0.z
            int r3 = r1.z
            r7 = r2 | r3
            com.google.ads.interactivemedia.v3.internal.fa r1 = r1.k
            com.google.ads.interactivemedia.v3.internal.fa r2 = r0.k
            com.google.ads.interactivemedia.v3.internal.fa r15 = com.google.ads.interactivemedia.v3.internal.fa.a(r1, r2)
            com.google.ads.interactivemedia.v3.internal.bs r1 = new com.google.ads.interactivemedia.v3.internal.bs
            r3 = r1
            com.google.ads.interactivemedia.v3.internal.js r10 = r0.f
            java.lang.String r11 = r0.g
            java.lang.String r12 = r0.h
            int r13 = r0.i
            java.util.List<byte[]> r14 = r0.j
            r34 = r1
            long r1 = r0.l
            r16 = r1
            int r1 = r0.m
            r18 = r1
            int r1 = r0.n
            r19 = r1
            int r1 = r0.p
            r21 = r1
            float r1 = r0.q
            r22 = r1
            byte[] r1 = r0.B
            r23 = r1
            int r1 = r0.A
            r24 = r1
            com.google.ads.interactivemedia.v3.internal.vi r1 = r0.r
            r25 = r1
            int r1 = r0.s
            r26 = r1
            int r1 = r0.t
            r27 = r1
            int r1 = r0.u
            r28 = r1
            int r1 = r0.v
            r29 = r1
            int r1 = r0.w
            r30 = r1
            int r1 = r0.y
            r32 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return r34
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bs.a(com.google.ads.interactivemedia.v3.internal.bs):com.google.ads.interactivemedia.v3.internal.bs");
    }

    public final bs a(int i2, int i3) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, i2, i3, this.x, this.y);
        return bsVar;
    }

    public final bs a(float f2) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, f2, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final bs a(fa faVar) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, this.f, this.g, this.h, this.i, this.j, faVar, this.l, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final bs a(js jsVar) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, this.d, this.e, jsVar, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final bs b(int i2) {
        bs bsVar = new bs(this.a, this.b, this.c, this.z, i2, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.B, this.A, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
        return bsVar;
    }

    public final int a() {
        int i2 = this.m;
        if (i2 != -1) {
            int i3 = this.n;
            if (i3 != -1) {
                return i2 * i3;
            }
        }
        return -1;
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        String str3 = this.g;
        String str4 = this.h;
        String str5 = this.e;
        int i2 = this.d;
        String str6 = this.x;
        int i3 = this.m;
        int i4 = this.n;
        float f2 = this.o;
        int i5 = this.s;
        int i6 = this.t;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 104 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length());
        sb.append("Format(");
        sb.append(str);
        sb.append(", ");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(str4);
        sb.append(", ");
        sb.append(str5);
        sb.append(", ");
        sb.append(i2);
        sb.append(", ");
        sb.append(str6);
        sb.append(", [");
        sb.append(i3);
        sb.append(", ");
        sb.append(i4);
        sb.append(", ");
        sb.append(f2);
        sb.append("], [");
        sb.append(i5);
        sb.append(", ");
        sb.append(i6);
        sb.append("])");
        return sb.toString();
    }

    public final int hashCode() {
        if (this.C == 0) {
            String str = this.a;
            int i2 = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 527) * 31;
            String str2 = this.b;
            int hashCode2 = (((((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.c) * 31) + this.z) * 31) + this.d) * 31;
            String str3 = this.e;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            js jsVar = this.f;
            int hashCode4 = (hashCode3 + (jsVar == null ? 0 : jsVar.hashCode())) * 31;
            String str4 = this.g;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.h;
            int hashCode6 = (((((((((((((((((((((((((((hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.i) * 31) + ((int) this.l)) * 31) + this.m) * 31) + this.n) * 31) + Float.floatToIntBits(this.o)) * 31) + this.p) * 31) + Float.floatToIntBits(this.q)) * 31) + this.A) * 31) + this.s) * 31) + this.t) * 31) + this.u) * 31) + this.v) * 31) + this.w) * 31;
            String str6 = this.x;
            if (str6 != null) {
                i2 = str6.hashCode();
            }
            this.C = ((hashCode6 + i2) * 31) + this.y;
        }
        return this.C;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bs bsVar = (bs) obj;
        int i2 = this.C;
        if (i2 != 0) {
            int i3 = bsVar.C;
            if (!(i3 == 0 || i2 == i3)) {
                return false;
            }
        }
        return this.c == bsVar.c && this.z == bsVar.z && this.d == bsVar.d && this.i == bsVar.i && this.l == bsVar.l && this.m == bsVar.m && this.n == bsVar.n && this.p == bsVar.p && this.A == bsVar.A && this.s == bsVar.s && this.t == bsVar.t && this.u == bsVar.u && this.v == bsVar.v && this.w == bsVar.w && this.y == bsVar.y && Float.compare(this.o, bsVar.o) == 0 && Float.compare(this.q, bsVar.q) == 0 && vf.a((Object) this.a, (Object) bsVar.a) && vf.a((Object) this.b, (Object) bsVar.b) && vf.a((Object) this.e, (Object) bsVar.e) && vf.a((Object) this.g, (Object) bsVar.g) && vf.a((Object) this.h, (Object) bsVar.h) && vf.a((Object) this.x, (Object) bsVar.x) && Arrays.equals(this.B, bsVar.B) && vf.a((Object) this.f, (Object) bsVar.f) && vf.a((Object) this.r, (Object) bsVar.r) && vf.a((Object) this.k, (Object) bsVar.k) && b(bsVar);
    }

    public final boolean b(bs bsVar) {
        if (this.j.size() != bsVar.j.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            if (!Arrays.equals((byte[]) this.j.get(i2), (byte[]) bsVar.j.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.z);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        boolean z2 = false;
        parcel.writeParcelable(this.f, 0);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
        int size = this.j.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeByteArray((byte[]) this.j.get(i3));
        }
        parcel.writeParcelable(this.k, 0);
        parcel.writeLong(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeFloat(this.o);
        parcel.writeInt(this.p);
        parcel.writeFloat(this.q);
        if (this.B != null) {
            z2 = true;
        }
        vf.a(parcel, z2);
        byte[] bArr = this.B;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.A);
        parcel.writeParcelable(this.r, i2);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeString(this.x);
        parcel.writeInt(this.y);
    }
}
