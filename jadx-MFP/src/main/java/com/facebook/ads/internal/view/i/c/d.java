package com.facebook.ads.internal.view.i.c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.u;
import com.facebook.ads.internal.view.i.b.v;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@TargetApi(12)
public class d implements b {
    private final j a;
    private final l b;
    private final com.facebook.ads.internal.view.i.b.d c;
    private final v d;
    /* access modifiers changed from: private */
    public final Handler e;
    /* access modifiers changed from: private */
    public final boolean f;
    /* access modifiers changed from: private */
    public final boolean g;
    /* access modifiers changed from: private */
    public View h;
    /* access modifiers changed from: private */
    @Nullable
    public a i;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.i.a j;
    /* access modifiers changed from: private */
    public boolean k;

    public enum a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public d(View view, a aVar) {
        this(view, aVar, false);
    }

    public d(View view, a aVar, boolean z) {
        this(view, aVar, z, false);
    }

    public d(View view, @Nullable a aVar, boolean z, boolean z2) {
        this.a = new j() {
            public void a(i iVar) {
                d.this.a(1, 0);
            }
        };
        this.b = new l() {
            public void a(k kVar) {
                if (d.this.k) {
                    if (d.this.i == a.FADE_OUT_ON_PLAY || d.this.f) {
                        d.this.i = null;
                        
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002d: INVOKE  (wrap: com.facebook.ads.internal.view.i.c.d
                              0x002b: IGET  (r3v6 com.facebook.ads.internal.view.i.c.d) = (r2v0 'this' com.facebook.ads.internal.view.i.c.d$2 A[THIS]) com.facebook.ads.internal.view.i.c.d.2.a com.facebook.ads.internal.view.i.c.d) com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d):void type: STATIC in method: com.facebook.ads.internal.view.i.c.d.2.a(com.facebook.ads.internal.view.i.b.k):void, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                            	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                            	... 50 more
                            Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                            	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                            	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.Class.forName0(Native Method)
                            	at java.base/java.lang.Class.forName(Unknown Source)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                            	... 67 more
                            */
                        /*
                            this = this;
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            boolean r3 = r3.k
                            if (r3 != 0) goto L_0x0009
                            return
                        L_0x0009:
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            com.facebook.ads.internal.view.i.c.d$a r3 = r3.i
                            com.facebook.ads.internal.view.i.c.d$a r0 = com.facebook.ads.internal.view.i.c.d.a.FADE_OUT_ON_PLAY
                            if (r3 == r0) goto L_0x0025
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            boolean r3 = r3.f
                            if (r3 == 0) goto L_0x001c
                            goto L_0x0025
                        L_0x001c:
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            r0 = 0
                            r1 = 8
                            r3.a(r0, r1)
                            return
                        L_0x0025:
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            r0 = 0
                            r3.i = r0
                            com.facebook.ads.internal.view.i.c.d r3 = com.facebook.ads.internal.view.i.c.d.this
                            
                            // error: 0x002d: INVOKE  (r3 I:com.facebook.ads.internal.view.i.c.d) com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d):void type: STATIC
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.i.c.d.AnonymousClass2.a(com.facebook.ads.internal.view.i.b.k):void");
                    }
                };
                this.c = new com.facebook.ads.internal.view.i.b.d() {
                    public void a(c cVar) {
                        if (d.this.i != a.INVSIBLE) {
                            d.this.h.setAlpha(1.0f);
                            d.this.h.setVisibility(0);
                        }
                    }
                };
                this.d = new v() {
                    public void a(u uVar) {
                        if (d.this.j != null && uVar.a().getAction() == 0) {
                            d.this.e.removeCallbacksAndMessages(null);
                            d.this.a((AnimatorListenerAdapter) new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animator) {
                                    d.this.e.postDelayed(new Runnable() {
                                        public void run() {
                                            if (!d.this.g && d.this.k) {
                                                
                                                /*  JADX ERROR: Method code generation error
                                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001e: INVOKE  (wrap: com.facebook.ads.internal.view.i.c.d
                                                      0x001c: IGET  (r0v10 com.facebook.ads.internal.view.i.c.d) = (wrap: com.facebook.ads.internal.view.i.c.d$4
                                                      0x001a: IGET  (r0v9 com.facebook.ads.internal.view.i.c.d$4) = (wrap: com.facebook.ads.internal.view.i.c.d$4$1
                                                      0x0018: IGET  (r0v8 com.facebook.ads.internal.view.i.c.d$4$1) = (r1v0 'this' com.facebook.ads.internal.view.i.c.d$4$1$1 A[THIS]) com.facebook.ads.internal.view.i.c.d.4.1.1.a com.facebook.ads.internal.view.i.c.d$4$1) com.facebook.ads.internal.view.i.c.d.4.1.a com.facebook.ads.internal.view.i.c.d$4) com.facebook.ads.internal.view.i.c.d.4.a com.facebook.ads.internal.view.i.c.d) com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d):void type: STATIC in method: com.facebook.ads.internal.view.i.c.d.4.1.1.run():void, dex: classes.dex
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
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
                                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:896)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
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
                                                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                                    	... 90 more
                                                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.Class.forName0(Native Method)
                                                    	at java.base/java.lang.Class.forName(Unknown Source)
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                                    	... 107 more
                                                    */
                                                /*
                                                    this = this;
                                                    com.facebook.ads.internal.view.i.c.d$4$1 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.AnonymousClass1.this
                                                    com.facebook.ads.internal.view.i.c.d$4 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.this
                                                    com.facebook.ads.internal.view.i.c.d r0 = com.facebook.ads.internal.view.i.c.d.this
                                                    boolean r0 = r0.g
                                                    if (r0 != 0) goto L_0x0021
                                                    com.facebook.ads.internal.view.i.c.d$4$1 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.AnonymousClass1.this
                                                    com.facebook.ads.internal.view.i.c.d$4 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.this
                                                    com.facebook.ads.internal.view.i.c.d r0 = com.facebook.ads.internal.view.i.c.d.this
                                                    boolean r0 = r0.k
                                                    if (r0 == 0) goto L_0x0021
                                                    com.facebook.ads.internal.view.i.c.d$4$1 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.AnonymousClass1.this
                                                    com.facebook.ads.internal.view.i.c.d$4 r0 = com.facebook.ads.internal.view.i.c.d.AnonymousClass4.this
                                                    com.facebook.ads.internal.view.i.c.d r0 = com.facebook.ads.internal.view.i.c.d.this
                                                    
                                                    // error: 0x001e: INVOKE  (r0 I:com.facebook.ads.internal.view.i.c.d) com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d):void type: STATIC
                                                L_0x0021:
                                                    return
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.i.c.d.AnonymousClass4.AnonymousClass1.AnonymousClass1.run():void");
                                            }
                                        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                                    }
                                });
                            }
                        }
                    };
                    this.k = true;
                    this.e = new Handler();
                    this.f = z;
                    this.g = z2;
                    a(view, aVar);
                }

                /* access modifiers changed from: private */
                public void a(int i2, int i3) {
                    this.e.removeCallbacksAndMessages(null);
                    this.h.clearAnimation();
                    this.h.setAlpha((float) i2);
                    this.h.setVisibility(i3);
                }

                /* access modifiers changed from: private */
                public void a(AnimatorListenerAdapter animatorListenerAdapter) {
                    this.h.setVisibility(0);
                    this.h.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
                }

                public void a(View view, a aVar) {
                    int i2;
                    View view2;
                    this.i = aVar;
                    this.h = view;
                    this.h.clearAnimation();
                    if (aVar == a.INVSIBLE) {
                        this.h.setAlpha(BitmapDescriptorFactory.HUE_RED);
                        view2 = this.h;
                        i2 = 8;
                    } else {
                        this.h.setAlpha(1.0f);
                        view2 = this.h;
                        i2 = 0;
                    }
                    view2.setVisibility(i2);
                }

                public void a(com.facebook.ads.internal.view.i.a aVar) {
                    this.j = aVar;
                    aVar.getEventBus().a((T[]) new f[]{this.a, this.b, this.d, this.c});
                }

                public boolean a() {
                    return this.k;
                }

                public void b() {
                    this.k = false;
                    a((AnimatorListenerAdapter) null);
                }

                public void b(com.facebook.ads.internal.view.i.a aVar) {
                    a(1, 0);
                    aVar.getEventBus().b((T[]) new f[]{this.c, this.d, this.b, this.a});
                    this.j = null;
                }
            }
