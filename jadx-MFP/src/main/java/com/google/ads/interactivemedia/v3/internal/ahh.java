package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: IMASDK */
public final class ahh {
    private static final ThreadLocal<Set<ahn<ahl, ahl>>> a = new ThreadLocal<>();
    private boolean b = true;
    private boolean c = false;
    private boolean d = false;
    private List<Class<?>> e = new ArrayList();
    private Class<?> f = null;
    private String[] g = null;

    private static Set<ahn<ahl, ahl>> a() {
        return (Set) a.get();
    }

    private static ahn<ahl, ahl> a(Object obj, Object obj2) {
        return ahn.a(new ahl(obj), new ahl(obj2));
    }

    private static void b(Object obj, Object obj2) {
        Set a2 = a();
        if (a2 != null) {
            a2.remove(a(obj, obj2));
            if (a2.isEmpty()) {
                a.remove();
            }
        }
    }

    public ahh() {
        this.e.add(String.class);
    }

    public static boolean a(Object obj, Object obj2, String... strArr) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        ahh ahh = new ahh();
        ahh.g = strArr;
        ahh.f = null;
        ahh.c = false;
        ahh.d = false;
        return ahh.c(obj, obj2).b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r2.isInstance(r6) == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r1.isInstance(r7) == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r3 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.ads.interactivemedia.v3.internal.ahh c(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean r0 = r5.b
            if (r0 != 0) goto L_0x0005
            return r5
        L_0x0005:
            if (r6 != r7) goto L_0x0008
            return r5
        L_0x0008:
            r0 = 0
            if (r6 == 0) goto L_0x0070
            if (r7 != 0) goto L_0x000e
            goto L_0x0070
        L_0x000e:
            java.lang.Class r1 = r6.getClass()
            java.lang.Class r2 = r7.getClass()
            boolean r3 = r1.isInstance(r7)
            if (r3 == 0) goto L_0x0023
            boolean r3 = r2.isInstance(r6)
            if (r3 != 0) goto L_0x002f
            goto L_0x0031
        L_0x0023:
            boolean r3 = r2.isInstance(r6)
            if (r3 == 0) goto L_0x006d
            boolean r3 = r1.isInstance(r7)
            if (r3 != 0) goto L_0x0031
        L_0x002f:
            r3 = r1
            goto L_0x0032
        L_0x0031:
            r3 = r2
        L_0x0032:
            boolean r4 = r3.isArray()     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r4 == 0) goto L_0x003c
            r5.d(r6, r7)     // Catch:{ IllegalArgumentException -> 0x006a }
            goto L_0x0069
        L_0x003c:
            java.util.List<java.lang.Class<?>> r4 = r5.e     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r4 == 0) goto L_0x0057
            java.util.List<java.lang.Class<?>> r4 = r5.e     // Catch:{ IllegalArgumentException -> 0x006a }
            boolean r1 = r4.contains(r1)     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r1 != 0) goto L_0x0050
            java.util.List<java.lang.Class<?>> r1 = r5.e     // Catch:{ IllegalArgumentException -> 0x006a }
            boolean r1 = r1.contains(r2)     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r1 == 0) goto L_0x0057
        L_0x0050:
            boolean r6 = r6.equals(r7)     // Catch:{ IllegalArgumentException -> 0x006a }
            r5.b = r6     // Catch:{ IllegalArgumentException -> 0x006a }
            goto L_0x0069
        L_0x0057:
            r5.a(r6, r7, r3)     // Catch:{ IllegalArgumentException -> 0x006a }
            java.lang.Class r1 = r3.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r1 == 0) goto L_0x0069
            java.lang.Class<?> r1 = r5.f     // Catch:{ IllegalArgumentException -> 0x006a }
            if (r3 == r1) goto L_0x0069
            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x006a }
            goto L_0x0057
        L_0x0069:
            return r5
        L_0x006a:
            r5.b = r0
            return r5
        L_0x006d:
            r5.b = r0
            return r5
        L_0x0070:
            r5.b = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ahh.c(java.lang.Object, java.lang.Object):com.google.ads.interactivemedia.v3.internal.ahh");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:31|32|33|34|35) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0095 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(java.lang.Object r6, java.lang.Object r7, java.lang.Class<?> r8) {
        /*
            r5 = this;
            java.util.Set r0 = a()
            com.google.ads.interactivemedia.v3.internal.ahn r1 = a(r6, r7)
            java.lang.Object r2 = r1.b()
            java.lang.Object r3 = r1.a()
            com.google.ads.interactivemedia.v3.internal.ahn r2 = com.google.ads.interactivemedia.v3.internal.ahn.a(r2, r3)
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.contains(r1)
            if (r1 != 0) goto L_0x0024
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x0026
        L_0x0024:
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r0 == 0) goto L_0x002a
            return
        L_0x002a:
            java.util.Set r0 = a()     // Catch:{ all -> 0x00a4 }
            if (r0 != 0) goto L_0x003a
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x00a4 }
            r0.<init>()     // Catch:{ all -> 0x00a4 }
            java.lang.ThreadLocal<java.util.Set<com.google.ads.interactivemedia.v3.internal.ahn<com.google.ads.interactivemedia.v3.internal.ahl, com.google.ads.interactivemedia.v3.internal.ahl>>> r1 = a     // Catch:{ all -> 0x00a4 }
            r1.set(r0)     // Catch:{ all -> 0x00a4 }
        L_0x003a:
            com.google.ads.interactivemedia.v3.internal.ahn r1 = a(r6, r7)     // Catch:{ all -> 0x00a4 }
            r0.add(r1)     // Catch:{ all -> 0x00a4 }
            java.lang.reflect.Field[] r8 = r8.getDeclaredFields()     // Catch:{ all -> 0x00a4 }
            java.lang.reflect.AccessibleObject.setAccessible(r8, r4)     // Catch:{ all -> 0x00a4 }
        L_0x0048:
            int r0 = r8.length     // Catch:{ all -> 0x00a4 }
            if (r3 >= r0) goto L_0x00a0
            boolean r0 = r5.b     // Catch:{ all -> 0x00a4 }
            if (r0 == 0) goto L_0x00a0
            r0 = r8[r3]     // Catch:{ all -> 0x00a4 }
            java.lang.String[] r1 = r5.g     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = r0.getName()     // Catch:{ all -> 0x00a4 }
            boolean r1 = com.google.ads.interactivemedia.v3.internal.ahe.a(r1, r2)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x009d
            java.lang.String r1 = r0.getName()     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = "$"
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x009d
            boolean r1 = r5.c     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0077
            int r1 = r0.getModifiers()     // Catch:{ all -> 0x00a4 }
            boolean r1 = java.lang.reflect.Modifier.isTransient(r1)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x009d
        L_0x0077:
            int r1 = r0.getModifiers()     // Catch:{ all -> 0x00a4 }
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x009d
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.ahi> r1 = com.google.ads.interactivemedia.v3.internal.ahi.class
            boolean r1 = r0.isAnnotationPresent(r1)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x009d
            java.lang.Object r1 = r0.get(r6)     // Catch:{ IllegalAccessException -> 0x0095 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ IllegalAccessException -> 0x0095 }
            r5.d(r1, r0)     // Catch:{ IllegalAccessException -> 0x0095 }
            goto L_0x009d
        L_0x0095:
            java.lang.InternalError r8 = new java.lang.InternalError     // Catch:{ all -> 0x00a4 }
            java.lang.String r0 = "Unexpected IllegalAccessException"
            r8.<init>(r0)     // Catch:{ all -> 0x00a4 }
            throw r8     // Catch:{ all -> 0x00a4 }
        L_0x009d:
            int r3 = r3 + 1
            goto L_0x0048
        L_0x00a0:
            b(r6, r7)
            return
        L_0x00a4:
            r8 = move-exception
            b(r6, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ahh.a(java.lang.Object, java.lang.Object, java.lang.Class):void");
    }

    private final ahh d(Object obj, Object obj2) {
        if (!this.b || obj == obj2) {
            return this;
        }
        int i = 0;
        if (obj == null || obj2 == null) {
            this.b = false;
            return this;
        }
        Class cls = obj.getClass();
        if (cls.isArray()) {
            if (obj.getClass() != obj2.getClass()) {
                this.b = false;
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                long[] jArr2 = (long[]) obj2;
                if (this.b && jArr != jArr2) {
                    if (jArr == null || jArr2 == null) {
                        this.b = false;
                    } else if (jArr.length != jArr2.length) {
                        this.b = false;
                    } else {
                        while (i < jArr.length && this.b) {
                            a(jArr[i], jArr2[i]);
                            i++;
                        }
                    }
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                int[] iArr2 = (int[]) obj2;
                if (this.b && iArr != iArr2) {
                    if (iArr == null || iArr2 == null) {
                        this.b = false;
                    } else if (iArr.length != iArr2.length) {
                        this.b = false;
                    } else {
                        while (i < iArr.length && this.b) {
                            a(iArr[i], iArr2[i]);
                            i++;
                        }
                    }
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                short[] sArr2 = (short[]) obj2;
                if (this.b && sArr != sArr2) {
                    if (sArr != null && sArr2 != null) {
                        if (sArr.length == sArr2.length) {
                            for (int i2 = 0; i2 < sArr.length; i2++) {
                                boolean z = this.b;
                                if (!z) {
                                    break;
                                }
                                short s = sArr[i2];
                                short s2 = sArr2[i2];
                                if (z) {
                                    this.b = s == s2;
                                }
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                char[] cArr2 = (char[]) obj2;
                if (this.b && cArr != cArr2) {
                    if (cArr != null && cArr2 != null) {
                        if (cArr.length == cArr2.length) {
                            for (int i3 = 0; i3 < cArr.length; i3++) {
                                boolean z2 = this.b;
                                if (!z2) {
                                    break;
                                }
                                char c2 = cArr[i3];
                                char c3 = cArr2[i3];
                                if (z2) {
                                    this.b = c2 == c3;
                                }
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (this.b && bArr != bArr2) {
                    if (bArr != null && bArr2 != null) {
                        if (bArr.length == bArr2.length) {
                            for (int i4 = 0; i4 < bArr.length; i4++) {
                                boolean z3 = this.b;
                                if (!z3) {
                                    break;
                                }
                                byte b2 = bArr[i4];
                                byte b3 = bArr2[i4];
                                if (z3) {
                                    this.b = b2 == b3;
                                }
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                if (this.b && dArr != dArr2) {
                    if (dArr != null && dArr2 != null) {
                        if (dArr.length == dArr2.length) {
                            while (i < dArr.length) {
                                boolean z4 = this.b;
                                if (!z4) {
                                    break;
                                }
                                double d2 = dArr[i];
                                double d3 = dArr2[i];
                                if (z4) {
                                    a(Double.doubleToLongBits(d2), Double.doubleToLongBits(d3));
                                }
                                i++;
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                float[] fArr2 = (float[]) obj2;
                if (this.b && fArr != fArr2) {
                    if (fArr != null && fArr2 != null) {
                        if (fArr.length == fArr2.length) {
                            while (i < fArr.length) {
                                boolean z5 = this.b;
                                if (!z5) {
                                    break;
                                }
                                float f2 = fArr[i];
                                float f3 = fArr2[i];
                                if (z5) {
                                    a(Float.floatToIntBits(f2), Float.floatToIntBits(f3));
                                }
                                i++;
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                boolean[] zArr2 = (boolean[]) obj2;
                if (this.b && zArr != zArr2) {
                    if (zArr != null && zArr2 != null) {
                        if (zArr.length == zArr2.length) {
                            for (int i5 = 0; i5 < zArr.length; i5++) {
                                boolean z6 = this.b;
                                if (!z6) {
                                    break;
                                }
                                boolean z7 = zArr[i5];
                                boolean z8 = zArr2[i5];
                                if (z6) {
                                    this.b = z7 == z8;
                                }
                            }
                        } else {
                            this.b = false;
                        }
                    } else {
                        this.b = false;
                    }
                }
            } else {
                Object[] objArr = (Object[]) obj;
                Object[] objArr2 = (Object[]) obj2;
                if (this.b && objArr != objArr2) {
                    if (objArr == null || objArr2 == null) {
                        this.b = false;
                    } else if (objArr.length != objArr2.length) {
                        this.b = false;
                    } else {
                        while (i < objArr.length && this.b) {
                            d(objArr[i], objArr2[i]);
                            i++;
                        }
                    }
                }
            }
        } else if (!this.d || ahf.a(cls)) {
            this.b = obj.equals(obj2);
        } else {
            c(obj, obj2);
        }
        return this;
    }

    private final ahh a(long j, long j2) {
        if (!this.b) {
            return this;
        }
        this.b = j == j2;
        return this;
    }

    private final ahh a(int i, int i2) {
        if (!this.b) {
            return this;
        }
        this.b = i == i2;
        return this;
    }
}
