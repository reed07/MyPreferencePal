package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzbgk implements OnAttachStateChangeListener {
    private final /* synthetic */ zzawr zzeyn;
    private final /* synthetic */ zzbgh zzeyp;

    zzbgk(zzbgh zzbgh, zzawr zzawr) {
        this.zzeyp = zzbgh;
        this.zzeyn = zzawr;
    }

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (wrap: com.google.android.gms.internal.ads.zzbgh
              0x0000: IGET  (r0v0 com.google.android.gms.internal.ads.zzbgh) = (r3v0 'this' com.google.android.gms.internal.ads.zzbgk A[THIS]) com.google.android.gms.internal.ads.zzbgk.zzeyp com.google.android.gms.internal.ads.zzbgh), (r4v0 'view' android.view.View), (wrap: com.google.android.gms.internal.ads.zzawr
              0x0002: IGET  (r1v0 com.google.android.gms.internal.ads.zzawr) = (r3v0 'this' com.google.android.gms.internal.ads.zzbgk A[THIS]) com.google.android.gms.internal.ads.zzbgk.zzeyn com.google.android.gms.internal.ads.zzawr), (10 int) com.google.android.gms.internal.ads.zzbgh.zza(com.google.android.gms.internal.ads.zzbgh, android.view.View, com.google.android.gms.internal.ads.zzawr, int):void type: STATIC in method: com.google.android.gms.internal.ads.zzbgk.onViewAttachedToWindow(android.view.View):void, dex: classes2.dex
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
            Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: length -1 is negative
            	at java.base/java.util.ArrayList.shiftTailOverGap(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:86)
            	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:79)
            	at jadx.core.dex.nodes.InsnNode.attachArg(InsnNode.java:87)
            	at jadx.core.dex.nodes.InsnNode.addArg(InsnNode.java:73)
            	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:335)
            	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            */
        /*
            this = this;
            com.google.android.gms.internal.ads.zzbgh r0 = r3.zzeyp
            com.google.android.gms.internal.ads.zzawr r1 = r3.zzeyn
            r2 = 10
            r0.zza(r4, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgk.onViewAttachedToWindow(android.view.View):void");
    }
}
