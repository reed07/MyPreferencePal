package com.google.ads.interactivemedia.v3.internal;

import java.math.BigInteger;

/* compiled from: IMASDK */
public final class xe extends wz {
    private static final Class<?>[] a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public xe(Boolean bool) {
        a((Object) bool);
    }

    public xe(Number number) {
        a((Object) number);
    }

    public xe(String str) {
        a((Object) str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r0 != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof java.lang.Character
            if (r0 == 0) goto L_0x0011
            java.lang.Character r8 = (java.lang.Character) r8
            char r8 = r8.charValue()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r7.b = r8
            return
        L_0x0011:
            boolean r0 = r8 instanceof java.lang.Number
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0037
            boolean r0 = r8 instanceof java.lang.String
            if (r0 == 0) goto L_0x001d
            r0 = 1
            goto L_0x0035
        L_0x001d:
            java.lang.Class r0 = r8.getClass()
            java.lang.Class<?>[] r3 = a
            int r4 = r3.length
            r5 = 0
        L_0x0025:
            if (r5 >= r4) goto L_0x0034
            r6 = r3[r5]
            boolean r6 = r6.isAssignableFrom(r0)
            if (r6 == 0) goto L_0x0031
            r0 = 1
            goto L_0x0035
        L_0x0031:
            int r5 = r5 + 1
            goto L_0x0025
        L_0x0034:
            r0 = 0
        L_0x0035:
            if (r0 == 0) goto L_0x0038
        L_0x0037:
            r1 = 1
        L_0x0038:
            com.google.ads.interactivemedia.v3.internal.tt.a(r1)
            r7.b = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.xe.a(java.lang.Object):void");
    }

    public final boolean h() {
        return this.b instanceof Boolean;
    }

    public final boolean f() {
        Object obj = this.b;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.parseBoolean(b());
    }

    public final boolean i() {
        return this.b instanceof Number;
    }

    public final Number a() {
        Object obj = this.b;
        return obj instanceof String ? new yn((String) obj) : (Number) obj;
    }

    public final boolean j() {
        return this.b instanceof String;
    }

    public final String b() {
        Object obj = this.b;
        if (obj instanceof Number) {
            return a().toString();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        return (String) obj;
    }

    public final double c() {
        return this.b instanceof Number ? a().doubleValue() : Double.parseDouble(b());
    }

    public final long d() {
        return this.b instanceof Number ? a().longValue() : Long.parseLong(b());
    }

    public final int e() {
        return this.b instanceof Number ? a().intValue() : Integer.parseInt(b());
    }

    public final int hashCode() {
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            long longValue = a().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        }
        Object obj = this.b;
        if (!(obj instanceof Number)) {
            return obj.hashCode();
        }
        long doubleToLongBits = Double.doubleToLongBits(a().doubleValue());
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        xe xeVar = (xe) obj;
        if (this.b == null) {
            return xeVar.b == null;
        }
        if (a(this) && a(xeVar)) {
            return a().longValue() == xeVar.a().longValue();
        }
        if (!(this.b instanceof Number) || !(xeVar.b instanceof Number)) {
            return this.b.equals(xeVar.b);
        }
        double doubleValue = a().doubleValue();
        double doubleValue2 = xeVar.a().doubleValue();
        return doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2));
    }

    private static boolean a(xe xeVar) {
        Object obj = xeVar.b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
