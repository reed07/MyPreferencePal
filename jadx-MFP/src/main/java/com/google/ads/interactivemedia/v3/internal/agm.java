package com.google.ads.interactivemedia.v3.internal;

import java.util.Map.Entry;
import kotlin.UShort;

/* compiled from: IMASDK */
final class agm<K, V> extends age<K, V> {
    private static final age<Object, Object> a = new agm(null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    private final transient Object b;
    private final transient Object[] c;
    private final transient int d;

    /* access modifiers changed from: 0000 */
    public final boolean e() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0057, code lost:
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0095, code lost:
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ca, code lost:
        r2[r7] = r3;
        r1 = r1 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> com.google.ads.interactivemedia.v3.internal.agm<K, V> a(int r10, java.lang.Object[] r11) {
        /*
            if (r10 != 0) goto L_0x0007
            com.google.ads.interactivemedia.v3.internal.age<java.lang.Object, java.lang.Object> r10 = a
            com.google.ads.interactivemedia.v3.internal.agm r10 = (com.google.ads.interactivemedia.v3.internal.agm) r10
            return r10
        L_0x0007:
            r0 = 0
            r1 = 0
            r2 = 1
            if (r10 != r2) goto L_0x0019
            r10 = r11[r1]
            r1 = r11[r2]
            com.google.ads.interactivemedia.v3.internal.agj.a(r10, r1)
            com.google.ads.interactivemedia.v3.internal.agm r10 = new com.google.ads.interactivemedia.v3.internal.agm
            r10.<init>(r0, r11, r2)
            return r10
        L_0x0019:
            int r3 = r11.length
            int r3 = r3 >> r2
            com.google.ads.interactivemedia.v3.internal.afx.b(r10, r3)
            int r3 = com.google.ads.interactivemedia.v3.internal.agg.a(r10)
            if (r10 != r2) goto L_0x002e
            r1 = r11[r1]
            r2 = r11[r2]
            com.google.ads.interactivemedia.v3.internal.agj.a(r1, r2)
            r2 = r0
            goto L_0x00df
        L_0x002e:
            int r0 = r3 + -1
            r2 = 128(0x80, float:1.794E-43)
            r4 = -1
            if (r3 > r2) goto L_0x006d
            byte[] r2 = new byte[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x003a:
            if (r1 >= r10) goto L_0x00df
            int r3 = r1 * 2
            r4 = r11[r3]
            r5 = r3 ^ 1
            r5 = r11[r5]
            com.google.ads.interactivemedia.v3.internal.agj.a(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.ads.interactivemedia.v3.internal.agj.b(r6)
        L_0x004f:
            r6 = r6 & r0
            byte r7 = r2[r6]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x005d
            byte r3 = (byte) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x003a
        L_0x005d:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x0068
            int r6 = r6 + 1
            goto L_0x004f
        L_0x0068:
            java.lang.IllegalArgumentException r10 = a(r4, r5, r11, r7)
            throw r10
        L_0x006d:
            r2 = 32768(0x8000, float:4.5918E-41)
            if (r3 > r2) goto L_0x00ab
            short[] r2 = new short[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x0077:
            if (r1 >= r10) goto L_0x00df
            int r3 = r1 * 2
            r4 = r11[r3]
            r5 = r3 ^ 1
            r5 = r11[r5]
            com.google.ads.interactivemedia.v3.internal.agj.a(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.ads.interactivemedia.v3.internal.agj.b(r6)
        L_0x008c:
            r6 = r6 & r0
            short r7 = r2[r6]
            r8 = 65535(0xffff, float:9.1834E-41)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x009b
            short r3 = (short) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x0077
        L_0x009b:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x00a6
            int r6 = r6 + 1
            goto L_0x008c
        L_0x00a6:
            java.lang.IllegalArgumentException r10 = a(r4, r5, r11, r7)
            throw r10
        L_0x00ab:
            int[] r2 = new int[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x00b0:
            if (r1 >= r10) goto L_0x00df
            int r3 = r1 * 2
            r5 = r11[r3]
            r6 = r3 ^ 1
            r6 = r11[r6]
            com.google.ads.interactivemedia.v3.internal.agj.a(r5, r6)
            int r7 = r5.hashCode()
            int r7 = com.google.ads.interactivemedia.v3.internal.agj.b(r7)
        L_0x00c5:
            r7 = r7 & r0
            r8 = r2[r7]
            if (r8 != r4) goto L_0x00cf
            r2[r7] = r3
            int r1 = r1 + 1
            goto L_0x00b0
        L_0x00cf:
            r9 = r11[r8]
            boolean r9 = r9.equals(r5)
            if (r9 != 0) goto L_0x00da
            int r7 = r7 + 1
            goto L_0x00c5
        L_0x00da:
            java.lang.IllegalArgumentException r10 = a(r5, r6, r11, r8)
            throw r10
        L_0x00df:
            com.google.ads.interactivemedia.v3.internal.agm r0 = new com.google.ads.interactivemedia.v3.internal.agm
            r0.<init>(r2, r11, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.agm.a(int, java.lang.Object[]):com.google.ads.interactivemedia.v3.internal.agm");
    }

    private static IllegalArgumentException a(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    private agm(Object obj, Object[] objArr, int i) {
        this.b = obj;
        this.c = objArr;
        this.d = i;
    }

    public final int size() {
        return this.d;
    }

    public final V get(Object obj) {
        Object obj2 = this.b;
        V[] vArr = this.c;
        int i = this.d;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (vArr[0].equals(obj)) {
                return vArr[1];
            }
            return null;
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int b2 = agj.b(obj.hashCode());
                while (true) {
                    int i2 = b2 & length;
                    byte b3 = bArr[i2] & 255;
                    if (b3 == 255) {
                        return null;
                    }
                    if (vArr[b3].equals(obj)) {
                        return vArr[b3 ^ 1];
                    }
                    b2 = i2 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int b4 = agj.b(obj.hashCode());
                while (true) {
                    int i3 = b4 & length2;
                    short s = sArr[i3] & UShort.MAX_VALUE;
                    if (s == 65535) {
                        return null;
                    }
                    if (vArr[s].equals(obj)) {
                        return vArr[s ^ 1];
                    }
                    b4 = i3 + 1;
                }
            } else {
                int[] iArr = (int[]) obj2;
                int length3 = iArr.length - 1;
                int b5 = agj.b(obj.hashCode());
                while (true) {
                    int i4 = b5 & length3;
                    int i5 = iArr[i4];
                    if (i5 == -1) {
                        return null;
                    }
                    if (vArr[i5].equals(obj)) {
                        return vArr[i5 ^ 1];
                    }
                    b5 = i4 + 1;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final agg<Entry<K, V>> b() {
        return new agn(this, this.c, 0, this.d);
    }

    /* access modifiers changed from: 0000 */
    public final agg<K> c() {
        return new agp(this, new agq(this.c, 0, this.d));
    }

    /* access modifiers changed from: 0000 */
    public final aga<V> d() {
        return new agq(this.c, 1, this.d);
    }
}
