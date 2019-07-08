package com.google.ads.interactivemedia.v3.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import kotlin.text.Typography;

/* compiled from: IMASDK */
public class abu implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    public abu(Reader reader) {
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader != null) {
            this.c = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected BEGIN_ARRAY but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void b() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 4) {
            this.n--;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected END_ARRAY but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void c() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 1) {
            a(3);
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected BEGIN_OBJECT but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void d() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 2) {
            this.n--;
            String[] strArr = this.o;
            int i3 = this.n;
            strArr[i3] = null;
            int[] iArr = this.p;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected END_OBJECT but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public boolean e() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        return (i2 == 2 || i2 == 4) ? false : true;
    }

    public abw f() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        switch (i2) {
            case 1:
                return abw.BEGIN_OBJECT;
            case 2:
                return abw.END_OBJECT;
            case 3:
                return abw.BEGIN_ARRAY;
            case 4:
                return abw.END_ARRAY;
            case 5:
            case 6:
                return abw.BOOLEAN;
            case 7:
                return abw.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return abw.STRING;
            case 12:
            case 13:
            case 14:
                return abw.NAME;
            case 15:
            case 16:
                return abw.NUMBER;
            case 17:
                return abw.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0215, code lost:
        if (a(r15) == false) goto L_0x021b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0217, code lost:
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x021b, code lost:
        if (r8 != 2) goto L_0x0241;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x021d, code lost:
        if (r10 == false) goto L_0x0241;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0223, code lost:
        if (r11 != Long.MIN_VALUE) goto L_0x0227;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0225, code lost:
        if (r16 == false) goto L_0x0241;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x022b, code lost:
        if (r11 != 0) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x022d, code lost:
        if (r16 != false) goto L_0x0241;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x022f, code lost:
        if (r16 == false) goto L_0x0232;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0232, code lost:
        r11 = -r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0233, code lost:
        r0.j = r11;
        r0.f += r3;
        r0.a = 15;
        r17 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0241, code lost:
        if (r8 == 2) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0244, code lost:
        if (r8 == 4) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0247, code lost:
        if (r8 != 7) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x024a, code lost:
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x024d, code lost:
        r0.k = r3;
        r0.a = 16;
        r17 = 16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0180 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int r() throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            int[] r1 = r0.m
            int r2 = r0.n
            int r3 = r2 + -1
            r3 = r1[r3]
            r5 = 34
            r6 = 93
            r7 = 59
            r8 = 44
            r9 = 3
            r10 = 6
            r11 = 7
            r12 = 4
            r13 = 5
            r14 = 2
            r15 = 0
            r4 = 1
            if (r3 != r4) goto L_0x0021
            int r2 = r2 - r4
            r1[r2] = r14
            goto L_0x00d4
        L_0x0021:
            if (r3 != r14) goto L_0x003c
            int r1 = r0.b(r4)
            if (r1 == r8) goto L_0x00d4
            if (r1 == r7) goto L_0x0037
            if (r1 != r6) goto L_0x0030
            r0.a = r12
            return r12
        L_0x0030:
            java.lang.String r1 = "Unterminated array"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x0037:
            r20.t()
            goto L_0x00d4
        L_0x003c:
            if (r3 == r9) goto L_0x02e0
            if (r3 != r13) goto L_0x0042
            goto L_0x02e0
        L_0x0042:
            if (r3 != r12) goto L_0x0077
            int r2 = r2 - r4
            r1[r2] = r13
            int r1 = r0.b(r4)
            r2 = 58
            if (r1 == r2) goto L_0x00d4
            r2 = 61
            if (r1 != r2) goto L_0x0070
            r20.t()
            int r1 = r0.f
            int r2 = r0.g
            if (r1 < r2) goto L_0x0062
            boolean r1 = r0.b(r4)
            if (r1 == 0) goto L_0x00d4
        L_0x0062:
            char[] r1 = r0.e
            int r2 = r0.f
            char r1 = r1[r2]
            r12 = 62
            if (r1 != r12) goto L_0x00d4
            int r2 = r2 + r4
            r0.f = r2
            goto L_0x00d4
        L_0x0070:
            java.lang.String r1 = "Expected ':'"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x0077:
            if (r3 != r10) goto L_0x00b9
            boolean r1 = r0.d
            if (r1 == 0) goto L_0x00b1
            r0.b(r4)
            int r1 = r0.f
            int r1 = r1 - r4
            r0.f = r1
            int r1 = r0.f
            char[] r2 = b
            int r12 = r2.length
            int r1 = r1 + r12
            int r12 = r0.g
            if (r1 <= r12) goto L_0x0096
            int r1 = r2.length
            boolean r1 = r0.b(r1)
            if (r1 == 0) goto L_0x00b1
        L_0x0096:
            r1 = 0
        L_0x0097:
            char[] r2 = b
            int r12 = r2.length
            if (r1 >= r12) goto L_0x00ab
            char[] r12 = r0.e
            int r10 = r0.f
            int r10 = r10 + r1
            char r10 = r12[r10]
            char r2 = r2[r1]
            if (r10 != r2) goto L_0x00b1
            int r1 = r1 + 1
            r10 = 6
            goto L_0x0097
        L_0x00ab:
            int r1 = r0.f
            int r2 = r2.length
            int r1 = r1 + r2
            r0.f = r1
        L_0x00b1:
            int[] r1 = r0.m
            int r2 = r0.n
            int r2 = r2 - r4
            r1[r2] = r11
            goto L_0x00d4
        L_0x00b9:
            if (r3 != r11) goto L_0x00d0
            int r1 = r0.b(r15)
            r2 = -1
            if (r1 != r2) goto L_0x00c7
            r1 = 17
            r0.a = r1
            return r1
        L_0x00c7:
            r20.t()
            int r1 = r0.f
            int r1 = r1 - r4
            r0.f = r1
            goto L_0x00d4
        L_0x00d0:
            r1 = 8
            if (r3 == r1) goto L_0x02d8
        L_0x00d4:
            int r1 = r0.b(r4)
            if (r1 == r5) goto L_0x02d3
            r2 = 39
            if (r1 == r2) goto L_0x02cb
            if (r1 == r8) goto L_0x02b2
            if (r1 == r7) goto L_0x02b2
            r2 = 91
            if (r1 == r2) goto L_0x02af
            if (r1 == r6) goto L_0x02a8
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x02a4
            int r1 = r0.f
            int r1 = r1 - r4
            r0.f = r1
            char[] r1 = r0.e
            int r2 = r0.f
            char r1 = r1[r2]
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L_0x0123
            r2 = 84
            if (r1 != r2) goto L_0x0100
            goto L_0x0123
        L_0x0100:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 == r2) goto L_0x011c
            r2 = 70
            if (r1 != r2) goto L_0x0109
            goto L_0x011c
        L_0x0109:
            r2 = 110(0x6e, float:1.54E-43)
            if (r1 == r2) goto L_0x0115
            r2 = 78
            if (r1 != r2) goto L_0x0112
            goto L_0x0115
        L_0x0112:
            r2 = 0
            goto L_0x017e
        L_0x0115:
            java.lang.String r1 = "null"
            java.lang.String r2 = "NULL"
            r3 = r2
            r2 = 7
            goto L_0x0129
        L_0x011c:
            java.lang.String r1 = "false"
            java.lang.String r2 = "FALSE"
            r3 = r2
            r2 = 6
            goto L_0x0129
        L_0x0123:
            java.lang.String r1 = "true"
            java.lang.String r2 = "TRUE"
            r3 = r2
            r2 = 5
        L_0x0129:
            int r5 = r1.length()
            r6 = 1
        L_0x012e:
            if (r6 >= r5) goto L_0x0159
            int r7 = r0.f
            int r7 = r7 + r6
            int r8 = r0.g
            if (r7 < r8) goto L_0x0141
            int r7 = r6 + 1
            boolean r7 = r0.b(r7)
            if (r7 != 0) goto L_0x0141
            r2 = 0
            goto L_0x017e
        L_0x0141:
            char[] r7 = r0.e
            int r8 = r0.f
            int r8 = r8 + r6
            char r7 = r7[r8]
            char r8 = r1.charAt(r6)
            if (r7 == r8) goto L_0x0156
            char r8 = r3.charAt(r6)
            if (r7 == r8) goto L_0x0156
            r2 = 0
            goto L_0x017e
        L_0x0156:
            int r6 = r6 + 1
            goto L_0x012e
        L_0x0159:
            int r1 = r0.f
            int r1 = r1 + r5
            int r3 = r0.g
            if (r1 < r3) goto L_0x0168
            int r1 = r5 + 1
            boolean r1 = r0.b(r1)
            if (r1 == 0) goto L_0x0177
        L_0x0168:
            char[] r1 = r0.e
            int r3 = r0.f
            int r3 = r3 + r5
            char r1 = r1[r3]
            boolean r1 = r0.a(r1)
            if (r1 == 0) goto L_0x0177
            r2 = 0
            goto L_0x017e
        L_0x0177:
            int r1 = r0.f
            int r1 = r1 + r5
            r0.f = r1
            r0.a = r2
        L_0x017e:
            if (r2 == 0) goto L_0x0181
            return r2
        L_0x0181:
            char[] r1 = r0.e
            int r2 = r0.f
            int r3 = r0.g
            r5 = 0
            r7 = r3
            r11 = r5
            r3 = 0
            r8 = 0
            r10 = 1
            r16 = 0
        L_0x0190:
            int r15 = r2 + r3
            if (r15 != r7) goto L_0x01a3
            int r2 = r1.length
            if (r3 == r2) goto L_0x024a
            int r2 = r3 + 1
            boolean r2 = r0.b(r2)
            if (r2 == 0) goto L_0x021b
            int r2 = r0.f
            int r7 = r0.g
        L_0x01a3:
            int r15 = r2 + r3
            char r15 = r1[r15]
            r13 = 43
            if (r15 == r13) goto L_0x027a
            r13 = 69
            if (r15 == r13) goto L_0x026d
            r13 = 101(0x65, float:1.42E-43)
            if (r15 == r13) goto L_0x026d
            switch(r15) {
                case 45: goto L_0x025e;
                case 46: goto L_0x0256;
                default: goto L_0x01b6;
            }
        L_0x01b6:
            r13 = 48
            if (r15 < r13) goto L_0x0211
            r13 = 57
            if (r15 <= r13) goto L_0x01bf
            goto L_0x0211
        L_0x01bf:
            if (r8 == r4) goto L_0x0206
            if (r8 != 0) goto L_0x01c4
            goto L_0x0206
        L_0x01c4:
            if (r8 != r14) goto L_0x01ee
            int r13 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r13 == 0) goto L_0x024a
            r18 = 10
            long r18 = r18 * r11
            int r15 = r15 + -48
            long r4 = (long) r15
            long r18 = r18 - r4
            r4 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x01e5
            if (r6 != 0) goto L_0x01e3
            int r4 = (r18 > r11 ? 1 : (r18 == r11 ? 0 : -1))
            if (r4 >= 0) goto L_0x01e3
            goto L_0x01e5
        L_0x01e3:
            r4 = 0
            goto L_0x01e6
        L_0x01e5:
            r4 = 1
        L_0x01e6:
            r4 = r4 & r10
            r10 = r4
            r11 = r18
            r5 = 0
            goto L_0x027e
        L_0x01ee:
            if (r8 != r9) goto L_0x01f5
            r5 = 0
            r8 = 4
            goto L_0x027e
        L_0x01f5:
            r4 = 5
            if (r8 == r4) goto L_0x0200
            r4 = 6
            if (r8 != r4) goto L_0x01fc
            goto L_0x0201
        L_0x01fc:
            r5 = 0
            goto L_0x027e
        L_0x0200:
            r4 = 6
        L_0x0201:
            r5 = 0
            r8 = 7
            goto L_0x027e
        L_0x0206:
            r4 = 6
            int r15 = r15 + -48
            int r5 = -r15
            long r5 = (long) r5
            r11 = r5
            r5 = 0
            r8 = 2
            goto L_0x027e
        L_0x0211:
            boolean r1 = r0.a(r15)
            if (r1 == 0) goto L_0x021b
            r17 = 0
            goto L_0x0286
        L_0x021b:
            if (r8 != r14) goto L_0x0241
            if (r10 == 0) goto L_0x0241
            r1 = -9223372036854775808
            int r4 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x0227
            if (r16 == 0) goto L_0x0241
        L_0x0227:
            r5 = 0
            int r1 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x022f
            if (r16 != 0) goto L_0x0241
        L_0x022f:
            if (r16 == 0) goto L_0x0232
            goto L_0x0233
        L_0x0232:
            long r11 = -r11
        L_0x0233:
            r0.j = r11
            int r1 = r0.f
            int r1 = r1 + r3
            r0.f = r1
            r15 = 15
            r0.a = r15
            r17 = 15
            goto L_0x0286
        L_0x0241:
            if (r8 == r14) goto L_0x024d
            r1 = 4
            if (r8 == r1) goto L_0x024d
            r1 = 7
            if (r8 != r1) goto L_0x024a
            goto L_0x024d
        L_0x024a:
            r17 = 0
            goto L_0x0286
        L_0x024d:
            r0.k = r3
            r15 = 16
            r0.a = r15
            r17 = 16
            goto L_0x0286
        L_0x0256:
            r4 = 6
            if (r8 != r14) goto L_0x025b
            r8 = 3
            goto L_0x027e
        L_0x025b:
            r17 = 0
            goto L_0x0286
        L_0x025e:
            r4 = 6
            if (r8 != 0) goto L_0x0265
            r8 = 1
            r16 = 1
            goto L_0x027e
        L_0x0265:
            r15 = 5
            if (r8 != r15) goto L_0x026a
            r8 = 6
            goto L_0x027e
        L_0x026a:
            r17 = 0
            goto L_0x0286
        L_0x026d:
            r4 = 6
            r15 = 5
            if (r8 == r14) goto L_0x0278
            r4 = 4
            if (r8 != r4) goto L_0x0275
            goto L_0x0278
        L_0x0275:
            r17 = 0
            goto L_0x0286
        L_0x0278:
            r8 = 5
            goto L_0x027e
        L_0x027a:
            r15 = 5
            if (r8 != r15) goto L_0x0284
            r8 = 6
        L_0x027e:
            int r3 = r3 + 1
            r4 = 1
            r13 = 5
            goto L_0x0190
        L_0x0284:
            r17 = 0
        L_0x0286:
            if (r17 == 0) goto L_0x0289
            return r17
        L_0x0289:
            char[] r1 = r0.e
            int r2 = r0.f
            char r1 = r1[r2]
            boolean r1 = r0.a(r1)
            if (r1 == 0) goto L_0x029d
            r20.t()
            r1 = 10
            r0.a = r1
            return r1
        L_0x029d:
            java.lang.String r1 = "Expected value"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x02a4:
            r1 = 1
            r0.a = r1
            return r1
        L_0x02a8:
            r1 = 1
            if (r3 != r1) goto L_0x02b3
            r2 = 4
            r0.a = r2
            return r2
        L_0x02af:
            r0.a = r9
            return r9
        L_0x02b2:
            r1 = 1
        L_0x02b3:
            if (r3 == r1) goto L_0x02bf
            if (r3 != r14) goto L_0x02b8
            goto L_0x02bf
        L_0x02b8:
            java.lang.String r1 = "Unexpected value"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x02bf:
            r20.t()
            int r2 = r0.f
            int r2 = r2 - r1
            r0.f = r2
            r1 = 7
            r0.a = r1
            return r1
        L_0x02cb:
            r20.t()
            r1 = 8
            r0.a = r1
            return r1
        L_0x02d3:
            r1 = 9
            r0.a = r1
            return r1
        L_0x02d8:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "JsonReader is closed"
            r1.<init>(r2)
            throw r1
        L_0x02e0:
            int[] r1 = r0.m
            int r2 = r0.n
            r4 = 1
            int r2 = r2 - r4
            r6 = 4
            r1[r2] = r6
            r1 = 5
            if (r3 != r1) goto L_0x0307
            int r1 = r0.b(r4)
            if (r1 == r8) goto L_0x0305
            if (r1 == r7) goto L_0x0302
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x02fb
            r0.a = r14
            return r14
        L_0x02fb:
            java.lang.String r1 = "Unterminated object"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x0302:
            r20.t()
        L_0x0305:
            r1 = 1
            goto L_0x0308
        L_0x0307:
            r1 = 1
        L_0x0308:
            int r2 = r0.b(r1)
            if (r2 == r5) goto L_0x0346
            r4 = 39
            if (r2 == r4) goto L_0x033e
            r4 = 125(0x7d, float:1.75E-43)
            if (r2 == r4) goto L_0x0331
            r20.t()
            int r3 = r0.f
            int r3 = r3 - r1
            r0.f = r3
            char r1 = (char) r2
            boolean r1 = r0.a(r1)
            if (r1 == 0) goto L_0x032a
            r1 = 14
            r0.a = r1
            return r1
        L_0x032a:
            java.lang.String r1 = "Expected name"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x0331:
            r1 = 5
            if (r3 == r1) goto L_0x0337
            r0.a = r14
            return r14
        L_0x0337:
            java.lang.String r1 = "Expected name"
            java.io.IOException r1 = r0.a(r1)
            throw r1
        L_0x033e:
            r20.t()
            r1 = 12
            r0.a = r1
            return r1
        L_0x0346:
            r1 = 13
            r0.a = r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.abu.r():int");
    }

    private final boolean a(char c2) throws IOException {
        switch (c2) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                t();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 14) {
            str = o();
        } else if (i2 == 12) {
            str = b('\'');
        } else if (i2 == 13) {
            str = b((char) Typography.quote);
        } else {
            StringBuilder sb = new StringBuilder("Expected a name but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 0;
        this.o[this.n - 1] = str;
        return str;
    }

    public String h() throws IOException {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 10) {
            str = o();
        } else if (i2 == 8) {
            str = b('\'');
        } else if (i2 == 9) {
            str = b((char) Typography.quote);
        } else if (i2 == 11) {
            str = this.l;
            this.l = null;
        } else if (i2 == 15) {
            str = Long.toString(this.j);
        } else if (i2 == 16) {
            str = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            StringBuilder sb = new StringBuilder("Expected a string but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    public boolean i() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 5) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            StringBuilder sb = new StringBuilder("Expected a boolean but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
    }

    public void j() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected null but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public double k() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9) {
            this.l = b(i2 == 8 ? '\'' : Typography.quote);
        } else if (i2 == 10) {
            this.l = o();
        } else if (i2 != 11) {
            StringBuilder sb = new StringBuilder("Expected a double but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        }
        StringBuilder sb2 = new StringBuilder("JSON forbids NaN and infinities: ");
        sb2.append(parseDouble);
        sb2.append(s());
        throw new aby(sb2.toString());
    }

    public long l() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = o();
            } else {
                this.l = b(i2 == 8 ? '\'' : Typography.quote);
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            StringBuilder sb = new StringBuilder("Expected a long but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j2 = (long) parseDouble;
        if (((double) j2) == parseDouble) {
            this.l = null;
            this.a = 0;
            int[] iArr3 = this.p;
            int i5 = this.n - 1;
            iArr3[i5] = iArr3[i5] + 1;
            return j2;
        }
        StringBuilder sb2 = new StringBuilder("Expected a long but was ");
        sb2.append(this.l);
        sb2.append(s());
        throw new NumberFormatException(sb2.toString());
    }

    private final String b(char c2) throws IOException {
        char[] cArr = this.e;
        StringBuilder sb = null;
        do {
            int i2 = this.f;
            int i3 = this.g;
            int i4 = i2;
            while (i2 < i3) {
                int i5 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f = i5;
                    int i6 = (i5 - i4) - 1;
                    if (sb == null) {
                        return new String(cArr, i4, i6);
                    }
                    sb.append(cArr, i4, i6);
                    return sb.toString();
                } else if (c3 == '\\') {
                    this.f = i5;
                    int i7 = (i5 - i4) - 1;
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i7 + 1) << 1, 16));
                    }
                    sb.append(cArr, i4, i7);
                    sb.append(v());
                    i4 = this.f;
                    i3 = this.g;
                    i2 = i4;
                } else {
                    if (c3 == 10) {
                        this.h++;
                        this.i = i5;
                    }
                    i2 = i5;
                }
            }
            if (sb == null) {
                sb = new StringBuilder(Math.max((i2 - i4) << 1, 16));
            }
            sb.append(cArr, i4, i2 - i4);
            this.f = i2;
        } while (b(1));
        throw a("Unterminated string");
    }

    private final String o() throws IOException {
        String str;
        int i2 = 0;
        StringBuilder sb = null;
        int i3 = 0;
        while (true) {
            int i4 = this.f;
            if (i4 + i3 < this.g) {
                switch (this.e[i4 + i3]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        t();
                        break;
                    default:
                        i3++;
                        continue;
                }
            } else if (i3 >= this.e.length) {
                if (sb == null) {
                    sb = new StringBuilder(Math.max(i3, 16));
                }
                sb.append(this.e, this.f, i3);
                this.f += i3;
                if (b(1)) {
                    i3 = 0;
                }
            } else if (b(i3 + 1)) {
            }
        }
        i2 = i3;
        if (sb == null) {
            str = new String(this.e, this.f, i2);
        } else {
            sb.append(this.e, this.f, i2);
            str = sb.toString();
        }
        this.f += i2;
        return str;
    }

    private final void c(char c2) throws IOException {
        char[] cArr = this.e;
        do {
            int i2 = this.f;
            int i3 = this.g;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f = i4;
                    return;
                } else if (c3 == '\\') {
                    this.f = i4;
                    v();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    if (c3 == 10) {
                        this.h++;
                        this.i = i4;
                    }
                    i2 = i4;
                }
            }
            this.f = i2;
        } while (b(1));
        throw a("Unterminated string");
    }

    public int m() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            long j2 = this.j;
            int i3 = (int) j2;
            if (j2 == ((long) i3)) {
                this.a = 0;
                int[] iArr = this.p;
                int i4 = this.n - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            StringBuilder sb = new StringBuilder("Expected an int but was ");
            sb.append(this.j);
            sb.append(s());
            throw new NumberFormatException(sb.toString());
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = o();
            } else {
                this.l = b(i2 == 8 ? '\'' : Typography.quote);
            }
            try {
                int parseInt = Integer.parseInt(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i5 = this.n - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            StringBuilder sb2 = new StringBuilder("Expected an int but was ");
            sb2.append(f());
            sb2.append(s());
            throw new IllegalStateException(sb2.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        int i6 = (int) parseDouble;
        if (((double) i6) == parseDouble) {
            this.l = null;
            this.a = 0;
            int[] iArr3 = this.p;
            int i7 = this.n - 1;
            iArr3[i7] = iArr3[i7] + 1;
            return i6;
        }
        StringBuilder sb3 = new StringBuilder("Expected an int but was ");
        sb3.append(this.l);
        sb3.append(s());
        throw new NumberFormatException(sb3.toString());
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
        r7.f += r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void n() throws java.io.IOException {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r7.a
            if (r2 != 0) goto L_0x000a
            int r2 = r7.r()
        L_0x000a:
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L_0x0015
            r7.a(r4)
            int r1 = r1 + 1
            goto L_0x008e
        L_0x0015:
            if (r2 != r4) goto L_0x001e
            r7.a(r3)
            int r1 = r1 + 1
            goto L_0x008e
        L_0x001e:
            r3 = 4
            if (r2 != r3) goto L_0x002a
            int r2 = r7.n
            int r2 = r2 - r4
            r7.n = r2
            int r1 = r1 + -1
            goto L_0x008e
        L_0x002a:
            r3 = 2
            if (r2 != r3) goto L_0x0035
            int r2 = r7.n
            int r2 = r2 - r4
            r7.n = r2
            int r1 = r1 + -1
            goto L_0x008e
        L_0x0035:
            r3 = 14
            if (r2 == r3) goto L_0x0068
            r3 = 10
            if (r2 != r3) goto L_0x003e
            goto L_0x0068
        L_0x003e:
            r3 = 8
            if (r2 == r3) goto L_0x0062
            r3 = 12
            if (r2 != r3) goto L_0x0047
            goto L_0x0062
        L_0x0047:
            r3 = 9
            if (r2 == r3) goto L_0x005c
            r3 = 13
            if (r2 != r3) goto L_0x0050
            goto L_0x005c
        L_0x0050:
            r3 = 16
            if (r2 != r3) goto L_0x008e
            int r2 = r7.f
            int r3 = r7.k
            int r2 = r2 + r3
            r7.f = r2
            goto L_0x008e
        L_0x005c:
            r2 = 34
            r7.c(r2)
            goto L_0x008e
        L_0x0062:
            r2 = 39
            r7.c(r2)
            goto L_0x008e
        L_0x0068:
            r2 = 0
        L_0x0069:
            int r3 = r7.f
            int r5 = r3 + r2
            int r6 = r7.g
            if (r5 >= r6) goto L_0x0085
            char[] r5 = r7.e
            int r3 = r3 + r2
            char r3 = r5[r3]
            switch(r3) {
                case 9: goto L_0x007f;
                case 10: goto L_0x007f;
                case 12: goto L_0x007f;
                case 13: goto L_0x007f;
                case 32: goto L_0x007f;
                case 35: goto L_0x007c;
                case 44: goto L_0x007f;
                case 47: goto L_0x007c;
                case 58: goto L_0x007f;
                case 59: goto L_0x007c;
                case 61: goto L_0x007c;
                case 91: goto L_0x007f;
                case 92: goto L_0x007c;
                case 93: goto L_0x007f;
                case 123: goto L_0x007f;
                case 125: goto L_0x007f;
                default: goto L_0x0079;
            }
        L_0x0079:
            int r2 = r2 + 1
            goto L_0x0069
        L_0x007c:
            r7.t()
        L_0x007f:
            int r3 = r7.f
            int r3 = r3 + r2
            r7.f = r3
            goto L_0x008e
        L_0x0085:
            int r3 = r3 + r2
            r7.f = r3
            boolean r2 = r7.b(r4)
            if (r2 != 0) goto L_0x0068
        L_0x008e:
            r7.a = r0
            if (r1 != 0) goto L_0x0002
            int[] r0 = r7.p
            int r1 = r7.n
            int r2 = r1 + -1
            r3 = r0[r2]
            int r3 = r3 + r4
            r0[r2] = r3
            java.lang.String[] r0 = r7.o
            int r1 = r1 - r4
            java.lang.String r2 = "null"
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.abu.n():void");
    }

    private final void a(int i2) {
        int i3 = this.n;
        int[] iArr = this.m;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[(i3 << 1)];
            int[] iArr3 = new int[(i3 << 1)];
            String[] strArr = new String[(i3 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.p, 0, iArr3, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr2;
            this.p = iArr3;
            this.o = strArr;
        }
        int[] iArr4 = this.m;
        int i4 = this.n;
        this.n = i4 + 1;
        iArr4[i4] = i2;
    }

    private final boolean b(int i2) throws IOException {
        char[] cArr = this.e;
        int i3 = this.i;
        int i4 = this.f;
        this.i = i3 - i4;
        int i5 = this.g;
        if (i5 != i4) {
            this.g = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            Reader reader = this.c;
            int i6 = this.g;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.g += read;
            if (this.h == 0) {
                int i7 = this.i;
                if (i7 == 0 && this.g > 0 && cArr[0] == 65279) {
                    this.f++;
                    this.i = i7 + 1;
                    i2++;
                }
            }
        } while (this.g < i2);
        return true;
    }

    private final int b(boolean z) throws IOException {
        char[] cArr = this.e;
        int i2 = this.f;
        int i3 = this.g;
        while (true) {
            boolean z2 = true;
            if (i2 == i3) {
                this.f = i2;
                if (b(1)) {
                    i2 = this.f;
                    i3 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    StringBuilder sb = new StringBuilder("End of input");
                    sb.append(s());
                    throw new EOFException(sb.toString());
                }
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == 10) {
                this.h++;
                this.i = i4;
            } else if (!(c2 == ' ' || c2 == 13 || c2 == 9)) {
                if (c2 == '/') {
                    this.f = i4;
                    if (i4 == i3) {
                        this.f--;
                        boolean b2 = b(2);
                        this.f++;
                        if (!b2) {
                            return c2;
                        }
                    }
                    t();
                    int i5 = this.f;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f = i5 + 1;
                        String str = "*/";
                        while (true) {
                            int i6 = 0;
                            if (this.f + 2 > this.g && !b(2)) {
                                z2 = false;
                                break;
                            }
                            char[] cArr2 = this.e;
                            int i7 = this.f;
                            if (cArr2[i7] != 10) {
                                while (i6 < 2) {
                                    if (this.e[this.f + i6] == str.charAt(i6)) {
                                        i6++;
                                    }
                                }
                                break;
                            }
                            this.h++;
                            this.i = i7 + 1;
                            this.f++;
                        }
                        if (z2) {
                            i2 = this.f + 2;
                            i3 = this.g;
                        } else {
                            throw a("Unterminated comment");
                        }
                    } else if (c3 != '/') {
                        return c2;
                    } else {
                        this.f = i5 + 1;
                        u();
                        i2 = this.f;
                        i3 = this.g;
                    }
                } else if (c2 == '#') {
                    this.f = i4;
                    t();
                    u();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    this.f = i4;
                    return c2;
                }
            }
            i2 = i4;
        }
    }

    private final void t() throws IOException {
        if (!this.d) {
            throw a("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private final void u() throws IOException {
        char c2;
        do {
            if (this.f >= this.g && !b(1)) {
                break;
            }
            char[] cArr = this.e;
            int i2 = this.f;
            this.f = i2 + 1;
            c2 = cArr[i2];
            if (c2 == 10) {
                this.h++;
                this.i = this.f;
                return;
            }
        } while (c2 != 13);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(s());
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String s() {
        int i2 = this.h + 1;
        int i3 = (this.f - this.i) + 1;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i2);
        sb.append(" column ");
        sb.append(i3);
        sb.append(" path ");
        sb.append(p());
        return sb.toString();
    }

    public String p() {
        StringBuilder sb = new StringBuilder("$");
        int i2 = this.n;
        for (int i3 = 0; i3 < i2; i3++) {
            switch (this.m[i3]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(this.p[i3]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String[] strArr = this.o;
                    if (strArr[i3] == null) {
                        break;
                    } else {
                        sb.append(strArr[i3]);
                        break;
                    }
            }
        }
        return sb.toString();
    }

    private final char v() throws IOException {
        int i2;
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i3 = this.f;
            this.f = i3 + 1;
            char c2 = cArr[i3];
            if (c2 == 10) {
                this.h++;
                this.i = this.f;
            } else if (!(c2 == '\"' || c2 == '\'' || c2 == '/' || c2 == '\\')) {
                if (c2 == 'b') {
                    return 8;
                }
                if (c2 == 'f') {
                    return 12;
                }
                if (c2 == 'n') {
                    return 10;
                }
                if (c2 == 'r') {
                    return 13;
                }
                switch (c2) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.f + 4 <= this.g || b(4)) {
                            char c3 = 0;
                            int i4 = this.f;
                            int i5 = i4 + 4;
                            while (i4 < i5) {
                                char c4 = this.e[i4];
                                char c5 = (char) (c3 << 4);
                                if (c4 >= '0' && c4 <= '9') {
                                    i2 = c4 - '0';
                                } else if (c4 >= 'a' && c4 <= 'f') {
                                    i2 = (c4 - 'a') + 10;
                                } else if (c4 < 'A' || c4 > 'F') {
                                    StringBuilder sb = new StringBuilder("\\u");
                                    sb.append(new String(this.e, this.f, 4));
                                    throw new NumberFormatException(sb.toString());
                                } else {
                                    i2 = (c4 - 'A') + 10;
                                }
                                c3 = (char) (c5 + i2);
                                i4++;
                            }
                            this.f += 4;
                            return c3;
                        }
                        throw a("Unterminated escape sequence");
                    default:
                        throw a("Invalid escape sequence");
                }
            }
            return c2;
        }
        throw a("Unterminated escape sequence");
    }

    private final IOException a(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(s());
        throw new aby(sb.toString());
    }

    static {
        ym.a = new abv();
    }
}
