package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ix implements ip {
    private final us a = new us(new byte[4]);
    private final /* synthetic */ iw b;

    public ix(iw iwVar) {
        this.b = iwVar;
    }

    public final void a(ve veVar, fs fsVar, jd jdVar) {
    }

    public final void a(ut utVar) {
        if (utVar.e() == 0) {
            utVar.d(7);
            int b2 = utVar.b() / 4;
            for (int i = 0; i < b2; i++) {
                utVar.a(this.a, 4);
                int c = this.a.c(16);
                this.a.b(3);
                if (c == 0) {
                    this.a.b(13);
                } else {
                    int c2 = this.a.c(13);
                    this.b.i.put(c2, new iq(new iy(this.b, c2)));
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE  (wrap: com.google.ads.interactivemedia.v3.internal.iw
                          0x004d: IGET  (r4v5 com.google.ads.interactivemedia.v3.internal.iw) = (r9v0 'this' com.google.ads.interactivemedia.v3.internal.ix A[THIS]) com.google.ads.interactivemedia.v3.internal.ix.b com.google.ads.interactivemedia.v3.internal.iw) com.google.ads.interactivemedia.v3.internal.iw.b(com.google.ads.interactivemedia.v3.internal.iw):int type: STATIC in method: com.google.ads.interactivemedia.v3.internal.ix.a(com.google.ads.interactivemedia.v3.internal.ut):void, dex: classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:148)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:209)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                        	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                        Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                        	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                        	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                        	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                        	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                        	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                        	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                        	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                        	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                        	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                        	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 36 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 54 more
                        */
                    /*
                        this = this;
                        int r0 = r10.e()
                        if (r0 == 0) goto L_0x0007
                        return
                    L_0x0007:
                        r0 = 7
                        r10.d(r0)
                        int r0 = r10.b()
                        r1 = 4
                        int r0 = r0 / r1
                        r2 = 0
                        r3 = 0
                    L_0x0013:
                        if (r3 >= r0) goto L_0x0055
                        com.google.ads.interactivemedia.v3.internal.us r4 = r9.a
                        r10.a(r4, r1)
                        com.google.ads.interactivemedia.v3.internal.us r4 = r9.a
                        r5 = 16
                        int r4 = r4.c(r5)
                        com.google.ads.interactivemedia.v3.internal.us r5 = r9.a
                        r6 = 3
                        r5.b(r6)
                        r5 = 13
                        if (r4 != 0) goto L_0x0032
                        com.google.ads.interactivemedia.v3.internal.us r4 = r9.a
                        r4.b(r5)
                        goto L_0x0052
                    L_0x0032:
                        com.google.ads.interactivemedia.v3.internal.us r4 = r9.a
                        int r4 = r4.c(r5)
                        com.google.ads.interactivemedia.v3.internal.iw r5 = r9.b
                        android.util.SparseArray r5 = r5.i
                        com.google.ads.interactivemedia.v3.internal.iq r6 = new com.google.ads.interactivemedia.v3.internal.iq
                        com.google.ads.interactivemedia.v3.internal.iy r7 = new com.google.ads.interactivemedia.v3.internal.iy
                        com.google.ads.interactivemedia.v3.internal.iw r8 = r9.b
                        r7.<init>(r8, r4)
                        r6.<init>(r7)
                        r5.put(r4, r6)
                        com.google.ads.interactivemedia.v3.internal.iw r4 = r9.b
                        
                        // error: 0x004f: INVOKE  (r4 I:com.google.ads.interactivemedia.v3.internal.iw) com.google.ads.interactivemedia.v3.internal.iw.b(com.google.ads.interactivemedia.v3.internal.iw):int type: STATIC
                    L_0x0052:
                        int r3 = r3 + 1
                        goto L_0x0013
                    L_0x0055:
                        com.google.ads.interactivemedia.v3.internal.iw r10 = r9.b
                        int r10 = r10.d
                        r0 = 2
                        if (r10 == r0) goto L_0x0067
                        com.google.ads.interactivemedia.v3.internal.iw r10 = r9.b
                        android.util.SparseArray r10 = r10.i
                        r10.remove(r2)
                    L_0x0067:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ix.a(com.google.ads.interactivemedia.v3.internal.ut):void");
                }
            }
