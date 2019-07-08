package com.google.ads.interactivemedia.v3.internal;

import java.util.Set;

/* compiled from: IMASDK */
public final class ahj {
    private static final ThreadLocal<Set<ahl>> a = new ThreadLocal<>();
    private final int b;
    private int c;

    private static Set<ahl> a() {
        return (Set) a.get();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:25|26|27|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0079 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.Object r5, java.lang.Class<?> r6, com.google.ads.interactivemedia.v3.internal.ahj r7, boolean r8, java.lang.String[] r9) {
        /*
            java.util.Set r0 = a()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0015
            com.google.ads.interactivemedia.v3.internal.ahl r3 = new com.google.ads.interactivemedia.v3.internal.ahl
            r3.<init>(r5)
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x0019
            return
        L_0x0019:
            java.util.Set r0 = a()     // Catch:{ all -> 0x0088 }
            if (r0 != 0) goto L_0x0029
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x0088 }
            r0.<init>()     // Catch:{ all -> 0x0088 }
            java.lang.ThreadLocal<java.util.Set<com.google.ads.interactivemedia.v3.internal.ahl>> r3 = a     // Catch:{ all -> 0x0088 }
            r3.set(r0)     // Catch:{ all -> 0x0088 }
        L_0x0029:
            com.google.ads.interactivemedia.v3.internal.ahl r3 = new com.google.ads.interactivemedia.v3.internal.ahl     // Catch:{ all -> 0x0088 }
            r3.<init>(r5)     // Catch:{ all -> 0x0088 }
            r0.add(r3)     // Catch:{ all -> 0x0088 }
            java.lang.reflect.Field[] r6 = r6.getDeclaredFields()     // Catch:{ all -> 0x0088 }
            java.lang.reflect.AccessibleObject.setAccessible(r6, r2)     // Catch:{ all -> 0x0088 }
            int r0 = r6.length     // Catch:{ all -> 0x0088 }
        L_0x0039:
            if (r1 >= r0) goto L_0x0084
            r2 = r6[r1]     // Catch:{ all -> 0x0088 }
            java.lang.String r3 = r2.getName()     // Catch:{ all -> 0x0088 }
            boolean r3 = com.google.ads.interactivemedia.v3.internal.ahe.a(r9, r3)     // Catch:{ all -> 0x0088 }
            if (r3 != 0) goto L_0x0081
            java.lang.String r3 = r2.getName()     // Catch:{ all -> 0x0088 }
            java.lang.String r4 = "$"
            boolean r3 = r3.contains(r4)     // Catch:{ all -> 0x0088 }
            if (r3 != 0) goto L_0x0081
            if (r8 != 0) goto L_0x005f
            int r3 = r2.getModifiers()     // Catch:{ all -> 0x0088 }
            boolean r3 = java.lang.reflect.Modifier.isTransient(r3)     // Catch:{ all -> 0x0088 }
            if (r3 != 0) goto L_0x0081
        L_0x005f:
            int r3 = r2.getModifiers()     // Catch:{ all -> 0x0088 }
            boolean r3 = java.lang.reflect.Modifier.isStatic(r3)     // Catch:{ all -> 0x0088 }
            if (r3 != 0) goto L_0x0081
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.ahk> r3 = com.google.ads.interactivemedia.v3.internal.ahk.class
            boolean r3 = r2.isAnnotationPresent(r3)     // Catch:{ all -> 0x0088 }
            if (r3 != 0) goto L_0x0081
            java.lang.Object r2 = r2.get(r5)     // Catch:{ IllegalAccessException -> 0x0079 }
            r7.b(r2)     // Catch:{ IllegalAccessException -> 0x0079 }
            goto L_0x0081
        L_0x0079:
            java.lang.InternalError r6 = new java.lang.InternalError     // Catch:{ all -> 0x0088 }
            java.lang.String r7 = "Unexpected IllegalAccessException"
            r6.<init>(r7)     // Catch:{ all -> 0x0088 }
            throw r6     // Catch:{ all -> 0x0088 }
        L_0x0081:
            int r1 = r1 + 1
            goto L_0x0039
        L_0x0084:
            a(r5)
            return
        L_0x0088:
            r6 = move-exception
            a(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ahj.a(java.lang.Object, java.lang.Class, com.google.ads.interactivemedia.v3.internal.ahj, boolean, java.lang.String[]):void");
    }

    private static <T> int a(int i, int i2, T t, boolean z, Class<? super T> cls, String... strArr) {
        qi.a(t != null, "The object to build a hash code for must not be null", new Object[0]);
        ahj ahj = new ahj(17, 37);
        Class cls2 = t.getClass();
        a(t, cls2, ahj, false, strArr);
        while (cls2.getSuperclass() != null && cls2 != null) {
            cls2 = cls2.getSuperclass();
            a(t, cls2, ahj, false, strArr);
        }
        return ahj.c;
    }

    public static int a(Object obj, String... strArr) {
        return a(17, 37, obj, false, null, strArr);
    }

    private static void a(Object obj) {
        Set a2 = a();
        if (a2 != null) {
            a2.remove(new ahl(obj));
            if (a2.isEmpty()) {
                a.remove();
            }
        }
    }

    public ahj() {
        this.c = 0;
        this.b = 37;
        this.c = 17;
    }

    private ahj(int i, int i2) {
        this.c = 0;
        boolean z = true;
        qi.a(i % 2 != 0, "HashCodeBuilder requires an odd initial value", new Object[0]);
        if (i2 % 2 == 0) {
            z = false;
        }
        qi.a(z, "HashCodeBuilder requires an odd multiplier", new Object[0]);
        this.b = i2;
        this.c = i;
    }

    private final ahj a(long j) {
        this.c = (this.c * this.b) + ((int) (j ^ (j >> 32)));
        return this;
    }

    private final ahj b(Object obj) {
        if (obj == null) {
            this.c *= this.b;
        } else if (obj.getClass().isArray()) {
            int i = 0;
            if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                if (jArr == null) {
                    this.c *= this.b;
                } else {
                    int length = jArr.length;
                    while (i < length) {
                        a(jArr[i]);
                        i++;
                    }
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                if (iArr == null) {
                    this.c *= this.b;
                } else {
                    int length2 = iArr.length;
                    while (i < length2) {
                        this.c = (this.c * this.b) + iArr[i];
                        i++;
                    }
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                if (sArr == null) {
                    this.c *= this.b;
                } else {
                    int length3 = sArr.length;
                    while (i < length3) {
                        this.c = (this.c * this.b) + sArr[i];
                        i++;
                    }
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                if (cArr == null) {
                    this.c *= this.b;
                } else {
                    int length4 = cArr.length;
                    while (i < length4) {
                        this.c = (this.c * this.b) + cArr[i];
                        i++;
                    }
                }
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr == null) {
                    this.c *= this.b;
                } else {
                    int length5 = bArr.length;
                    while (i < length5) {
                        this.c = (this.c * this.b) + bArr[i];
                        i++;
                    }
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                if (dArr == null) {
                    this.c *= this.b;
                } else {
                    int length6 = dArr.length;
                    while (i < length6) {
                        a(Double.doubleToLongBits(dArr[i]));
                        i++;
                    }
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                if (fArr == null) {
                    this.c *= this.b;
                } else {
                    int length7 = fArr.length;
                    while (i < length7) {
                        this.c = (this.c * this.b) + Float.floatToIntBits(fArr[i]);
                        i++;
                    }
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                if (zArr == null) {
                    this.c *= this.b;
                } else {
                    int length8 = zArr.length;
                    while (i < length8) {
                        this.c = (this.c * this.b) + (zArr[i] ^ true ? 1 : 0);
                        i++;
                    }
                }
            } else {
                Object[] objArr = (Object[]) obj;
                if (objArr == null) {
                    this.c *= this.b;
                } else {
                    int length9 = objArr.length;
                    while (i < length9) {
                        b(objArr[i]);
                        i++;
                    }
                }
            }
        } else {
            this.c = (this.c * this.b) + obj.hashCode();
        }
        return this;
    }

    public final int hashCode() {
        return this.c;
    }
}
