package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;

/* renamed from: com.myfitnesspal.feature.registration.service.-$$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuC-HM reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM implements MfpV2ApiErrorCallback {
    public static final /* synthetic */ $$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM INSTANCE = new $$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM();

    private /* synthetic */ $$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM() {
    }

    public final void execute(Object obj) {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.shared.api.ApiResponseBase
              0x0000: CHECK_CAST  (r1v1 com.myfitnesspal.shared.api.ApiResponseBase) = (com.myfitnesspal.shared.api.ApiResponseBase) (r1v0 'obj' java.lang.Object)) com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.lambda$loadNewsFeed$1(com.myfitnesspal.shared.api.ApiResponseBase):void type: STATIC in method: com.myfitnesspal.feature.registration.service.-$$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuC-HM.execute(java.lang.Object):void, dex: classes3.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
            	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
            	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
            	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
            	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
            	at java.base/java.lang.Class.forName0(Native Method)
            	at java.base/java.lang.Class.forName(Unknown Source)
            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
            	... 36 more
            */
        /*
            this = this;
            com.myfitnesspal.shared.api.ApiResponseBase r1 = (com.myfitnesspal.shared.api.ApiResponseBase) r1
            
            // error: 0x0002: INVOKE  (r1 I:com.myfitnesspal.shared.api.ApiResponseBase) com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.lambda$loadNewsFeed$1(com.myfitnesspal.shared.api.ApiResponseBase):void type: STATIC
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.service.$$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM.execute(java.lang.Object):void");
    }
}
