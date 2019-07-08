package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.facebook.ads.internal.f.b;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.w.e.g;

public abstract class c extends FrameLayout {
    boolean a;
    protected final e b;
    private final com.facebook.ads.internal.s.c c;
    /* access modifiers changed from: private */
    public final String d;
    /* access modifiers changed from: private */
    @Nullable
    public final a e;
    /* access modifiers changed from: private */
    @Nullable
    public final C0012a f;
    /* access modifiers changed from: private */
    @Nullable
    public b g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public b i;
    /* access modifiers changed from: private */
    public b.a j;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.f.c k;

    public c(Context context, com.facebook.ads.internal.s.c cVar, String str) {
        this(context, cVar, str, null, null);
    }

    public c(Context context, com.facebook.ads.internal.s.c cVar, String str, @Nullable a aVar, @Nullable C0012a aVar2) {
        super(context);
        this.h = 0;
        this.j = b.a.NONE;
        this.k = null;
        this.b = new e() {
            public void a() {
                if (c.this.k == null) {
                    a(false);
                    return;
                }
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE  (wrap: com.facebook.ads.internal.view.a.c
                      0x000d: IGET  (r0v2 com.facebook.ads.internal.view.a.c) = (r2v0 'this' com.facebook.ads.internal.view.a.c$1 A[THIS]) com.facebook.ads.internal.view.a.c.1.a com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.b(com.facebook.ads.internal.view.a.c):int type: STATIC in method: com.facebook.ads.internal.view.a.c.1.a():void, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
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
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 40 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 58 more
                    */
                /*
                    this = this;
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.c r0 = r0.k
                    if (r0 != 0) goto L_0x000d
                    r0 = 0
                    r2.a(r0)
                    return
                L_0x000d:
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    
                    // error: 0x000f: INVOKE  (r0 I:com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.b(com.facebook.ads.internal.view.a.c):int type: STATIC
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.c r0 = r0.k
                    com.facebook.ads.internal.f.c r0 = r0.e()
                    if (r0 != 0) goto L_0x0024
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    r0.g()
                    goto L_0x0031
                L_0x0024:
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.c r1 = r0.k
                    com.facebook.ads.internal.f.c r1 = r1.e()
                    com.facebook.ads.internal.view.a.c.a(r0, r1)
                L_0x0031:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.a.c.AnonymousClass1.a():void");
            }

            public void a(b.a aVar) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.facebook.ads.internal.view.a.c
                      0x0000: IGET  (r0v0 com.facebook.ads.internal.view.a.c) = (r1v0 'this' com.facebook.ads.internal.view.a.c$1 A[THIS]) com.facebook.ads.internal.view.a.c.1.a com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.d(com.facebook.ads.internal.view.a.c):int type: STATIC in method: com.facebook.ads.internal.view.a.c.1.a(com.facebook.ads.internal.f.b$a):void, dex: classes.dex
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
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
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
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.d(com.facebook.ads.internal.view.a.c):int type: STATIC
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    r0.j = r2
                    com.facebook.ads.internal.view.a.c r2 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.b$a r2 = r2.j
                    com.facebook.ads.internal.f.b$a r0 = com.facebook.ads.internal.f.b.a.HIDE
                    if (r2 != r0) goto L_0x001f
                    com.facebook.ads.internal.view.a.c r2 = com.facebook.ads.internal.view.a.c.this
                    android.content.Context r2 = r2.getContext()
                    com.facebook.ads.internal.f.c r2 = com.facebook.ads.internal.f.a.d(r2)
                    goto L_0x0029
                L_0x001f:
                    com.facebook.ads.internal.view.a.c r2 = com.facebook.ads.internal.view.a.c.this
                    android.content.Context r2 = r2.getContext()
                    com.facebook.ads.internal.f.c r2 = com.facebook.ads.internal.f.a.g(r2)
                L_0x0029:
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.view.a.c.a(r0, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.a.c.AnonymousClass1.a(com.facebook.ads.internal.f.b$a):void");
            }

            public void a(com.facebook.ads.internal.f.c cVar) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.facebook.ads.internal.view.a.c
                      0x0000: IGET  (r0v0 com.facebook.ads.internal.view.a.c) = (r2v0 'this' com.facebook.ads.internal.view.a.c$1 A[THIS]) com.facebook.ads.internal.view.a.c.1.a com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.d(com.facebook.ads.internal.view.a.c):int type: STATIC in method: com.facebook.ads.internal.view.a.c.1.a(com.facebook.ads.internal.f.c):void, dex: classes.dex
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
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
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
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.facebook.ads.internal.view.a.c) com.facebook.ads.internal.view.a.c.d(com.facebook.ads.internal.view.a.c):int type: STATIC
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.b r0 = r0.i
                    int r1 = r3.a()
                    r0.a(r1)
                    java.util.List r0 = r3.d()
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L_0x0039
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.view.a.c.b(r0, r3)
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.view.a.b r0 = r0.g
                    if (r0 == 0) goto L_0x003e
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.view.a.b r0 = r0.g
                    com.facebook.ads.internal.view.a.c r1 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.f.b$a r1 = r1.j
                    r0.a(r3, r1)
                    goto L_0x003e
                L_0x0039:
                    com.facebook.ads.internal.view.a.c r0 = com.facebook.ads.internal.view.a.c.this
                    com.facebook.ads.internal.view.a.c.a(r0, r3)
                L_0x003e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.a.c.AnonymousClass1.a(com.facebook.ads.internal.f.c):void");
            }

            public void a(boolean z) {
                c.this.c();
                if (c.this.e != null) {
                    c.this.e.b(true);
                }
                if (c.this.g != null) {
                    c.this.g.a(z);
                }
                if (!z) {
                    c.this.f();
                }
            }

            public void b() {
                if (c.this.f != null) {
                    c.this.f.a("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW");
                }
            }

            public void c() {
                if (!TextUtils.isEmpty(com.facebook.ads.internal.f.a.n(c.this.getContext()))) {
                    g.a(new g(), c.this.getContext(), Uri.parse(com.facebook.ads.internal.f.a.n(c.this.getContext())), c.this.d);
                }
                c.this.i.c();
            }

            public void d() {
                c.this.c();
                if (c.this.e != null) {
                    c.this.e.b(true);
                }
                if (!TextUtils.isEmpty(com.facebook.ads.internal.f.a.m(c.this.getContext()))) {
                    g.a(new g(), c.this.getContext(), Uri.parse(com.facebook.ads.internal.f.a.m(c.this.getContext())), c.this.d);
                }
                c.this.i.b();
                c.this.f();
            }
        };
        this.c = cVar;
        this.e = aVar;
        this.f = aVar2;
        this.d = str;
    }

    static /* synthetic */ void a(c cVar, com.facebook.ads.internal.f.c cVar2) {
        cVar.k = cVar2;
        cVar.i.a(cVar.j, cVar.h);
        cVar.a(cVar2, cVar.j);
    }

    static /* synthetic */ void b(c cVar, com.facebook.ads.internal.f.c cVar2) {
        cVar.i.a(cVar.j);
        cVar.b(cVar2, cVar.j);
        if (cVar.e()) {
            cVar.f();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.i.e()) {
            this.c.n(this.d, this.i.d());
            this.i.f();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        this.k = null;
        this.i.a();
        d();
    }

    public void a() {
        this.i = new b();
        a aVar = this.e;
        if (aVar != null) {
            aVar.a_(true);
        }
        g();
        b bVar = this.g;
        if (bVar != null) {
            bVar.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(com.facebook.ads.internal.f.c cVar, b.a aVar);

    public void a(boolean z) {
        this.a = z;
    }

    public void b() {
        f();
    }

    /* access modifiers changed from: 0000 */
    public abstract void b(com.facebook.ads.internal.f.c cVar, b.a aVar);

    /* access modifiers changed from: 0000 */
    public abstract void c();

    /* access modifiers changed from: 0000 */
    public abstract void d();

    /* access modifiers changed from: 0000 */
    public abstract boolean e();

    public void setAdReportingFlowListener(@Nullable b bVar) {
        this.g = bVar;
    }
}
