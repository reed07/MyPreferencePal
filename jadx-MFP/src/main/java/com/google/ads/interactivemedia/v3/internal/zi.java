package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class zi implements xl {
    private final xu a;

    public zi(xu xuVar) {
        this.a = xuVar;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        xm xmVar = (xm) abt.a().getAnnotation(xm.class);
        if (xmVar == null) {
            return null;
        }
        return a(this.a, woVar, abt, xmVar);
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [com.google.ads.interactivemedia.v3.internal.xj<?>, com.google.ads.interactivemedia.v3.internal.xj] */
    /* JADX WARNING: type inference failed for: r8v13, types: [com.google.ads.interactivemedia.v3.internal.xj] */
    /* JADX WARNING: type inference failed for: r8v14, types: [com.google.ads.interactivemedia.v3.internal.xj] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.ads.interactivemedia.v3.internal.xj<?> a(com.google.ads.interactivemedia.v3.internal.xu r8, com.google.ads.interactivemedia.v3.internal.wo r9, com.google.ads.interactivemedia.v3.internal.abt<?> r10, com.google.ads.interactivemedia.v3.internal.xm r11) {
        /*
            java.lang.Class r0 = r11.a()
            com.google.ads.interactivemedia.v3.internal.abt r0 = com.google.ads.interactivemedia.v3.internal.abt.a(r0)
            com.google.ads.interactivemedia.v3.internal.ys r8 = r8.a(r0)
            java.lang.Object r8 = r8.a()
            boolean r0 = r8 instanceof com.google.ads.interactivemedia.v3.internal.xj
            if (r0 == 0) goto L_0x0017
            com.google.ads.interactivemedia.v3.internal.xj r8 = (com.google.ads.interactivemedia.v3.internal.xj) r8
            goto L_0x0072
        L_0x0017:
            boolean r0 = r8 instanceof com.google.ads.interactivemedia.v3.internal.xl
            if (r0 == 0) goto L_0x0022
            com.google.ads.interactivemedia.v3.internal.xl r8 = (com.google.ads.interactivemedia.v3.internal.xl) r8
            com.google.ads.interactivemedia.v3.internal.xj r8 = r8.a(r9, r10)
            goto L_0x0072
        L_0x0022:
            boolean r0 = r8 instanceof com.google.ads.interactivemedia.v3.internal.xg
            if (r0 != 0) goto L_0x0058
            boolean r1 = r8 instanceof com.google.ads.interactivemedia.v3.internal.wy
            if (r1 == 0) goto L_0x002b
            goto L_0x0058
        L_0x002b:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "Invalid attempt to bind an instance of "
            r11.<init>(r0)
            java.lang.Class r8 = r8.getClass()
            java.lang.String r8 = r8.getName()
            r11.append(r8)
            java.lang.String r8 = " as a @JsonAdapter for "
            r11.append(r8)
            java.lang.String r8 = r10.toString()
            r11.append(r8)
            java.lang.String r8 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            r9.<init>(r8)
            throw r9
        L_0x0058:
            r1 = 0
            if (r0 == 0) goto L_0x0060
            r0 = r8
            com.google.ads.interactivemedia.v3.internal.xg r0 = (com.google.ads.interactivemedia.v3.internal.xg) r0
            r3 = r0
            goto L_0x0061
        L_0x0060:
            r3 = r1
        L_0x0061:
            boolean r0 = r8 instanceof com.google.ads.interactivemedia.v3.internal.wy
            if (r0 == 0) goto L_0x0068
            r1 = r8
            com.google.ads.interactivemedia.v3.internal.wy r1 = (com.google.ads.interactivemedia.v3.internal.wy) r1
        L_0x0068:
            r4 = r1
            com.google.ads.interactivemedia.v3.internal.zy r8 = new com.google.ads.interactivemedia.v3.internal.zy
            r7 = 0
            r2 = r8
            r5 = r9
            r6 = r10
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x0072:
            if (r8 == 0) goto L_0x007e
            boolean r9 = r11.b()
            if (r9 == 0) goto L_0x007e
            com.google.ads.interactivemedia.v3.internal.xj r8 = r8.nullSafe()
        L_0x007e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zi.a(com.google.ads.interactivemedia.v3.internal.xu, com.google.ads.interactivemedia.v3.internal.wo, com.google.ads.interactivemedia.v3.internal.abt, com.google.ads.interactivemedia.v3.internal.xm):com.google.ads.interactivemedia.v3.internal.xj");
    }
}
