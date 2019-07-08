package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class zy<T> extends xj<T> {
    private final xg<T> a;
    private final wy<T> b;
    private final wo c;
    private final abt<T> d;
    private final xl e;
    private final zz f = new zz(this, 0);
    private xj<T> g;

    public zy(xg<T> xgVar, wy<T> wyVar, wo woVar, abt<T> abt, xl xlVar) {
        this.a = xgVar;
        this.b = wyVar;
        this.c = woVar;
        this.d = abt;
        this.e = xlVar;
    }

    public final T read(abu abu) throws IOException {
        if (this.b == null) {
            return a().read(abu);
        }
        if (yu.a(abu) instanceof xb) {
            return null;
        }
        try {
            return this.b.a();
        } catch (xd e2) {
            throw e2;
        } catch (Exception e3) {
            throw new xd((Throwable) e3);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.ads.interactivemedia.v3.internal.xf, com.google.ads.interactivemedia.v3.internal.zz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.ads.interactivemedia.v3.internal.xf, com.google.ads.interactivemedia.v3.internal.zz]
  assigns: [com.google.ads.interactivemedia.v3.internal.zz]
  uses: [com.google.ads.interactivemedia.v3.internal.xf]
  mth insns count: 14
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void write(com.google.ads.interactivemedia.v3.internal.abx r4, T r5) throws java.io.IOException {
        /*
            r3 = this;
            com.google.ads.interactivemedia.v3.internal.xg<T> r0 = r3.a
            if (r0 != 0) goto L_0x000c
            com.google.ads.interactivemedia.v3.internal.xj r0 = r3.a()
            r0.write(r4, r5)
            return
        L_0x000c:
            if (r5 != 0) goto L_0x0012
            r4.f()
            return
        L_0x0012:
            com.google.ads.interactivemedia.v3.internal.abt<T> r1 = r3.d
            java.lang.reflect.Type r1 = r1.b()
            com.google.ads.interactivemedia.v3.internal.zz r2 = r3.f
            com.google.ads.interactivemedia.v3.internal.wz r5 = r0.a(r5, r1, r2)
            com.google.ads.interactivemedia.v3.internal.yu.a(r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zy.write(com.google.ads.interactivemedia.v3.internal.abx, java.lang.Object):void");
    }

    private final xj<T> a() {
        xj<T> xjVar = this.g;
        if (xjVar != null) {
            return xjVar;
        }
        xj<T> a2 = this.c.a(this.e, this.d);
        this.g = a2;
        return a2;
    }

    public static xl a(abt<?> abt, Object obj) {
        return new aaa(obj, abt, abt.b() == abt.a(), null);
    }
}
