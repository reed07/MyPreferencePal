package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class io implements iz {
    private final ic a;
    private final us b = new us(new byte[10]);
    private int c = 0;
    private int d;
    private ve e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private long l;

    public io(ic icVar) {
        this.a = icVar;
    }

    public final void a(ve veVar, fs fsVar, jd jdVar) {
        this.e = veVar;
        this.a.a(fsVar, jdVar);
    }

    public final void a() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.a.a();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Regions count limit reached
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01dc A[SYNTHETIC] */
    public final void a(com.google.ads.interactivemedia.v3.internal.ut r17, int r18) throws com.google.ads.interactivemedia.v3.internal.ca {
        /*
            r16 = this;
            r0 = r16
            r1 = r18 & 1
            r2 = -1
            r3 = 1
            if (r1 == 0) goto L_0x0047
            int r1 = r0.c
            switch(r1) {
                case 0: goto L_0x0041;
                case 1: goto L_0x0041;
                case 2: goto L_0x003a;
                case 3: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0013:
            int r1 = r0.j
            if (r1 == r2) goto L_0x0034
            java.lang.String r4 = "PesReader"
            r5 = 59
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Unexpected start indicator: expected "
            r6.append(r5)
            r6.append(r1)
            java.lang.String r1 = " more bytes"
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            android.util.Log.w(r4, r1)
        L_0x0034:
            com.google.ads.interactivemedia.v3.internal.ic r1 = r0.a
            r1.b()
            goto L_0x0041
        L_0x003a:
            java.lang.String r1 = "PesReader"
            java.lang.String r4 = "Unexpected start indicator reading extended header"
            android.util.Log.w(r1, r4)
        L_0x0041:
            r1 = r17
            r5 = r18
            r4 = r0
            goto L_0x0089
        L_0x0047:
            r1 = r17
            r5 = r18
            r4 = r0
        L_0x004c:
            int r6 = r1.b()
            if (r6 <= 0) goto L_0x01dc
            int r6 = r4.c
            r7 = 0
            switch(r6) {
                case 0: goto L_0x01d3;
                case 1: goto L_0x0144;
                case 2: goto L_0x008d;
                case 3: goto L_0x005e;
                default: goto L_0x0058;
            }
        L_0x0058:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x005e:
            int r6 = r1.b()
            int r8 = r4.j
            if (r8 != r2) goto L_0x0067
            goto L_0x0069
        L_0x0067:
            int r7 = r6 - r8
        L_0x0069:
            if (r7 <= 0) goto L_0x0074
            int r6 = r6 - r7
            int r7 = r1.d()
            int r7 = r7 + r6
            r1.b(r7)
        L_0x0074:
            com.google.ads.interactivemedia.v3.internal.ic r7 = r4.a
            r7.a(r1)
            int r7 = r4.j
            if (r7 == r2) goto L_0x004c
            int r7 = r7 - r6
            r4.j = r7
            int r6 = r4.j
            if (r6 != 0) goto L_0x004c
            com.google.ads.interactivemedia.v3.internal.ic r6 = r4.a
            r6.b()
        L_0x0089:
            r4.a(r3)
            goto L_0x004c
        L_0x008d:
            r6 = 10
            int r8 = r4.i
            int r6 = java.lang.Math.min(r6, r8)
            com.google.ads.interactivemedia.v3.internal.us r8 = r4.b
            byte[] r8 = r8.a
            boolean r6 = r4.a(r1, r8, r6)
            if (r6 == 0) goto L_0x004c
            r6 = 0
            int r8 = r4.i
            boolean r6 = r4.a(r1, r6, r8)
            if (r6 == 0) goto L_0x004c
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r6.a(r7)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r4.l = r8
            boolean r6 = r4.f
            r8 = 3
            r9 = 4
            if (r6 == 0) goto L_0x0130
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r6.b(r9)
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            int r6 = r6.c(r8)
            long r10 = (long) r6
            r6 = 30
            long r10 = r10 << r6
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            r12.b(r3)
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            r13 = 15
            int r12 = r12.c(r13)
            int r12 = r12 << r13
            long r14 = (long) r12
            long r10 = r10 | r14
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            r12.b(r3)
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            int r12 = r12.c(r13)
            long r14 = (long) r12
            long r10 = r10 | r14
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            r12.b(r3)
            boolean r12 = r4.h
            if (r12 != 0) goto L_0x0128
            boolean r12 = r4.g
            if (r12 == 0) goto L_0x0128
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            r12.b(r9)
            com.google.ads.interactivemedia.v3.internal.us r12 = r4.b
            int r12 = r12.c(r8)
            long r14 = (long) r12
            long r14 = r14 << r6
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r6.b(r3)
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            int r6 = r6.c(r13)
            int r6 = r6 << r13
            long r7 = (long) r6
            long r6 = r14 | r7
            com.google.ads.interactivemedia.v3.internal.us r8 = r4.b
            r8.b(r3)
            com.google.ads.interactivemedia.v3.internal.us r8 = r4.b
            int r8 = r8.c(r13)
            long r12 = (long) r8
            long r6 = r6 | r12
            com.google.ads.interactivemedia.v3.internal.us r8 = r4.b
            r8.b(r3)
            com.google.ads.interactivemedia.v3.internal.ve r8 = r4.e
            r8.b(r6)
            r4.h = r3
        L_0x0128:
            com.google.ads.interactivemedia.v3.internal.ve r6 = r4.e
            long r6 = r6.b(r10)
            r4.l = r6
        L_0x0130:
            boolean r6 = r4.k
            if (r6 == 0) goto L_0x0135
            goto L_0x0136
        L_0x0135:
            r9 = 0
        L_0x0136:
            r5 = r5 | r9
            com.google.ads.interactivemedia.v3.internal.ic r6 = r4.a
            long r7 = r4.l
            r6.a(r7, r5)
            r6 = 3
            r4.a(r6)
            goto L_0x004c
        L_0x0144:
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            byte[] r6 = r6.a
            r7 = 9
            boolean r6 = r4.a(r1, r6, r7)
            if (r6 == 0) goto L_0x004c
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r8 = 0
            r6.a(r8)
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r9 = 24
            int r6 = r6.c(r9)
            r9 = 2
            if (r6 == r3) goto L_0x017d
            java.lang.String r7 = "PesReader"
            r10 = 41
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r10)
            java.lang.String r10 = "Unexpected start code prefix: "
            r11.append(r10)
            r11.append(r6)
            java.lang.String r6 = r11.toString()
            android.util.Log.w(r7, r6)
            r4.j = r2
            r6 = 0
            goto L_0x01cb
        L_0x017d:
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r10 = 8
            r6.b(r10)
            com.google.ads.interactivemedia.v3.internal.us r6 = r4.b
            r11 = 16
            int r6 = r6.c(r11)
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            r12 = 5
            r11.b(r12)
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            boolean r11 = r11.d()
            r4.k = r11
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            r11.b(r9)
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            boolean r11 = r11.d()
            r4.f = r11
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            boolean r11 = r11.d()
            r4.g = r11
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            r12 = 6
            r11.b(r12)
            com.google.ads.interactivemedia.v3.internal.us r11 = r4.b
            int r10 = r11.c(r10)
            r4.i = r10
            if (r6 != 0) goto L_0x01c2
            r4.j = r2
            goto L_0x01ca
        L_0x01c2:
            int r6 = r6 + 6
            int r6 = r6 - r7
            int r7 = r4.i
            int r6 = r6 - r7
            r4.j = r6
        L_0x01ca:
            r6 = 1
        L_0x01cb:
            if (r6 == 0) goto L_0x01ce
            r8 = 2
        L_0x01ce:
            r4.a(r8)
            goto L_0x004c
        L_0x01d3:
            int r6 = r1.b()
            r1.d(r6)
            goto L_0x004c
        L_0x01dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.io.a(com.google.ads.interactivemedia.v3.internal.ut, int):void");
    }

    private final void a(int i2) {
        this.c = i2;
        this.d = 0;
    }

    private final boolean a(ut utVar, byte[] bArr, int i2) {
        int min = Math.min(utVar.b(), i2 - this.d);
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            utVar.d(min);
        } else {
            utVar.a(bArr, this.d, min);
        }
        this.d += min;
        if (this.d == i2) {
            return true;
        }
        return false;
    }
}
