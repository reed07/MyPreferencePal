package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: IMASDK */
public final class wo {
    private static final abt<?> a = abt.a(Object.class);
    private final ThreadLocal<Map<abt<?>, wu<?>>> b;
    private final Map<abt<?>, xj<?>> c;
    private final xu d;
    private final zi e;
    private final List<xl> f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;

    public wo() {
        this(yj.a, wg.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, xi.DEFAULT, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    wo(yj yjVar, wn wnVar, Map<Type, ww<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, xi xiVar, String str, int i2, int i3, List<xl> list, List<xl> list2, List<xl> list3) {
        xj xjVar;
        xj xjVar2;
        xj xjVar3;
        yj yjVar2 = yjVar;
        this.b = new ThreadLocal<>();
        this.c = new ConcurrentHashMap();
        Map<Type, ww<?>> map2 = map;
        this.d = new xu(map);
        this.g = z;
        this.h = z3;
        this.i = z4;
        this.j = z5;
        this.k = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(aac.D);
        arrayList.add(zp.a);
        arrayList.add(yjVar);
        arrayList.addAll(list3);
        arrayList.add(aac.r);
        arrayList.add(aac.g);
        arrayList.add(aac.d);
        arrayList.add(aac.e);
        arrayList.add(aac.f);
        if (xiVar == xi.DEFAULT) {
            xjVar = aac.k;
        } else {
            xjVar = new wr();
        }
        arrayList.add(aac.a(Long.TYPE, Long.class, xjVar));
        Class cls = Double.TYPE;
        Class<Double> cls2 = Double.class;
        if (z7) {
            xjVar2 = aac.m;
        } else {
            xjVar2 = new wp(this);
        }
        arrayList.add(aac.a(cls, cls2, xjVar2));
        Class cls3 = Float.TYPE;
        Class<Float> cls4 = Float.class;
        if (z7) {
            xjVar3 = aac.l;
        } else {
            xjVar3 = new wq(this);
        }
        arrayList.add(aac.a(cls3, cls4, xjVar3));
        arrayList.add(aac.n);
        arrayList.add(aac.h);
        arrayList.add(aac.i);
        arrayList.add(aac.a(AtomicLong.class, new ws(xjVar).nullSafe()));
        arrayList.add(aac.a(AtomicLongArray.class, new wt(xjVar).nullSafe()));
        arrayList.add(aac.j);
        arrayList.add(aac.o);
        arrayList.add(aac.s);
        arrayList.add(aac.t);
        arrayList.add(aac.a(BigDecimal.class, aac.p));
        arrayList.add(aac.a(BigInteger.class, aac.q));
        arrayList.add(aac.u);
        arrayList.add(aac.v);
        arrayList.add(aac.x);
        arrayList.add(aac.y);
        arrayList.add(aac.B);
        arrayList.add(aac.w);
        arrayList.add(aac.b);
        arrayList.add(zg.a);
        arrayList.add(aac.A);
        arrayList.add(zw.a);
        arrayList.add(zu.a);
        arrayList.add(aac.z);
        arrayList.add(zc.a);
        arrayList.add(aac.a);
        arrayList.add(new ze(this.d));
        boolean z8 = z2;
        arrayList.add(new zn(this.d, z2));
        this.e = new zi(this.d);
        arrayList.add(this.e);
        arrayList.add(aac.E);
        wn wnVar2 = wnVar;
        arrayList.add(new zr(this.d, wnVar, yjVar, this.e));
        this.f = Collections.unmodifiableList(arrayList);
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(d2);
            sb.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.ads.interactivemedia.v3.internal.abt<T>, code=com.google.ads.interactivemedia.v3.internal.abt, for r6v0, types: [com.google.ads.interactivemedia.v3.internal.abt, com.google.ads.interactivemedia.v3.internal.abt<T>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> com.google.ads.interactivemedia.v3.internal.xj<T> a(com.google.ads.interactivemedia.v3.internal.abt r6) {
        /*
            r5 = this;
            java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.xj<?>> r0 = r5.c
            if (r6 != 0) goto L_0x0007
            com.google.ads.interactivemedia.v3.internal.abt<?> r1 = a
            goto L_0x0008
        L_0x0007:
            r1 = r6
        L_0x0008:
            java.lang.Object r0 = r0.get(r1)
            com.google.ads.interactivemedia.v3.internal.xj r0 = (com.google.ads.interactivemedia.v3.internal.xj) r0
            if (r0 == 0) goto L_0x0011
            return r0
        L_0x0011:
            java.lang.ThreadLocal<java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.wu<?>>> r0 = r5.b
            java.lang.Object r0 = r0.get()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            if (r0 != 0) goto L_0x0027
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.ThreadLocal<java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.wu<?>>> r1 = r5.b
            r1.set(r0)
            r1 = 1
        L_0x0027:
            java.lang.Object r2 = r0.get(r6)
            com.google.ads.interactivemedia.v3.internal.wu r2 = (com.google.ads.interactivemedia.v3.internal.wu) r2
            if (r2 == 0) goto L_0x0030
            return r2
        L_0x0030:
            com.google.ads.interactivemedia.v3.internal.wu r2 = new com.google.ads.interactivemedia.v3.internal.wu     // Catch:{ all -> 0x0077 }
            r2.<init>()     // Catch:{ all -> 0x0077 }
            r0.put(r6, r2)     // Catch:{ all -> 0x0077 }
            java.util.List<com.google.ads.interactivemedia.v3.internal.xl> r3 = r5.f     // Catch:{ all -> 0x0077 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0077 }
        L_0x003e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0077 }
            if (r4 == 0) goto L_0x0063
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0077 }
            com.google.ads.interactivemedia.v3.internal.xl r4 = (com.google.ads.interactivemedia.v3.internal.xl) r4     // Catch:{ all -> 0x0077 }
            com.google.ads.interactivemedia.v3.internal.xj r4 = r4.a(r5, r6)     // Catch:{ all -> 0x0077 }
            if (r4 == 0) goto L_0x003e
            r2.a(r4)     // Catch:{ all -> 0x0077 }
            java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.xj<?>> r2 = r5.c     // Catch:{ all -> 0x0077 }
            r2.put(r6, r4)     // Catch:{ all -> 0x0077 }
            r0.remove(r6)
            if (r1 == 0) goto L_0x0062
            java.lang.ThreadLocal<java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.wu<?>>> r6 = r5.b
            r6.remove()
        L_0x0062:
            return r4
        L_0x0063:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = "GSON (2.8.5) cannot handle "
            r3.<init>(r4)     // Catch:{ all -> 0x0077 }
            r3.append(r6)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0077 }
            r2.<init>(r3)     // Catch:{ all -> 0x0077 }
            throw r2     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r2 = move-exception
            r0.remove(r6)
            if (r1 == 0) goto L_0x0082
            java.lang.ThreadLocal<java.util.Map<com.google.ads.interactivemedia.v3.internal.abt<?>, com.google.ads.interactivemedia.v3.internal.wu<?>>> r6 = r5.b
            r6.remove()
        L_0x0082:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.wo.a(com.google.ads.interactivemedia.v3.internal.abt):com.google.ads.interactivemedia.v3.internal.xj");
    }

    public final <T> xj<T> a(xl xlVar, abt<T> abt) {
        if (!this.f.contains(xlVar)) {
            xlVar = this.e;
        }
        boolean z = false;
        for (xl xlVar2 : this.f) {
            if (z) {
                xj<T> a2 = xlVar2.a(this, abt);
                if (a2 != null) {
                    return a2;
                }
            } else if (xlVar2 == xlVar) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder("GSON cannot serialize ");
        sb.append(abt);
        throw new IllegalArgumentException(sb.toString());
    }

    public final <T> xj<T> a(Class<T> cls) {
        return a(abt.a(cls));
    }

    public final String a(Object obj) {
        if (obj == null) {
            xb xbVar = xb.a;
            StringWriter stringWriter = new StringWriter();
            a((wz) xbVar, (Appendable) stringWriter);
            return stringWriter.toString();
        }
        Class cls = obj.getClass();
        StringWriter stringWriter2 = new StringWriter();
        a(obj, cls, stringWriter2);
        return stringWriter2.toString();
    }

    private final void a(Object obj, Type type, Appendable appendable) throws xa {
        abx a2;
        boolean g2;
        boolean h2;
        boolean i2;
        try {
            a2 = a(yu.a(appendable));
            xj a3 = a(abt.a(type));
            g2 = a2.g();
            a2.b(true);
            h2 = a2.h();
            a2.c(this.i);
            i2 = a2.i();
            a2.d(this.g);
            a3.write(a2, obj);
            a2.b(g2);
            a2.c(h2);
            a2.d(i2);
        } catch (IOException e2) {
            throw new xa((Throwable) e2);
        } catch (AssertionError e3) {
            StringBuilder sb = new StringBuilder("AssertionError (GSON 2.8.5): ");
            sb.append(e3.getMessage());
            throw new AssertionError(sb.toString(), e3);
        } catch (IOException e4) {
            throw new xa((Throwable) e4);
        } catch (Throwable th) {
            a2.b(g2);
            a2.c(h2);
            a2.d(i2);
            throw th;
        }
    }

    private final void a(wz wzVar, Appendable appendable) throws xa {
        abx a2;
        boolean g2;
        boolean h2;
        boolean i2;
        try {
            a2 = a(yu.a(appendable));
            g2 = a2.g();
            a2.b(true);
            h2 = a2.h();
            a2.c(this.i);
            i2 = a2.i();
            a2.d(this.g);
            yu.a(wzVar, a2);
            a2.b(g2);
            a2.c(h2);
            a2.d(i2);
        } catch (IOException e2) {
            throw new xa((Throwable) e2);
        } catch (AssertionError e3) {
            StringBuilder sb = new StringBuilder("AssertionError (GSON 2.8.5): ");
            sb.append(e3.getMessage());
            throw new AssertionError(sb.toString(), e3);
        } catch (IOException e4) {
            throw new xa((Throwable) e4);
        } catch (Throwable th) {
            a2.b(g2);
            a2.c(h2);
            a2.d(i2);
            throw th;
        }
    }

    private final abx a(Writer writer) throws IOException {
        if (this.h) {
            writer.write(")]}'\n");
        }
        abx abx = new abx(writer);
        if (this.j) {
            abx.c("  ");
        }
        abx.d(this.g);
        return abx;
    }

    public final <T> T a(String str, Class<T> cls) throws xh {
        Object obj;
        if (str == null) {
            obj = null;
        } else {
            abu abu = new abu(new StringReader(str));
            abu.a(this.k);
            Object a2 = a(abu, (Type) cls);
            a(a2, abu);
            obj = a2;
        }
        return yt.a(cls).cast(obj);
    }

    private static void a(Object obj, abu abu) {
        if (obj != null) {
            try {
                if (abu.f() != abw.END_DOCUMENT) {
                    throw new xa("JSON document was not fully consumed.");
                }
            } catch (aby e2) {
                throw new xh((Throwable) e2);
            } catch (IOException e3) {
                throw new xa((Throwable) e3);
            }
        }
    }

    private final <T> T a(abu abu, Type type) throws xa, xh {
        boolean q = abu.q();
        abu.a(true);
        try {
            abu.f();
            T read = a(abt.a(type)).read(abu);
            abu.a(q);
            return read;
        } catch (EOFException e2) {
            if (1 != 0) {
                abu.a(q);
                return null;
            }
            throw new xh((Throwable) e2);
        } catch (IllegalStateException e3) {
            throw new xh((Throwable) e3);
        } catch (IOException e4) {
            throw new xh((Throwable) e4);
        } catch (AssertionError e5) {
            StringBuilder sb = new StringBuilder("AssertionError (GSON 2.8.5): ");
            sb.append(e5.getMessage());
            throw new AssertionError(sb.toString(), e5);
        } catch (Throwable th) {
            abu.a(q);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{serializeNulls:");
        sb.append(this.g);
        sb.append(",factories:");
        sb.append(this.f);
        sb.append(",instanceCreators:");
        sb.append(this.d);
        sb.append("}");
        return sb.toString();
    }
}
